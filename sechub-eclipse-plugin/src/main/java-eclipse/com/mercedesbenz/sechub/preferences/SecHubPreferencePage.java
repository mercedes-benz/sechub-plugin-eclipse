// SPDX-License-Identifier: MIT
package com.mercedesbenz.sechub.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.mercedesbenz.sechub.SecHubActivator;

public class SecHubPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public SecHubPreferencePage() {
		super(GRID);
		setPreferenceStore(SecHubActivator.getDefault().getPreferenceStore());
		setDescription("SecHub connection, project and user specific preferences");
	}

	public void createFieldEditors() {
		addField(new StringFieldEditor(PreferenceConstants.P_HOST, "&Host:", getFieldEditorParent()));
		addField(new IntegerFieldEditor(PreferenceConstants.P_PORT, "&Port:", getFieldEditorParent()));
		addField(new StringFieldEditor(PreferenceConstants.P_PROJECT, "&Project: ", getFieldEditorParent()));
		addField(new StringFieldEditor(PreferenceConstants.P_USERNAME, "&Username:", getFieldEditorParent()));
		addField(new StringFieldEditor(PreferenceConstants.P_APITOKEN, "&API Token:", getFieldEditorParent()) {
			// replace letters with Asterisks to hide the API Token (password)
			@Override
			protected void doFillIntoGrid(Composite parent, int numColumns) {
				super.doFillIntoGrid(parent, numColumns);
				getTextControl().setEchoChar('*');
			}
		});
	}

	public void init(IWorkbench workbench) {
	}

}