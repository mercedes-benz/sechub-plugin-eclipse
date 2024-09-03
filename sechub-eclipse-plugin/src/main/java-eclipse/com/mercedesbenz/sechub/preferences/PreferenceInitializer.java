// SPDX-License-Identifier: MIT
package com.mercedesbenz.sechub.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.mercedesbenz.sechub.SecHubActivator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	public void initializeDefaultPreferences() {
		IPreferenceStore store = SecHubActivator.getDefault().getPreferenceStore();
		
		// set the port by default to 443 (usually HTTPS).
		store.setDefault(PreferenceConstants.P_PORT, 443);
	}

}
