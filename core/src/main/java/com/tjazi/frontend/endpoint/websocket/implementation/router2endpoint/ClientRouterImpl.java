package com.tjazi.frontend.endpoint.websocket.implementation.router2endpoint;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Krzysztof Wasiak on 08/04/2016.
 */
@Service
public class ClientRouterImpl implements ClientRouter {

    @Override
    public void passPayloadToClients(List<String> clientAddresses, String payload) {

    }
}
