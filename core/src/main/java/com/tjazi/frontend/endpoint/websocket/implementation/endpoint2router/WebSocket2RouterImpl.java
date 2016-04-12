package com.tjazi.frontend.endpoint.websocket.implementation.endpoint2router;

import com.tjazi.frontend.endpoint.websocket.implementation.websocketmessages.ChatMessage;
import com.tjazi.routing.router.messages.endpoint.EndpointToRouterPayloadMessage;
import org.springframework.stereotype.Service;

/**
 * Created by Krzysztof Wasiak on 08/04/2016.
 */
@Service
public class WebSocket2RouterImpl implements WebSocket2Router {

    @Override
    public void passWebSocketMessageToRouter(ChatMessage chatMessage) {

    }
}
