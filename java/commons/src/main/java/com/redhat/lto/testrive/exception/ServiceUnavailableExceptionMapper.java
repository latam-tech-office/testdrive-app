/*
 * 
 * Copyright 2014 LATAM Tech Office <maltron at redhat dot com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.redhat.lto.testrive.exception;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Something happens during the 
 * 503 - Service Unavailable 
 * @author Mauricio "Maltron" Leal <maltron at redhat dot com>
 */
public class ServiceUnavailableExceptionMapper implements ExceptionMapper<ServiceUnavailableException> {

    private static final Logger LOG = Logger.getLogger(ServiceUnavailableExceptionMapper.class.getName());

    public ServiceUnavailableExceptionMapper() {
    }

    @Override
    public Response toResponse(ServiceUnavailableException ex) {
        LOG.log(Level.INFO, "### ServiceUnavailableException: {0}", ex.getMessage());
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
    }
}
