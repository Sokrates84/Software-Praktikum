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
package org.eclipse.gef.examples.shapes.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import org.eclipse.gef.examples.shapes.model.Connection;
import org.eclipse.gef.examples.shapes.model.ModelElement;
import org.eclipse.gef.examples.shapes.model.RectangularShape;
import org.eclipse.gef.examples.shapes.model.commands.ConnectionCreateCommand;
import org.eclipse.gef.examples.shapes.model.commands.ConnectionReconnectCommand;

import figure.ActorFigure;

/**
 * EditPart used for RectangularShape instances.
 * <p>
 * This edit part must implement the PropertyChangeListener interface, so it can
 * be notified of property changes in the corresponding model element.
 * </p>
 * 
 * @author Elias Volanakis
 */
class ShapeEditPart extends AbstractGraphicalEditPart
		implements PropertyChangeListener, NodeEditPart {

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
		// allow the creation of connections and
		// and the reconnection of connections between Shape instances
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new GraphicalNodeEditPolicy() {
					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#
					 * getConnectionCompleteCommand
					 * (org.eclipse.gef.requests.CreateConnectionRequest)
					 */
					protected Command getConnectionCompleteCommand(
							CreateConnectionRequest request) {
						ConnectionCreateCommand cmd = (ConnectionCreateCommand) request
								.getStartCommand();
						cmd.setTarget((RectangularShape) getHost().getModel());
						return cmd;
					}

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#
					 * getConnectionCreateCommand
					 * (org.eclipse.gef.requests.CreateConnectionRequest)
					 */
					protected Command getConnectionCreateCommand(
							CreateConnectionRequest request) {
						RectangularShape source = (RectangularShape) getHost()
								.getModel();
						int style = ((Integer) request.getNewObjectType())
								.intValue();
						ConnectionCreateCommand cmd = new ConnectionCreateCommand(
								source, style);
						request.setStartCommand(cmd);
						return cmd;
					}

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#
					 * getReconnectSourceCommand
					 * (org.eclipse.gef.requests.ReconnectRequest)
					 */
					protected Command getReconnectSourceCommand(
							ReconnectRequest request) {
						Connection conn = (Connection) request
								.getConnectionEditPart().getModel();
						RectangularShape newSource = (RectangularShape) getHost()
								.getModel();
						ConnectionReconnectCommand cmd = new ConnectionReconnectCommand(
								conn);
						cmd.setNewSource(newSource);
						return cmd;
					}

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#
					 * getReconnectTargetCommand
					 * (org.eclipse.gef.requests.ReconnectRequest)
					 */
					protected Command getReconnectTargetCommand(
							ReconnectRequest request) {
						Connection conn = (Connection) request
								.getConnectionEditPart().getModel();
						RectangularShape newTarget = (RectangularShape) getHost()
								.getModel();
						ConnectionReconnectCommand cmd = new ConnectionReconnectCommand(
								conn);
						cmd.setNewTarget(newTarget);
						return cmd;
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		RectangularShape shape = (RectangularShape) getModel();
		ActorFigure figure = new ActorFigure(shape.getData());
		figure.setVisible(true);

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

	private RectangularShape getCastedModel() {
		return (RectangularShape) getModel();
	}

	protected ConnectionAnchor getConnectionAnchor() {
		if (anchor == null) {
			if (getModel() instanceof RectangularShape) {
				// anchor = new LabelAnchor((Label) getFigure());
				// anchor = new RoundedRectangleAnchor(null, null);
				anchor = new ChopboxAnchor(getFigure());
			} else
				// if Shapes gets extended the conditions above must be updated
				throw new IllegalArgumentException("unexpected model");
		}

		return anchor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#
	 * getModelSourceConnections ()
	 */
	protected List getModelSourceConnections() {
		return getCastedModel().getSourceConnections();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#
	 * getModelTargetConnections ()
	 */
	protected List getModelTargetConnections() {
		return getCastedModel().getTargetConnections();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef
	 * .ConnectionEditPart)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		return getConnectionAnchor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef
	 * .Request)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return getConnectionAnchor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef
	 * .ConnectionEditPart)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		return getConnectionAnchor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef
	 * .Request)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return getConnectionAnchor();
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
		boolean contains = getCastedModel().getPropertiesIdMap()
				.containsKey(prop);
		if (contains || RectangularShape.LOCATION_PROP.equals(prop)
				|| RectangularShape.SIZE_PROP.equals(prop)) {
			refreshVisuals();
		} else if (RectangularShape.SOURCE_CONNECTIONS_PROP.equals(prop)) {
			refreshSourceConnections();
		} else if (RectangularShape.TARGET_CONNECTIONS_PROP.equals(prop)) {
			refreshTargetConnections();
		}
	}

	protected void refreshVisuals() {
		// notify parent container of changed position & location
		// if this line is removed, the XYLayoutManager used by the parent
		// container
		// (the Figure of the ShapesDiagramEditPart), will not know the bounds
		// of this figure
		// and will not draw it correctly.
		Rectangle bounds = new Rectangle(getCastedModel().getLocation(),
				getCastedModel().getSize());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(),
				bounds);

		ActorFigure af = (ActorFigure) getFigure();

		// change the properties shown in the figure
		if (evt != null
				&& getCastedModel().getPropertiesIdMap()
						.containsKey(evt.getPropertyName())
				&& af.getLabelMap().containsKey(evt.getOldValue())) {
			af.getLabelMap().get(evt.getOldValue())
					.setText((String) evt.getNewValue());
		}

		// only set new model values if the property change event isn't a move
		// or resize event
		if (evt != null && !(evt.getNewValue() instanceof Dimension))
			getCastedModel().getPropertiesIdMap().put(evt.getPropertyName(),
					(String) evt.getNewValue());

	}

}