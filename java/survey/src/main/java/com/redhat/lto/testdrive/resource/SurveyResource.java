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
import com.mongodb.client.model.Sorts;
import com.redhat.lto.testdrive.model.Survey;
import java.util.logging.Logger;
import javax.ws.rs.Path;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * Resource in charge of handling surveys for the whole system
 * 
 * @author Mauricio "Maltron" Leal <maltron at redhat dot com>
 */
@Path("/v1/survey")
public class SurveyResource extends AbstractResource<Survey> {

    private static final Logger LOG = Logger.getLogger(SurveyResource.class.getName());
    
    /**
     * Returns a factory of creating a new instance */
    public Survey newInstance(Document document) {
        Survey newSurvey = new Survey();
        newSurvey.fromDocument(document);
        
        return newSurvey;
    }
    
    /**
     * Returns the way to sort the data out */
    public Bson sort() {
        return Sorts.ascending(Survey.TAG_NAME);
    }
    
    /**
     * Returns the name of the Collection */
    public MongoCollection<Document> getCollection() {
        return provider.getDatabase().getCollection(Survey.COLLECTION);
    }
}
