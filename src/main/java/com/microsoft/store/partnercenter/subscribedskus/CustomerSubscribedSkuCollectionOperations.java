// -----------------------------------------------------------------------
// <copyright file="CustomerSubscribedSkuCollectionOperations.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.subscribedskus;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.microsoft.store.partnercenter.BasePartnerComponent;
import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.PartnerService;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.licenses.LicenseGroupId;
import com.microsoft.store.partnercenter.models.licenses.SubscribedSku;
import com.microsoft.store.partnercenter.models.utils.KeyValuePair;
import com.microsoft.store.partnercenter.utils.StringHelper;

public class CustomerSubscribedSkuCollectionOperations 
		extends BasePartnerComponent<String>
		implements ICustomerSubscribedSkuCollection {

	/**
	 * Initializes a new instance of the CustomerSubscribedSkuCollectionOperations class.
	 * 
	 * @param rootPartnerOperations The root partner operations instance.
	 * @param customerId The customer identifier.
	 */
	public CustomerSubscribedSkuCollectionOperations( IPartner rootPartnerOperations, String customerId )
	{
		super( rootPartnerOperations, customerId );
		if ( StringHelper.isNullOrWhiteSpace( customerId ) )
		{
			throw new IllegalArgumentException( "customerId must be set" );
		}
	}

	/**
	 * Retrieves all the customer subscribed products.
	 * 
	 * @return All the customer subscribed products.
	 */
	public ResourceCollection<SubscribedSku> get() 
	{
		return this.getPartner().getServiceClient().get(
			this.getPartner(),
			new TypeReference<ResourceCollection<SubscribedSku>>(){}, 
			MessageFormat.format(
				PartnerService.getInstance().getConfiguration().getApis().get("GetCustomerSubscribedSkus").getPath(),
				this.getContext()));
	}

	/**
	 * Retrieves all the customer subscribed products.
	 * 
	 * @param licenseGroupIds group identifiers.
	 * @return All the customer subscribed products.
	 */
	public ResourceCollection<SubscribedSku> get(List<LicenseGroupId> licenseGroupIds)
	{
		Collection<KeyValuePair<String, String>> parameters = new ArrayList<KeyValuePair<String, String>>();

		for (LicenseGroupId groupId : licenseGroupIds) 
		{
			parameters.add
			(
				new KeyValuePair<String, String>
				(
					PartnerService.getInstance().getConfiguration().getApis().get("GetCustomerSubscribedSkus").getParameters().get("licenseGroupIds"),
					groupId.toString()
				) 
			);
		}

		return this.getPartner().getServiceClient().get(
			this.getPartner(),
			new TypeReference<ResourceCollection<SubscribedSku>>(){}, 
			MessageFormat.format(
				PartnerService.getInstance().getConfiguration().getApis().get("GetCustomerSubscribedSkus").getPath(),
				this.getContext()),
			parameters);
	}
}