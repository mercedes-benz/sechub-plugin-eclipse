package com.daimler.sechub.server;

import org.eclipse.core.runtime.preferences.InstanceScope;

import org.eclipse.jface.widgets.WidgetFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

public class SecHubServerView extends ViewPart {
	
    private Label serverUrlLabel;
    private Label serverConnectionLabel;

	private static final String SERVER_PREFERENCES_TEXT_FIELD = "serverUrl";
	private static final String USERNAME_PREFERENCES_TEXT_FIELD = "username";
	private static final String APITOKEN_PREFERENCES_TEXT_FIELD = "apiToken";
	
	private final String SERVER_URL_TEXT = "Server URL: ";
	private final String SERVER_CONNECTION_TEXT = "Server Connection: ";

	@Override
	public void createPartControl(Composite parent) {
        ScopedPreferenceStore scopedPreferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE,
                "sechubplugin.settings.page");
        String string = scopedPreferenceStore.getString(SERVER_PREFERENCES_TEXT_FIELD);
        serverUrlLabel = WidgetFactory.label(SWT.NONE).layoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false)).text(SERVER_URL_TEXT + string).create(parent);
        serverConnectionLabel = WidgetFactory.label(SWT.NONE).layoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false)).text(SERVER_CONNECTION_TEXT + "not checked").create(parent);

        // add change listener to the node so that we are notified
        // in case of changes
        scopedPreferenceStore.addPropertyChangeListener(event -> {
            if (event.getProperty().equals(SERVER_PREFERENCES_TEXT_FIELD)) {
                serverUrlLabel.setText(event.getNewValue().toString());
                checkServerAlive();
            }
        });
		
        scopedPreferenceStore.addPropertyChangeListener(event -> {
            if (event.getProperty().equals(USERNAME_PREFERENCES_TEXT_FIELD) || event.getProperty().equals(USERNAME_PREFERENCES_TEXT_FIELD)) {
            	checkServerAlive();            }
        });
	}

	@Override
	public void setFocus() {}
	
	private void checkServerAlive() {
		// TODO: use new client and replace old jar file

		serverConnectionLabel.setText(SERVER_CONNECTION_TEXT + "placeholder");
	}

}
