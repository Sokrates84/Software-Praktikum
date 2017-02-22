package com.sp.r2.editorplugin.shapes;

import org.eclipse.jface.resource.ImageDescriptor;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.requests.CreationFactory;

import com.sp.r2.editorplugin.xml.ActorRootElement;

/**
 * A palette component for the {@link PaletteContainer} containing the data of a
 * {@link ActorRootElement}
 * 
 * @author Laurentiu Vlad
 * @author Tim Ungerhofer
 * @author Lex Winandy
 */
public class PaletteComponent extends CombinedTemplateCreationEntry {

	/*
	 * Constructor for a PaletteComponent.
	 */
	public PaletteComponent(String label, String shortDesc, Object template,
			CreationFactory factory, ImageDescriptor iconSmall,
			ImageDescriptor iconLarge) {
		super(label, shortDesc, template, factory, iconSmall, iconLarge);
	}

	// The metadata of a parsed xml file.
	private ActorRootElement data;

	/**
	 * Getter for the {@link ActorRootElement}
	 * 
	 * @return the {@link ActorRootElement}
	 */
	public ActorRootElement getData() {
		return data;
	}

	/**
	 * Setter for the {@link ActorRootElement}
	 */
	public void setData(ActorRootElement data) {
		this.data = data;
	}

	/**
	 * @see org.eclipse.gef.palette.CombinedTemplateCreationEntry#getTemplate()
	 */
	@Override
	public Object getTemplate() {
		return super.getTemplate();
	}

	/**
	 * @see org.eclipse.gef.palette.CombinedTemplateCreationEntry#setTemplate(java.lang.Object)
	 */
	@Override
	public void setTemplate(Object template) {
		super.setTemplate(template);
	}

}
