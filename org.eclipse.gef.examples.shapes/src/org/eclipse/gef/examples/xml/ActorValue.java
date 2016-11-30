
package org.eclipse.gef.examples.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class ActorValue {

	@Attribute
	private String name;

	@Attribute
	private String type;

	@Attribute
	private String id;

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}

}
