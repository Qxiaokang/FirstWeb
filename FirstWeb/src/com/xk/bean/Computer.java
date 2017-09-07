package com.xk.bean;

public class Computer {
	private int  id;
	private String model;
	private String pic;
	private String price;
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getDescriptionString() {
		return descriptionString;
	}
	public void setDescriptionString(String descriptionString) {
		this.descriptionString = descriptionString;
	}
	public String getRepertory() {
		return repertory;
	}
	public void setRepertory(String repertory) {
		this.repertory = repertory;
	}
	private String descriptionString;
	private String repertory;
	
}
