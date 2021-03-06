// -----------------------------------------------------------------------
// <copyright file="CustomerLicensesUsageInsights.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.models.analytics;

/**
 * SDK business object model for Insights of license usage at customer level.
 */
public class CustomerLicensesUsageInsights
{
    /**
     * Gets or sets the Workload Code. (Example : Exchange - EXO).
     */
    private String workloadCode;

    public String getWorkloadCode()
    {
        return workloadCode;
    }

    public void setWorkloadCode( String value )
    {
        workloadCode = value;
    }

    /**
     * Gets or sets the Workload name (Example: Exchange).
     */
    private String workloadName;

    public String getWorkloadName()
    {
        return workloadName;
    }

    public void setWorkloadName( String value )
    {
        workloadName = value;
    }

    /**
     * Gets or sets the total active seats as of processed time stamp.
     */
    private long licensesActive;

    public long getLicensesActive()
    {
        return licensesActive;
    }

    public void setLicensesActive( long value )
    {
        licensesActive = value;
    }

    /**
     * Gets or sets the total qualified entitlements as of processed time stamp.
     */
    private long licensesQualified;

    public long getLicensesQualified()
    {
        return licensesQualified;
    }

    public void setLicensesQualified( long value )
    {
        licensesQualified = value;
    }

    /**
     * Gets or sets the usage percent of the given workload.
     */
    private long usagePrecent;

    public long getUsagePercent()
    {
        return usagePrecent;
    }

    public void setUsagePercent( long value )
    {
        usagePrecent = value;
    }
}