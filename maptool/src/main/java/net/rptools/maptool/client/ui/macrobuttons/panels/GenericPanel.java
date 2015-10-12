/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.rptools.maptool.client.ui.macrobuttons.panels;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import net.rptools.maptool.client.MapTool;
import net.rptools.maptool.client.ui.MapToolFrame.MTFrame;
import net.rptools.maptool.client.ui.macrobuttons.buttongroups.AbstractButtonGroup;
import net.rptools.maptool.client.ui.macrobuttons.buttons.MacroButtonPrefs;
import net.rptools.maptool.model.MacroButtonProperties;

public class GenericPanel extends AbstractMacroPanel {
	public GenericPanel() {
		super();
		setPanelClass("GenericPanel");
		addMouseListener(this);
		init();
	}

	private void init() {
		if (MapTool.getPlayer() == null || MapTool.getPlayer().isGM() || MapTool.getServerPolicy().playersReceiveCampaignMacros()) {
			addArea(MapTool.getCampaign().getGenericMacroButtonPropertiesArray(), "");
		}
	}

	public void reset() {
		clear();
		init();
	}

	public static void deleteButtonGroup(String macroGroup) {
		AbstractButtonGroup.clearHotkeys(MapTool.getFrame().getGenericPanel(), macroGroup);
		List<MacroButtonProperties> campProps = MapTool.getCampaign().getGenericMacroButtonPropertiesArray();
		List<MacroButtonProperties> startingProps = new ArrayList<MacroButtonProperties>(MapTool.getCampaign().getGenericMacroButtonPropertiesArray());
		campProps.clear();
		for (MacroButtonProperties nextProp : startingProps) {
			if (!macroGroup.equals(nextProp.getGroup())) {
				MapTool.getCampaign().saveGenericMacroButtonProperty(nextProp);
			}
		}
		MapTool.getFrame().getGenericPanel().reset();
	}

	public static void clearPanel() {
		AbstractMacroPanel.clearHotkeys(MapTool.getFrame().getGenericPanel());
		MapTool.getCampaign().getGenericMacroButtonPropertiesArray().clear();
		MapTool.getFrame().getGenericPanel().reset();
	}
}
