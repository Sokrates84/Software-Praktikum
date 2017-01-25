
package com.sp.r2.editorplugin.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "port")
public class PortElement {

	@Element(name = "graphical")
	private GraphicalElement graphicalElement;

	public GraphicalElement getGraphicalElement() {
		return graphicalElement;
	}

	@Attribute(name = "name")
	private String name;

	@Attribute
	private String multiport;

	@Attribute
	private String width;

	@Attribute
	private String type;

	public String getName() {
		return name;
	}

	public String getMultiport() {
		return multiport;
	}

	public String getWidth() {
		return width;
	}

	public String getType() {
		return type;
	}

}
