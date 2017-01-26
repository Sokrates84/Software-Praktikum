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

package com.sp.r2.editorplugin.figures;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import com.sp.r2.editorplugin.xml.ActorRootElement;
import com.sp.r2.editorplugin.xml.PortElement;

public class ActorFigure extends Figure {

	private Map<String, Label> labelMap = new HashMap<>();
	private ActorRootElement data;
	private Label name = new Label();
	private XYLayout layout;
	private int x = 15;
	private int y = 5;
	private int width_height = -1;

	public ActorFigure(ActorRootElement data) {
		this.data = data;
		layout = new XYLayout();
		setLayoutManager(layout);

		setBorder(new LineBorder(2));
		setBackgroundColor(ColorConstants.cyan);
		setOpaque(true);
		createLabels(data);
	}

	private void createLabels(ActorRootElement data) {

		Font font = new Font(Display.getCurrent(), "sansserif", 11, SWT.BOLD);
		name.setFont(font);
		name.setForegroundColor(ColorConstants.blue);
		name.setBorder(new LineBorder());
		name.setText(data.getName());
		add(name);
		setConstraint(name, new Rectangle(x, y, width_height, width_height));
		y = y + 25;
		labelMap.put(data.getName(), name);

		Label label;
		Collection<PortElement> ports = data.getPort();
		for (PortElement port : ports) {
			label = new Label();
			label.setText(port.getName());
			label.setForegroundColor(ColorConstants.black);
			add(label);
			setConstraint(label,
					new Rectangle(x, y, width_height, width_height));
			y = y + 15;
			labelMap.put(port.getName(), label);
		}
	}

	public Map<String, Label> getLabelMap() {
		return labelMap;
	}

	public Dimension getDimension() {
		int width = 0;

		for (Label label : labelMap.values()) {
			if (label.getTextBounds().width > width)
				width = label.getTextBounds().width;
		}
		return new Dimension(width + 35, y + 15);
	}
}
