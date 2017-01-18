
package org.eclipse.gef.examples.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "graphical")
public class GraphicalElement {

	@Attribute
	private String color;

	@Attribute
	private String index;

	@Attribute
	private String alignment;

	public String getColor() {
		return color;
	}

	public String getIndex() {
		return index;
	}

	public String getAlignment() {
		return alignment;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

}
