
package org.eclipse.gef.examples.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class PropertyElement {

	@Element
	private PropertyValue propertyValue;

	public PropertyValue getPropertyValue() {
		return propertyValue;
	}

}
