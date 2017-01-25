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

package com.sp.r2.editorplugin.xml;

import java.util.Collection;

public class Actor {
	private ActorRootElement data;

	private String propertyName;
	private String propertyValue;
	private String portName;
	private String portMultiport;
	private String portWidth;
	private String portType;
	private String graphicalColor;
	private String graphicalIndex;
	private String graphicalAlignment;
	private String actorName;
	private String actorType;
	private String actorId;

	public Actor(ActorRootElement data) {
		super();
		this.data = data;
	}

	private void setEntityValues() {

		actorId = data.getId();
		actorName = data.getName();
		actorType = data.getType();

		Collection<PortElement> ports = data.getPort();
		for (PortElement port : ports) {

		}

	}

}
