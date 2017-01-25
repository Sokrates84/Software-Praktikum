
package com.sp.r2.editorplugin.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="property")
public class PropertyElement {

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
