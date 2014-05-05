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
	private ArrayList<Item> retrievedItems = new ArrayList<Item>();
	public SearchEngine(Map<String, String[]> paramMap, Enumeration<String> params){
		paramKeys = new ArrayList<String>();
		selectedParams = new boolean[4];
		remapKeys(params);
		dbengine = new DBProvider();
		this.mappedParameters = paramMap;
		retrievedItems = mapSelections();
	}
	
	public ArrayList<Item> getItems(){
		return this.retrievedItems;
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
				Item item = new Item(rs.getInt(1), rs.getString(2),rs.getInt(5) ,rs.getInt(3), rs.getInt(4),rs.getInt(10), rs.getString(11), rs.getString(7), rs.getString(6), rs.getString(8), rs.getString(9));
				items.add(item);
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
			System.out.println(item);
			paramKeys.add(item);
		}
		initSelections();
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
}
