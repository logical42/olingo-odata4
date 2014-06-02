/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.olingo.fit.tecsvc;

import static org.junit.Assert.assertNotNull;

import org.apache.olingo.client.api.communication.request.retrieve.EdmMetadataRequest;
import org.apache.olingo.client.api.communication.request.retrieve.ODataServiceDocumentRequest;
import org.apache.olingo.client.api.v4.ODataClient;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.commons.api.domain.ODataServiceDocument;
import org.apache.olingo.commons.api.edm.Edm;
import org.junit.Before;
import org.junit.Test;

public class BasicITCase {

  private static final String BASE_URI = "http://localhost:9080/tecsvc/odata.svc";

  private ODataClient odata;

  @Before
  public void before() {
    odata = ODataClientFactory.getV4();
  }

  @Test
  public void readServiceDocument() {
    ODataServiceDocumentRequest request = odata.getRetrieveRequestFactory().getServiceDocumentRequest(BASE_URI + "/");
    assertNotNull(request);
    ODataServiceDocument serviceDocument = request.execute().getBody();
    assertNotNull(serviceDocument);
  }

  @Test
  public void readMetadata() {
    EdmMetadataRequest request = odata.getRetrieveRequestFactory().getMetadataRequest(BASE_URI + "/$metadata");
    assertNotNull(request);
    Edm edm = request.execute().getBody();
    assertNotNull(edm);
  }
}
