// ----------------------------------------------------------------
// <copyright file="BillingProvider.java" company="Microsoft Corporation">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// ----------------------------------------------------------------

package com.microsoft.store.partnercenter.models.invoices;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BillingProvider
{
    /**
     * Different providers of billing information Enum initializer
     */
    NONE("none"),

    /**
    * Bill is provided by Office. Example: O365, and In-tune.
    */
    OFFICE("office"),

    /**
    * Bill is provided by Azure. Example: Azure Services.
    */
    AZURE("azure"),

    /**
    * Bill is provided by Azure Data Market.
    */
    AZURE_DATA_MARKET("azureDataMarket"),

    /**
    * Bill is provided for one time purchases.
    */
    ONE_TIME("oneTime");

    private final String value;

    BillingProvider(String value)
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
