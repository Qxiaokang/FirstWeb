package com.xk;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xk.bean.User;
import com.xk.in.IUser;
import com.xk.utils.FactoryDao;

public class AddCookieSL extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.service(req, resp);
		try {
			//Thread.sleep(1000);
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter printWriter = resp.getWriter();
			IUser iUser = (IUser) FactoryDao.getObject("User");
			String nameString = req.getParameter("name");
			String pwdString = req.getParameter("pwd");
			System.err.println("sessionId:" + req.getSession().getId());
			User user = iUser.findUserByName(nameString);
			boolean b = iUser.checkNameAndPwd(nameString, pwdString);
			Cookie[] cookies = req.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					System.out.println("getcookies:" + "  name:"
							+ cookies[i].getName() + "  value:"
							+ cookies[i].getValue());
				}
			}
			if (user == null) {
				// printWriter.write("<script type='text/javascript'>alert('用户名或密码错误，请重新输入！');window.history.back(-1);</script>");
				// window.location.href='index.html'
				printWriter.print("用户名不存在！！！");
			} else if (!b) {
				printWriter.print("密码错误！！！");
			} else {
				Cookie cookie1 = new Cookie("name", nameString);
				Cookie cookie2 = new Cookie("pwd", pwdString);
				cookie1.setPath("/FirstWeb");
				cookie2.setPath("/FirstWeb");
				// cookie1.setMaxAge(60*60*24);
				// cookie2.setMaxAge(60*60*24);
				resp.addCookie(cookie1);
				resp.addCookie(cookie2);
				System.out.println("add cookie  successful");
				HttpSession session = req.getSession();
				session.setMaxInactiveInterval(60);// session失效60S
				session.setAttribute("user", iUser.findUserByName(nameString));
				// resp.sendRedirect("index.jsp");
				printWriter.print("登录成功！");
			}
			printWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
