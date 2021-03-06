// -----------------------------------------------------------------------
// <copyright file="PartnerCredentials.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.extensions;

import com.microsoft.store.partnercenter.AuthenticationToken;
import com.microsoft.store.partnercenter.IAadLoginHandler;
import com.microsoft.store.partnercenter.IPartnerCredentials;
import com.microsoft.store.partnercenter.requestcontext.IRequestContext;
import com.microsoft.store.partnercenter.utils.StringHelper;

/**
 * The third party partner credentials. Use to generate the credentials.
 */
public class PartnerCredentials
{
    /**
     * A singleton instance of the partner credentials.
     */
    private static PartnerCredentials instance = new PartnerCredentials();

    /**
     * Prevents a default instance of the PartnerCredentials class from being created.
     */
    private PartnerCredentials()
    {
    }

    /**
     * Gets an instance of the partner credentials.
     * 
     * @return An instance of the partner credentials.
     */
    public static PartnerCredentials getInstance()
    {
        return PartnerCredentials.instance;
    }
    
    /**
     * Generates partner credentials for third party applications using application credentials.
     *
     * @param clientId The client id of the application in Azure Active Directory.
     * @param applicationSecret The application secret with Azure Active Directory.
     * @param aadApplicationDomain The application domain in Azure Active Directory.
     * @return The partner service credentials.
     */
    public IPartnerCredentials generateByApplicationCredentials( String clientId, String applicationSecret,
                                                                        String aadApplicationDomain )
    {
        return PartnerCredentials.getInstance().generateByApplicationCredentials( clientId, applicationSecret, aadApplicationDomain,
                                                                    null );
    }

    /**
     * Generates partner credentials for third party applications using application credentials.
     *
     * @param clientId The client id of the application in Azure Active Directory.
     * @param applicationSecret The application secret with Azure Active Directory.
     * @param aadApplicationDomain The application domain in Azure Active Directory.
     * @param requestContext The request context.
     * @return The partner service credentials.
     */
    public IPartnerCredentials generateByApplicationCredentials( String clientId, String applicationSecret,
                                                                        String aadApplicationDomain,
                                                                        IRequestContext requestContext )
    {
        ApplicationPartnerCredentials partnerCredentials =
            new ApplicationPartnerCredentials( clientId, applicationSecret, aadApplicationDomain );
        partnerCredentials.authenticate( requestContext );

        return partnerCredentials;
    }

    /**
     * Generates partner credentials for third party applications using a user Azure Active Directory token.
     * 
     * @param clientId The client id of the application in Azure Active Directory.
     * @param authenticationToken The Azure Active Directory token.
     * @param loginHandler An optional delegate which will be called when the Azure Active Directory token expires and
     *            can no longer be used to generate the partner credentials. This delegate should return an up to date
     *            Azure Active Directory token.
     * @return The partner service credentials.
     */
    public IPartnerCredentials generateByUserCredentials( String clientId,
                                                                 AuthenticationToken authenticationToken,
                                                                 IAadLoginHandler loginHandler )
    {
        final UserPartnerCredentials partnerCredentials =
            new UserPartnerCredentials( clientId, authenticationToken, loginHandler );
        return partnerCredentials;
    }

    /**
     * Generates partner credentials for third party applications using application credentials with the provided AAD
     * overrides.
     *
     * @param clientId The client id of the application in Azure Active Directory.
     * @param applicationSecret The application secret with Azure Active Directory.
     * @param aadApplicationDomain The application domain in Azure Active Directory.
     * @param aadAuthorityEndpoint The active directory authority endpoint.
     * @param graphEndpoint The AAD graph API endpoint.
     * @return The partner service credentials.
     */
    public IPartnerCredentials generateByApplicationCredentials( String clientId, String applicationSecret,
                                                                        String aadApplicationDomain,
                                                                        String aadAuthorityEndpoint,
                                                                        String graphEndpoint )
    {
        return PartnerCredentials.getInstance().generateByApplicationCredentials( clientId, applicationSecret, aadApplicationDomain,
                                                                    aadAuthorityEndpoint, graphEndpoint, null );
    }

    public IPartnerCredentials generateByApplicationCredentials( String clientId, String applicationSecret,
                                                                        String aadApplicationDomain,
                                                                        String aadAuthorityEndpoint,
                                                                        String graphEndpoint,
                                                                        IRequestContext requestContext )
    {
        if ( StringHelper.isNullOrWhiteSpace( aadAuthorityEndpoint ) )
        {
            throw new IllegalArgumentException( "aadAuthorityEndpoint can't be empty" );
        }
        if ( StringHelper.isNullOrWhiteSpace( graphEndpoint ) )
        {
            throw new IllegalArgumentException( "graphEndpoint can't be empty" );
        }

        ApplicationPartnerCredentials partnerCredentials =
            new ApplicationPartnerCredentials( clientId, applicationSecret, aadApplicationDomain, aadAuthorityEndpoint,
                                               graphEndpoint );
        partnerCredentials.authenticate( requestContext );

        return partnerCredentials;
    }
}