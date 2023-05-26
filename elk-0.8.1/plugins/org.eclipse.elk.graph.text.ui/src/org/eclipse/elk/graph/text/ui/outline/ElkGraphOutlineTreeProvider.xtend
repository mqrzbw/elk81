/*******************************************************************************
 * Copyright (c) 2016 TypeFox GmbH (http://www.typefox.io) and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.elk.graph.text.ui.outline

import org.eclipse.elk.graph.ElkEdge
import org.eclipse.xtext.ui.editor.outline.IOutlineNode
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider

/**
 * Customization of the outline tree structure.
 */
class ElkGraphOutlineTreeProvider extends DefaultOutlineTreeProvider {
    
    def dispatch createChildren(IOutlineNode parentNode, ElkEdge edge) {
        for (label : edge.labels) {
            createEObjectNode(parentNode, label)
        }
    }
    
}
