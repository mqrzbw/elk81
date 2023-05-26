/*******************************************************************************
 * Copyright (c) 2018, 2019 Kiel University and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.elk.alg.test.framework.io;

import com.google.common.base.Strings;

/**
 * Path to a resource inside ELK's models repository. To turn the path into an absolute path, the system property
 * {@code MODELS_REPO} needs to be set to the path where the models repository is checked out.
 */
public class ModelResourcePath extends AbstractPropertyDependentResourcePath {
    
    /** The system property or environment variable that contains our base path. */
    public static final String PATH_PROPERTY = "MODELS_REPO";

    /**
     * Creates a new instance that points to the given resource in ELK's models repository.
     */
    public ModelResourcePath(final String filePath) {
        if (Strings.isNullOrEmpty(filePath)) {
            throw new IllegalArgumentException("The file path cannot be empty.");
        }
        
        initialize(basePathForProperty(PATH_PROPERTY), filePath);
    }

}
