package com.xk.in;

import com.xk.bean.Computer;

public class CartItem {
	private Computer computer;
	private long userId;
	public Computer getComputer() {
		return computer;
	}
	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
