package com.tjazi.frontend.endpoint.websocket.implementation.router2endpoint;

import com.tjazi.routing.endpoint.template.coreinterfaces.IRoutingEndpointCoreImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Krzysztof Wasiak on 06/04/2016.
 *
 * Router to Endpoint interface implementation.
 * This will be called from the Endpoint module, once message from router arrives
 */
@Service
public class WebSocketEndpointImplementation implements IRoutingEndpointCoreImplementation {

    private final static Logger log = LoggerFactory.getLogger(WebSocketEndpointImplementation.class);

    @Autowired
    private ClientRouter clientRouter;

    @Override
    public void passPayloadToClients(List<String> clientAddresses, String payload) {
        log.debug("Got payload from router. Payload size: {}, number of client addresses: {}",
                payload.length(), clientAddresses.size());

        clientRouter.passPayloadToClients(clientAddresses, payload);
    }
}
