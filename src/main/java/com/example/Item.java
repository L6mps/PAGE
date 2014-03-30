package com.example;

public class Item {
	private String name;
	private int price;
	private int loc1;
	private int loc2;
	private String[] additionals;
	private int mainopts;
	public Item(String name, int price, int loc1, int loc2, int mainopts) {
		super();
		this.name = name;
		this.price = price;
		this.additionals = null;
		this.loc1 = loc1;
		this.loc2 = loc2;
		this.mainopts = mainopts;
		generateBinary();
	
	}
	
	public Item(String name, int price, int loc1, int loc2) {
		super();
		this.name = name;
		this.price = price;
		this.additionals = null;
		this.loc1 = loc1;
		this.loc2 = loc2;
	
	}


	private void generateBinary(){
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getLoc1() {
		return loc1;
	}
	public void setLoc1(int loc1) {
		this.loc1 = loc1;
	}
	public int getLoc2() {
		return loc2;
	}
	public void setLoc2(int loc2) {
		this.loc2 = loc2;
	}
	public String[] getAdditionals() {
		return additionals;
	}
	public void setAdditionals(String[] additionals) {
		this.additionals = additionals;
	}
	public int getMainopts() {
		return mainopts;
	}
	public void setMainopts(int mainopts) {
		this.mainopts = mainopts;
	}
	
}
