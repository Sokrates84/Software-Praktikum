package com.sp.r2.editorplugin.xml;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton class for parsing multiple actor xml files and storing them in a
 * map.
 * 
 * @author Laurentiu Vlad
 * @author Tim Ungerhofer
 * @author Lex Winandy
 */
public class ComponentMetadata {

	/*
	 * Contains all parsed xml file in the form of ActorRootElement. The id's of
	 * those elements are the keys.
	 */
	private static Map<String, ActorRootElement> componentMetadata = new HashMap<>();

	// Inner private classe, which will only be initialize when accessed by the
	// surrounding class.
	private static final class InstanceHolder {
		// Only "one time" initialization
		static final ComponentMetadata INSTANCE = new ComponentMetadata();
	}

	// Exists only to defeat instantiation.
	private ComponentMetadata() {
	}

	/**
	 * Getter for the singleton instance.
	 * 
	 * @return the singleton instance of this class
	 */
	public static ComponentMetadata getInstance() {
		return InstanceHolder.INSTANCE;
	}

	/**
	 * Static method for reading multiple actor xml files and storing them in a
	 * map.
	 * 
	 * @param directoryListing
	 *            the path to the directory containing the actor xml files.
	 * @throws Exception
	 */
	public static void readXmlMetadata(File[] directoryListing)
			throws Exception {

		XmlReader reader = null;

		for (int i = 0; i < directoryListing.length; i++) {
			reader = new XmlReader(directoryListing[i].getPath());

			ActorRootElement root = reader.getActor();

			if (!componentMetadata.containsKey(root.getId())) {
				componentMetadata.put(root.getId(), root);
			}
		}
	}

	/**
	 * Getter for the map containing the parsed xml files as ActorRootElement.
	 * 
	 * @return the map containing the parsed xml files as ActorRootElement.
	 */
	public static Map<String, ActorRootElement> getComponentMetadataMap() {
		return componentMetadata;
	}
}
