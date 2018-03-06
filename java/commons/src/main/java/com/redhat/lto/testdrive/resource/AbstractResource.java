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
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.redhat.lto.testdrive.MongoType;
import com.redhat.lto.testdrive.setup.MongoProvider;
import com.redhat.lto.testrive.exception.MissingInformationException;
import com.redhat.lto.testrive.exception.NoContentException;
import com.redhat.lto.testrive.exception.NotFoundException;
import com.redhat.lto.testrive.exception.ServiceUnavailableException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 * Basic Resource which provides all the necessary CRUD operations 
 * 
 * @author Mauricio "Maltron" Leal <maltron at redhat dot com>
 */
public abstract class AbstractResource<T extends MongoType> implements Serializable {

    private static final Logger LOG = Logger.getLogger(AbstractResource.class.getName());
    private static final long serialVersionUID = -1144113959173611896L;    

    @EJB
    protected MongoProvider provider;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() throws NoContentException {
        LOG.log(Level.INFO, ">> all()");
        
        Collection<T> all = new ArrayList<>();
        for(Document document: getCollection().find().sort(sort()))
            all.add(newInstance(document));
        
        // No Content found 
        if(all.isEmpty()) throw new NoContentException("No surveys found");
        
        GenericEntity<Collection<T>> result = 
                new GenericEntity<Collection<T>>(all){};
        
        return Response.ok(result).build();
    }
    
    
    @GET @Path("/{ID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchByID(@PathParam("ID")String ID) throws NotFoundException {
        Document document = new Document().append("_id",new ObjectId(ID));
        Document found = getCollection().find(document).first();
        LOG.log(Level.INFO, ">>> fetchByID() {0}", found);
        
        if(found == null) 
            throw new NotFoundException("_ID "+ID+" not found");
        
        return Response.ok(newInstance(found), MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(T t) throws ServiceUnavailableException {
        Document document = t.toDocument();
        getCollection().insertOne(document); 
        LOG.log(Level.INFO, ">>> create() {0}", document.toJson());
        LOG.log(Level.INFO, ">>> ObjectID: HEX:{0} String:{1}", new Object[] {
            document.getObjectId("_id").toString(),
            document.getObjectId("_id").toHexString()}
        );
            
        // Returns the created ID for this information
        return Response.status(Response.Status.CREATED)
                .entity(document.getObjectId("_id").toHexString())
                .type(MediaType.APPLICATION_JSON).build();
    }
    
    @Path("/{ID}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("ID") String ID, T t) 
                         throws NotFoundException, MissingInformationException {
        // Is there any Person to work with ?
        if(t == null) 
            throw new MissingInformationException("No objects were found");
        
        Document found = fetchByObjectId(ID);
        
        UpdateResult result = getCollection().updateOne(found, 
                                    new Document("$set", t.toDocument()));
        if(result.getModifiedCount() == 0)
            return Response.status(Response.Status.GONE).build();
        
        // Indicates nothing was modified at all
        return Response.status(Response.Status.ACCEPTED).build();
    }
    
    @DELETE @Path("{ID}")
    public Response delete(@PathParam("ID")String ID) 
            throws NotFoundException, ServiceUnavailableException {
        Document found = fetchByObjectId(ID);
        DeleteResult result = getCollection().deleteOne(found);
        
        // Inidicate the content selected was deleted
        if(result.getDeletedCount() == 0)
            return Response.status(Response.Status.GONE).build();
        
        // Although the content was found, nothing was deleted
        return Response.status(Response.Status.ACCEPTED).build();
    }    
    
    protected Document fetchByObjectId(String ID) throws NotFoundException {
        Document found = getCollection().find(
                new Document().append("_id", new ObjectId(ID))).first();
        if(found == null) throw new NotFoundException("ID: "+ID+" not found");
        
        return found;
    }
    
    // ABSTRACT ABSTRACT ABSTRACT ABSTRACT ABSTRACT ABSTRACT ABSTRACT ABSTRACT 
    //  ABSTRACT ABSTRACT ABSTRACT ABSTRACT ABSTRACT ABSTRACT ABSTRACT ABSTRACT 
    
    /**
     * Returns a factory of creating a new instance */
    public abstract T newInstance(Document document);
    
    /**
     * Returns the way to sort the data out */
    public abstract Bson sort();
    
    /**
     * Returns the name of the Collection */
    public abstract MongoCollection<Document> getCollection();
}
