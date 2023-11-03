package org.opengear.springframework.boot.terminal;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.*;

@Component
@CrossOrigin(origins = {"http://localhost:8080","websocketking.com"})
public class MyWebSocketHandler implements WebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // connection established
        System.out.println();
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // handle message
        Object payload1 = message.getPayload();
        String payload = message.getPayload().toString();
        String response = "Received message: " + payload;
        System.out.println(response);
//        session.sendMessage(new TextMessage("sdf"));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // handle transport error
        try {
            throw exception;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println();
        // connection closed

    }



    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
