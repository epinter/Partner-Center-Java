// -----------------------------------------------------------------------
// <copyright file="CustomerProductCollectionByTargetViewByTargetSegmentOperations.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.customers.products;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.microsoft.store.partnercenter.BasePartnerComponent;
import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.PartnerService;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.products.Product;
import com.microsoft.store.partnercenter.models.utils.KeyValuePair;
import com.microsoft.store.partnercenter.models.utils.TripletTuple;
import com.microsoft.store.partnercenter.utils.StringHelper;

/**
 * Product operations by customer id, by target view and by target segment implementation class.
 */
public class CustomerProductCollectionByTargetViewByTargetSegmentOperations
	extends BasePartnerComponent<TripletTuple<String, String, String>>
	implements ICustomerProductCollectionByTargetViewByTargetSegment
{
	/**
	 * Initializes a new instance of the CustomerProductCollectionByTargetViewByTargetSegmentOperations class.
	 * 
	 * @param rootPartnerOperations The root partner operations instance.
	 * @param customerId Identifier for the customer.
	 * @param targetView The target view which contains the products.
	 * @param targetSegment The target segment used for filtering the products. 
	 */
	public CustomerProductCollectionByTargetViewByTargetSegmentOperations( IPartner rootPartnerOperations, String customerId, String targetView, String targetSegment )
	{
		super( rootPartnerOperations, new TripletTuple<String, String, String>( customerId, targetView, targetSegment ) );

		if ( StringHelper.isNullOrWhiteSpace( customerId ) )
		{
			throw new IllegalArgumentException( "customerId must be set" );
		}

		if ( StringHelper.isNullOrWhiteSpace( targetView ) )
		{
			throw new IllegalArgumentException( "targetView must be set" );
		}

		if ( StringHelper.isNullOrWhiteSpace( targetSegment ) )
		{
			throw new IllegalArgumentException( "targetSegment must be set" );
		}
	}

	/**
	 * Retrieves all the products in a given catalog view and that apply to a given customer, filtered by target segment.
	 * 
	 * @return The products in a given catalog view and that apply to a given customer, filtered by target segment.
	 */
	@Override
	public ResourceCollection<Product> get()
	{
		Collection<KeyValuePair<String, String>> parameters = new ArrayList<KeyValuePair<String, String>>();

		parameters.add
		(
			new KeyValuePair<String, String>
			(
				PartnerService.getInstance().getConfiguration().getApis().get("GetCustomerProducts").getParameters().get("TargetSegment"),
				this.getContext().getItem2()
			) 
		);

		parameters.add
		(
			new KeyValuePair<String, String>
			(
				PartnerService.getInstance().getConfiguration().getApis().get("GetCustomerProducts").getParameters().get("TargetView"),
				this.getContext().getItem3()
			) 
		);

		return this.getPartner().getServiceClient().get(
			this.getPartner(),
			new TypeReference<ResourceCollection<Product>>(){}, 
			MessageFormat.format( 
				PartnerService.getInstance().getConfiguration().getApis().get("GetCustomerProducts").getPath(),
				this.getContext().getItem1()),
			parameters);
	}
}