// -----------------------------------------------------------------------
// <copyright file="VettingStatus.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.models.partners;

import com.fasterxml.jackson.annotation.JsonValue;

public enum VettingStatus
{
    /**
     * None vetting status
     */
    NONE("none"),

    /**
     * Pending vetting status
     */
    PENDING("pending"),

    /**
     * Authorized vetting status
     */
    AUTHORIZED("authorized"),

    /**
     * Rejected vetting status
     */
    REJECTED("rejected");

    private final String value;

    VettingStatus(String value)
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