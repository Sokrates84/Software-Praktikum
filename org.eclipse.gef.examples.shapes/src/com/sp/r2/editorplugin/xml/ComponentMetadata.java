/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package com.sp.r2.editorplugin.xml;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.gef.palette.PaletteDrawer;

public class ComponentMetadata {

	private static Map<String, ActorRootElement> componentMetadata = new HashMap<>();

	private static PaletteDrawer PALETTE_DRAWER;

	// Innere private Klasse, die erst beim Zugriff durch die umgebende Klasse
	// initialisiert wird
	private static final class InstanceHolder {
		// Die Initialisierung von Klassenvariablen geschieht nur einmal
		// und wird vom ClassLoader implizit synchronisiert
		static final ComponentMetadata INSTANCE = new ComponentMetadata();
	}

	// Verhindere die Erzeugung des Objektes über andere Methoden
	private ComponentMetadata() {
	}

	// Eine nicht synchronisierte Zugriffsmethode auf Klassenebene.
	public static ComponentMetadata getInstance() {
		return InstanceHolder.INSTANCE;
	}

	public static void populateShapeComponent() {

	}

	public static void readXmlMetadata(File[] directoryListing)
			throws Exception {

		XmlReader reader = null;

		for (int i = 0; i < directoryListing.length; i++) {
			reader = new XmlReader(directoryListing[i].getPath());

			ActorRootElement root = reader.getActor();

			if (!componentMetadata.containsKey(root.getId())) {
				componentMetadata.put(root.getId(), root);
			}
		}
	}

	public static Map<String, ActorRootElement> getComponentMetadataMap() {
		return componentMetadata;
	}
}
