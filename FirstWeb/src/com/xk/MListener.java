package com.xk;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 * 
 * @author Admin
 *	监听器
 */
public class MListener implements HttpSessionListener{
	private int count=0;
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		System.err.println("sessionListener---onCreate");
		count++;
		HttpSession session = se.getSession();
		ServletContext servletContext = session.getServletContext();
		System.err.println("count:"+count);
		servletContext.setAttribute("count", count);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		System.err.println("sessionListtener---onDestroy");
		count--;
		HttpSession session = se.getSession();
		ServletContext servletContext = session.getServletContext();
		servletContext.setAttribute("count", count);
	}

}
