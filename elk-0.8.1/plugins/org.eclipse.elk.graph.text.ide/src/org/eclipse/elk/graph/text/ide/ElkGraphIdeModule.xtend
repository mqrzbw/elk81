/*******************************************************************************
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.elk.graph.text.ide

import org.eclipse.elk.graph.text.ide.contentassist.ElkGraphProposalProvider
import org.eclipse.xtext.ide.editor.contentassist.IdeContentProposalProvider

/**
 * Use this class to register ide components.
 */
class ElkGraphIdeModule extends AbstractElkGraphIdeModule {
    
    def Class<? extends IdeContentProposalProvider> bindIdeContentProposalProvider() {
        ElkGraphProposalProvider
    }
    
}
