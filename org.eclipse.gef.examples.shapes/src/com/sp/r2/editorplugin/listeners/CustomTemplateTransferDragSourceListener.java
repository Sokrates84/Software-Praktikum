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

package com.sp.r2.editorplugin.listeners;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;

import com.sp.r2.editorplugin.model.Model;
import com.sp.r2.editorplugin.shapes.PaletteComponent;

public class CustomTemplateTransferDragSourceListener
		extends TemplateTransferDragSourceListener {

	public CustomTemplateTransferDragSourceListener(EditPartViewer viewer) {
		super(viewer);
	}

	@Override
	protected Object getTemplate() {
		List selection = getViewer().getSelectedEditParts();
		if (selection.size() == 1) {
			EditPart editpart = (EditPart) getViewer().getSelectedEditParts()
					.get(0);
			PaletteComponent model = (PaletteComponent) editpart.getModel();

			if (model instanceof CombinedTemplateCreationEntry) {
				Object template = ((CombinedTemplateCreationEntry) model)
						.getTemplate();
				Model shape = (Model) template;
				shape.setData(model.getData());
				return shape;
			}
		}
		return null;
	}

}
