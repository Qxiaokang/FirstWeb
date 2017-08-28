package com.xk;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Properties;

public class DBTest {
	private static String url="jdbc:mysql://localhost:3306/javawebdb";
	 private static String name="root";
	 private static String password="xk123456";
	 private static Connection connection=null;
	/* public static void main(String[] args){
	  // TODO Auto-generated method stub
	  try {
	   //加载驱动程序；
	    * 
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
	 public static void main(String[] args) {
		new DBTest().query();
		new DBTest().bstchQuery();
	}
	 //get db connection
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
	 
	 // get normal statement
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
	 
	 //close connection
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
	 //get mysql n p url
	 public void getParam(String fileString){
		 Properties properties=new Properties();
		 try {
			 File file=new File(fileString);
			 FileInputStream inputStream=new FileInputStream(file);
			 properties.load(inputStream);
			 this.url=properties.getProperty("dburl");
			 this.name=properties.getProperty("dbname");
			 this.password=properties.getProperty("dbpwd");
			 System.out.println("dburl:"+url+"  dbname:"+name+"  dbpwd:"+password);
		} catch (Exception e) {
			System.err.println("DBTest:"+e.toString());
			// TODO: handle exception
		} 
	 }
	 //paging
	 public void  query() {
		getConnection();
		String sqlString="select * from t_user";
		try {
			Statement statement=connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE//可 跳 步 的
					, ResultSet.CONCUR_READ_ONLY);//设置并发性为其他用户只读
			ResultSet set = statement.executeQuery(sqlString);
			set.absolute(2);
			System.err.println("row2:"+set.getString("user_name"));
			set.next();
			System.err.println("row3:"+set.getString("user_name"));
			set.relative(1);
			System.err.println("row4:"+set.getString("user_name"));
			set.previous();
			System.err.println("row4-1:"+set.getString("user_name"));	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
	}
	//batching 
	public void bstchQuery(){
		getConnection();
		
	    String sqlString="update t_user set city_name='深圳' where user_id=? ";
	    try {
	    	connection.setAutoCommit(false);//set autocommit false
			PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
			for (int i = 14; i < 21; i++) {
				prepareStatement.setInt(1,i); 
				prepareStatement.addBatch();
				if(i%2==0){
					prepareStatement.executeBatch();//excute
					prepareStatement.clearBatch();//clear
				}
			}
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
	    
	}
}

