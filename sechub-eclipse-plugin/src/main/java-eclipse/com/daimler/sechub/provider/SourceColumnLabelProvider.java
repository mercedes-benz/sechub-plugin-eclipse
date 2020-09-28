// SPDX-License-Identifier: MIT
package com.daimler.sechub.provider;

import com.daimler.sechub.model.FindingNode;

final class SourceColumnLabelProvider extends AbstractSecHubFindingNodeColumnLabelProvider {
	@Override
	public String getTextForNode(FindingNode node) {
		String source = node.getSource();
		if (source==null) {
			return "";
		}
		source=source.trim();
		int max = 200;
		if (source.length()>max) {
			source = source.substring(0,max-3)+"...";
		}
		return source;
	}
}