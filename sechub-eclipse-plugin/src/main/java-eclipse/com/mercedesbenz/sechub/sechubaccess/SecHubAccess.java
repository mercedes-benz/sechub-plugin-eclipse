package com.mercedesbenz.sechub.sechubaccess;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mercedesbenz.sechub.api.DefaultSecHubClient;
import com.mercedesbenz.sechub.api.SecHubClient;
import com.mercedesbenz.sechub.api.SecHubClientException;

public class SecHubAccess {
	
	private static final Logger LOG = LoggerFactory.getLogger(SecHubAccess.class);
	
    private SecHubClient client;
    public SecHubAccess(String secHubServerUrl, String userId, String apiToken, boolean trustAllCertificates) {
        initSecHubClient(secHubServerUrl, userId, apiToken, trustAllCertificates);
    }

    public boolean isSecHubServerAlive() {
        if (client == null) {
            LOG.debug("SecHub client is not initialized");
            return false;
        }
        try {
            return client.isServerAlive();
        } catch (SecHubClientException e) {
            return false;
        }
    }

    private void initSecHubClient(String secHubServerUrl, String userId, String apiToken, boolean trustAllCertificates) {

        if (isInputMissingOrEmpty(secHubServerUrl, userId, apiToken)) {
            return;
        }
        try {
            URI serverUri = URI.create(secHubServerUrl);

            /* @formatter:off */
            this.client = DefaultSecHubClient.builder()
                    .server(serverUri)
                    .user(userId)
                    .apiToken(apiToken)
                    .trustAll(trustAllCertificates)
                    .build();
            /* @formatter:on */

        } catch (IllegalArgumentException e) {
            LOG.error("Failed to initialize SecHub client", e);
        }
    }

    private boolean isInputMissingOrEmpty(String secHubServerUrl, String userId, String apiToken) {
        return secHubServerUrl.isBlank() || userId == null || apiToken == null;
    }
}
