package com.tjazi.frontend.endpoint.websocket.implementation.endpoint2router.converters;

import com.tjazi.frontend.endpoint.websocket.implementation.websocketmessages.ChatMessage;
import com.tjazi.routing.router.messages.endpoint.EndpointToRouterPayloadMessage;
import com.tjazi.routing.router.messages.endpoint.EndpointToRouterPayloadMessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 08/04/2016.
 */
public class ChatMessage2EndpointToRouterPayloadMessageConverter {

    private final static Logger log = LoggerFactory.getLogger(ChatMessage2EndpointToRouterPayloadMessageConverter.class);

    public static EndpointToRouterPayloadMessage convertChatMessage(ChatMessage chatMessage) {

        assertInput(chatMessage);

        EndpointToRouterPayloadMessage endpointToRouterPayloadMessage = new EndpointToRouterPayloadMessage();
        endpointToRouterPayloadMessage.setMessageType(getMessageTypeFromChatMessage(chatMessage));
        endpointToRouterPayloadMessage.setPayload(chatMessage.getPayload());

        String targetId = chatMessage.getTargetId();

        try {
            endpointToRouterPayloadMessage.setTarget(UUID.fromString(targetId));
        }
        catch (IllegalArgumentException ex) {
            log.error("Target ID cannot be converted to UUID: " + targetId);
            throw ex;
        }

        return endpointToRouterPayloadMessage;
    }

    private static EndpointToRouterPayloadMessageType getMessageTypeFromChatMessage(ChatMessage chatMessage) {

        return chatMessage.isGroupMessage() ?
                EndpointToRouterPayloadMessageType.MULTICAST : EndpointToRouterPayloadMessageType.PER2PER;
    }

    private static void assertInput(ChatMessage chatMessage) {

        String errorMessage = null;

        if (chatMessage == null) {
            errorMessage = "chatMessage is null";
        }
        else
        {
            String targetId = chatMessage.getTargetId();
            if (targetId == null || targetId.isEmpty()) {
                errorMessage = "Message targetId is null or empty";
            }

            String authorizationToken = chatMessage.getSenderAuthorizationToken();
            if (authorizationToken == null || authorizationToken.isEmpty()) {
                errorMessage = "Message authorization token is null or empty";
            }

            String payload = chatMessage.getPayload();
            if (payload == null || payload.isEmpty()) {
                errorMessage = "Message payload is null or empty";
            }
        }

        if (errorMessage != null) {
            log.error(errorMessage);
            throw new IllegalArgumentException();
        }

    }
}
