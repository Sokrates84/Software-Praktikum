
package org.eclipse.gef.examples.xml;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="graphical")
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


}
