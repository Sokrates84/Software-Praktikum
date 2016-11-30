/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.gef.examples.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class PortElement {

	@Element
	private PortVariables portVariables;

	@Element
	private GraphicalElement graphicalElement;

	public PortVariables getPortVariables() {
		return portVariables;
	}

	public GraphicalElement getGraphicalElement() {
		return graphicalElement;
	}

}
