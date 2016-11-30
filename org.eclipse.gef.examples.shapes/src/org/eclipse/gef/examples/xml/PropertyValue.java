
package org.eclipse.gef.examples.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class PropertyValue {

	@Attribute
	private String name;

	@Attribute
	private String value;

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

}
