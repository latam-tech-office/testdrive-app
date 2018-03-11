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

/**
 * A question within a Survey
 * 
 * @author Mauricio "Maltron" Leal <maltron at redhat dot com>
 */
@XmlRootElement(name="question")
@XmlAccessorType(XmlAccessType.FIELD)
public class Question implements Serializable, JSON, MongoType<Question> {

    private static final Logger LOG = Logger.getLogger(Question.class.getName());

    public static final String TAG_ORDER = "order";
    @XmlElement(name = TAG_ORDER, nillable = false, required = true)
    private int order;
    
    public static final String TAG_QUESTION = "question";
    @XmlElement(name = TAG_QUESTION, nillable = false, required = true)
    private String question;
    
    public static final String TAG_QUESTION_TYPE = "type";
    @XmlElement(name = TAG_QUESTION_TYPE, nillable = false, required = true)
    private QuestionType type;
    
    public static final String TAG_ANSWERS = "answers";
    @XmlElement(name = TAG_ANSWERS, nillable = false, required = true)
    private Answer[] answers;    

    public Question() {
    }
    public Question(int order, String question, QuestionType type) {
        this(order, question, type, null);
    }

    public Question(int order, String question, QuestionType type, Answer[] answers) {
        this.order = order;
        this.question = question;
        this.type = type;
        this.answers = answers;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public Answer[] getAnswers() {
        return answers;
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.order;
        hash = 29 * hash + Objects.hashCode(this.question);
        hash = 29 * hash + Objects.hashCode(this.type);
        hash = 29 * hash + Arrays.deepHashCode(this.answers);
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
        final Question other = (Question) obj;
        if (this.order != other.order) {
            return false;
        }
        if (!Objects.equals(this.question, other.question)) {
            return false;
        }
        if (this.type != other.type) {
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
        builder.add(TAG_ORDER, this.order)
                .add(TAG_QUESTION, this.question)
                .add(TAG_QUESTION_TYPE, this.type.toString());
        
        // Assuming answers are NOT null
        if(this.answers != null && this.answers.length > 0) {
            JsonArrayBuilder array = Json.createArrayBuilder();
            for(Answer answer: this.answers) array.add(answer.toJSON());
            builder.add(TAG_ANSWERS, array);
        }
        
        return builder;
    }
    
    // MONGO TYPE MONGO TYPE MONGO TYPE MONGO TYPE MONGO TYPE MONGO TYPE MONGO
    //   MONGO TYPE MONGO TYPE MONGO TYPE MONGO TYPE MONGO TYPE MONGO TYPE MONGO    
    /**
     * Convert a object into a Mongo's DOcument */
    public Document toDocument() {
        Document document = new Document();
        document.append(TAG_ORDER, this.order)
                .append(TAG_QUESTION, this.question)
                .append(TAG_QUESTION_TYPE, this.type.toString());
        
        if(this.answers != null && this.answers.length > 0) {
            List<Document> arrayAnswers = new ArrayList<>(this.answers.length);
            for(Answer answer: this.answers) arrayAnswers.add(answer.toDocument());
            document.append(TAG_ANSWERS, arrayAnswers);
        }
            
        return document;
    }

    /**
     * Returns a object based on a Document */
    public void fromDocument(Document document) {
        setOrder(document.getInteger(TAG_ORDER, this.order));
        setQuestion(document.getString(TAG_QUESTION));
        setType(QuestionType.valueOf(document.getString(TAG_QUESTION_TYPE)));
        
        List<Document> arrayAnswers = (List<Document>)document.get(TAG_ANSWERS);
        if(arrayAnswers != null && !arrayAnswers.isEmpty()) {
            Answer[] answers = new Answer[arrayAnswers.size()];
            for(int i=0; i < arrayAnswers.size(); i++) {
                answers[i] = new Answer();
                answers[i].fromDocument(arrayAnswers.get(i));
            }
            setAnswers(answers);
        }
    }
}
