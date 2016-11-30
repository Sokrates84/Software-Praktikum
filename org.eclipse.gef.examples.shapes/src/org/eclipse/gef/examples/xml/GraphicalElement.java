
package org.eclipse.gef.examples.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class GraphicalElement {

	@Element
	private GraphicalValues graphicalValues;

	public GraphicalValues getGraphicalValues() {
		return graphicalValues;
	}

}
