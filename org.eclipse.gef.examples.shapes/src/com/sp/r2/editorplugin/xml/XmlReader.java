
package com.sp.r2.editorplugin.xml;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class XmlReader {

	private String filename;
	private Serializer serializer;
	private File file;
	private ActorRootElement root;

	public XmlReader(String filename) throws Exception {
		this.filename = filename;
		init();
	}

	private void init() throws Exception {
		file = new File(filename);
		serializer = new Persister();
		root = serializer.read(ActorRootElement.class, file);
	}

	public ActorRootElement getActor() {
		return root;
	}

	public Map<PortElement, GraphicalElement> getPortsAndGrapicals() {
		Map<PortElement, GraphicalElement> map = new HashMap<PortElement, GraphicalElement>();
		for (PortElement pe : root.getPort()) {
			map.put(pe, pe.getGraphicalElement());
		}

		return map;
	}

	public Collection<PropertyElement> getProperties() {
		return root.getProperty();
	}

}
