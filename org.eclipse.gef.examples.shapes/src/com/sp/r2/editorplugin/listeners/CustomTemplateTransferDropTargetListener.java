package com.sp.r2.editorplugin.listeners;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.dnd.TransferDropTargetListener;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.CreationFactory;

import com.sp.r2.editorplugin.shapes.ActorCreationFactory;

/**
 * A custom {@link TransferDropTargetListener}.
 */
public class CustomTemplateTransferDropTargetListener
		extends TemplateTransferDropTargetListener {

	/**
	 * Constructor for a {@link TransferDropTargetListener}
	 */
	public CustomTemplateTransferDropTargetListener(EditPartViewer viewer) {
		super(viewer);
	}

	/**
	 * @see org.eclipse.gef.dnd.TemplateTransferDropTargetListener#getFactory(java.lang.Object)
	 */
	@Override
	protected CreationFactory getFactory(Object template) {
		ToolEntry entry = getViewer().getEditDomain().getPaletteViewer()
				.getActiveTool();
		ActorCreationFactory factory = new ActorCreationFactory(
				template.getClass(), entry);
		return factory;
	}

}
