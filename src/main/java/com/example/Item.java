package com.example;

public class Item {
	private String name;
	private int price;
	private int loc1;
	private int loc2;
	private String[] additionals;
	private int opt1;
	private int opt2;
	private int opt3;
	private int opt4;
	public Item(String name, int price, int loc1, int loc2, int opt1, int opt2,
			int opt3, int opt4) {
		super();
		this.name = name;
		this.price = price;
		this.additionals = null;
		this.loc1 = loc1;
		this.loc2 = loc2;
		this.opt1 = opt1;
		this.opt2 = opt2;
		this.opt3 = opt3;
		this.opt4 = opt4;
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
	public int getOpt1() {
		return opt1;
	}
	public void setOpt1(int opt1) {
		this.opt1 = opt1;
	}
	public int getOpt2() {
		return opt2;
	}
	public void setOpt2(int opt2) {
		this.opt2 = opt2;
	}
	public int getOpt3() {
		return opt3;
	}
	public void setOpt3(int opt3) {
		this.opt3 = opt3;
	}
	public int getOpt4() {
		return opt4;
	}
	public void setOpt4(int opt4) {
		this.opt4 = opt4;
	}
	
	
}
