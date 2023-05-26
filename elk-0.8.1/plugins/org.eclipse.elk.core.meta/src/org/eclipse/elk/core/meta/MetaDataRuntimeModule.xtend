/*******************************************************************************
 * Copyright (c) 2016 Kiel University and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.elk.core.meta

import com.google.inject.Binder
import java.util.Set
import org.eclipse.elk.core.meta.jvmmodel.MelkDocumentationGenerator
import org.eclipse.xtext.generator.IOutputConfigurationProvider
import org.eclipse.xtext.generator.OutputConfiguration
import org.eclipse.xtext.generator.OutputConfigurationProvider

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
class MetaDataRuntimeModule extends AbstractMetaDataRuntimeModule {
    
    override bindIGenerator() {
        return MelkDocumentationGenerator
    }
    
    // register MelkOutputConfigurationProvider that inserts a configuration used to read files in the project
    override configure(Binder binder) {
        super.configure(binder)
        binder.bind(IOutputConfigurationProvider).to(MelkOutputConfigurationProvider)
    }
    
    static class MelkOutputConfigurationProvider extends OutputConfigurationProvider {
        public static String AD_INPUT = "AD_INPUT"

        override Set<OutputConfiguration> getOutputConfigurations() {
            val configurations = super.outputConfigurations
            val input = new OutputConfiguration(AD_INPUT);
            input.setDescription("Additional Documentation Input Folder");
            input.setOutputDirectory("./");
            configurations.add(input)
            configurations
        }

    }
}
