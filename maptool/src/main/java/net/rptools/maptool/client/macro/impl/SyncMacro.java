/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package net.rptools.maptool.client.macro.impl;

import net.rptools.maptool.client.AppActions;
import net.rptools.maptool.client.MapTool;
import net.rptools.maptool.client.MapToolMacroContext;
import net.rptools.maptool.client.macro.MacroContext;
import net.rptools.maptool.client.macro.MacroDefinition;
import net.rptools.maptool.client.ui.MapToolFrame;
import net.rptools.maptool.model.Token;

@MacroDefinition(
                name = "sync",
                aliases = { "tokensync" },
                description = "sync.description")

public class SyncMacro extends AbstractMacro {
    public void execute(MacroContext context, String macro, MapToolMacroContext executionContext) {
        //macro = processText(macro);		// jmoskie: commented this out...
        final MapToolFrame frame = MapTool.getFrame();

        StringBuilder sb = new StringBuilder();

        String name = macro;
        int index = macro.indexOf(":");
        if (index > 0) {
            if (macro.substring(0, index).equalsIgnoreCase("lib")) {
                index = macro.indexOf(":", index + 1);
            }
        }
        if (index > 0) {
            name = macro.substring(0, index).trim();
            macro = macro.substring(index + 1);
        }
        Token token = frame.getCurrentZoneRenderer().getZone().resolveToken(name);
        if (token != null) {
            name = token.getName();
        }

        AppActions.syncToken(token);
    }
}
