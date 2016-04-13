package com.tjazi.frontend.endpoint.websocket.implementation.endpoint2router;

import com.tjazi.frontend.endpoint.websocket.implementation.websocketmessages.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by Krzysztof Wasiak on 08/04/2016.
 */
@Controller
public class WebSocketController {

    private static final Logger log = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private WebSocket2Router webSocket2Router;

    @MessageMapping("/messages")
    public void handleChatMessage(Message<Object> message, @Payload ChatMessage chatMessage) {

        log.debug("[WebSocketController] Received payload: " + chatMessage.getPayload());

        log.debug("[WebSocketController] Sending message to all subscribers...");
        messagingTemplate.convertAndSend("/topic", chatMessage);
        //webSocket2Router.passWebSocketMessageToRouter(chatMessage);
    }
}
