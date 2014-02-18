/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.olingo.odata4.commons.api.edm;

/**
 * A CSDL EntityContainer element.
 *
 * <br/>
 * EdmEntityContainer hold the information of EntitySets, Singletons, ActionImports and FunctionImports contained
 */
public interface EdmEntityContainer extends EdmNamed {

  /**
   * @return namespace of this entity container
   */
  String getNamespace();

  /**
   * Get contained Singleton by name.
   *
   * @param name
   * @return {@link EdmSingleton}
   */
  EdmSingleton getSingleton(String name);

  /**
   * Get contained EntitySet by name.
   *
   * @param name
   * @return {@link EdmEntitySet}
   */
  EdmEntitySet getEntitySet(String name);

  /**
   * Get contained ActionImport by name.
   *
   * @param name
   * @return {@link EdmActionImport}
   */
  EdmActionImport getActionImport(String name);

  /**
   * Get contained FunctionImport by name.
   *
   * @param name
   * @return {@link EdmFunctionImport}
   */
  EdmFunctionImport getFunctionImport(String name);

}
