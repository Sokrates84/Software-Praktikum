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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

public class ActorFigure extends Figure {

	private Label name = new Label();
	private Label port1 = new Label();
	private Label port2 = new Label();
	// private Label port3 = new Label();
	private XYLayout layout;

	public ActorFigure() {

		layout = new XYLayout();
		setLayoutManager(layout);
		port1.setForegroundColor(ColorConstants.blue);
		add(port1);
		setConstraint(port1, new Rectangle(5, 5, -1, -1));
		port2.setForegroundColor(ColorConstants.black);
		add(port2);
		setConstraint(port2, new Rectangle(5, 17, -1, -1));
		// port3.setForegroundColor(ColorConstants.red);
		// add(port3);
		// setConstraint(port3, new Rectangle(5, 30, -1, -1));

		setForegroundColor(ColorConstants.black);
		setBorder(new LineBorder(1));
	}

	public void setPort1(String port1) {
		this.port1.setText(port1);
	}

	public void setPort2(String port2) {
		this.port2.setText(port2);
	}

	// public void setPort3(String port3) {
	// this.port3.setText(port3);
	// }

	public void setLayout(XYLayout layout) {
		this.layout = layout;
	}

}
