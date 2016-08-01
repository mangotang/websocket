package com.snb.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/serverEndpoint", configurator=ServerConfigurator.class)
public class ChatroomServerEndpoint {
    
    private static Set<Session> users = Collections.synchronizedSet(new HashSet<Session>());
    
    @OnOpen
    public void handleOpen(EndpointConfig endpointConfig, Session userSession) {
        userSession.getUserProperties().put("username", endpointConfig.getUserProperties().get("username"));
        users.add(userSession);
        
        System.out.println("done handleOpen");
    }
    
    @OnMessage
    public void handleMessage(String message, Session session) {
        String username = (String)session.getUserProperties().get("username");
        if (null != username) {
            for (Session s : users) {
                try {
                    s.getBasicRemote().sendText(buildJsonData(username, message));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    @OnClose
    public void handleClose(Session session) {
        users.remove(session);
        System.out.println("done handleClose");
    }
    
    @OnError
    public void handleError(Throwable t) {
   

        System.out.println("done handleError");
    }
    
    private String buildJsonData(String username, String message) {
        String data = "{\"message\": \"" + message + "\"," + "\"username\": \"" + username + "\"}";
        return data;
    }

}
