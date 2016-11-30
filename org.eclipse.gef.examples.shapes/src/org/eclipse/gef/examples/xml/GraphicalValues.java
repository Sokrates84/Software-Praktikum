
package org.eclipse.gef.examples.xml;

import java.util.List;

import org.simpleframework.xml.Attribute;

public class GraphicalValues {

	@Attribute
	private List<Integer> color;

	@Attribute
	private String index;

	@Attribute
	private String alignment;

	public List<Integer> getColor() {
		return color;
	}

	public String getIndex() {
		return index;
	}

	public String getAlignment() {
		return alignment;
	}

}
