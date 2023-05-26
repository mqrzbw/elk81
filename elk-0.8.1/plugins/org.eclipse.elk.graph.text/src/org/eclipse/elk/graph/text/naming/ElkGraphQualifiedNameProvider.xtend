/*******************************************************************************
 * Copyright (c) 2016 TypeFox GmbH (http://www.typefox.io) and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.elk.graph.text.naming

import org.eclipse.elk.graph.ElkEdgeSection
import org.eclipse.elk.graph.ElkGraphElement
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider
import org.eclipse.xtext.naming.QualifiedName

class ElkGraphQualifiedNameProvider extends DefaultDeclarativeQualifiedNameProvider {

    def QualifiedName qualifiedName(ElkGraphElement element) {
        prependParentNames(element, QualifiedName.create(element.identifier))
    }

    def QualifiedName qualifiedName(ElkEdgeSection section) {
        QualifiedName.create(section.identifier)
    }

    def private prependParentNames(EObject object, QualifiedName name) {
        var curr = object
        while (curr.eContainer !== null) {
            curr = curr.eContainer
            val parentsQualifiedName = getFullyQualifiedName(curr)
            if (parentsQualifiedName !== null)
                return parentsQualifiedName.append(name)
        }
        return name
    }

}
