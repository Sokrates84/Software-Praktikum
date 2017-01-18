
package org.eclipse.gef.examples.xml;

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

	public String isMultiport() {
		return multiport;
	}

	public String getWidth() {
		return width;
	}

	public String getType() {
		return type;
	}

	public String getMultiport() {
		return multiport;
	}

	public void setMultiport(String multiport) {
		this.multiport = multiport;
	}

	public void setGraphicalElement(GraphicalElement graphicalElement) {
		this.graphicalElement = graphicalElement;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void setType(String type) {
		this.type = type;
	}

}
