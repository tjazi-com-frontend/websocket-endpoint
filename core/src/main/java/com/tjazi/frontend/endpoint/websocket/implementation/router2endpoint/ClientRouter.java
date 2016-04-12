package com.tjazi.frontend.endpoint.websocket.implementation.router2endpoint;

import java.util.List;

/**
 * Created by Krzysztof Wasiak on 08/04/2016.
 */
public interface ClientRouter {

    void passPayloadToClients(List<String> clientAddresses, String payload);
}
