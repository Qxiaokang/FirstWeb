package com.xk;

import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xk.bean.User;
import com.xk.in.IUser;
/**
 * 
 * @author Admin
 *	user tool
 */
public class RealizeIUser implements IUser {
	private Connection connection;

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		try {
			connection = DBTest.getConnection();
			String sql = "select * from t_user";
			Statement statement = connection.createStatement();
			ResultSet executeQuery = statement.executeQuery(sql);
			while (executeQuery.next()) {
				User user = new User();
				user.setUser_name(executeQuery.getString("user_name"));
				user.setUser_pwd(executeQuery.getString("user_pwd"));
				user.setUser_id(executeQuery.getLong("user_id"));
				user.setCity_id(executeQuery.getString("city_id"));
				user.setCity_name(executeQuery.getString("city_name"));
				user.setUser_jursdiction(executeQuery
						.getString("user_jurisdiction"));
				user.setUser_pic(executeQuery.getString("user_pic"));
				user.setUser_description(executeQuery
						.getString("user_description"));
				user.setUser_sex(executeQuery.getInt("user_sex"));
				user.setUser_ch_name(executeQuery.getString("user_ch_name"));
				users.add(user);
			}
			DBTest.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return users;
		}
		return users;
	}

	@Override
	public boolean saveUser(User user) {
		boolean b = false;
		// TODO Auto-generated method stub

		try {
			connection = DBTest.getConnection();
			String sql = "insert into t_user(user_name,user_pwd,city_id,city_name,user_jurisdicion,user_pic,user_description,"
					+ "user_sex,user_ch_name) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, user.getUser_name());
			pStatement.setString(2, user.getUser_pwd());
			pStatement.setString(3, user.getCity_id());
			pStatement.setString(4, user.getCity_name());
			pStatement.setString(5, user.getUser_jursdiction());
			pStatement.setString(6, user.getUser_pic());
			pStatement.setString(7, user.getUser_description());
			pStatement.setInt(8, user.getUser_sex());
			pStatement.setString(9, user.getUser_ch_name());
			pStatement.executeUpdate();
			b = true;
			DBTest.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			b = false;
			return b;
		}

		return b;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		boolean b = false;
		try {
			connection = DBTest.getConnection();
			String sql = "update t_user set user_name=?,user_pwd=?,city_id=?,city_name=?,user_jurisdicion=?,user_pic=?,"
					+ "user_description=?,user_sex=?,user_ch_name=?";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, user.getUser_name());
			pStatement.setString(2, user.getUser_pwd());
			pStatement.setString(3, user.getCity_id());
			pStatement.setString(4, user.getCity_name());
			pStatement.setString(5, user.getUser_jursdiction());
			pStatement.setString(6, user.getUser_pic());
			pStatement.setString(7, user.getUser_description());
			pStatement.setInt(8, user.getUser_sex());
			pStatement.setString(9, user.getUser_ch_name());
			pStatement.executeUpdate();
			b = true;
			DBTest.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			b = false;
			return b;
		}
		return b;
	}

	@Override
	public boolean deleteUser(String name) {
		// TODO Auto-generated method stub
		boolean b = false;
		try {
			connection = DBTest.getConnection();
			String sql = "delete from t_user where user_name=?";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, name);
			pStatement.executeUpdate();
			b=true;
			DBTest.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			b=false;
			return b;
		}
		return b;
	}

	@Override
	public User findUserByName(String name) {
		User user=null;
		// TODO Auto-generated method stub
		try {
		connection=DBTest.getConnection();
		String sql="select * from t_user where user_name=?";	
		PreparedStatement pStatement=connection.prepareStatement(sql);
		pStatement.setString(1, name);
		ResultSet executeQuery = pStatement.executeQuery();
		while (executeQuery.next()) {
			user=new User();
			user.setUser_name(executeQuery.getString("user_name"));
			user.setUser_pwd(executeQuery.getString("user_pwd"));
			user.setUser_id(executeQuery.getLong("user_id"));
			user.setCity_id(executeQuery.getString("city_id"));
			user.setCity_name(executeQuery.getString("city_name"));
			user.setUser_jursdiction(executeQuery.getString("user_jursdiction"));
			user.setUser_pic(executeQuery.getString("user_pic"));
			user.setUser_sex(executeQuery.getInt("user_sex"));
			user.setUser_ch_name(executeQuery.getString("user_ch_name"));
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return user;
		}
		return user;
	}

	@Override
	public boolean checkNameAndPwd(String name, String pwd) {
		boolean b=false;
		try {
		connection=DBTest.getConnection();
		String sql="select * from t_user where user_name=? and user_pwd=?";
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		prepareStatement.setString(1,name);
		prepareStatement.setString(2, pwd);
		ResultSet executeQuery = prepareStatement.executeQuery();
		b= executeQuery.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			b=false;
			return b;
		}
		// TODO Auto-generated method stub
		return b;
	}

}
