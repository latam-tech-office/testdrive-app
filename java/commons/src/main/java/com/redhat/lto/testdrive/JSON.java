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
package com.redhat.lto.testdrive;

import javax.json.JsonObjectBuilder;

/**
 * Indicates this content has the capability to generate JSON Content 
 * 
 * @author Mauricio "Maltron" Leal <maltron at redhat dot com>
 */
public interface JSON {
    
    /**
     * Return the content in a JSON Format
     * @return JsonObject that represents this object */
    public JsonObjectBuilder toJSON();
    
}
