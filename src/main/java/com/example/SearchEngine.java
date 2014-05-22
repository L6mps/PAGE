package com.example;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SearchEngine {
	private final String[] tabs = {"service1","service2","service3","service4"};
	
	private boolean[] selectedParams;
	private int loc1, loc2, price;
	private Map<String, String[]> mappedParameters;
	private ArrayList<String> paramKeys;
	private DBProvider dbengine;
	private String searchString;
	private ArrayList<Item> retrievedItems = new ArrayList<Item>();
	public SearchEngine(Map<String, String[]> paramMap, Enumeration<String> params, String searchString, String priceString){
		this.mappedParameters = paramMap;
		translatePrice(priceString);
		this.searchString = handleSearchString(searchString);
		paramKeys = new ArrayList<String>();
		selectedParams = new boolean[4];
		remapKeys(params);
		dbengine = new DBProvider();		
		retrievedItems = mapSelections();
	}
	
	public ArrayList<Item> getItems(){
		return this.retrievedItems;
	}
	
	private void translatePrice(String price){
		try{
			this.price = Integer.valueOf(price);
		} catch (Exception e){
			//PEBKAC probably
			this.price = Integer.MAX_VALUE;
		}
	}
	
	private ArrayList<Item> mapSelections(){
		ResultSet rs;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM generalinfo ");
		ArrayList<Item> items = new ArrayList<Item>();
		for(int i = 0; i<4; i++)
			if(selectedParams[i])
				sb.append("INNER JOIN service" + (i+1) + " ON (generalinfo.id = service"+(i+1)+".id) ");
		//Just append other params to sb with WHERE somethingsomething = somethingother
		try {
			rs = dbengine.execute(sb.toString());
			while(rs.next()){
				
				//Here we check that the item that is about to be added contains (or the other way around) the search term and is
				//cheaper than the price requested.
				Item item = new Item(rs.getInt(1), rs.getString(2),rs.getInt(5) ,rs.getInt(3), rs.getInt(4),rs.getInt(10), rs.getString(11), rs.getString(7), rs.getString(6), rs.getString(8), rs.getString(9));
				boolean check1 = item.getName().toLowerCase().contains(searchString.toLowerCase());
				boolean check2 = searchString.toLowerCase().contains(item.getName().toLowerCase());
				boolean check3;
				if(mappedParameters.get("D1")[0].equalsIgnoreCase("0")){
					check3 = true;
				} else {
					check3 = item.getLoc1Int().equalsIgnoreCase(mappedParameters.get("D1")[0]);
				}
				boolean check4;
				if(mappedParameters.get("D2")[0].equalsIgnoreCase("0")){
					check4 = true;
				} else {
					check4 = item.getLoc2Int().equalsIgnoreCase(mappedParameters.get("D2")[0]);
				}
				if((check1 || check2) && (check3 && check4)){
					if(item.getPrice()<this.price){
						items.add(item);
					}
				}
				
			}
			rs.close();
			dbengine.closeConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	private void remapKeys(Enumeration<String> params){
		while(params.hasMoreElements()){
			String item = params.nextElement();
			paramKeys.add(item);
		}
		initSelections();
	}
	
	
	public void setPrice(int price) {
		this.price = price;
	}


	private void initSelections(){
		Arrays.fill(selectedParams, Boolean.FALSE);
		if(paramKeys.contains("demented")){
			selectedParams[0]=true;
		}
		if(paramKeys.contains("wheelchair")){
			selectedParams[1]=true;
		}
		if(paramKeys.contains("nursing")){
			selectedParams[2]=true;
		}
		if(paramKeys.contains("paidservices")){
			selectedParams[3]=true;
		}
	}
	
	
	private String handleSearchString(String search){
		String[] matches = {"o", "a", "o", "u", "O", "A", "O", "U"};
		String[] matcher = {"%C3%B5","%C3%A4","%C3%B6","%C3%BC","%C3%95","%C3%84","%C3%96","%C3%9C"};		
		if(search.contains("%")){
			for(int i=0;i<matcher.length;i++){
				search.replace(matcher[i], matches[i]);
			}
			System.out.println("replaced string : " + search);
			return search.replace("+", " ");					
		} else {
			return search.replace("+", " ");
		}
	}
	
}
