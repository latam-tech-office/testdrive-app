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
package com.redhat.lto.testdrive.model;

import com.redhat.lto.testdrive.JSON;
import com.redhat.lto.testdrive.MongoType;

import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.Document;

/**
 * Represents an answer from a Question within a Survey
 * 
 * @author Mauricio "Maltron" Leal <maltron at redhat dot com>
 */
@XmlRootElement(name="answer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Answer implements Serializable, JSON, MongoType<Answer> {

    private static final Logger LOG = Logger.getLogger(Answer.class.getName());
    private static final long serialVersionUID = 882893370538053242L;
    
    public static final String TAG_ORDER = "order";
    @XmlElement(name = TAG_ORDER, nillable = false, required = true)
    private int order;
    
    public static final String TAG_ANSWER = "answer";
    @XmlElement(name = TAG_ANSWER, nillable = false, required = true)
    private String answer;

    public Answer() {
    }

    public Answer(int order, String answer) {
        this.order = order;
        this.answer = answer;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.order;
        hash = 37 * hash + Objects.hashCode(this.answer);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Answer other = (Answer) obj;
        if (this.order != other.order) {
            return false;
        }
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        return true;
    }
   
    @Override
    public String toString() {
        return toJSON().build().toString();
    }
    
    // JSON JSON JSON JSON JSON JSON JSON JSON JSON JSON JSON JSON JSON JSON 
    //   JSON JSON JSON JSON JSON JSON JSON JSON JSON JSON JSON JSON JSON JSON 
    /**
     * Return the content in a JSON Format
     * @return JsonObject that represents this object */
    @Override
    public JsonObjectBuilder toJSON() {
        return Json.createObjectBuilder()
                .add(TAG_ORDER, this.order)
                .add(TAG_ANSWER, this.answer);
    }    
    
    
    // MONGO TYPE MONGO TYPE MONGO TYPE MONGO TYPE MONGO TYPE MONGO TYPE MONGO
    //   MONGO TYPE MONGO TYPE MONGO TYPE MONGO TYPE MONGO TYPE MONGO TYPE MONGO    
    /**
     * Convert a object into a Mongo's DOcument */
    public Document toDocument() {
        return new Document().append(TAG_ORDER, this.order)
                .append(TAG_ANSWER, this.answer);
    }

    /**
     * Returns a object based on a Document */
    public void fromDocument(Document document) {
        setOrder(document.getInteger(TAG_ORDER, 0));
        setAnswer(document.getString(TAG_ANSWER));
    }
}
