// SPDX-License-Identifier: MIT
package com.daimler.sechub.provider;

import com.daimler.sechub.model.FindingNode;

final class RelevantPartColumnLabelProvider extends AbstractSecHubFindingNodeColumnLabelProvider {
	@Override
	public String getTextForNode(FindingNode node) {
		return node.getRelevantPart();
	}
}