

package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter {
    public static ConcurrentHashMap<String, ArrayList<CounterServlet>> sessions = new ConcurrentHashMap<String,ArrayList<CounterServlet>>();
    public SessionCounter() {
    }


    public static int getActiveSessionNumber(){
    	return SessionCounter.sessions.size();
    }
    
    public static boolean sessionExists(HttpSession session){
    	return SessionCounter.sessions.contains(session.getId());
    }
    
    public static void unregisterSessionServlet(HttpSession session, CounterServlet servlet){
    	try{
    	if(SessionCounter.sessions.get(session.getId()).size()>1){
    		SessionCounter.sessions.get(session.getId()).remove(servlet);
    	} else {
    		SessionCounter.sessions.remove(session.getId());
    	}
    	}
    	catch (NullPointerException e){
    		// Session did not exist - do nothing.
    	}
    	
    }
    //Puts the string:servlet pair into the hashmap.
    public static void registerSessionServlet(HttpSession session, CounterServlet servlet){
    	if(SessionCounter.sessions.containsKey(session.getId())){
    		SessionCounter.sessions.get(session.getId()).add(servlet);
    	} else {
    		ArrayList<CounterServlet> newList = new ArrayList<CounterServlet>();
    		newList.add(servlet);
    		SessionCounter.sessions.put(session.getId(), newList);
    	}  	
    }
}