/*
 * Copyright 2015-2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hawkular.client.core.jaxrs;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class RestResponseFilter implements ClientResponseFilter {
    private static final Logger _logger = LoggerFactory.getLogger(RestResponseFilter.class);

    //private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext)
            throws IOException {
        logResponses(responseContext);
    }

    private void logResponses(ClientResponseContext responseContext) {
        if (_logger.isDebugEnabled()) {
            _logger.debug("<< Response headers:{}", responseContext.getHeaders());
            _logger.debug("<< Status -> code:{}, message:{}",
                    responseContext.getStatusInfo().getStatusCode(),
                    responseContext.getStatusInfo().getReasonPhrase());
            /*
            try {
                String data = null;
                if (responseContext.getEntityStream() != null) {
                    data = IOUtils.toString(responseContext.getEntityStream());
                }
                _logger.debug("<< Data:{}", data);
            } catch (Exception ex) {
                _logger.error("Exception,", ex);
            }
            */
        }
    }

}
