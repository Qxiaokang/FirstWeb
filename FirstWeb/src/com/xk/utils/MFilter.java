package com.xk.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.spec.XPathType.Filter;
/**
 * 
 * @author Admin
 *	servlet 过滤器
 */
public class MFilter implements javax.servlet.Filter{
	private FilterConfig config;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.err.println("MFilter---destroy()");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.err.println("MFilter---doFilter");
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter writer = resp.getWriter(); 
		String  naString= req.getParameter("name");
		if(naString!=null&&naString.length()>=10){
			writer.write("<script>window.alert('the name is too long,please write again...');window.history.back(-1);</script>");
		}else {
			//如果没有了，则调用web组件
			chain.doFilter(request, response);
		}
		System.err.println("Mfilter---process");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.err.println("MFilter---init()");
	}

}
