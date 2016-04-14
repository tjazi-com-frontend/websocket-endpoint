package com.tjazi.frontend.endpoint.websocket.implementation.endpoint2router;

import com.tjazi.frontend.endpoint.websocket.implementation.websocketmessages.ChatMessage;
import com.tjazi.routing.router.client.EndpointToRouterClient;
import com.tjazi.routing.router.messages.endpoint.EndpointToRouterPayloadMessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 08/04/2016.
 */
@Service
public class WebSocket2RouterImpl implements WebSocket2Router {

    private final static Logger log = LoggerFactory.getLogger(WebSocket2RouterImpl.class);

    @Autowired
    private EndpointToRouterClient endpointToRouterClient;

    @Override
    public void passWebSocketMessageToRouter(ChatMessage chatMessage) {

        if (chatMessage == null) {
            String errorMessage = "chatMessage is null";
            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        EndpointToRouterPayloadMessageType messageType = this.decodeMessageType(chatMessage.isGroupMessage());

        endpointToRouterClient.routeMessage(
                messageType,
                UUID.fromString(chatMessage.getTargetId()),
                chatMessage.getPayload());
    }

    private EndpointToRouterPayloadMessageType decodeMessageType(boolean isGroupMessage) {
        return isGroupMessage ? EndpointToRouterPayloadMessageType.MULTICAST :
                EndpointToRouterPayloadMessageType.PER2PER;
    }

}
