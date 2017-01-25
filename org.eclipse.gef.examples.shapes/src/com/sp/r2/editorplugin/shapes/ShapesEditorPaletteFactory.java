/*******************************************************************************
 * Copyright (c) 2004, 2010 Elias Volanakis and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Elias Volanakis - initial API and implementation
 *******************************************************************************/
package com.sp.r2.editorplugin.shapes;

import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;

import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteToolbar;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;

import com.sp.r2.editorplugin.model.Model;
import com.sp.r2.editorplugin.xml.ActorRootElement;

/**
 * Utility class that can create a GEF Palette.
 * 
 * @see #createPalette()
 * @author Elias Volanakis
 */
public class ShapesEditorPaletteFactory {

	public static PaletteComponent selectedComponent;

	private static PaletteDrawer PALETTE_DRAWER;

	/** Create the "Shapes" drawer. */
	public static PaletteContainer populatePaletteView(
			Map<String, ActorRootElement> componentData) {

		for (Map.Entry<String, ActorRootElement> entry : componentData
				.entrySet()) {
			// if (!palleteEntryMap.containsKey(entry.getKey())) {

			Model shape = new Model(entry.getValue());

			PaletteComponent component = new PaletteComponent(
					entry.getValue().getType(),
					"Create an:" + " " + entry.getValue().getName(),
					Model.class,
					new ActorCreationFactory(Model.class, shape),
					ImageDescriptor.createFromFile(ShapesPlugin.class,
							"/icons/roundRectangle.png"),
					ImageDescriptor.createFromFile(ShapesPlugin.class,
							"/icons/roundRectangle.png"));

			component.setData(entry.getValue());

			PALETTE_DRAWER.add(component);
		}
		return PALETTE_DRAWER;
	}

	public static PaletteDrawer getPaletteDrawer() {
		if (PALETTE_DRAWER == null) {
			PALETTE_DRAWER = new PaletteDrawer("Shapes");
			return PALETTE_DRAWER;
		}
		return PALETTE_DRAWER;
	}

	/**
	 * Creates the PaletteRoot and adds all palette elements. Use this factory
	 * method to create a new palette for your graphical editor.
	 * 
	 * @return a new PaletteRoot
	 */
	static PaletteRoot createPalette() {
		PaletteRoot palette = new PaletteRoot();
		palette.add(createToolsGroup(palette));
		palette.add(getPaletteDrawer());
		return palette;
	}

	/** Create the "Tools" group. */
	private static PaletteContainer createToolsGroup(PaletteRoot palette) {
		PaletteToolbar toolbar = new PaletteToolbar("Tools");

		// Add a selection tool to the group
		ToolEntry tool = new PanningSelectionToolEntry();
		toolbar.add(tool);
		palette.setDefaultEntry(tool);
		return toolbar;
	}

	/** Utility class. */
	private ShapesEditorPaletteFactory() {
		// Utility class
	}

	public static PaletteComponent getSelectedComponent() {
		return selectedComponent;
	}

	public static void setSelectedComponent(PaletteComponent s) {
		selectedComponent = s;
	}

}