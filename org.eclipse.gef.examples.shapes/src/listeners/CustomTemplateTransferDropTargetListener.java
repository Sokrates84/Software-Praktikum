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

package listeners;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.CreationFactory;

import creationFactory.ActorCreationFactory;

public class CustomTemplateTransferDropTargetListener
		extends TemplateTransferDropTargetListener {

	public CustomTemplateTransferDropTargetListener(EditPartViewer viewer) {
		super(viewer);
	}

	@Override
	protected CreationFactory getFactory(Object template) {
		ToolEntry entry = getViewer().getEditDomain().getPaletteViewer()
				.getActiveTool();
		ActorCreationFactory factory = new ActorCreationFactory(
				template.getClass(), entry);
		return factory;
	}

}
