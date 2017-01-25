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

package com.sp.r2.editorplugin.shapes;

import org.eclipse.gef.requests.CreationFactory;

public class ActorCreationFactory implements CreationFactory {

	private Class type;
	private Object object;

	public ActorCreationFactory(Class type, Object object) {
		this.type = type;
		this.object = object;
	}

	@Override
	public Object getNewObject() {
		return object;
	}

	@Override
	public Object getObjectType() {
		return type;
	}

}
