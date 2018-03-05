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

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * HTTP Code 204 - No Content
 * 
 * @author Mauricio "Maltron" Leal <maltron at redhat dot com>
 */
public class NoContentExceptionMapper implements ExceptionMapper<NoContentException> {

    private static final Logger LOG = Logger.getLogger(NoContentExceptionMapper.class.getName());

    @Override
    public Response toResponse(NoContentException ex) {
        LOG.log(Level.INFO, "### 204 - NoContentException: {0}", ex.getMessage());
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
