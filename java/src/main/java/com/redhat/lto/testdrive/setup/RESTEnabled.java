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
package com.redhat.lto.testdrive.setup;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Enable REST to be part of the application
 *
 * @author Mauricio "Maltron" Leal <maltron at redhat dot com>
 */
@ApplicationPath("/api")
public class RESTEnabled extends Application {
}
