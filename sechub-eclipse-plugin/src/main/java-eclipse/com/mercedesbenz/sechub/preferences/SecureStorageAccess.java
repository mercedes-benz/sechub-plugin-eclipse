package com.mercedesbenz.sechub.preferences;

import org.eclipse.equinox.security.storage.ISecurePreferences;
import org.eclipse.equinox.security.storage.SecurePreferencesFactory;
import org.eclipse.equinox.security.storage.StorageException;

public class SecureStorageAccess {
	
	private ISecurePreferences preferences;
	private ISecurePreferences node;
	
	public SecureStorageAccess() {
        preferences = SecurePreferencesFactory.getDefault();
        node = preferences.node(PreferenceConstants.SECURE_STORAGE_NODE);
	}
	
	public ISecurePreferences getSecurePreferences() {
		return preferences;
	}

    public void storeSecureStorage(String username, String password) throws StorageException  {
        node.put(PreferenceConstants.USERNAME_PREFERENCES_TEXT_FIELD, username, true);
        node.put(PreferenceConstants.APITOKEN_PREFERENCES_TEXT_FIELD, password, true);
    }
    
    public String readSecureStorageUsername() throws StorageException {
    	String username = node.get(PreferenceConstants.USERNAME_PREFERENCES_TEXT_FIELD, "Sechub Username");
    	return username;
    }
    
    public String readSecureStorageApitoken() throws StorageException {
    	String apitoken = node.get(PreferenceConstants.APITOKEN_PREFERENCES_TEXT_FIELD, "Sechub ApiToken");
    	return apitoken;
    }
}
