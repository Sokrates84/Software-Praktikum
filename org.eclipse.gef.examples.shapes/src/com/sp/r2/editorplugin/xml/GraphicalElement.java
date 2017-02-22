
package com.sp.r2.editorplugin.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Entity for a graphical element of a parsed xml file.
 * 
 * @author Laurentiu Vlad
 * @author Tim Ungerhofer
 * @author Lex Winandy
 */
@Root(name = "graphical")
public class GraphicalElement {

	// Color of a graphical element
	@Attribute
	private String color;
	// Index of a graphical element
	@Attribute
	private String index;
	// Alignment of a graphical element
	@Attribute
	private String alignment;

	/**
	 * Getter for the color of a graphical element.
	 * 
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Getter for the index of a graphical element.
	 * 
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * Getter for the alignment of a graphical element.
	 * 
	 * @return the alignment
	 */
	public String getAlignment() {
		return alignment;
	}

}
