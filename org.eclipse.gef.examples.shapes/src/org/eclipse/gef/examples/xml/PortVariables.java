
package org.eclipse.gef.examples.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class PortVariables {

	@Attribute
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

}
