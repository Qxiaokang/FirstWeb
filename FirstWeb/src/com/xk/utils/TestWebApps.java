package com.xk.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestWebApps extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.service(req, resp);
		doFilter(req, resp, null);
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;

        String uri = httpRequest.getRequestURI();       ///wopihost/wopi/files/excel.xlsx
        System.out.println("uri:"+uri);
        //解决中文乱码问题
        String fileUri = URLDecoder.decode(uri.substring(uri.indexOf("/FirstWeb/") + 1, uri.length()),"UTF-8");     //  /wopi/files/test.docx
        String filePath = request.getServletContext().getRealPath("/")+fileUri;
        System.out.println("fileUri:"+fileUri+"\n"+"filePath:"+filePath); 
        if(fileUri.endsWith("/contentsss")) {     // GetFile ：返回文件流
                filePath = filePath.substring(0, filePath.indexOf("/contents"));
                filePath="D:/testshare/db.xls";
                getFile(filePath, httpResponse);

        } else {        // CheckFileInfo ：返回json
        	    filePath="D:/testshare/db.xls";
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter out = null;
                try {
                        out = response.getWriter();
                        out.write(new FileUtils().checkFileInfo(filePath));
                } catch (IOException e) {
                        e.printStackTrace();
                } finally {
                        if (out != null) {
                                out.close();
                        }
                }
        }
        return;
}

//其中getFile(filePath, httpResponse);是返回文件下载流，FileUtils.checkFileInfo(filePath)是返回文件的JSON信息。


private HttpServletResponse getFile(String path, HttpServletResponse response) {
try {
    // path是指欲下载的文件的路径。
    File file = new File(path);
    // 取得文件名。
    String filename = file.getName();
    String contentType = "application/octet-stream";
    // 以流的形式下载文件。
    InputStream fis = new BufferedInputStream(
    		new FileInputStream(path));
    byte[] buffer = new byte[fis.available()];
    fis.read(buffer);
    fis.close();
    // 清空response
    response.reset();
    // 设置response的Header

    response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("utf-8"),"ISO-8859-1"));
    response.addHeader("Content-Length", "" + file.length());
    OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
    response.setContentType(contentType);
    toClient.write(buffer);
    toClient.flush();
    toClient.close();
} catch (IOException ex) {
    ex.printStackTrace();
}
return response;
}

//FileUtils.checkFileInfo(filePath)实现：
/**
 * 获取文件基本信息
 * @param filePath      文件路径
 * @return
 */
public class FileUtils{
public  String checkFileInfo(String filePath) {
        File file = new File(filePath);
        String baseFileName = null;     //文件名
        String ownerId = null;  //文件所有者的唯一编号
        long size = 0;  //文件大小，以bytes为单位
        //String sha256 = null; //文件的256位bit的SHA-2编码散列内容
        long version = 0;       //文件版本号，文件如果被编辑，版本号也要跟着改变
        if(file.exists()) {
                // 取得文件名。
                baseFileName = file.getName();
                size = file.length();
                // 取得文件的后缀名。
                //String ext = baseFileName.substring(baseFileName.lastIndexOf(".") + 1);
                ownerId = "admin";
                //sha256 = new SHAUtils().SHA(FileUtils.readByByte(file), "SHA-256");
                version = file.lastModified();
        }

        return "{\"BaseFileName\":\"" + baseFileName + "\",\"OwnerId\":\"" + ownerId + "\",\"Size\":\"" + size
                        + "\",\"AllowExternalMarketplace\":\"" + true + "\",\"Version\":\"" + version + "\"}";
}}
}
