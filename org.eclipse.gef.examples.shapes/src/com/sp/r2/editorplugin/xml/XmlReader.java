
package com.sp.r2.editorplugin.xml;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 * XML-Parser for custom actor xml files.
 * <p>
 * Example file:
 * <p>
 * 
 * <pre>
 * {@code
 * <actor name="CPU Scheduler" type="CPUSCHEDULER" ID="0"> 
	<port name="interrupts" multiport="false" width="1" type="inputport"> 
		<graphical color="123,215,128" index="1" alignment="West" /> 
	</port> 
	<port name="toPeripherals" multiport="false" width="1" type="outputport"> 
		<graphical color="123,215,128" index="2" alignment="West" /> 
	</port> <property name="Monitor" value="false" /> 
	<property name="CPUCycleDuration" value="100" /> 
</actor>
 * }
 * </pre>
 * 
 * @author Laurentiu Vlad
 * @author Tim Ungerhofer
 * @author Lex Winandy
 */
public class XmlReader {

	// the file name
	private String filename;
	// for serializing and deserializing objects
	private Serializer serializer;
	// the xml root element
	private ActorRootElement root;
	private File file;

	/**
	 * Constructor for a new XML-Reader
	 * 
	 * @param filename
	 *            the path to the file to be parsed.
	 * @throws Exception
	 */
	public XmlReader(String filename) throws Exception {
		this.filename = filename;
		init();
	}

	private void init() throws Exception {
		file = new File(filename);
		serializer = new Persister();
		root = serializer.read(ActorRootElement.class, file);
	}

	/**
	 * Getter for the xml root element.
	 * 
	 * @return the xml root element
	 */
	public ActorRootElement getActor() {
		return root;
	}

	/**
	 * Getter for the ports and graphical elements of the xml root.
	 * 
	 * @return a map containing the ports and graphical elements.
	 */
	public Map<PortElement, GraphicalElement> getPortsAndGrapicals() {
		Map<PortElement, GraphicalElement> map = new HashMap<PortElement, GraphicalElement>();
		for (PortElement pe : root.getPort()) {
			map.put(pe, pe.getGraphicalElement());
		}

		return map;
	}

	/**
	 * Getter for the properties of the xml root.
	 * 
	 * @return a collection containing the properties of the xml root.
	 */
	public Collection<PropertyElement> getProperties() {
		return root.getProperty();
	}

}
