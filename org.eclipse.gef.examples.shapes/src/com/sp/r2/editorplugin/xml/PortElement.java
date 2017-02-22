
package com.sp.r2.editorplugin.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Entity for a port element of a parsed xml file.
 * 
 * @author Laurentiu Vlad
 * @author Tim Ungerhofer
 * @author Lex Winandy
 */
@Root(name = "port")
public class PortElement {

	// Graphical element
	@Element(name = "graphical")
	private GraphicalElement graphicalElement;
	// Name of the port element
	@Attribute(name = "name")
	private String name;
	// Multiport of the port element
	@Attribute
	private String multiport;
	// Width of the port element
	@Attribute
	private String width;
	// Type of the port element
	@Attribute
	private String type;

	/**
	 * Getter for a graphical element.
	 * 
	 * @return the graphical element
	 */
	public GraphicalElement getGraphicalElement() {
		return graphicalElement;
	}

	/**
	 * Getter for the name of a port element
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the multiport of a port element.
	 * 
	 * @return the mulitport
	 */
	public String getMultiport() {
		return multiport;
	}

	/**
	 * Getter for the width of a port element.
	 * 
	 * @return the width
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * Getter for the type of a port element.
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

}
