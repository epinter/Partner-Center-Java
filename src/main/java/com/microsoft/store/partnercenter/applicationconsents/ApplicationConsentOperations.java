// -----------------------------------------------------------------------
// <copyright file="ApplicationConsentOperations.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.applicationconsents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.microsoft.store.partnercenter.BasePartnerComponent;
import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.PartnerService;
import com.microsoft.store.partnercenter.models.applicationconsents.ApplicationConsent;
import com.microsoft.store.partnercenter.models.roles.DirectoryRole;
import com.microsoft.store.partnercenter.models.utils.Tuple;
import com.microsoft.store.partnercenter.utils.StringHelper;

import java.text.MessageFormat;

public class ApplicationConsentOperations
        extends BasePartnerComponent<Tuple<String, String>>
        implements IApplicationConsent
{
    /**
     * Initializes a new instance of the ApplicationConsentOperations class.
     *
     * @param rootPartnerOperations The partner operations instance.
     * @param customerId            The customer identifier.
     * @param applicationId         The application identifier.
     */
    public ApplicationConsentOperations(IPartner rootPartnerOperations, String customerId, String applicationId)
    {
        super(rootPartnerOperations, new Tuple<>(customerId, applicationId));
        if (StringHelper.isNullOrEmpty(customerId))
        {
            throw new IllegalArgumentException("customerId must be set.");
        }

        if (StringHelper.isNullOrEmpty(applicationId))
        {
            throw new IllegalArgumentException("applicationId must be set.");
        }
    }

    /**
     * Remove application consent.
     */
    @Override
    public void delete()
    {
        this.getPartner().getServiceClient().delete(
                this.getPartner(),
                new TypeReference<ApplicationConsent>() {},
                MessageFormat.format(PartnerService.getInstance().getConfiguration().getApis()
                                .get("DeleteCustomerApplicationConsent").getPath(),
                        this.getContext().getItem1(),
                        this.getContext().getItem2()));
    }
}
