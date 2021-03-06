// -----------------------------------------------------------------------
// <copyright file="MessageType.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.models.serviceincidents;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the status of partner center services.
 */
public enum MessageType
{
    /**
     * Default type none.
     */
    NONE("none"),

    /**
     * Active incident.
     */
    INCIDENT("incident"),

    /**
     * Message center message.
     */
    MESSAGE_CENTER("message_center"),

    /**
     * All types.
     */
    ALL("all");

    private final String value;

    MessageType(String value)
    {
        this.value = value;
    }

    /**
     * Converts the object to a string.
     *
     * @return A string that represents this object.
     */
    @JsonValue
    @Override
    public String toString()
    {
        return value;
    }
}
