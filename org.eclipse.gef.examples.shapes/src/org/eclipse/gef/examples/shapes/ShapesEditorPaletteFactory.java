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
package org.eclipse.gef.examples.shapes;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteToolbar;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.requests.SimpleFactory;

import org.eclipse.gef.examples.shapes.model.Connection;
import org.eclipse.gef.examples.shapes.model.EllipticalShape;
import org.eclipse.gef.examples.xml.ActorRootElement;
import org.eclipse.gef.examples.xml.XmlReader;

/**
 * Utility class that can create a GEF Palette.
 * 
 * @see #createPalette()
 * @author Elias Volanakis
 */
public class ShapesEditorPaletteFactory {

	private static Map<String, ActorRootElement> map = new HashMap<>();

	private static PaletteDrawer PALETTE_DRAWER;

	/** Create the "Shapes" drawer. */
	public static PaletteContainer createShapesDrawer(File[] directoryListing) {

		XmlReader reader = null;
		try {

			for (int i = 0; i < directoryListing.length; i++) {
				reader = new XmlReader(directoryListing[i].getPath());

				ActorRootElement root = reader.getActor();

				if (!map.containsKey(root.getId())) {
					map.put(root.getId(), root);

					CombinedTemplateCreationEntry component = new CombinedTemplateCreationEntry(
							root.getType(), "Create an:" + " " + root.getName(),
							EllipticalShape.class,
							new SimpleFactory(EllipticalShape.class),
							ImageDescriptor.createFromFile(ShapesPlugin.class,
									"icons/ellipse16.gif"),
							ImageDescriptor.createFromFile(ShapesPlugin.class,
									"icons/ellipse24.gif"));
					PALETTE_DRAWER.add(component);
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		// Add a marquee tool to the group
		toolbar.add(new MarqueeToolEntry());

		// Add (solid-line) connection tool
		tool = new ConnectionCreationToolEntry("Solid connection",
				"Create a solid-line connection", new CreationFactory() {
					public Object getNewObject() {
						return null;
					}

					// see ShapeEditPart#createEditPolicies()
					// this is abused to transmit the desired line style
					public Object getObjectType() {
						return Connection.SOLID_CONNECTION;
					}
				},
				ImageDescriptor.createFromFile(ShapesPlugin.class,
						"icons/connection_s16.gif"),
				ImageDescriptor.createFromFile(ShapesPlugin.class,
						"icons/connection_s24.gif"));
		toolbar.add(tool);

		// Add (dashed-line) connection tool
		tool = new ConnectionCreationToolEntry("Dashed connection",
				"Create a dashed-line connection", new CreationFactory() {
					public Object getNewObject() {
						return null;
					}

					// see ShapeEditPart#createEditPolicies()
					// this is abused to transmit the desired line style
					public Object getObjectType() {
						return Connection.DASHED_CONNECTION;
					}
				},
				ImageDescriptor.createFromFile(ShapesPlugin.class,
						"icons/connection_d16.gif"),
				ImageDescriptor.createFromFile(ShapesPlugin.class,
						"icons/connection_d24.gif"));
		toolbar.add(tool);

		return toolbar;
	}

	/** Utility class. */
	private ShapesEditorPaletteFactory() {
		// Utility class
	}

}