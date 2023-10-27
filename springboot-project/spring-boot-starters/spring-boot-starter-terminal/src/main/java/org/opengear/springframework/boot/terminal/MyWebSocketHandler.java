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
//        String payload = message.getPayload().toString();
//        String response = "Received message: " + payload;
        session.sendMessage(new TextMessage("sdf"));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // handle transport error
        exception.printStackTrace();
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
