package com.mercedesbenz.sechub.preferences;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.equinox.security.storage.ISecurePreferences;
import org.eclipse.equinox.security.storage.SecurePreferencesFactory;
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
	
	private ISecurePreferences preferences;
	private ISecurePreferences node;

	public SechubPreferencePage() {
		super(GRID);
	}
	
	@Override
    public void init(IWorkbench workbench) {
        setPreferenceStore(new ScopedPreferenceStore(InstanceScope.INSTANCE, PreferenceConstants.SECHUB_PREFERENCES_PAGE));
        preferences = SecurePreferencesFactory.getDefault();
        node = preferences.node(PreferenceConstants.SECURE_STORAGE_NODE);
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
			 String username = readSecureStorageUsername();
			 String apitoken = readSecureStorageApitoken();
			 usernameField.setStringValue(username);
			 apiTokenField.setStringValue(apitoken);
			 
		 }catch (StorageException e) {
			 usernameField.setStringValue("");
			 apiTokenField.setStringValue("");
		 }
	}

	
	 @Override
	 public boolean performOk() {
		 serverUrlField.store();
		 
		 String username = usernameField.getStringValue();
		 String apitoken = apiTokenField.getStringValue();
		 try {
			 storeSecureStorage(username, apitoken);
		 }catch (StorageException e) {
			 return false;
		 }
		 return true;
	 }
    
    private void storeSecureStorage(String username, String password) throws StorageException  {
        node.put(PreferenceConstants.USERNAME_PREFERENCES_TEXT_FIELD, username, true);
        node.put(PreferenceConstants.APITOKEN_PREFERENCES_TEXT_FIELD, password, true);
    }
    
    private String readSecureStorageUsername() throws StorageException {
    	String username = node.get(PreferenceConstants.USERNAME_PREFERENCES_TEXT_FIELD, "Sechub Username");
    	return username;
    }
    
    private String readSecureStorageApitoken() throws StorageException {
    	String apitoken = node.get(PreferenceConstants.APITOKEN_PREFERENCES_TEXT_FIELD, "Sechub ApiToken");
    	return apitoken;
    }
    
}
	
