/*******************************************************************************
 * Copyright (c) 2016 TypeFox GmbH (http://www.typefox.io) and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.elk.graph.text.ui.labeling

import org.eclipse.emf.ecore.EClass
import org.eclipse.xtext.ui.label.DefaultDescriptionLabelProvider

import static org.eclipse.elk.graph.ElkGraphPackage.Literals.*

/**
 * Provides labels for IEObjectDescriptions and IResourceDescriptions.
 */
class ElkGraphDescriptionLabelProvider extends DefaultDescriptionLabelProvider {

    def image(EClass clazz) {
        switch clazz {
            case ELK_NODE: 'elknode.gif'
            case ELK_EDGE: 'elkedge.gif'
            case ELK_PORT: 'elkport.gif'
            case ELK_LABEL: 'elklabel.gif'
        }
    }

}
