package com.xk.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xk.DBTest;
import com.xk.bean.Computer;

public class ComputerDao {
	private Connection connection;

	public List<Computer> findAll() {
		List<Computer> computers = new ArrayList<Computer>();
		try {
			String sql = "select * from t_computer";
			connection = DBTest.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement(sql);
			ResultSet executeQuery = prepareStatement.executeQuery();
			while (executeQuery.next()) {
				Computer computer = new Computer();
				computer.setId(executeQuery.getInt("id"));
				computer.setModel(executeQuery.getString("c_model"));
				computer.setPic(executeQuery.getString("c_pic"));
				computer.setDescriptionString(executeQuery
						.getString("c_description"));
				computer.setPrice(executeQuery.getString("c_price"));
				computer.setRepertory(executeQuery.getInt("c_repertory"));
				computers.add(computer);
			}
			DBTest.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return computers;
		}

		return computers;
	}

	public Computer findbyId(int id) {
		Computer computer=null;
		try {
			connection = DBTest.getConnection();
			String sqlString = "select * from t_computer where id=" + id;
			PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
			ResultSet executeQuery = prepareStatement.executeQuery();
			while (executeQuery.next()) {
				computer=new Computer();
				computer.setId(id);
				computer.setModel(executeQuery.getString("c_model"));
				computer.setPic(executeQuery.getString("c_pic"));
				computer.setDescriptionString("c_description");
				computer.setPrice(executeQuery.getString("c_price"));
				computer.setRepertory(executeQuery.getInt("c_repertory"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return computer;
		}
		return computer;
	}
}
