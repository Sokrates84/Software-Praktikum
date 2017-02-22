package com.sp.r2.editorplugin.shapes;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;

import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteToolbar;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;

import com.sp.r2.editorplugin.model.Model;
import com.sp.r2.editorplugin.xml.ActorRootElement;

/**
 * Utility class that creates a palette an populates it with the parsed
 * components.
 * 
 * @author Laurentiu Vlad
 * @author Tim Ungerhofer
 * @author Lex Winandy
 */
public class ShapesEditorPaletteFactory {

	// Singleton instance of the palette drawer
	private static PaletteDrawer PALETTE_DRAWER;

	/**
	 * Pouplates the palette with the palette components. Those contain the
	 * metadata from the parsed xml files. It takes care not to add multiple
	 * instances of a ActorRootElement to the container.
	 * 
	 * @param componentData
	 *            a map containing the component metadata.
	 * @return the populated palette container.
	 */
	public static PaletteContainer populatePaletteView(
			Map<String, ActorRootElement> componentData) {

		for (Map.Entry<String, ActorRootElement> entry : componentData
				.entrySet()) {

			if (!containsActorRootElement(entry)) {
				Model shape = new Model(entry.getValue());

				PaletteComponent component = new PaletteComponent(
						entry.getValue().getType(),
						"Create an:" + " " + entry.getValue().getName(),
						Model.class,
						new ActorCreationFactory(Model.class, shape),
						ImageDescriptor.createFromFile(ShapesPlugin.class,
								"/icons/roundRectangle.png"),
						ImageDescriptor.createFromFile(ShapesPlugin.class,
								"/icons/roundRectangle.png"));

				component.setData(entry.getValue());

				PALETTE_DRAWER.add(component);
			}
		}
		return PALETTE_DRAWER;
	}

	/**
	 * Helper method for checking if a element has already been added to the
	 * container.
	 * 
	 * @param entry
	 * @return {@code true} if the element has already been added. {@code false}
	 *         otherwise.
	 */
	private static boolean containsActorRootElement(Map.Entry<String, ActorRootElement> entry) {
		List<PaletteComponent> children = PALETTE_DRAWER.getChildren();
		for (PaletteComponent comp : children)
			if (comp.getData().getId().equals(entry.getValue().getId()))
				return true;

		return false;
	}

	/**
	 * Getter for a singleton instance of the {@link PaletteDrawer}
	 * 
	 * @return the {@link PaletteDrawer}
	 */
	public static PaletteDrawer getPaletteDrawer() {
		if (PALETTE_DRAWER == null) {
			PALETTE_DRAWER = new PaletteDrawer("Shapes");
			return PALETTE_DRAWER;
		}
		return PALETTE_DRAWER;
	}

	/**
	 * Creates the PaletteRoot and adds all palette elements.
	 * 
	 * @return a new PaletteRoot
	 */
	static PaletteRoot createPalette() {
		PaletteRoot palette = new PaletteRoot();
		palette.add(createToolsGroup(palette));
		palette.add(getPaletteDrawer());
		return palette;
	}

	/** Create the "Tools" group. */
	private static PaletteContainer createToolsGroup(PaletteRoot palette) {
		PaletteToolbar toolbar = new PaletteToolbar("Tools");

		// Add a selection tool to the group
		ToolEntry tool = new PanningSelectionToolEntry();
		toolbar.add(tool);
		palette.setDefaultEntry(tool);
		return toolbar;
	}

	// Exists only to defeat instantiation.
	private ShapesEditorPaletteFactory() {
	}
}