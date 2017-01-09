/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package logic;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

public class ActorFigureBorder extends AbstractBorder {

	protected static Insets insets = new Insets(8, 6, 8, 6);
	protected static PointList leftAnchors = new PointList();
	protected static PointList rightAnchors = new PointList();

	static {
		leftAnchors.addPoint(-2, 0);
		leftAnchors.addPoint(1, 0);
		leftAnchors.addPoint(2, 1);
		leftAnchors.addPoint(2, 5);
		leftAnchors.addPoint(-1, 5);
		leftAnchors.addPoint(-1, 1);

		rightAnchors.addPoint(-2, -1);
		rightAnchors.addPoint(1, -1);
		rightAnchors.addPoint(2, -2);
		rightAnchors.addPoint(2, -6);
		rightAnchors.addPoint(-1, -6);
		rightAnchors.addPoint(-1, -2);
	}

	private void drawConnectors(Graphics g, Rectangle rec) {
		int y1 = rec.y, width = rec.width, x1, bottom = y1 + rec.height;
		g.setBackgroundColor(ColorConstants.green);
		for (int i = 0; i < 4; i++) {
			x1 = rec.x + (2 * i + 1) * width / 8;

			// Draw the "gap" for the connector
			// g.setForegroundColor(ColorConstants.listBackground);
			// g.drawLine(x1 - 2, y1 + 2, x1 + 3, y1 + 2);

			// Draw the connectors
			// g.setForegroundColor(ColorConstants.green);
			// leftAnchors.translate(y1, x1);
			// g.fillPolygon(leftAnchors);
			// g.drawPolygon(leftAnchors);
			// leftAnchors.translate(-y1, -x1);
			// g.setForegroundColor(ColorConstants.listBackground);
			// g.drawLine(x1 - 2, bottom - 3, x1 + 3, bottom - 3);
			// g.setForegroundColor(ColorConstants.green);

			rightAnchors.translate(x1, bottom);
			g.fillPolygon(rightAnchors);
			g.drawPolygon(rightAnchors);
			rightAnchors.translate(-x1, -bottom);
		}
	}

	public Insets getInsets(IFigure figure) {
		return insets;
	}

	public void paint(IFigure figure, Graphics g, Insets in) {
		Rectangle r = figure.getBounds().getShrinked(in);

		// g.setForegroundColor(ColorConstants.black);
		g.setBackgroundColor(ColorConstants.black);

		// Draw the sides of the border
		g.fillRectangle(r.x, r.y + 2, r.width - 5, 1);
		g.fillRectangle(r.x, r.bottom() - 4, r.width - 5, 1);
		g.fillRectangle(r.x, r.y + 2, 1, r.height - 5);
		g.fillRectangle(r.right() - 6, r.y + 2, 1, r.height - 5);

		// // Outline the border
		// g.setForegroundColor(ColorConstants.red);
		// g.drawLine(r.x, r.y + 2, r.right() - 1, r.y + 2);
		// g.drawLine(r.x, r.bottom() - 3, r.right() - 1, r.bottom() - 3);
		// g.drawLine(r.x, r.y + 2, r.x, r.bottom() - 3);
		// g.drawLine(r.right() - 1, r.bottom() - 3, r.right() - 1, r.y + 2);

		r.shrink(new Insets(1, 1, 0, 0));
		r.expand(1, 1);
		r.shrink(getInsets(figure));
		drawConnectors(g, figure.getBounds().getShrinked(in));
	}

}
