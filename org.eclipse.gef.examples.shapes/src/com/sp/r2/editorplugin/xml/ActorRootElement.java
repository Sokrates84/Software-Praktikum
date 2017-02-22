
package com.sp.r2.editorplugin.xml;

import java.util.Collection;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * Entity for a actor element of a parsed xml file. This is the root of the xml.
 * All children can be accessed through this class.
 * 
 * @author Laurentiu Vlad
 * @author Tim Ungerhofer
 * @author Lex Winandy
 */
@Root(name = "actor")
public class ActorRootElement {

	// Collection of port element children.
	@ElementList(name = "port", inline = true)
	private Collection<PortElement> port;
	// Collecton of property element children.
	@ElementList(name = "property", inline = true)
	private Collection<PropertyElement> property;
	// The name of the root
	@Attribute(name = "name")
	private String name;
	// The type of the root
	@Attribute
	private String type;
	// The id of the root
	@Attribute
	private String ID;

	/**
	 * Getter for the port children.
	 * 
	 * @return the ports of this root
	 */
	public Collection<PortElement> getPort() {
		return port;
	}

	/**
	 * Getter for the property children.
	 * 
	 * @return the properties of this root.
	 */
	public Collection<PropertyElement> getProperty() {
		return property;
	}

	/**
	 * Getter for the name of this xml root element.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the type of this xml root element.
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Getter for the id of this xml root element.
	 * 
	 * @return the id
	 */
	public String getId() {
		return ID;
	}

}
