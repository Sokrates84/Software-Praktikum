package com.sp.r2.editorplugin.handler;

import java.io.File;

import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.handlers.HandlerUtil;

import org.eclipse.gef.palette.PaletteContainer;

import com.sp.r2.editorplugin.shapes.ShapesEditorPaletteFactory;
import com.sp.r2.editorplugin.xml.ComponentMetadata;

/**
 * A hanlder for the open file dialog which opens multiple files and works on
 * the folder level.
 * 
 * @author Laurentiu Vlad
 * @author Tim Ungerhofer
 * @author Lex Winandy
 */
public class OPFHandler implements IHandler {

	// of no use
	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {

	}

	// of no use
	@Override
	public void dispose() {
	}

	/**
	 * Opens the system file dialog and calls the
	 * {@link ComponentMetadata#readXmlMetadata(File[])} for parsing the files.
	 * Afterwards the
	 * {@link ShapesEditorPaletteFactory#populatePaletteView(java.util.Map)} is
	 * being called in order to pupulate the {@link PaletteContainer}.
	 * 
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
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

	/**
	 * @see org.eclipse.core.commands.IHandler#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * @see org.eclipse.core.commands.IHandler#isHandled()
	 */
	@Override
	public boolean isHandled() {
		return true;
	}

	// of no use
	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {

	}

}
