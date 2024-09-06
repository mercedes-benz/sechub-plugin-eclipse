package com.mercedesbenz.sechub.server;

import org.eclipse.jface.widgets.ButtonFactory;
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
    private final String SERVER_URL_TEXT = "Server URL: ";
	private final String SERVER_CONNECTION_TEXT = "Server Connection: ";
	private final String SERVER_BUTTON_TEXT = "Check connection";

	
	private SecHubAccess secHubAccess;

	@Override
	public void createPartControl(Composite parent) {
		preferenceAccess = new PreferenceStorageAcccess(); 
		String serverURL = preferenceAccess.readServerURLFromPreferenceStorage();

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
        serverActiveButton.setText(SERVER_BUTTON_TEXT);
        serverActiveButton.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true));
        serverActiveButton.addListener(SWT.Selection, event -> checkServerAlive());
        
        checkServerAlive();
        
        /* whenever the serverURL changes the check will be executed */
        preferenceAccess.getScopedPreferenceStore().addPropertyChangeListener(event -> {
            if (event.getProperty().equals(PreferenceConstants.SERVER_PREFERENCES_TEXT_FIELD)) {
                serverUrlLabel.setText(SERVER_URL_TEXT + event.getNewValue().toString());
                checkServerAlive();
            }
        });
	}
	
	@Override
	public void setFocus() {
	}
	
	private void checkServerAlive() {
		secHubAccess = SecHubAccessFactory.create();
		boolean isSecHubAlive = secHubAccess.isSecHubServerAlive();
		serverConnectionLabel.setText(SERVER_CONNECTION_TEXT + isSecHubAlive);
	}


}
