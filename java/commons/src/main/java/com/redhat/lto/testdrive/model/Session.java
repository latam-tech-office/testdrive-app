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

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.bson.types.ObjectId;

/**
 * This represents a model a specific TestDrive's Session, which usually
 * it's a single day of activity towards a group of people
 *
 * @author Mauricio "Maltron" Leal <maltron at redhat dot com>
 */
@XmlRootElement(name="session")
@XmlAccessorType(XmlAccessType.FIELD)
public class Session implements Serializable {

    private static final Logger LOG = Logger.getLogger(Session.class.getName());
    private static final long serialVersionUID = -5019544301887724526L;
    
    public static final String TAG_ID = "_id";
    @XmlElement(name = TAG_ID, nillable = true, required = false)    
    private ObjectId ID;    
    
    public static final String TAG_DATE = "date";
    @XmlElement(name = TAG_DATE, nillable = false, required = true)
    private Date date; // When the TestDrive will take place
    
    public static final String TAG_LOCATION = "location";
    @XmlElement(name = TAG_LOCATION, nillable = false, required = true)
    private String location; // What city/country will take place
    
    public static final String TAG_ATTENDEES = "attendees";
    @XmlElement(name = TAG_ATTENDEES, nillable = true)
    private Attendee[] attendees;
    
//    private Survey preSurvey;
//    private Survey postSurvey;

    public Session() {
    }

}
