package com.mercedesbenz.sechub.preferences;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.equinox.security.storage.StorageException;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

public class SechubPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage  {
	
	private StringFieldEditor serverUrlField;
	private StringFieldEditor usernameField;
	private StringFieldEditor apiTokenField;
	
	private SecureStorageAccess secureStorageAccess;

	public SechubPreferencePage() {
		super(GRID);
	}
	
	@Override
    public void init(IWorkbench workbench) {
        setPreferenceStore(new ScopedPreferenceStore(InstanceScope.INSTANCE, PreferenceConstants.SECHUB_PREFERENCES_PAGE));
        secureStorageAccess = new SecureStorageAccess();
	}
    
	@Override
    public void createFieldEditors() {
    	serverUrlField = new StringFieldEditor(PreferenceConstants.SERVER_PREFERENCES_TEXT_FIELD, "Server URL:", getFieldEditorParent());
    	usernameField = new StringFieldEditor(PreferenceConstants.USERNAME_PREFERENCES_TEXT_FIELD, "Username", getFieldEditorParent());
        
        apiTokenField = new StringFieldEditor(PreferenceConstants.APITOKEN_PREFERENCES_TEXT_FIELD, "API Token:", getFieldEditorParent());
        apiTokenField.getTextControl(getFieldEditorParent()).setEchoChar('*');
        addField(serverUrlField);
        addField(usernameField);
        addField(apiTokenField);
       
     }
	
	@Override
	public void initialize() {
		super.initialize();
		
		 try {
			 String username = secureStorageAccess.readSecureStorageUsername();
			 String apitoken = secureStorageAccess.readSecureStorageApitoken();
			 usernameField.setStringValue(username);
			 apiTokenField.setStringValue(apitoken);
			 
		 }catch (StorageException e) {
			 usernameField.setStringValue("");
			 apiTokenField.setStringValue("");
		 }
	}

	
	 @Override
	 public boolean performOk() {
		 try {
			 validateServerURL();
		 }catch (URISyntaxException e) {
			 serverUrlField.setFocus();
			 serverUrlField.setErrorMessage("Please enter a valid URI");
			 serverUrlField.showErrorMessage();
			 return false;
		 }
		 addHttpsProtocol();
		 
		 serverUrlField.store();
		 
		 String username = usernameField.getStringValue();
		 String apitoken = apiTokenField.getStringValue();
		 try {
			 secureStorageAccess.storeSecureStorage(username, apitoken);
		 }catch (StorageException e) {
			 return false;
		 }
		 return true;
	 }
	 
	 private void validateServerURL() throws URISyntaxException {
		new URI(serverUrlField.getStringValue());
	 }
	 
	 private void addHttpsProtocol() {
		String url = serverUrlField.getStringValue();
		if (url.startsWith("http://") || url.startsWith("https://") || url.isBlank()) {
			return;
		}
		url = "https://" + url;
		serverUrlField.setStringValue(url);
	 }
}
	
