/*******************************************************************************
 * Copyright (c) 2018, 2019 Kiel University and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.elk.alg.test.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.elk.alg.force.options.ForceOptions;
import org.eclipse.elk.alg.test.framework.LayoutTestRunner;
import org.eclipse.elk.alg.test.framework.annotations.Algorithm;
import org.eclipse.elk.alg.test.framework.annotations.Configurator;
import org.eclipse.elk.alg.test.framework.annotations.GraphProvider;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;
import org.eclipse.elk.graph.util.ElkGraphUtil;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * An example for using configurator methods to configure the layout graph.
 */
@RunWith(LayoutTestRunner.class)
@Algorithm(ForceOptions.ALGORITHM_ID)
public class ConfiguratorTest {

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Sources

    /**
     * Supply a basic graph.
     */
    @GraphProvider
    public ElkNode basicGraph() {
        return ElkGraphUtil.createGraph();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Configuration

    // A property to test with
    private static final IProperty<String> TEST_PROPERTY = new Property<>("org.eclipse.elk.alg.common.test", "");
    private static final String TEST_PROPERTY_VALUE = "Test";

    /**
     * Configure our test property on the graph.
     */
    @Configurator
    public void configureStuff(final ElkNode graph) {
        graph.setProperty(TEST_PROPERTY, TEST_PROPERTY_VALUE);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Tests

    /**
     * Check that the property was set on the graph.
     */
    @Test
    public void testConfigurator(final ElkNode graph) {
        assertEquals("Property is not set properly.", TEST_PROPERTY_VALUE, graph.getProperty(TEST_PROPERTY));
    }

}
