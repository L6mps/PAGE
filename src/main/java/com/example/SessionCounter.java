

package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter implements HttpSessionListener {
    public static List sessions = new ArrayList();
 
    public SessionCounter() {
    }
 
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        SessionCounter.sessions.add(session.getId());
 
        session.setAttribute("counter", this);
    }
 
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        SessionCounter.sessions.remove(session.getId());
 
        session.setAttribute("counter", this);
    }
 
    public static int getActiveSessionNumber() {
        return sessions.size();
    }

    public static boolean sessionExists(HttpSession session){
    	return SessionCounter.sessions.contains(session.getId());
    }
    
    public static void destroySession(HttpSession session){
    	SessionCounter.sessions.remove(session.getId());
    }

}