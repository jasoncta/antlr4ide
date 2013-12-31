/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *   Jan Koehnlein - Initial API and implementation
 *******************************************************************************/
package com.github.jknack.ui.railroad.figures.primitives;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

/**
 * A connection between two {@link CrossPoint}s.
 *
 * @author Jan Koehnlein - Initial contribution and API
 */
public class Connection extends PolylineConnection {

  public Connection(final CrossPoint source, final CrossPoint target) {
    createAnchors(source, target);
    setLineCap(SWT.CAP_SQUARE);
  }

  private void createAnchors(final CrossPoint source, final CrossPoint target) {
    ConnectionAnchor sourceAnchor = new Anchor(source);
    ConnectionAnchor targetAnchor = new Anchor(target);
    setSourceAnchor(sourceAnchor);
    setTargetAnchor(targetAnchor);
  }

  protected static class Anchor extends ChopboxAnchor {

    public Anchor(final IFigure owner) {
      super(owner);
    }

    @Override
    public Point getLocation(final Point reference) {
      IFigure owner = getOwner();
      Rectangle bounds = owner.getBounds().getCopy();
      owner.translateToAbsolute(bounds);
      if (reference.x < bounds.getLeft().x) {
        return bounds.getLeft();
      } else {
        return bounds.getRight();
      }
    }

  }

}