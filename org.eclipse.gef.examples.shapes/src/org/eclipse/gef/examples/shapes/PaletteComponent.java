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

package org.eclipse.gef.examples.shapes;

import org.eclipse.jface.resource.ImageDescriptor;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.requests.CreationFactory;

import org.eclipse.gef.examples.xml.ActorRootElement;

public class PaletteComponent extends CombinedTemplateCreationEntry {

	private ActorRootElement data;

	public PaletteComponent(String label, String shortDesc, Object template,
			CreationFactory factory, ImageDescriptor iconSmall,
			ImageDescriptor iconLarge) {
		super(label, shortDesc, template, factory, iconSmall, iconLarge);
	}

	public ActorRootElement getData() {
		return data;
	}

	public void setData(ActorRootElement data) {
		this.data = data;
	}

}
