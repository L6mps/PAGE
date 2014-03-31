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
		sb.append("SELECT * FROM resultSummary ");
		ArrayList<Item> items = new ArrayList<Item>();
		for(int i = 0; i<4; i++)
			if(selectedParams[i])
				sb.append("INNER JOIN service" + (i+1) + " ON (resultSummary.id = service"+(i+1)+".id) ");
		//Just append other params to sb with WHERE somethingsomething = somethingother
		try {
			rs = dbengine.execute(sb.toString());
			while(rs.next()){
				Item item = new Item(rs.getString(2), rs.getInt(5), rs.getInt(3), rs.getInt(4));
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
		/*
		ArrayList<ResultSet> resultsets = new ArrayList<ResultSet>();
		ArrayList<Set<Integer>> comparingSets = new ArrayList<Set<Integer>>();
		for(int i=0;i<4; i++){
			if(selectedParams[i]){
				ResultSet rs;
				try {
					rs = dbengine.execute("select * from " + tabs[i] + ";");
					resultsets.add(rs);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} for(ResultSet result : resultsets){
			try {
				Set<Integer> setter = new HashSet<Integer>();
				while(result.next()){
					setter.add(result.getInt(1));
				}
				comparingSets.add(setter);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(resultsets.size()>1){
			for(int i=resultsets.size()-1; i>0;i--){
				comparingSets.get(i-1).retainAll(comparingSets.get(i));
			}
		}
	}*/
	
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
