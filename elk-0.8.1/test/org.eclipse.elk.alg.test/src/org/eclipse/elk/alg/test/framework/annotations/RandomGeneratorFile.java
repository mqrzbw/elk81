/*******************************************************************************
 * Copyright (c) 2018, 2019 Kiel University and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.elk.alg.test.framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.elk.alg.test.framework.io.AbstractResourcePath;

/**
 * Flags a method as supplying a file which specifies how to produce random graphs. A method such annotated must not
 * expect any parameters and have return type {@link AbstractResourcePath}.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RandomGeneratorFile {

}
