package com.daimler.sechub.settings;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.equinox.security.storage.ISecurePreferences;
import org.eclipse.equinox.security.storage.SecurePreferencesFactory;
import org.eclipse.equinox.security.storage.StorageException;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

public class SechubPreferencesPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage  {
	
	private static final String SERVER_PREFERENCES_TEXT_FIELD = "serverUrl";
	private static final String USERNAME_PREFERENCES_TEXT_FIELD = "username";
	private static final String APITOKEN_PREFERENCES_TEXT_FIELD = "apiToken";
	
	private static final String SECURE_STORAGE_NODE = "sechubSecureSTorage";
	
	private StringFieldEditor serverUrlField;
	private StringFieldEditor usernameField;
	private StringFieldEditor apiTokenField;

	public SechubPreferencesPage() {
		super(GRID);
	}
		
    public void createFieldEditors() {
    	serverUrlField = new StringFieldEditor(SERVER_PREFERENCES_TEXT_FIELD, "Server URL:", getFieldEditorParent());
    	usernameField = new StringFieldEditor(USERNAME_PREFERENCES_TEXT_FIELD, "Username", getFieldEditorParent());
        
        apiTokenField = new StringFieldEditor(APITOKEN_PREFERENCES_TEXT_FIELD, "API Token:", getFieldEditorParent());
        apiTokenField.getTextControl(getFieldEditorParent()).setEchoChar('*');
        addField(serverUrlField);
        addField(usernameField);
        addField(apiTokenField);
        
       // storeSecureStorage();
    }

    
    public void init(IWorkbench workbench) {
        // second parameter is typically the plug-in id
        setPreferenceStore(new ScopedPreferenceStore(InstanceScope.INSTANCE, "sechubplugin.settings.page"));
    }
    
    private void storeSecureStorage() {
    	// TODO: when are the variables saved and read from preferences -> should not be saved in InstanceSCope but in sec storage
    	ISecurePreferences preferences = SecurePreferencesFactory
                .getDefault();
    	ISecurePreferences node = preferences.node(SECURE_STORAGE_NODE);
    	  try {
              node.put(USERNAME_PREFERENCES_TEXT_FIELD, usernameField.getTextControl(getFieldEditorParent()).getText() , true);
              node.put(APITOKEN_PREFERENCES_TEXT_FIELD, apiTokenField.getTextControl(getFieldEditorParent()).getText(), true);
          } catch (StorageException e) {
              e.printStackTrace();
          } 
    }
}
	
