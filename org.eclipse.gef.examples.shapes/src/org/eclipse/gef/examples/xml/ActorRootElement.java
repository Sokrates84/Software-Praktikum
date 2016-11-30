
package org.eclipse.gef.examples.xml;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class ActorRootElement {

	@ElementList
	private List<PortElement> port;

	@ElementList
	private List<PropertyElement> property;

	@Attribute
	private ActorValue actorValue;

	public List<PortElement> getPort() {
		return port;
	}

	public List<PropertyElement> getProperty() {
		return property;
	}

	public ActorValue getActorValue() {
		return actorValue;
	}

}
