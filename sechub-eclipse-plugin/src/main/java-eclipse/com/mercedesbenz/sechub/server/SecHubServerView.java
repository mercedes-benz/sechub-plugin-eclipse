package com.mercedesbenz.sechub.server;

import org.eclipse.jface.widgets.WidgetFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.mercedesbenz.sechub.preferences.PreferenceStorageAcccess;
import com.mercedesbenz.sechub.preferences.PreferenceConstants;
import com.mercedesbenz.sechub.sechubaccess.SecHubAccess;
import com.mercedesbenz.sechub.sechubaccess.SecHubAccessFactory;

public class SecHubServerView extends ViewPart {
	
    private Label serverUrlLabel;
    private Label serverConnectionLabel;
    private Button serverActiveButton;
    private PreferenceStorageAcccess preferenceAccess;
    
    private static final String SERVER_URL_TEXT = "Server URL: ";
	private static final String SERVER_CONNECTION_TEXT = "Server Connection: ";
    private static final String SERVER_URL_NOT_CONFIGURED = "Server URL not configured";
    private static final String SERVER_CONNECTION_ALIVE = "Connection alive";
    private static final String SERVER_CONNECTION_NOT_ALIVE  = "Connection not alive";
    private static final String BUTTON_TEXT = "check connection";

	@Override
	public void createPartControl(Composite parent) {
		preferenceAccess = new PreferenceStorageAcccess(); 
		String serverURL = preferenceAccess.readServerURLFromPreferenceStorage();
		
		if (serverURL.isEmpty()) {
			serverURL = SERVER_URL_NOT_CONFIGURED;
		}
 		
        setUpLablesAndButton(parent, serverURL);
                
        /* whenever the serverURL changes the check will be executed */
        preferenceAccess.getScopedPreferenceStore().addPropertyChangeListener(event -> {
            if (event.getProperty().equals(PreferenceConstants.SERVER_PREFERENCES_TEXT_FIELD)) {
                serverUrlLabel.setText(SERVER_URL_TEXT + event.getNewValue().toString());
                checkServerAlive();
            }
        });
	}

	private void setUpLablesAndButton(Composite parent, String serverURL) {
 		GridLayout gridLayout = new GridLayout(2, false);
 		parent.setLayout(gridLayout);
 		
		serverUrlLabel = WidgetFactory
        		.label(SWT.NONE)
        		.layoutData(new GridData(SWT.LEFT, SWT.TOP, true, true))
        		.text(SERVER_URL_TEXT + serverURL)
        		.create(parent);
        
        serverConnectionLabel = WidgetFactory
        		.label(SWT.NONE)
        		.layoutData(new GridData(SWT.LEFT, SWT.TOP, true, true))
        		.text(SERVER_CONNECTION_TEXT + "not checked")
        		.create(parent);
        
        serverActiveButton = new Button(parent, SWT.PUSH);
        serverActiveButton.setText(BUTTON_TEXT);
        serverActiveButton.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true));
        serverActiveButton.addListener(SWT.Selection, event -> checkServerAlive());
	}
	
	@Override
	public void setFocus() {
	}
	
	private void checkServerAlive() {
		SecHubAccess secHubAccess = SecHubAccessFactory.create();
		boolean isSecHubAlive = secHubAccess.isSecHubServerAlive();
		updateServerAliveStatus(isSecHubAlive);
	}
	
	private void updateServerAliveStatus(boolean isALive) {
		if (isALive) {
			serverConnectionLabel.setText(SERVER_CONNECTION_TEXT + SERVER_CONNECTION_ALIVE);
		} else {
			serverConnectionLabel.setText(SERVER_CONNECTION_TEXT + SERVER_CONNECTION_NOT_ALIVE);
		}
	}
	


}
