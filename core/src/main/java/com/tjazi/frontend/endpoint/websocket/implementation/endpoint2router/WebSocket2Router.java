package com.tjazi.frontend.endpoint.websocket.implementation.endpoint2router;

import com.tjazi.frontend.endpoint.websocket.implementation.websocketmessages.ChatMessage;

/**
 * Created by Krzysztof Wasiak on 08/04/2016.
 */
public interface WebSocket2Router {

    void passWebSocketMessageToRouter(ChatMessage chatMessage);
}
