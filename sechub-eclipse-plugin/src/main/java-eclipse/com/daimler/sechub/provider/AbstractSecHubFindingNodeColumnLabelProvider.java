// SPDX-License-Identifier: MIT
package com.daimler.sechub.provider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.daimler.sechub.model.FindingNode;

abstract class AbstractSecHubFindingNodeColumnLabelProvider extends ColumnLabelProvider {
	@Override
	public final String getText(Object element) {
		if (element instanceof FindingNode) {
			return getTextForNode((FindingNode) element);
		} else {
			return super.getText(element);
		}
	}

	protected abstract String getTextForNode(FindingNode element);

	protected String getTextForInteger(Integer integer) {
		if (integer == null) {
			return "";
		}
		return String.valueOf(integer);
	}
}