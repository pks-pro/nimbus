/*
 * Copyright 1999-2009 University of Chicago
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.nimbustools.ctxbroker.blackboard;

public class DataPair {
    private final String name;
    private final String value;

    public DataPair(String name) {
        this(name, null);
    }

    public DataPair(String name, String value) {

        // value may be null but not name

        if (name == null) {
            throw new IllegalArgumentException("name may not be null");
        }
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "DataPair{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
