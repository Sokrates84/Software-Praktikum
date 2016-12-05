import java.io.File;

import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.handlers.HandlerUtil;

import org.eclipse.gef.examples.shapes.ShapesEditorPaletteFactory;
import org.eclipse.gef.examples.xml.ComponentMetadata;

/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: IBM Corporation - initial API and implementation
 *******************************************************************************/

public class OPFHandler implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {

	}

	@Override
	public void dispose() {
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();

		DirectoryDialog dialogD = new DirectoryDialog(shell);
		String filename = dialogD.open();

		File dir = new File(filename);
		File[] directoryListing = dir.listFiles();

		try {
			ComponentMetadata.readXmlMetadata(directoryListing);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ShapesEditorPaletteFactory.populatePaletteView(
				ComponentMetadata.getComponentMetadataMap());

		return null;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {

	}

}
