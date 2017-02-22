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

/**
 * The view part which will be displayed in the canvas after a drag and drop
 * from the pallete view.
 * 
 * @author Laurentiu Vlad
 * @author Tim Ungerhofer
 * @author Lex Winandy
 */
public class ActorFigure extends Figure {

	// A map containing all created labels for this figure.
	private Map<String, Label> labelMap = new HashMap<>();
	// Data for the label text.
	private ActorRootElement data;
	// The name label for this figure.
	private Label name = new Label();
	// The layout manager
	private XYLayout layout;

	// Position values for arranging the labels inside the figure
	private int x = 15;
	private int y = 5;
	private int width_height = -1;

	/**
	 * Constructor for a ActorFigure.
	 */
	public ActorFigure(ActorRootElement data) {
		this.data = data;
		layout = new XYLayout();
		setLayoutManager(layout);

		setBorder(new LineBorder(2));
		setBackgroundColor(ColorConstants.cyan);
		setOpaque(true);
		createLabels(data);
	}

	/**
	 * Creates labels according to the provided data in {@link ActorRootElement}
	 * and sets the corresponding display text.
	 */
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

	/**
	 * Getter for the label map.
	 * 
	 * @return the label map.
	 */
	public Map<String, Label> getLabelMap() {
		return labelMap;
	}

	/**
	 * Getter for the figure dimensions. Callings this method will automatically
	 * enlarge the figure so the label text fits and is not cut.
	 * 
	 * @return the adjusted figure dimensions.
	 */
	public Dimension getDimension() {
		int width = 0;

		for (Label label : labelMap.values()) {
			if (label.getTextBounds().width > width)
				width = label.getTextBounds().width;
		}
		return new Dimension(width + 35, y + 15);
	}
}
