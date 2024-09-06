package com.mercedesbenz.sechub.sechubaccess;

import org.eclipse.equinox.security.storage.StorageException;
import com.mercedesbenz.sechub.preferences.PreferenceStorageAcccess;
import com.mercedesbenz.sechub.preferences.SecureStorageAccess;

public class SecHubAccessFactory {
	
    public static SecHubAccess create() {
    	PreferenceStorageAcccess preferenceAccess = new PreferenceStorageAcccess();
    	SecureStorageAccess secureStorageAccess = new SecureStorageAccess();
    	
    	String serverURL = preferenceAccess.readServerURLFromPreferenceStorage();
    	String username;
    	String apiToken;
    	
		try {
			username = secureStorageAccess.readSecureStorageApitoken();
			apiToken = secureStorageAccess.readSecureStorageApitoken();
		} catch (StorageException e) {
			username = "";
			apiToken = "";
		}

        return new SecHubAccess(serverURL, username, apiToken, true);
    }
}
