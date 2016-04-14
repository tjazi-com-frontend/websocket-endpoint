package com.tjazi.frontend.endpoint.websocket.implementation.router2endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Krzysztof Wasiak on 08/04/2016.
 */
@Service
public class ClientRouterImpl implements ClientRouter {

    private final static Logger log = LoggerFactory.getLogger(ClientRouterImpl.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void passPayloadToClients(List<String> clientAddresses, String payload) {

        this.assertInput(clientAddresses, payload);

        // go through addresses and send message to each of them
        for (String singleAddress: clientAddresses) {

            if (singleAddress == null || singleAddress.isEmpty()) {
                throw new IllegalArgumentException("One of the addresses is null or empty");
            }

            // send message to the web socket
            messagingTemplate.convertAndSend(
                    createWebSocketAddress(singleAddress),
                    payload);
        }
    }

    private void assertInput(List<String> clientAddresses, String payload) {

        String errorMessage = null;

        if (clientAddresses == null || clientAddresses.isEmpty()) {
            errorMessage = "List of client addresses is null or empty";
        }

        if (payload == null || payload.isEmpty()) {
            errorMessage = "Payload is null or empty";
        }

        if (errorMessage != null) {
            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private String createWebSocketAddress(String clientAddress) {
        return "/topic/" + clientAddress;
    }
}
