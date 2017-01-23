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

import java.util.List;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.SimpleRaisedBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

public class ActorFigure extends Figure {

	private Label name = new Label();
	private Label port1 = new Label();
	private Label port2 = new Label();
	private Label port3 = new Label();
	private Label port4 = new Label();
	private XYLayout layout;

	public ActorFigure() {

		BorderLayout b = new BorderLayout();

		GridLayout g = new GridLayout();
		g.numColumns = 2;
		// g.horizontalSpacing = 4;
		g.verticalSpacing = 2;
		// --------------------------------------------------------------------
		layout = new XYLayout();
		setLayoutManager(layout);
		SimpleRaisedBorder srb = new SimpleRaisedBorder();

		setBorder(new LineBorder());

		port1.setForegroundColor(ColorConstants.blue);
		port2.setForegroundColor(ColorConstants.black);
		port3.setForegroundColor(ColorConstants.red);
		port4.setForegroundColor(ColorConstants.green);

		Rectangle r = getBounds();

		add(port1);
		setConstraint(port1, new Rectangle(5, 5, -1, -1));
		add(port2);
		setConstraint(port2, new Rectangle(5, 20, -1, -1));
		add(port3);
		setConstraint(port3, new Rectangle(5, 35, -1, -1));
		add(port4);
		setConstraint(port4, new Rectangle(5, 50, -1, -1));

		// --------------------------------------------------------------------
		// Figure r = new Figure();
		// r.setLayoutManager(g);
		//
		// setBorder(new ActorFigureBorder());
		// setLayoutManager(b);
		//
		// add(port1, BorderLayout.TOP);
		// add(r, BorderLayout.CENTER);
		//
		// port1.setForegroundColor(ColorConstants.blue);
		// port2.setForegroundColor(ColorConstants.black);
		// port3.setForegroundColor(ColorConstants.red);
		// port4.setForegroundColor(ColorConstants.green);
		//
		// r.add(port2, new GridData(0, SWT.BEGINNING, true, false));
		// r.add(port3, new GridData(0, SWT.END, false, false));
		// r.add(port4, new GridData(0, SWT.BEGINNING, true, false));
		//
		List children = getChildren();
		System.out.println();

	}

	public void setPort1(String port1) {
		this.port1.setText(port1);
	}

	public void setPort2(String port2) {
		this.port2.setText(port2);
	}

	public void setPort3(String port3) {
		this.port3.setText(port3);
	}

	public void setPort4(String port4) {
		this.port4.setText(port4);
	}

	public void setLayout(XYLayout layout) {
		this.layout = layout;
	}

}
