

package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter implements HttpSessionListener {
    public static ConcurrentHashMap<String, ArrayList<CounterServlet>> sessions = new ConcurrentHashMap<String,ArrayList<CounterServlet>>();
    private List automaticSessions = new ArrayList();
    public SessionCounter() {
    }
 
    @Override
    public void sessionCreated(HttpSessionEvent event) {
    	System.out.println("Session created! ID : " + event.getSession().getId());
        HttpSession session = event.getSession();
        automaticSessions.add(session.getId());
 
        session.setAttribute("counter", this);
    }
 
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        automaticSessions.remove(session.getId());
 
        session.setAttribute("counter", this);
    }
 
    public int getAutoActiveSessionNumber() {
        return sessions.size();
    }

    public static int getActiveSessionNumber(){
    	return SessionCounter.sessions.size();
    }
    
    public static boolean sessionExists(HttpSession session){
    	return SessionCounter.sessions.contains(session.getId());
    }
    
    public static void unregisterSessionServlet(HttpSession session, CounterServlet servlet){
    	if(SessionCounter.sessions.get(session.getId()).size()>1){
    		SessionCounter.sessions.get(session.getId()).remove(servlet);
    	} else {
    		SessionCounter.sessions.remove(session.getId());
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