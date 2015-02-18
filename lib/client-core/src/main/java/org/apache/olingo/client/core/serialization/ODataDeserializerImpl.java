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
package org.apache.olingo.client.core.serialization;

import java.io.InputStream;

import javax.xml.stream.XMLStreamException;

import org.apache.olingo.client.api.data.ServiceDocument;
import org.apache.olingo.client.api.edm.xml.XMLMetadata;
import org.apache.olingo.client.api.serialization.ODataDeserializer;
import org.apache.olingo.client.core.data.JSONServiceDocumentDeserializer;
import org.apache.olingo.client.core.data.XMLServiceDocumentDeserializer;
import org.apache.olingo.client.core.edm.xml.EdmxImpl;
import org.apache.olingo.client.core.edm.xml.XMLMetadataImpl;
import org.apache.olingo.commons.api.data.Delta;
import org.apache.olingo.commons.api.data.ResWrap;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeException;
import org.apache.olingo.commons.api.edm.constants.ODataServiceVersion;
import org.apache.olingo.commons.api.format.ODataFormat;
import org.apache.olingo.commons.api.serialization.ODataDeserializerException;
import org.apache.olingo.commons.core.serialization.AtomDeserializer;
import org.apache.olingo.commons.core.serialization.JsonDeltaDeserializer;

public class ODataDeserializerImpl extends AbstractODataDeserializer implements ODataDeserializer {

  private final ODataFormat format;

  public ODataDeserializerImpl(final ODataServiceVersion version, final boolean serverMode, final ODataFormat format) {
    super(version, serverMode, format);
    this.format = format;
  }

  @Override
  public XMLMetadata toMetadata(final InputStream input) {
    try {
      return new XMLMetadataImpl(getXmlMapper().readValue(input, EdmxImpl.class));
    } catch (Exception e) {
      throw new IllegalArgumentException("Could not parse as Edmx document", e);
    }
  }

  @Override
  public ResWrap<ServiceDocument> toServiceDocument(final InputStream input) throws ODataDeserializerException {
    return format == ODataFormat.XML ?
        new XMLServiceDocumentDeserializer(version, false).toServiceDocument(input) :
        new JSONServiceDocumentDeserializer(version, false).toServiceDocument(input);
  }

  @Override
  public ResWrap<Delta> toDelta(final InputStream input) throws ODataDeserializerException {
    try {
      return format == ODataFormat.ATOM ?
          new AtomDeserializer(version).delta(input) :
          new JsonDeltaDeserializer(version, false).toDelta(input);
    } catch (XMLStreamException e) {
      throw new ODataDeserializerException(e);
    } catch (final EdmPrimitiveTypeException e) {
      throw new ODataDeserializerException(e);
    }
  }
}