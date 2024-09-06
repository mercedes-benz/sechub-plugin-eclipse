package com.mercedesbenz.sechub.preferences;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

public class PreferenceStorageAcccess {
	
	private ScopedPreferenceStore scopedPreferenceStore;
	
	public PreferenceStorageAcccess() {
		this.scopedPreferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, PreferenceConstants.SECHUB_PREFERENCES_PAGE);
	}
	
	public ScopedPreferenceStore getScopedPreferenceStore() {
		return scopedPreferenceStore; 
	}

	public String readServerURLFromPreferenceStorage() {
        String serverURL = scopedPreferenceStore.getString(PreferenceConstants.SERVER_PREFERENCES_TEXT_FIELD);
        return serverURL;
	}
}
