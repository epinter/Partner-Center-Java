// -----------------------------------------------------------------------
// <copyright file="OfferCountrySelector.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.offers;

import com.microsoft.store.partnercenter.BasePartnerComponentString;
import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.genericoperations.ICountrySelector;
import com.microsoft.store.partnercenter.utils.ParameterValidator;

public class OfferCountrySelector 
	extends BasePartnerComponentString 
	implements ICountrySelector<IOfferCollection>
{

	/**
	 * Initializes a new instance of the OfferCountrySelector class.
	 * @param rootPartnerOperations The root partner operations instance.
	 */
	public OfferCountrySelector( IPartner rootPartnerOperations ) 
	{
		super(rootPartnerOperations);
	}

	/**
	 * Scopes offers behavior to a specific country.
	 */
	@Override
	public IOfferCollection byCountry( String country )
	{
		ParameterValidator.isValidCountryCode( country );

        return new OfferCollectionOperations( this.getPartner(), country );
	}

}
