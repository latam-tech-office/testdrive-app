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
package com.redhat.lto.testdrive.resource;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * A simple ping resource to make sure the application is running
 * It's also used to get Kubernetes/OpenShift this application is 
 * responding properly
 *
 * @author Mauricio "Maltron" Leal <maltron at redhat dot com>
 */
@Path("/ping")
public class PingResource implements Serializable {

    private static final Logger LOG = Logger.getLogger(PingResource.class.getName());

    @GET
    public Response ping() {
        return Response.ok().build();
    }
}
