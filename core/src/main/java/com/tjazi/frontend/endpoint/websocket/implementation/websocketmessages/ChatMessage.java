package com.tjazi.frontend.endpoint.websocket.implementation.websocketmessages;

/**
 * Created by Krzysztof Wasiak on 08/04/2016.
 */
public class ChatMessage {

    /**
     * This is payload of the message.
     */
    public String payload;

    /**
     * This is token, which can be checked. If Authorization token is invalid or inactive,
     * the processing will stop with error.
     */
    public String senderAuthorizationToken;

    /**
     * An indicator of whenever this message target should be considered as a group
     */
    public boolean groupMessage;

    /**
     * ID of the receiver - either group or individual
     */
    public String targetId;

    //
    // getters & setters
    //

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getSenderAuthorizationToken() {
        return senderAuthorizationToken;
    }

    public void setSenderAuthorizationToken(String senderAuthorizationToken) {
        this.senderAuthorizationToken = senderAuthorizationToken;
    }

    public boolean isGroupMessage() {
        return groupMessage;
    }

    public void setGroupMessage(boolean groupMessage) {
        this.groupMessage = groupMessage;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
}
