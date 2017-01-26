/*******************************************************************************
 * Copyright (c) 2004, 2005 Elias Volanakis and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *����Elias Volanakis - initial API and implementation
 *******************************************************************************/
package com.sp.r2.editorplugin.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.sp.r2.editorplugin.figures.ActorFigure;
import com.sp.r2.editorplugin.model.Model;
import com.sp.r2.editorplugin.model.ModelElement;

/**
 * EditPart used for RectangularShape instances.
 * <p>
 * This edit part must implement the PropertyChangeListener interface, so it can
 * be notified of property changes in the corresponding model element.
 * </p>
 * 
 * @author Elias Volanakis
 */
class ShapeEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener, NodeEditPart {

	private ConnectionAnchor anchor;
	private PropertyChangeEvent evt;

	/**
	 * Upon activation, attach to the model element as a property change
	 * listener.
	 */
	public void activate() {
		if (!isActive()) {
			super.activate();
			((ModelElement) getModel()).addPropertyChangeListener(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		// allow removal of the associated model element
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new ShapeComponentEditPolicy());

		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new EditPartSelectionEditPolicy());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		Model shape = (Model) getModel();
		ActorFigure figure = new ActorFigure(shape.getData());

		Dimension d = shape.getSize();
		d.setWidth(figure.getBounds().width + 35);
		shape.setSize(figure.getDimension());
		return figure;
	}

	/**
	 * Upon deactivation, detach from the model element as a property change
	 * listener.
	 */
	public void deactivate() {
		if (isActive()) {
			super.deactivate();
			((ModelElement) getModel()).removePropertyChangeListener(this);
		}
	}

	private Model getCastedModel() {
		return (Model) getModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.
	 * PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		this.evt = evt;
		boolean contains = getCastedModel().getPropertiesIdMap().containsKey(prop);
		if (contains || Model.LOCATION_PROP.equals(prop) || Model.SIZE_PROP.equals(prop))
			refreshVisuals();
	}

	protected void refreshVisuals() {
		// notify parent container of changed position & location
		Rectangle bounds = new Rectangle(getCastedModel().getLocation(),
				getCastedModel().getSize());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(),
				bounds);

		ActorFigure af = (ActorFigure) getFigure();

		if (evt != null) {
			boolean modelContainsKey = getCastedModel().getPropertiesIdMap().containsKey(evt.getPropertyName());
			boolean figureContainsValue = af.getLabelMap().containsKey(evt.getOldValue());
			// change the properties shown in the figure
			if (modelContainsKey && figureContainsValue) {
				Label label = af.getLabelMap().get(evt.getOldValue());
				label.setText((String) evt.getNewValue());
				// remove old entry and put new key
				af.getLabelMap().remove(evt.getOldValue());
				af.getLabelMap().put((String) evt.getNewValue(), label);
			}
		}

		// only set new model values if the property change event isn't a move
		// or resize event
		if (evt != null && !(evt.getNewValue() instanceof Dimension))
			getCastedModel().getPropertiesIdMap().put(evt.getPropertyName(), (String) evt.getNewValue());

	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return null;
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return null;
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return null;
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return null;
	}

}