/*
 * This software copyright by various authors including the RPTools.net
 * development team, and licensed under the LGPL Version 3 or, at your option,
 * any later version.
 *
 * Portions of this software were originally covered under the Apache Software
 * License, Version 1.1 or Version 2.0.
 *
 * See the file LICENSE elsewhere in this distribution for license details.
 */

package net.rptools.maptool.client.ui.assetpanel;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 */
public class AssetTreeCellRenderer extends DefaultTreeCellRenderer {

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		setBorder(null);

		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		if (value instanceof Directory) {
			setText(((Directory) value).getPath().getName());
		}

		if (row == 0) {
			setIcon(null);
		}

		return this;
	}

}
