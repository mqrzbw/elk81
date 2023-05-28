/*******************************************************************************
 * Copyright (c) 2012, 2015 Kiel University and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.elk.alg.graphviz.dot;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import java.util.HashMap;
import java.util.Map;

/**
 * Activator for the Graphviz Dot plugin. This class was originally generated by Xtext.
 * 
 * @author msp
 */
public class GraphvizDotActivator extends Plugin {

    /** The Graphviz Dot language name. */
    public static final String GRAPHVIZDOT = "org.eclipse.elk.alg.graphviz.dot.GraphvizDot";

    /** the injectors cache. */
    private Map<String, Injector> injectors = new HashMap<String, Injector>();

    /** the shared instance. */
    private static GraphvizDotActivator instance;

    /**
     * Returns the injector for the Graphviz Dot language.
     * 
     * @param languageName the language name
     * @return the injector
     */
    public Injector getInjector(final String languageName) {
        Injector injector = injectors.get(languageName);
        if (injector == null) {
            Module runtimeModule = getRuntimeModule(languageName);
            injector = Guice.createInjector(runtimeModule);
            injectors.put(languageName, injector);
        }
        return injector;
    }

    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        instance = this;
    }

    @Override
    public void stop(final BundleContext context) throws Exception {
        injectors.clear();
        instance = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * 
     * @return the activator instance
     */
    public static GraphvizDotActivator getInstance() {
        return instance;
    }

    /**
     * Returns the runtime module for the Graphviz Dot grammar.
     * 
     * @param grammar the grammar name
     * @return the runtime module
     */
    protected Module getRuntimeModule(final String grammar) {
        if (GRAPHVIZDOT.equals(grammar)) {
            return new GraphvizDotRuntimeModule();
        }
        throw new IllegalArgumentException(grammar);
    }

}