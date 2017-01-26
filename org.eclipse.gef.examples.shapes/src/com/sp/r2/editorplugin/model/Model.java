/*******************************************************************************
 * Copyright (c) 2004, 2005 Elias Volanakis and others.
�* All rights reserved. This program and the accompanying materials
�* are made available under the terms of the Eclipse Public License v1.0
�* which accompanies this distribution, and is available at
�* http://www.eclipse.org/legal/epl-v10.html
�*
�* Contributors:
�*����Elias Volanakis - initial API and implementation
�*******************************************************************************/
package com.sp.r2.editorplugin.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Image;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import com.sp.r2.editorplugin.shapes.ShapesPlugin;
import com.sp.r2.editorplugin.xml.ActorRootElement;
import com.sp.r2.editorplugin.xml.GraphicalElement;
import com.sp.r2.editorplugin.xml.PortElement;
import com.sp.r2.editorplugin.xml.PropertyElement;

/**
 * A rectangular shape.
 * 
 * @author
 */
public class Model extends ModelElement {

	private Map<String, String> propertiesIdMap = new HashMap<>();
	private Map<String, String> descriptorsNameMap = new HashMap<>();
	private Map<TextPropertyDescriptor, String> descriptorsMap = new HashMap<>();

	/**
	 * A static array of property descriptors. There is one IPropertyDescriptor
	 * entry per editable property.
	 * 
	 * @see #getPropertyDescriptors()
	 * @see #getPropertyValue(Object)
	 * @see #setPropertyValue(Object, Object)
	 */
	private static IPropertyDescriptor[] descriptors;

	public static final String LOCATION_PROP = "Shape.Location";
	public static final String SIZE_PROP = "Shape.Size";
	public static final String SOURCE_CONNECTIONS_PROP = "Shape.SourceConn";
	public static final String TARGET_CONNECTIONS_PROP = "Shape.TargetConn";

	private static final String HEIGHT_PROP = "Shape.Height";
	private static final String WIDTH_PROP = "Shape.Width";
	private static final String XPOS_PROP = "Shape.xPos";
	private static final String YPOS_PROP = "Shape.yPos";
	private final String ACTOR_NAME = "Actor.name";
	private final String ACTOR_TYPE = "Actor.type";
	private final String ACTOR_ID = "Actor.id";
	private final String ACTOR = "Actor";
	private final String PORT = "Port.";
	private final String GRAPHICAL = "Graphical.";
	private final String PROPERTY = "Property.";
	// The names shown in the Propertie View
	private final String actorCategory = "Actor";
	private final String portCategory = "Ports";
	private final String graphicalCategory = "Graphicals P";
	private final String propertyCategory = "Properties";

	private final String NAME = "name";
	private final String ID = "Id";
	private final String TYPE = "type";
	private final String MULTIPORT = "multiport";
	private final String WIDTH = "width";
	private final String ALIGNMENT = "alignment";
	private final String COLOR = "color";
	private final String INDEX = "index";
	private final String VALUE = "value";

	/** Location of this shape. */
	private Point location = new Point(0, 0);
	/** Size of this shape. */
	private Dimension size = new Dimension(100, 100);

	ActorRootElement data;

	private static final long serialVersionUID = 1;

	public Model(ActorRootElement data) {
		super();
		this.data = data;
		mapProperiesValues();
		createPropertyDescriptors();
	}

