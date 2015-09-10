/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package net.rptools.maptool.client.ui.macrobuttons.panels;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import net.rptools.maptool.client.MapTool;
import net.rptools.maptool.client.ui.macrobuttons.buttongroups.AbstractButtonGroup;
import net.rptools.maptool.client.ui.macrobuttons.buttons.MacroButtonPrefs;
import net.rptools.maptool.model.MacroButtonProperties;

public class OffensePanel extends AbstractMacroPanel {
	public OffensePanel() {
		super();
		setPanelClass("OffensePanel");
		addMouseListener(this);
		init();
	}

	private void init() {		
		if ( MapTool.getPlayer() == null || MapTool.getPlayer().isGM() || MapTool.getServerPolicy().playersReceiveCampaignMacros()) {
			addArea(MapTool.getCampaign().getOffenseMacroButtonPropertiesArray(),"");
		}
	}

	public void reset() {
		clear();
		init();
	}
	
	public static void deleteButtonGroup(String macroGroup) {
		AbstractButtonGroup.clearHotkeys(MapTool.getFrame().getOffensePanel(), macroGroup);
		List<MacroButtonProperties> campProps = MapTool.getCampaign().getOffenseMacroButtonPropertiesArray();
		List<MacroButtonProperties> startingProps = new ArrayList<MacroButtonProperties>(MapTool.getCampaign().getOffenseMacroButtonPropertiesArray());
		campProps.clear();
		for(MacroButtonProperties nextProp : startingProps) {
			if(!macroGroup.equals(nextProp.getGroup())) {
				MapTool.getCampaign().saveOffenseMacroButtonProperty(nextProp);
			}
		}
		MapTool.getFrame().getOffensePanel().reset();
	}
	
	public static void clearPanel() {
		AbstractMacroPanel.clearHotkeys(MapTool.getFrame().getOffensePanel());
		MapTool.getCampaign().getOffenseMacroButtonPropertiesArray().clear();
		MapTool.getFrame().getOffensePanel().reset();
	}
}
