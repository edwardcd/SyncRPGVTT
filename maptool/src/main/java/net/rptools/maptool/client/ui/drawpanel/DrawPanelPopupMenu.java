package net.rptools.maptool.client.ui.drawpanel;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

import net.rptools.maptool.client.AppUtil;
import net.rptools.maptool.client.MapTool;
import net.rptools.maptool.client.ui.zone.ZoneRenderer;
import net.rptools.maptool.model.GUID;
import net.rptools.maptool.model.Token;
import net.rptools.maptool.model.Zone;
import net.rptools.maptool.model.drawing.DrawnElement;

public class DrawPanelPopupMenu extends JPopupMenu {
	
	private static final long serialVersionUID = 8889082158114727461L;
	private final ZoneRenderer renderer;
	private final DrawnElement elementUnderMouse;
	int x, y;
	Set<GUID> selectedDrawSet;
	
	public DrawPanelPopupMenu(Set<GUID> selectedDrawSet, int x, int y, ZoneRenderer renderer, DrawnElement elementUnderMouse) {
		super();
		this.selectedDrawSet = selectedDrawSet;
		this.x = x;
		this.y = y;
		this.renderer = renderer;
		this.elementUnderMouse = elementUnderMouse;

		addGMItem(createChangeToMenu(Zone.Layer.TOKEN, Zone.Layer.GM, Zone.Layer.OBJECT, Zone.Layer.BACKGROUND));
		addGMItem(createArrangeMenu());
		addGMItem(new JSeparator());
		add(new DeleteDrawingAction());
		add(new JSeparator());
		add(new DrawingPropertiesAction());
	}
	
	public class DrawingPropertiesAction extends AbstractAction {
		public DrawingPropertiesAction() {
			super("Properties");
		}
		public void actionPerformed(ActionEvent e) {
			
		}		
	}
	
	public class DeleteDrawingAction extends AbstractAction {
		public DeleteDrawingAction() {
			super("Delete");
		}
		public void actionPerformed(ActionEvent e) {
			// check to see if this is the required action
			if (!MapTool.confirmDrawDelete()) {
				return;
			}
			List<DrawnElement> drawableList = renderer.getZone().getAllDrawnElements();
			Iterator<DrawnElement> iter = drawableList.iterator();
			while (iter.hasNext()) {
				DrawnElement de = iter.next();
				if (selectedDrawSet.contains(de.getDrawable().getId())) {
					renderer.getZone().removeDrawable(de.getDrawable().getId());
					MapTool.serverCommand().undoDraw(renderer.getZone().getId(), de.getDrawable().getId());
				}
			}
			renderer.repaint();
			MapTool.getFrame().updateDrawTree();
			MapTool.getFrame().refresh();
		}
	}

	protected void addGMItem(JMenu menu) {
		if (menu == null) {
			return;
		}
		if (MapTool.getPlayer().isGM()) {
			add(menu);
		}
	}

	protected void addGMItem(JSeparator separator) {
		if (separator == null) {
			return;
		}
		if (MapTool.getPlayer().isGM())
			add(separator);
	}

	protected JMenu createChangeToMenu(Zone.Layer... types) {
		JMenu changeTypeMenu = new JMenu("Change to");
		for (Zone.Layer layer : types) {
			changeTypeMenu.add(new JMenuItem(new ChangeTypeAction(layer)));
		}
		return changeTypeMenu;
	}

	public class ChangeTypeAction extends AbstractAction {
		private final Zone.Layer layer;

		public ChangeTypeAction(Zone.Layer layer) {
			putValue(Action.NAME, layer.toString());
			this.layer = layer;
		}

		public void actionPerformed(ActionEvent e) {
			List<DrawnElement> drawableList = renderer.getZone().getAllDrawnElements();
			Iterator<DrawnElement> iter = drawableList.iterator();
			while (iter.hasNext()) {
				DrawnElement de = iter.next();
				if (de.getDrawable().getLayer()!=this.layer && selectedDrawSet.contains(de.getDrawable().getId())) {
					renderer.getZone().removeDrawable(de.getDrawable().getId());
					MapTool.serverCommand().undoDraw(renderer.getZone().getId(), de.getDrawable().getId());
					de.getDrawable().setLayer(this.layer);
					renderer.getZone().addDrawable(de);
					MapTool.serverCommand().draw(renderer.getZone().getId(), de.getPen(), de.getDrawable());
				}
			}
			MapTool.getFrame().updateDrawTree();
			MapTool.getFrame().refresh();
		}
	}

	protected JMenu createArrangeMenu() {
		JMenu arrangeMenu = new JMenu("Arrange");
		JMenuItem bringToFrontMenuItem = new JMenuItem("Bring to Front");
		bringToFrontMenuItem.addActionListener(new BringToFrontAction());

		JMenuItem sendToBackMenuItem = new JMenuItem("Send to Back");
		sendToBackMenuItem.addActionListener(new SendToBackAction());

		arrangeMenu.add(bringToFrontMenuItem);
		arrangeMenu.add(sendToBackMenuItem);

		return arrangeMenu;
	}

	public class BringToFrontAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			List<DrawnElement> drawableList = renderer.getZone().getAllDrawnElements();
			Iterator<DrawnElement> iter = drawableList.iterator();
			while (iter.hasNext()) {
				DrawnElement de = iter.next();
				if (selectedDrawSet.contains(de.getDrawable().getId())) {
					renderer.getZone().removeDrawable(de.getDrawable().getId());
					MapTool.serverCommand().undoDraw(renderer.getZone().getId(), de.getDrawable().getId());
					renderer.getZone().addDrawable(new DrawnElement(de.getDrawable(), de.getPen()));
					MapTool.serverCommand().draw(renderer.getZone().getId(), de.getPen(), de.getDrawable());
				}
			}
			MapTool.getFrame().updateDrawTree();
			MapTool.getFrame().refresh();
		}
	}

	public class SendToBackAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			List<DrawnElement> drawableList = renderer.getZone().getAllDrawnElements();
			Iterator<DrawnElement> iter = drawableList.iterator();
			while (iter.hasNext()) {
				DrawnElement de = iter.next();
				if (selectedDrawSet.contains(de.getDrawable().getId())) {
					renderer.getZone().removeDrawable(de.getDrawable().getId());
					renderer.getZone().addDrawableRear(de);
				}
			}
			// horrid kludge needed to redraw zone :(
			for (DrawnElement de: renderer.getZone().getAllDrawnElements()) {
				MapTool.serverCommand().undoDraw(renderer.getZone().getId(), de.getDrawable().getId());
				MapTool.serverCommand().draw(renderer.getZone().getId(), de.getPen(), de.getDrawable());
			}
			MapTool.getFrame().updateDrawTree();
			MapTool.getFrame().refresh();
		}
	}

	public void showPopup(JComponent component) {
		show(component, x, y);
	}
}
