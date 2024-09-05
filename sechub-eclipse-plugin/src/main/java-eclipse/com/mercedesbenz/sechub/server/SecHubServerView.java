package com.mercedesbenz.sechub.server;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.widgets.WidgetFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.mercedesbenz.sechub.preferences.PreferenceConstants;

public class SecHubServerView extends ViewPart {
	
    private Label serverUrlLabel;
    private Label serverConnectionLabel;
	
	private final String SERVER_URL_TEXT = "Server URL: ";
	private final String SERVER_CONNECTION_TEXT = "Server Connection: ";
	
	private ScopedPreferenceStore scopedPreferenceStore;

	@Override
	public void createPartControl(Composite parent) {
		String serverURL = readServerURLFromPreferenceStorage();
        serverUrlLabel = WidgetFactory.label(SWT.NONE).layoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false)).text(SERVER_URL_TEXT + serverURL).create(parent);
        serverConnectionLabel = WidgetFactory.label(SWT.NONE).layoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false)).text(SERVER_CONNECTION_TEXT + "not checked").create(parent);

        // add change listener to the node so that we are notified
        // in case of changes
        scopedPreferenceStore.addPropertyChangeListener(event -> {
            if (event.getProperty().equals(PreferenceConstants.SERVER_PREFERENCES_TEXT_FIELD)) {
                serverUrlLabel.setText(SERVER_URL_TEXT + event.getNewValue().toString());
                checkServerAlive();
            }
        });
	}

	@Override
	public void setFocus() {}
	
	private void checkServerAlive() {
		// TODO: use new client and replace old jar file

		serverConnectionLabel.setText(SERVER_CONNECTION_TEXT + "placeholder");
	}
	
	private String readServerURLFromPreferenceStorage() {
		scopedPreferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, PreferenceConstants.SECHUB_PREFERENCES_PAGE);
        String serverURL = scopedPreferenceStore.getString(PreferenceConstants.SERVER_PREFERENCES_TEXT_FIELD);
        return serverURL;
	}

}
