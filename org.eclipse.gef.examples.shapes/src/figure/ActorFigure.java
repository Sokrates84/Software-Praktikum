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

package figure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

import org.eclipse.gef.examples.xml.ActorRootElement;
import org.eclipse.gef.examples.xml.PortElement;

public class ActorFigure extends Figure {
	ActorRootElement data;

	Label name;
	List<Label> portLabels;
	List<Label> propertylabels;

	private XYLayout layout;
	private int x = 5;
	private int y = 15;
	private int width = -1;
	private int height = -1;

	public ActorFigure(ActorRootElement data) {
		this.data = data;

		layout = new XYLayout();
		setLayoutManager(layout);

		addName();
		addPorts();
		setBorder(new MarginBorder(5));
		setBackgroundColor(ColorConstants.lightGray);
		setOpaque(true);

	}

	private void addName() {
		name = new Label();
		name.setForegroundColor(ColorConstants.black);
		name.setText(data.getName());
		add(name);
		setConstraint(name, new Rectangle(x, y, width, height));
		y = y + 15;
	}

	private void addPorts() {
		Collection<PortElement> elements = data.getPort();
		portLabels = new ArrayList<>();

		for (PortElement port : elements) {
			Label label = new Label();
			label.setForegroundColor(ColorConstants.black);
			label.setText(port.getName());
			add(label);
			setConstraint(label, new Rectangle(x, y, width, height));
			y = y + 15;
			portLabels.add(label);
		}

	}

	public Label getLabel(String labelName) {
		for (Label label : portLabels) {
			if (label.getText().equals(labelName))
				return label;
		}
		return null;
	}
}
