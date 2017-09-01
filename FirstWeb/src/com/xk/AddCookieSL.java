package com.xk;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xk.in.IUser;
import com.xk.utils.FactoryDao;

public class AddCookieSL extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.service(req, resp);
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter printWriter=resp.getWriter();
		IUser iUser=(IUser) FactoryDao.getObject("User");
		String nameString=req.getParameter("name");
		String pwdString=req.getParameter("pwd");
		boolean b=iUser.checkNameAndPwd(nameString,
				pwdString);
		Cookie[] cookies = req.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(int i=0;i<cookies.length;i++){
				System.out.println("getcookies:"+"  name:"+cookies[i].getName()+"  value:"+cookies[i].getValue());
			}
		}
		if(!b){
			printWriter.write("<script type='text/javascript'>alert('用户名或密码错误，请重新输入！');window.history.back(-1);</script>");
		//window.location.href='index.html'
		}else {
			Cookie cookie1=new Cookie("name", nameString);
			Cookie cookie2=new Cookie("pwd", pwdString);
			cookie1.setMaxAge(20);
			cookie2.setMaxAge(20);
			resp.addCookie(cookie1);
			resp.addCookie(cookie2);
			resp.sendRedirect("index.html");
		}
		printWriter.close();
		}
}
