// -----------------------------------------------------------------------
// <copyright file="ValidationOperations.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.validations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.microsoft.store.partnercenter.BasePartnerComponentString;
import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.PartnerService;
import com.microsoft.store.partnercenter.models.Address;

/**
 * The validations collection operations implementation.
 */
public class ValidationOperations
    extends BasePartnerComponentString
    implements IValidationOperations
{
    /**
     * Initializes a new instance of the ValidationOperations class.
     * 
     * @param rootPartnerOperations The root partner operations instance.
     */
    public ValidationOperations( IPartner rootPartnerOperations )
    {
        super( rootPartnerOperations );
    }

    /**
     * Checks if the address is valid or not.
     * 
     * @param address The address to be validated.
     * @return True if the address is valid, false otherwise.
     */
    public Boolean isAddressValid(Address address)
    {
        if ( address == null )
        {
            throw new IllegalArgumentException( "The address is a required parameter." );
        }

        return this.getPartner().getServiceClient().post(
            this.getPartner(), 
            new TypeReference<Boolean>(){},
            PartnerService.getInstance().getConfiguration().getApis().get("AddressValidation").getPath(),
            address);
    }
}