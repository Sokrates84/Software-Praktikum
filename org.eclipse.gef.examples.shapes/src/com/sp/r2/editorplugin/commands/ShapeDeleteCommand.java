/*******************************************************************************
 * Copyright (c) 2004, 2005 Elias Volanakis and others.
�* All rights reserved. This program and the accompanying materials
�* are made available under the terms of the Eclipse Public License v1.0
�* which accompanies this distribution, and is available at
�* http://www.eclipse.org/legal/epl-v10.html
�*
�* Contributors:
�*����Elias Volanakis - initial API and implementation
�*******************************************************************************/
package com.sp.r2.editorplugin.commands;

import org.eclipse.gef.commands.Command;

import com.sp.r2.editorplugin.model.Model;
import com.sp.r2.editorplugin.model.ShapesDiagram;

/**
 * A command to remove a shape from its parent. The command can be undone or
 * redone.
 * 
 * @author Elias Volanakis
 */
public class ShapeDeleteCommand extends Command {
	/** Shape to remove. */
	private final Model child;

	/** ShapeDiagram to remove from. */
	private final ShapesDiagram parent;
	/** True, if child was removed from its parent. */
	private boolean wasRemoved;

	/**
	 * Create a command that will remove the shape from its parent.
	 * 
	 * @param parent
	 *            the ShapesDiagram containing the child
	 * @param child
	 *            the Shape to remove
	 * @throws IllegalArgumentException
	 *             if any parameter is null
	 */
	public ShapeDeleteCommand(ShapesDiagram parent, Model child) {
		if (parent == null || child == null) {
			throw new IllegalArgumentException();
		}
		setLabel("shape deletion");
		this.parent = parent;
		this.child = child;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	public boolean canUndo() {
		return wasRemoved;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		redo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		// remove the child and disconnect its connections
		wasRemoved = parent.removeChild(child);
	}

	@Override
	public void undo() {
		parent.addChild(child);
	}

}