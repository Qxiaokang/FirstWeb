package com.xk.in;

import java.util.List;

import com.xk.bean.User;

public interface IUser {
	public List<User> fidAllUsers();
	public boolean saveUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(String name);
	public User findUserByName(String name);
}
