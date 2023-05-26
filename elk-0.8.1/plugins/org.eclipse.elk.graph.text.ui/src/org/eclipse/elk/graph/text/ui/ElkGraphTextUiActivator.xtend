/*******************************************************************************
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.elk.graph.text.ui

import com.google.inject.Guice
import com.google.inject.Module
import org.apache.log4j.Logger
import org.eclipse.elk.graph.text.ide.ElkGraphIdeModule
import org.eclipse.elk.graph.text.ui.internal.TextActivator
import org.eclipse.xtext.util.Modules2

class ElkGraphTextUiActivator extends TextActivator {
    
    protected def Module getIdeModule(String grammar) {
        if (grammar == ORG_ECLIPSE_ELK_GRAPH_TEXT_ELKGRAPH) {
            return new ElkGraphIdeModule
        }
        throw new IllegalArgumentException(grammar)
    }
    
    override protected createInjector(String language) {
        try {
            val runtimeModule = getRuntimeModule(language)
            val ideModule = getIdeModule(language)
            val sharedStateModule = getSharedStateModule()
            val uiModule = getUiModule(language)
            val mergedModule = Modules2.mixin(runtimeModule, ideModule, sharedStateModule, uiModule)
            return Guice.createInjector(mergedModule)
        } catch (Exception e) {
            val logger = Logger.getLogger(ElkGraphTextUiActivator)
            logger.error("Failed to create injector for " + language, e)
            throw new RuntimeException("Failed to create injector for " + language, e)
        }
    }
    
}