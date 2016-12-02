
package org.eclipse.gef.examples.xml;

import java.util.Collection;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "actor")
public class ActorRootElement {

	@ElementList(name = "port", inline = true)
	private Collection<PortElement> port;

	@ElementList(name = "property", inline = true)
	private Collection<PropertyElement> property;

	public Collection<PortElement> getPort() {
		return port;
	}

	public Collection<PropertyElement> getProperty() {
		return property;
	}

	@Attribute(name = "name")
	private String name;

	@Attribute
	private String type;

	@Attribute
	private String ID;

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getId() {
		return ID;
	}

}
