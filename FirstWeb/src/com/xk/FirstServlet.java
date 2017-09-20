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
import java.util.ArrayList;
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

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.xk.bean.Computer;
import com.xk.bean.ComputerDao;
import com.xk.bean.Province;
import com.xk.bean.User;
import com.xk.in.Cart;
import com.xk.in.CartItem;
import com.xk.in.IUser;
import com.xk.utils.FactoryDao;

public class FirstServlet extends HttpServlet {
	private Statement statement;
	private PrintWriter printWriter;
	private String sqlString = "";
	private ResultSet executeQuery;
	private boolean isnull = false;
	private Connection connection = null;
	private User user = null;
	private java.util.List<Computer> computers = new ArrayList<Computer>();

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
		// get init param
		ServletConfig servletConfig = getServletConfig();
		String author = servletConfig.getInitParameter("author");
		System.out.println("author:" + author);
		HttpSession session = req.getSession();
		if (session.getAttribute("user") == null) {
			resp.sendRedirect("logina.html");
			return;
		}
		user = (User) req.getSession().getAttribute("user");
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
		} else if (pathString.equals("/buy")) {
			System.err.println("complist---buy");
			buyComp(req, resp);
		} else if (pathString.equals("/deletecom")) {
			deleteComp(req, resp);
		} else if (pathString.equals("/changeCity")) {
			changeCity(req, resp);
		} else if (pathString.equals("/changeDesp")) {
			changeDescription(req, resp);
		}
	}

	private void changeDescription(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			String messageString = "";
			String provinceString = req.getParameter("province");
			resp.setContentType("text/html;charset=utf-8");
			System.err.println("provincestring:"+provinceString);
			PrintWriter writer = resp.getWriter();
			if ("gd".equals(provinceString)) {
				Province province = new Province(1,"GD",
				"广东，名由岭南东道、广南东路演变而来，简称“粤”，省会广州，是中国大陆南端沿海的一个省份。");
				Gson gson=new Gson();
				String jsonString = gson.toJson(province);
				writer.print(jsonString);
			}else if ("hb".equals(provinceString)) {
				Province province = new Province(2,"HB","湖北，简称“鄂”，是中华人民共和国省级行政区，省会为武汉，因位于长江中游、洞庭湖以北，故名湖北");
				Gson gson=new Gson();
				String jsonString = gson.toJson(province);
				writer.print(jsonString);
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// changeCity
	private void changeCity(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String message = "";
			String proviceString = req.getParameter("province");
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter writer = resp.getWriter();
			if ("gd".equals(proviceString)) {
				message = "gz,广州;st,汕头;zj,湛江;jm,江门;fs,佛山;sg,韶关;mm,茂名;zq,肇庆;hz,惠州;zh,珠海;sz,深圳;gz,潮州;zs,中山;dg,东莞;sw,汕尾;yj,阳江;hy,河源;mz,梅州;qy,清远;jy,揭阳;yf,云浮 ";
			} else if ("hb".equals(proviceString)) {
				message = "wh,武汉市;sy,十堰市;xf,襄樊市;sz,随州市;jm,荆门市;xg,孝感市;yc,宜昌市;hg,黄冈市;ez,鄂州市;jz,荆州市;hs,黄石市;xn,咸宁市";
			} else if ("hn".equals(proviceString)) {
				message = "cs,长沙;xt,湘潭;zz,株洲;bz,郴州;hy,衡阳;sy,邵阳;yz,永州;xx,湘西自治州;zjj,张家界;cd,常德;ld,娄底;yy,益阳;hh,怀化;yy,岳阳";
			} else if ("js".equals(proviceString)) {
				message = "nj,南京;wx,无锡;xz,徐州;cz,常州;sz,苏州;nt,南通;lyg,连云港;ha,淮安;yc,盐城;yz,扬州;zj,镇江;tz,泰州;sq,宿迁";
			}
			writer.print(message);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// delete list computers
	private void deleteComp(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		try {
			long userId = user.getUser_id();
			String deleteIdString = req.getParameter("id");
			if ("-1".equals(deleteIdString)) {
				Cart.getInstance().deleteAllByUser(userId);
			} else {
				Cart.getInstance().deleteItem(Integer.parseInt(deleteIdString),
						userId);
			}
			req.setAttribute("carts", Cart.getInstance().getList());
			System.err.println("carts   size:"
					+ Cart.getInstance().getList().size());

			req.getRequestDispatcher("buycomplist.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// buy list computers
	private void buyComp(HttpServletRequest req, HttpServletResponse resp) {
		try {
			long userId = user.getUser_id();
			String array = req.getParameter("array");
			System.err.println("array" + array);
			if (array != null && !"".equals(array)) {
				String[] split = array.split(";");
				for (int i = 0; i < split.length; i++) {
					if (split[i].startsWith(",")) {
						split[i] = split[i].substring(1);
					}
					String idString = split[i].split(",")[0];
					String numString = split[i].split(",")[1];
					Cart.getInstance().updateItem(Integer.parseInt(idString),
							Integer.parseInt(numString), userId);
				}
			}
			req.setAttribute("carts", Cart.getInstance().getList());
			System.err.println("carts   size:"
					+ Cart.getInstance().getList().size());
			req.getRequestDispatcher("buycomplist.jsp").forward(req, resp);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// computer list
	private void listComp(HttpServletRequest req, HttpServletResponse resp) {
		java.util.List<Computer> list = new ComputerDao().findAll();
		java.util.List<CartItem> cartItems = Cart.getInstance().getList();
		try {
			for (int i = 0; i < list.size(); i++) {
				Computer computer = list.get(i);
				for (int j = 0; j < cartItems.size(); j++) {
					CartItem cartItem = cartItems.get(j);
					if (cartItem.getComputer().getId() == computer.getId()) {
						computer.setBuynum(cartItem.getComputer().getBuynum());
					}
				}
			}
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
					/*
					 * printWriter.print("<script type=" + "\"text/javascript\""
					 * + " charset=" + "\"utf-8\"" + ">window.alert(" +
					 * "\"用户已存在，请重新添加！\"" + ")</script>");
					 */
					printWriter.print("用户名已存在...");
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