	// Create propertie ids for the Properties View
	private void mapProperiesValues() {
		int index = 1;
		String id;
		propertiesIdMap.put(ACTOR_NAME, data.getName());
		descriptorsNameMap.put(ACTOR_NAME, NAME);
		propertiesIdMap.put(ACTOR_TYPE, data.getType());
		descriptorsNameMap.put(ACTOR_TYPE, TYPE);
		propertiesIdMap.put(ACTOR_ID, data.getId());
		descriptorsNameMap.put(ACTOR_ID, ID);

		for (PortElement port : data.getPort()) {
			id = PORT + NAME + index;
			propertiesIdMap.put(id, port.getName());
			descriptorsNameMap.put(id, NAME);

			id = PORT + MULTIPORT + index;
			propertiesIdMap.put(id, port.getMultiport());
			descriptorsNameMap.put(id, MULTIPORT);

			id = PORT + TYPE + index;
			propertiesIdMap.put(id, port.getType());
			descriptorsNameMap.put(id, TYPE);

			id = PORT + WIDTH + index;
			propertiesIdMap.put(id, port.getWidth());
			descriptorsNameMap.put(id, WIDTH);

			GraphicalElement element = port.getGraphicalElement();
			id = GRAPHICAL + ALIGNMENT + index;
			propertiesIdMap.put(id, element.getAlignment());
			descriptorsNameMap.put(id, ALIGNMENT);

			id = GRAPHICAL + COLOR + index;
			propertiesIdMap.put(id, element.getColor());
			descriptorsNameMap.put(id, COLOR);

			id = GRAPHICAL + INDEX + index;
			propertiesIdMap.put(id, element.getIndex());
			descriptorsNameMap.put(id, INDEX);
			index++;
		}

		index = 1;
		for (PropertyElement prop : data.getProperty()) {
			id = PROPERTY + NAME + index;
			propertiesIdMap.put(id, prop.getName());
			descriptorsNameMap.put(id, NAME);

			id = PROPERTY + VALUE + index;
			propertiesIdMap.put(id, prop.getValue());
			descriptorsNameMap.put(id, VALUE);
			index++;
		}
	}

	private void createPropertyDescriptors() {
		for (Map.Entry<String, String> entry : propertiesIdMap.entrySet()) {

			if (entry.getKey().startsWith(ACTOR)) {
				descriptorsMap.put(
						new TextPropertyDescriptor(entry.getKey(), descriptorsNameMap.get(entry.getKey())),
						actorCategory);

			} else if (entry.getKey().startsWith(PORT)) {
				String i = entry.getKey().substring(entry.getKey().length() - 1);
				descriptorsMap.put(
						new TextPropertyDescriptor(entry.getKey(), descriptorsNameMap.get(entry.getKey())),
						portCategory + i);

			} else if (entry.getKey().startsWith(PROPERTY)) {
				String i = entry.getKey().substring(entry.getKey().length() - 1);
				descriptorsMap.put(
						new TextPropertyDescriptor(entry.getKey(), descriptorsNameMap.get(entry.getKey())),
						propertyCategory + i);

			} else if (entry.getKey().startsWith(GRAPHICAL)) {
				String i = entry.getKey().substring(entry.getKey().length() - 1);
				descriptorsMap.put(
						new TextPropertyDescriptor(entry.getKey(), descriptorsNameMap.get(entry.getKey())),
						graphicalCategory + i);
			}
		}
	}

	private PropertyDescriptor[] addDescriptors() {
		int i = 0;
		PropertyDescriptor[] newDescriptors = new PropertyDescriptor[descriptorsMap
				.size()];
		for (Map.Entry<TextPropertyDescriptor, String> entry : descriptorsMap
				.entrySet()) {
			TextPropertyDescriptor d = entry.getKey();
			d.setCategory(entry.getValue());
			newDescriptors[i] = d;
			i++;
		}
		return newDescriptors;
	}

	/** A 16x16 pictogram of a rectangular shape. */
	private static final Image RECTANGLE_ICON = createImage("/icons/roundRectangle.png");

	public Image getIcon() {

		return RECTANGLE_ICON;
	}

	public String toString() {
		return "Rectangle " + hashCode();
	}

	public ActorRootElement getData() {
		return data;
	}

	public void setData(ActorRootElement data) {
		this.data = data;
	}

	public Map<String, String> getPropertiesIdMap() {
		return propertiesIdMap;
	}

