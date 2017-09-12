package com.xk.in;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

import com.xk.bean.Computer;
import com.xk.bean.ComputerDao;

public class Cart {
	private DecimalFormat decimalFormat=new DecimalFormat("#.00");
	private static Cart cart;
	private  Cart(){
	}
	public synchronized static Cart getInstance(){
		if(cart==null){
			cart=new Cart();
		}
		return cart;
	}
	private List<CartItem> list=new ArrayList<CartItem>();
	public List<CartItem> getList() {
		return list;
	}
	public void setList(List<CartItem> list) {
		this.list = list;
	}
	public void addItem(CartItem cartItem){
		list.add(cartItem);
	}
	public List<CartItem> updateItem(int id,int num,long userId){
		boolean hasit=false;
		if(list.size()==0){
			CartItem cartItem2=new CartItem();
			cartItem2.setUserId(userId);
			Computer computer=new ComputerDao().findbyId(id);
			computer.setBuynum(num);
			
			cartItem2.setComputer(computer);
			list.add(cartItem2);
		}
		for (int i = 0; i < list.size(); i++) {
			CartItem cartItem=list.get(i);
			//if has it then update it
			if(cartItem.getComputer().getId()==id&&userId==cartItem.getUserId()){
				Computer computer=new ComputerDao().findbyId(id);
				computer.setBuynum(num);
				cartItem.setComputer(computer);
				hasit=true;
				break;
			}else {
				hasit=false;
			}
		}
		//if not has it then add it
		if(!hasit){
			CartItem cartItem2=new CartItem();
			cartItem2.setUserId(userId);
			Computer computer=new ComputerDao().findbyId(id);
			computer.setBuynum(num);
			cartItem2.setComputer(computer);
			list.add(cartItem2);
		}
		return list;
	}
	//delete computer by computerId and userId
	public List<CartItem> deleteItem(int id,long userId){
		for (int i = 0; i < list.size(); i++) {
			CartItem cartItem=list.get(i);
			if(cartItem.getComputer().getId()==id&&userId==cartItem.getUserId()){
				list.remove(i);
			}
		}
		return list;
	}
	//delete all computer by userId
	public boolean deleteAllByUser(long userId){
		for (int i = 0; i < list.size(); i++) {
			CartItem cartItem=list.get(i);
			if(cartItem.getUserId()==userId){
				list.remove(i);
				deleteAllByUser(userId);
			}
		}
		return true;
	}
	//get computers total price
	public String getTotalPrice(){
		double  total=0;
		for (int i = 0; i < list.size(); i++) {
			Computer computer=list.get(i).getComputer();
		double price=computer.getBuynum()*1.00*Double.parseDouble(computer.getPrice());
			total+=price;
		}
		System.err.println("合计："+total);
		if(total==0){
			return "0.00";
		}
		return decimalFormat.format(total);
	}
}
