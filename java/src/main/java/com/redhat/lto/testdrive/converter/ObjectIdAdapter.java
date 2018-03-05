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
package com.redhat.lto.testdrive.converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.bson.types.ObjectId;

/**
 * It helps JAXB to replace content into ObjectId object
 * 
 * @author Mauricio "Maltron" Leal <maltron at redhat dot com>
 */
public class ObjectIdAdapter extends XmlAdapter<String, ObjectId> {

    private static final Logger LOG = Logger.getLogger(ObjectIdAdapter.class.getName());

    public ObjectIdAdapter() {
    }
    
    @Override
    public ObjectId unmarshal(String content) throws Exception {
        LOG.log(Level.INFO, ">>> unmarshall() Content:{0}", content);
        if(content == null) throw new Exception("### ObjectIdAdapter: content is null");
        return new ObjectId(content);
    }

    @Override
    public String marshal(ObjectId objectId) throws Exception {
        LOG.log(Level.INFO, ">>> marshall() Content:{0}", objectId.toString());
        if(objectId == null) throw new Exception("### ObjectIdAdapter: objectId is null");
        return objectId.toString();
    }
}
