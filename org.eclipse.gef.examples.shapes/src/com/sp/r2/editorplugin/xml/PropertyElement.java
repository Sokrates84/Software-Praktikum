
package com.sp.r2.editorplugin.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Entity for a property element of a parsed xml file.
 * 
 * @author Laurentiu Vlad
 * @author Tim Ungerhofer
 * @author Lex Winandy
 */
@Root(name = "property")
public class PropertyElement {

	// Property name
	@Attribute
	private String name;

	// Property value
	@Attribute
	private String value;

	/**
	 * Getter for the property name
	 * 
	 * @return the property name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the property value
	 * 
	 * @return the property value
	 */
	public String getValue() {
		return value;
	}

}
