package com.daimler.sechub.settings;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

public class SechubPreferencesPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage  {

	public SechubPreferencesPage() {
		super(GRID);
	}
		
    public void createFieldEditors() {
        addField(new StringFieldEditor("serverUrl", "Server URL:", getFieldEditorParent()));
        addField(new StringFieldEditor("username", "Username", getFieldEditorParent()));
        StringFieldEditor passwordField = new StringFieldEditor("apiToken", "API Token:", getFieldEditorParent());
        passwordField.getTextControl(getFieldEditorParent()).setEchoChar('*');
        addField(passwordField);
    }

    
    public void init(IWorkbench workbench) {
        // second parameter is typically the plug-in id
        setPreferenceStore(new ScopedPreferenceStore(InstanceScope.INSTANCE, "eclipsetestplugin.settings.pageid"));
        setDescription("A demonstration of a preference page implementation");
    }
}
	
