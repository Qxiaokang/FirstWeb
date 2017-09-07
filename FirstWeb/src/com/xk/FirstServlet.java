package com.xk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JEditorPane;
import javax.xml.soap.Detail;

import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.xk.bean.Computer;
import com.xk.bean.ComputerDao;
import com.xk.bean.User;
import com.xk.in.IUser;
import com.xk.utils.FactoryDao;

public class FirstServlet extends HttpServlet {
	private Statement statement;
	private PrintWriter printWriter;
	private String sqlString = "";
	private ResultSet executeQuery;
	private boolean isnull = false;
	private Connection connection = null;

	public FirstServlet() {
		System.out.println("FirstServlet---构造");
	}

	/*
	 * @Override public void service(ServletRequest req, ServletResponse res)
	 * throws ServletException, IOException { // TODO Auto-generated method stub
	 * //super.service(req, res); System.out.println("service----start"); String
	 * nameString=req.getParameter("name"); String
	 * ssString="<span style='color:red;font-size:50px;'> "
	 * +nameString+"   登录成功！"+"</span>";
	 * res.setContentType("text/html; charset=utf-8"); PrintWriter
	 * printWriter=res.getWriter(); printWriter.write(ssString);
	 * printWriter.close();
	 * 
	 * }
	 */

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("http-service-start");
		String uriString = req.getRequestURI();
		String pathString = uriString.substring(uriString.lastIndexOf("/"),
				uriString.lastIndexOf("."));
		System.out.println("request---uriString:" + uriString);
		ServletConfig servletConfig = getServletConfig();
		String author = servletConfig.getInitParameter("author");
		System.out.println("author:" + author);
		if (pathString.equals("/first")) {
			loadFirst(req, resp);
		} else if (pathString.equals("/list")) {
			loadList(req, resp);
		} else if (pathString.equals("/all")) {
			allDao(req, resp);
		} else if (pathString.equals("/checkCode")) {
			getCheckCode(req, resp);
		} else if (pathString.equals("/complist")) {
			System.err.println("index---complist---servlet");
			listComp(req, resp);
		}
	}
	// computer list
	private void listComp(HttpServletRequest req, HttpServletResponse resp) {
		java.util.List<Computer> list = new ComputerDao().findAll();
		try {
			req.setAttribute("computers", list);
			req.getRequestDispatcher("complist.jsp").forward(req, resp);

		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// get checkCode
	private void getCheckCode(HttpServletRequest req, HttpServletResponse resp) {
		try {
			resp.setContentType("image/jpeg");
			BufferedImage image = new BufferedImage(60, 20,
					BufferedImage.TYPE_INT_RGB);
			Random random = new Random();
			Graphics graphics = image.getGraphics();
			graphics.setColor(new Color(random.nextInt(255), random
					.nextInt(255), random.nextInt(255)));
			graphics.fillRect(0, 0, 60, 20);
			graphics.setColor(new Color(0, 0, 0));
			// lines
			graphics.drawLine(random.nextInt(60), random.nextInt(20),
					random.nextInt(60), random.nextInt(20));
			graphics.drawLine(random.nextInt(60), random.nextInt(20),
					random.nextInt(60), random.nextInt(20));
			graphics.drawLine(random.nextInt(60), random.nextInt(20),
					random.nextInt(60), random.nextInt(20));
			graphics.drawLine(random.nextInt(60), random.nextInt(20),
					random.nextInt(60), random.nextInt(20));
			String numsString = String
					.valueOf((int) (random.nextFloat() * 1000000));
			// add session
			/*
			 * HttpSession session=req.getSession();
			 * session.setAttribute("sessionId", numsString);
			 */
			graphics.drawString(numsString, 5, 15);
			OutputStream outputStream = resp.getOutputStream();
			JPEGImageEncoder jpegImageEncoder = JPEGCodec
					.createJPEGEncoder(outputStream);
			jpegImageEncoder.encode(image);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// query all users jsp
	private void allDao(HttpServletRequest req, HttpServletResponse resp) {
		try {
			IUser iUser = (IUser) FactoryDao.getObject("User");
			java.util.List<User> users = iUser.findAllUsers();
			req.setAttribute("users", users);
			RequestDispatcher rDispatcher = req
					.getRequestDispatcher("find.jsp");
			rDispatcher.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// add user
	private void loadFirst(HttpServletRequest req, HttpServletResponse resp) {
		String nString = req.getParameter("name");
		String pString = req.getParameter("pwd");
		String sexString = req.getParameter("sex");
		System.out.println("n:" + nString + "  p:" + pString);
		try {
			resp.setContentType("text/html; charset=utf-8");
			printWriter = resp.getWriter();
			if (nString == null || pString == null || "".equals(nString)
					|| "".equals(pString)) {
				printWriter.print("<script type=" + "\"text/javascript\""
						+ " charset=" + "\"utf-8\"" + ">window.alert("
						+ "\"name or pwd is null\"" + ")</script>");
				isnull = true;
			} else {
				isnull = false;
			}
			connection = DBTest.getConnection();
			statement = connection.createStatement();
			if (statement != null && !isnull) {
				executeQuery = statement
						.executeQuery("select * from t_user where user_name='"
								+ nString + "'");
				if (executeQuery.next()) {
					System.err.println("has  next");
					printWriter.print("<script type=" + "\"text/javascript\""
							+ " charset=" + "\"utf-8\"" + ">window.alert("
							+ "\"用户已存在，请重新添加！\"" + ")</script>");
					printWriter.close();
				} else {
					sqlString = "insert into t_user(user_name,user_pwd) values('"
							+ nString + "','" + pString + "')";
					statement.execute(sqlString);
					printWriter = resp.getWriter();
					printWriter
							.println("<table border='1' width='30%' align='center'>");
					printWriter
							.println("<tr><td align='ceter'>name</td><td align='ceter'>pwd</td></tr>");
					sqlString = "select * from t_user";
					executeQuery = statement.executeQuery(sqlString);
					while (executeQuery.next()) {
						String nameString = executeQuery.getString("user_name");
						String pwdString = executeQuery.getString("user_pwd");
						printWriter.println("<tr><td>" + nameString
								+ "</td><td>" + pwdString + "</td></tr>");
					}
					printWriter.println("</table>");
					printWriter.println("<a href='h1.html'>继续添加</a>");
				}
				// statement.execute("insert into t_user(user_name,user_pwd)");
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (printWriter != null) {
					printWriter.close();
					printWriter = null;
				}
				if (statement != null) {
					statement.cancel();
					statement = null;
				}
				if (executeQuery != null) {
					executeQuery.close();
					executeQuery = null;
				}
				if (connection != null) {
					System.err.println("close  connection");
					DBTest.closeConnection(connection);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// delete user list
	private void loadList(HttpServletRequest req, HttpServletResponse resp) {
		try {
			connection = DBTest.getConnection();
			statement = connection.createStatement();
			String name = req.getParameter("user_name");
			System.out.println("will delete user:" + name);
			if (name != null && !"".equals(name)) {
				// statement.execute("delete from t_user where user_name='"+name+"'");
				IUser iUser = (IUser) FactoryDao.getObject("User");
				iUser.deleteUser(name);
			}
			resp.setContentType("text/html; charset=utf-8");
			printWriter = resp.getWriter();
			printWriter.println("<table border='1' width='30%'>");
			printWriter
					.println("<tr><td>name</td><td>pwd</td><td>操作</td></tr>");
			sqlString = "select * from t_user";

			executeQuery = statement.executeQuery(sqlString);
			while (executeQuery.next()) {
				String nameString = executeQuery.getString("user_name");
				String pwdString = executeQuery.getString("user_pwd");
				printWriter.println("<tr><td>" + nameString + "</td><td>"
						+ pwdString + "</td><td><a href='list.do?user_name="
						+ nameString + "'>删除</a></td></tr>");
			}
			printWriter.println("</table>");
			printWriter.println("<a href='h1.html'>添加</a>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (printWriter != null) {
					printWriter.close();
					printWriter = null;
				}
				if (statement != null) {
					statement.cancel();
					statement = null;
				}
				if (executeQuery != null) {
					executeQuery.close();
					executeQuery = null;
				}
				if (connection != null) {
					System.err.println("close  connection");
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
