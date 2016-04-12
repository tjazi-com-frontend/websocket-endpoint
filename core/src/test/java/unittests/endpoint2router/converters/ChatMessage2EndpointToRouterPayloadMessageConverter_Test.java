package unittests.endpoint2router.converters;

import com.tjazi.frontend.endpoint.websocket.implementation.endpoint2router.converters.ChatMessage2EndpointToRouterPayloadMessageConverter;
import com.tjazi.frontend.endpoint.websocket.implementation.websocketmessages.ChatMessage;
import com.tjazi.routing.router.messages.endpoint.EndpointToRouterPayloadMessage;
import com.tjazi.routing.router.messages.endpoint.EndpointToRouterPayloadMessageType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * Created by Krzysztof Wasiak on 11/04/2016.
 */
public class ChatMessage2EndpointToRouterPayloadMessageConverter_Test {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void convertChatMessage_nullInput_Test() {
        thrown.expect(IllegalArgumentException.class);

        ChatMessage2EndpointToRouterPayloadMessageConverter.convertChatMessage(null);
    }

    @Test
    public void convertChatMessage_nullOrEmptyTarget_Test() {
        thrown.expect(IllegalArgumentException.class);

        ChatMessage sampleChatMessage = new ChatMessage();
        sampleChatMessage.setPayload("Sample payload");
        sampleChatMessage.setGroupMessage(true);
        sampleChatMessage.setSenderAuthorizationToken(UUID.randomUUID().toString());
        sampleChatMessage.setTargetId(null);

        ChatMessage2EndpointToRouterPayloadMessageConverter.convertChatMessage(sampleChatMessage);
    }

    @Test
    public void convertChatMessage_nullOrEmptyAuthorizationToken_Test() {
        thrown.expect(IllegalArgumentException.class);

        ChatMessage sampleChatMessage = new ChatMessage();
        sampleChatMessage.setPayload("Sample payload");
        sampleChatMessage.setGroupMessage(true);
        sampleChatMessage.setSenderAuthorizationToken(null);
        sampleChatMessage.setTargetId(UUID.randomUUID().toString());

        ChatMessage2EndpointToRouterPayloadMessageConverter.convertChatMessage(sampleChatMessage);
    }

    @Test
    public void convertChatMessage_nullOrEmptyPayload_Test() {
        thrown.expect(IllegalArgumentException.class);

        ChatMessage sampleChatMessage = new ChatMessage();
        sampleChatMessage.setPayload(null);
        sampleChatMessage.setGroupMessage(true);
        sampleChatMessage.setSenderAuthorizationToken(UUID.randomUUID().toString());
        sampleChatMessage.setTargetId(UUID.randomUUID().toString());

        ChatMessage2EndpointToRouterPayloadMessageConverter.convertChatMessage(sampleChatMessage);
    }

    @Test
    public void convertChatMessage_wrongTargetIdFormat_Test() {
        thrown.expect(IllegalArgumentException.class);

        ChatMessage sampleChatMessage = new ChatMessage();
        sampleChatMessage.setPayload("Sample paylod");
        sampleChatMessage.setGroupMessage(true);
        sampleChatMessage.setSenderAuthorizationToken(UUID.randomUUID().toString());
        sampleChatMessage.setTargetId("non UUID target ID");

        ChatMessage2EndpointToRouterPayloadMessageConverter.convertChatMessage(sampleChatMessage);
    }

    @Test
    public void convertChatMessage_allFieldsMoved_GroupMessage_Test() {

        final String messagePayload = "Sample payload " + UUID.randomUUID().toString();
        final String senderAuthorizationToken = "Authorization token" + UUID.randomUUID().toString();
        final boolean isGroupMessage = true;
        final UUID targetId = UUID.randomUUID();

        ChatMessage sampleChatMessage = new ChatMessage();
        sampleChatMessage.setPayload(messagePayload);
        sampleChatMessage.setSenderAuthorizationToken(senderAuthorizationToken);
        sampleChatMessage.setGroupMessage(isGroupMessage);
        sampleChatMessage.setTargetId(targetId.toString());

        EndpointToRouterPayloadMessage converterMessage =
                ChatMessage2EndpointToRouterPayloadMessageConverter.convertChatMessage(sampleChatMessage);

        assertEquals(messagePayload, converterMessage.getPayload());
        assertEquals(EndpointToRouterPayloadMessageType.MULTICAST, converterMessage.getMessageType());
        assertEquals(targetId, converterMessage.getTarget());
    }

    @Test
    public void convertChatMessage_allFieldsMoved_P2PMessage_Test() {

        final String messagePayload = "Sample payload " + UUID.randomUUID().toString();
        final String senderAuthorizationToken = "Authorization token" + UUID.randomUUID().toString();
        final boolean isGroupMessage = false;
        final UUID targetId = UUID.randomUUID();

        ChatMessage sampleChatMessage = new ChatMessage();
        sampleChatMessage.setPayload(messagePayload);
        sampleChatMessage.setSenderAuthorizationToken(senderAuthorizationToken);
        sampleChatMessage.setGroupMessage(isGroupMessage);
        sampleChatMessage.setTargetId(targetId.toString());

        EndpointToRouterPayloadMessage converterMessage =
                ChatMessage2EndpointToRouterPayloadMessageConverter.convertChatMessage(sampleChatMessage);

        assertEquals(messagePayload, converterMessage.getPayload());
        assertEquals(EndpointToRouterPayloadMessageType.PER2PER, converterMessage.getMessageType());
        assertEquals(targetId, converterMessage.getTarget());
    }

}
