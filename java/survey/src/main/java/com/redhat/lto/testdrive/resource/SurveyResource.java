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

import com.mongodb.client.MongoCollection;
import com.redhat.lto.testdrive.model.Survey;
import com.redhat.lto.testdrive.setup.MongoProvider;
import com.redhat.lto.testrive.exception.NoContentException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bson.Document;

/**
 * Resource in charge of handling surveys for the whole system
 * 
 * @author Mauricio "Maltron" Leal <maltron at redhat dot com>
 */
@Path("/v1/survey")
public class SurveyResource implements Serializable {

    private static final Logger LOG = Logger.getLogger(SurveyResource.class.getName());

    @EJB
    private MongoProvider provider;    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() throws NoContentException // 204 - No Content
            {
        Collection<Survey> all = new ArrayList<>();
        
        // There is no content available
        if(all.isEmpty())
            throw new NoContentException("Survey data is empty");
        
        GenericEntity<Collection<Survey>> result = 
                new GenericEntity<Collection<Survey>>(all){};
        
        
        
        return Response.ok().build();
    }
    
    private MongoCollection<Document> getCollection() {
        return provider.getDatabase().getCollection(Survey.COLLECTION);
    }
}
