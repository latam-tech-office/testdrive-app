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
import java.util.Objects;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Information about a single Attendee (in most case it is a Customer) 
 * 
 * @author Mauricio "Maltron" Leal <maltron at redhat dot com>
 */
@XmlRootElement(name="attendee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Attendee implements Serializable, JSON {

    private static final Logger LOG = Logger.getLogger(Attendee.class.getName());
    private static final long serialVersionUID = -7585999144693736855L;

    public static final String TAG_FIRSTNAME = "firstName";
    @XmlElement(name = TAG_FIRSTNAME, nillable = false, required = true, type = String.class)
    private String firstName;
    
    public static final String TAG_LASTNAME = "lastName";
    @XmlElement(name = TAG_LASTNAME, nillable = false, required = true, type = String.class)
    private String lastName;
    
    public static final String TAG_EMAIL = "email";
    @XmlElement(name = TAG_EMAIL, nillable = false, required = true, type = String.class)
    private String email;
    
    public static final String TAG_POSITION = "position";
    @XmlElement(name = TAG_POSITION, nillable = false, required = true, type = String.class)
    private String position;
    
    public static final String TAG_ORGANIZATION = "organization";
    @XmlElement(name = TAG_ORGANIZATION, nillable = false, required = true, type = String.class)
    private String organization;

    public Attendee() {
    }

    public Attendee(String firstName, String lastName, String email, 
                                        String position, String organization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.position = position;
        this.organization = organization;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.firstName);
        hash = 41 * hash + Objects.hashCode(this.lastName);
        hash = 41 * hash + Objects.hashCode(this.email);
        hash = 41 * hash + Objects.hashCode(this.position);
        hash = 41 * hash + Objects.hashCode(this.organization);
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
        final Attendee other = (Attendee) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.position, other.position)) {
            return false;
        }
        if (!Objects.equals(this.organization, other.organization)) {
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
                .add(TAG_FIRSTNAME, this.firstName)
                .add(TAG_LASTNAME, this.lastName)
                .add(TAG_EMAIL, this.email)
                .add(TAG_POSITION, this.position)
                .add(TAG_ORGANIZATION, this.organization);
    }    
}
