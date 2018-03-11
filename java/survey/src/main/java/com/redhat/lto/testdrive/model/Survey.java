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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * A specific Survey to gather data from customers 
 * 
 * @author Mauricio "Maltron" Leal <maltron at redhat dot com>
 */
@XmlRootElement(name="survey")
@XmlAccessorType(XmlAccessType.FIELD)
public class Survey implements Serializable, JSON, MongoType<Survey> {

    private static final Logger LOG = Logger.getLogger(Survey.class.getName());
    private static final long serialVersionUID = -5847791377362246378L;
    
    public static final String COLLECTION = "surveys";
    
    public static final String TAG_ID = "_id";
    @XmlElement(name = TAG_ID, nillable = true, required = false)
    private ObjectId ID;    
    
    public static final String TAG_NAME = "name";
    @XmlElement(name = TAG_NAME, nillable = false, required = true)
    private String name;

    public static final String TAG_QUESTIONS = "questions";
    @XmlElement(name = TAG_QUESTIONS, nillable = false, required = true)
    private Question[] questions;
    
    public static final String TAG_NUMBER_OF_QUESTIONS = "numberOfQuestions";
    @XmlElement(name = TAG_NUMBER_OF_QUESTIONS, nillable = false, required = true)
    private int numberOfQuestions;
    
    public Survey() {
    }

    public Survey(String name, Question[] questions) {
        this.name = name;
        this.questions = questions;
        this.numberOfQuestions = questions != null ? questions.length : 0;
    }

    public ObjectId getID() {
        return ID;
    }

    public void setID(ObjectId ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.ID);
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Arrays.deepHashCode(this.questions);
        hash = 53 * hash + this.numberOfQuestions;
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
        final Survey other = (Survey) obj;
        if (this.numberOfQuestions != other.numberOfQuestions) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (!Arrays.deepEquals(this.questions, other.questions)) {
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
        JsonObjectBuilder builder = Json.createObjectBuilder();
        if(this.ID != null) builder.add(TAG_ID, this.ID.toHexString());
        builder.add(TAG_NAME, this.name)
               .add(TAG_NUMBER_OF_QUESTIONS, this.numberOfQuestions);
        
        if(this.questions != null && this.questions.length > 0) {
            JsonArrayBuilder array = Json.createArrayBuilder();
            for(Question question: this.questions) array.add(question.toJSON());
            builder.add(TAG_QUESTIONS, array);
        }
        
        return builder;
    }
    
    // MONGO TYPE MONGO TYPE MONGO TYPE MONGO TYPE MONGO TYPE MONGO TYPE MONGO
    //   MONGO TYPE MONGO TYPE MONGO TYPE MONGO TYPE MONGO TYPE MONGO TYPE MONGO    
    /**
     * Convert a object into a Mongo's DOcument */
    @Override
    public Document toDocument() {
        Document document = new Document();
        if(this.ID != null) document.append(TAG_ID, this.ID);
        document.append(TAG_NAME, this.name)
                .append(TAG_NUMBER_OF_QUESTIONS, this.numberOfQuestions);
        
        if(this.questions != null && this.questions.length > 0) {
            List<Document> arrayQuestions = new ArrayList<>(this.questions.length);
            for(Question question: this.questions) arrayQuestions.add(question.toDocument());
            document.append(TAG_QUESTIONS, arrayQuestions);
        }
        
        return document;
    }

    /**
     * Returns a object based on a Document
     * @param document */
    @Override
    public void fromDocument(Document document) {
        this.ID = document.getObjectId(TAG_ID);
        this.name = document.getString(TAG_NAME);
        this.numberOfQuestions = document.getInteger(TAG_NUMBER_OF_QUESTIONS, 0);
        
        List<Document> arrayQuestions = (List<Document>)document.get(TAG_QUESTIONS);
        if(arrayQuestions != null && !arrayQuestions.isEmpty()) {
            Question[] questions = new Question[arrayQuestions.size()];
            for(int i=0; i < arrayQuestions.size(); i++) {
                questions[i] = new Question();
                questions[i].fromDocument(arrayQuestions.get(i));
            }
            setQuestions(questions);
        }
    }
}
