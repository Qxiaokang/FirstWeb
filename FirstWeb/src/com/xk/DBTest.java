package com.xk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest {
	private static String url="jdbc:mysql://localhost:3306/javawebdb";
	 private static String name="root";
	 private static String password="xk123456";
	 private static Connection connection=null;
	/* public static void main(String[] args){
	  // TODO Auto-generated method stub
	  try {
	   //加载驱动程序；
	   Class.forName("com.mysql.jdbc.Driver");
	   //获得数据库连接；
	   Connection conn=DriverManager.getConnection(url,name,password);
	   //通过数据库的连接操作数据库，实现增删改查。
	   Statement stmt=conn.createStatement();
	   ResultSet rs=stmt.executeQuery("select * from t_user;");
	   while(rs.next())
	   {
	    System.out.println(rs.getString("user_name")+","+rs.getString("user_pwd")+","
	    		+rs.getString("user_id"));
	   
	   }
	  } catch (ClassNotFoundException e) {
	   e.printStackTrace();
	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	 
	 }*/
	 public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection(url,name,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
			//e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
		return connection;
	 }
	 public Statement getResult(){
		 Statement statement=null;
		 //加载驱动
		 try {
		 Class.forName("com.mysql.jdbc.Driver");
			//获得连接
		 connection=DriverManager.getConnection(url,name,password);
		 
	     statement=connection.createStatement();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		} 
		 return statement;
	 }
	 
	 
	 public static void closeConnection(Connection c){
		 if(c!=null){
			 try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	 }
}

