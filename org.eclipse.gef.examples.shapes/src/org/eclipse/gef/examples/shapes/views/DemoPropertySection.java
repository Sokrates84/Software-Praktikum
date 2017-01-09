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

package org.eclipse.gef.examples.shapes.views;

import org.eclipse.swt.widgets.Composite;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class DemoPropertySection extends AbstractPropertySection {
	private TableViewer tableViewer;

	/**
	*
	*/
	public DemoPropertySection() {
	}

	/*
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySecti
	 * on#createControls(org.eclipse.swt.widgets.Composite,
	 * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPa ge)
	 */
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		Composite composite = getWidgetFactory()
				.createFlatFormComposite(parent);
		TableColumnLayout layout = new TableColumnLayout();
		composite.setLayout(layout);
		tableViewer = PropertiesView.createParameterTable(composite, layout);
	}

	/*
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySecti
	 * on#refresh()
	 */
	@Override
	public void refresh() {
		tableViewer.refresh();
	}

	/*
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySecti
	 * on#setInput(org.eclipse.ui.IWorkbenchPart,
	 * org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		tableViewer.setInput(structuredSelection.toArray());
	}
}