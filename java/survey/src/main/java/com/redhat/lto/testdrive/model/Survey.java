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
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.bson.types.ObjectId;

/**
 * A specific Survey to gather data from customers 
 * 
 * @author Mauricio "Maltron" Leal <maltron at redhat dot com>
 */
@XmlRootElement(name="survey")
@XmlAccessorType(XmlAccessType.FIELD)
public class Survey implements Serializable, JSON {

    private static final Logger LOG = Logger.getLogger(Survey.class.getName());
    private static final long serialVersionUID = -5847791377362246378L;
    
    public static final String COLLECTION = "surveys";
    
    public static final String TAG_ID = "_id";
    @XmlElement(name = TAG_ID, nillable = true, required = false)
    private ObjectId ID;    
    
    public static final String TAG_NAME = "name";
    @XmlElement(name = TAG_NAME, nillable = false, required = true)
    private String name;

    public static final String TAG_QUESTION = "question";
    @XmlElement(name = TAG_QUESTION, nillable = false, required = true)
    private Question question;
    
    public static final String TAG_ANSWERS = "answers";
    @XmlElement(name = TAG_ANSWERS, nillable = false, required = true)
    private Answer[] answers;

    public Survey() {
    }

    public Survey(String name, Question question, Answer[] answers) {
        this.name = name;
        this.question = question;
        this.answers = answers;
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
    

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer[] getAnswers() {
        return answers;
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.ID);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.question);
        hash = 97 * hash + Arrays.deepHashCode(this.answers);
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
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.question, other.question)) {
            return false;
        }
        if (!Arrays.deepEquals(this.answers, other.answers)) {
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
        if(this.ID != null) builder.add(TAG_ID, this.ID);
        
        return builder.                
                .add(TAG_NAME, this.name)
                .add(TAG_QUESTION, this.question.toJSON());
        // PENDING: Answers as Array
                
    }
}
