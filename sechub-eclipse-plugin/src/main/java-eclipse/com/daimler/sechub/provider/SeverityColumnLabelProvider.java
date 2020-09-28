// SPDX-License-Identifier: MIT
package com.daimler.sechub.provider;

import com.daimler.sechub.commons.model.Severity;
import com.daimler.sechub.model.FindingNode;

final class SeverityColumnLabelProvider extends AbstractSecHubFindingNodeColumnLabelProvider {
	@Override
	public String getTextForNode(FindingNode node) {
		Severity severity = node.getSeverity();
		if (severity == null) {
			return "";
		}
		return severity.toString();
	}
}