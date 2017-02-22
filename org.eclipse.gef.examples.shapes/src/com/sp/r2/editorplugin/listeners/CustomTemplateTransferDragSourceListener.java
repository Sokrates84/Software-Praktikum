
package com.sp.r2.editorplugin.listeners;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.dnd.TransferDragSourceListener;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;

import com.sp.r2.editorplugin.model.Model;
import com.sp.r2.editorplugin.shapes.PaletteComponent;

/**
 * A custom {@link TransferDragSourceListener}
 */
public class CustomTemplateTransferDragSourceListener
		extends TemplateTransferDragSourceListener {

	/**
	 * Constructor for a @link CustomTemplateTransferDragSourceListener
	 */
	public CustomTemplateTransferDragSourceListener(EditPartViewer viewer) {
		super(viewer);
	}

	/**
	 * Modified getTemplate() method which sets the ActorRootElement data from
	 * the {@link PaletteComponent} into the Model dragged into the canvas.
	 * 
	 * @see org.eclipse.gef.dnd.TemplateTransferDragSourceListener#getTemplate()
	 */
	@Override
	protected Object getTemplate() {
		List selection = getViewer().getSelectedEditParts();
		if (selection.size() == 1) {
			EditPart editpart = (EditPart) getViewer().getSelectedEditParts()
					.get(0);
			PaletteComponent model = (PaletteComponent) editpart.getModel();

			if (model instanceof CombinedTemplateCreationEntry) {
				Object template = ((CombinedTemplateCreationEntry) model)
						.getTemplate();
				Model shape = (Model) template;
				shape.setData(model.getData());
				return shape;
			}
		}
		return null;
	}

}
