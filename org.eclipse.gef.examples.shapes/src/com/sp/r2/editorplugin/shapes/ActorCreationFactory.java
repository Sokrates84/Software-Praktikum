package com.sp.r2.editorplugin.shapes;

import org.eclipse.gef.requests.CreationFactory;

/**
 * A factory for creating new {@link PaletteComponent}
 * 
 * @author Laurentiu Vlad
 * @author Tim Ungerhofer
 * @author Lex Winandy
 */
public class ActorCreationFactory implements CreationFactory {

	private Class type;
	private Object object;

	/**
	 * Constructor for a ActorCreationFactory
	 */
	public ActorCreationFactory(Class type, Object object) {
		this.type = type;
		this.object = object;
	}

	/**
	 * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
	 */
	@Override
	public Object getNewObject() {
		return object;
	}

	/**
	 * @see org.eclipse.gef.requests.CreationFactory#getObjectType()
	 */
	@Override
	public Object getObjectType() {
		return type;
	}

}