	protected static Image createImage(String name) {
		InputStream stream = ShapesPlugin.class.getResourceAsStream(name);
		Image image = new Image(null, stream);
		try {
			stream.close();
		} catch (IOException ioe) {
		}
		return image;
	}

	/**
	 * Return the Location of this shape.
	 * 
	 * @return a non-null location instance
	 */
	public Point getLocation() {
		return location.getCopy();
	}

	/**
	 * Returns an array of IPropertyDescriptors for this shape.
	 * <p>
	 * The returned array is used to fill the property view, when the edit-part
	 * corresponding to this model element is selected.
	 * </p>
	 * 
	 * @see #descriptors
	 * @see #getPropertyValue(Object)
	 * @see #setPropertyValue(Object, Object)
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors = addDescriptors();
	}

	/**
	 * Return the property value for the given propertyId, or null.
	 * <p>
	 * The property view uses the IDs from the IPropertyDescriptors array to
	 * obtain the value of the corresponding properties.
	 * </p>
	 * 
	 * @see #descriptors
	 * @see #getPropertyDescriptors()
	 */
	public Object getPropertyValue(Object propertyId) {
		if (propertiesIdMap.containsKey(propertyId)) {
			return propertiesIdMap.get(propertyId);
		}
		if (XPOS_PROP.equals(propertyId)) {
			return Integer.toString(location.x);
		}
		if (YPOS_PROP.equals(propertyId)) {
			return Integer.toString(location.y);
		}
		if (HEIGHT_PROP.equals(propertyId)) {
			return Integer.toString(size.height);
		}
		if (WIDTH_PROP.equals(propertyId)) {
			return Integer.toString(size.width);
		}

		return super.getPropertyValue(propertyId);
	}

	/**
	 * Return the Size of this shape.
	 * 
	 * @return a non-null Dimension instance
	 */
	public Dimension getSize() {
		return size.getCopy();
	}

	/**
	 * Set the Location of this shape.
	 * 
	 * @param newLocation
	 *            a non-null Point instance
	 * @throws IllegalArgumentException
	 *             if the parameter is null
	 */
	public void setLocation(Point newLocation) {
		if (newLocation == null) {
			throw new IllegalArgumentException();
		}
		location.setLocation(newLocation);
		firePropertyChange(LOCATION_PROP, null, location);
	}

	/**
	 * Set the property value for the given property id. If no matching id is
	 * found, the call is forwarded to the superclass.
	 * <p>
	 * The property view uses the IDs from the IPropertyDescriptors array to set
	 * the values of the corresponding properties.
	 * </p>
	 * 
	 * @see #descriptors
	 * @see #getPropertyDescriptors()
	 */
	public void setPropertyValue(Object propertyId, Object value) {
		if (propertiesIdMap.containsKey(propertyId)) {
			for (String key : propertiesIdMap.keySet()) {
				if (key.equals(propertyId)) {
					firePropertyChange(key, propertiesIdMap.get(key), value);
					break;
				}
			}
		} else if (XPOS_PROP.equals(propertyId)) {
			int x = Integer.parseInt((String) value);
			setLocation(new Point(x, location.y));
		} else if (YPOS_PROP.equals(propertyId)) {
			int y = Integer.parseInt((String) value);
			setLocation(new Point(location.x, y));
		} else if (HEIGHT_PROP.equals(propertyId)) {
			int height = Integer.parseInt((String) value);
			setSize(new Dimension(size.width, height));
		} else if (WIDTH_PROP.equals(propertyId)) {
			int width = Integer.parseInt((String) value);
			setSize(new Dimension(width, size.height));
		} else {
			super.setPropertyValue(propertyId, value);
		}
	}

	/**
	 * Set the Size of this shape. Will not modify the size if newSize is null.
	 * 
	 * @param newSize
	 *            a non-null Dimension instance or null
	 */
	public void setSize(Dimension newSize) {
		if (newSize != null) {
			size.setSize(newSize);
			firePropertyChange(SIZE_PROP, null, size);
		}
	}
}
