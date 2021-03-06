/*
 * Copyright 1999-2008 University of Chicago
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

package org.nimbustools.auto_common.ezpz_ca;

public class FindCAPubFile {

    // -------------------------------------------------------------------------
    // MAIN
    // -------------------------------------------------------------------------

    public static void main(String[] args) {

        if (args == null || args.length != 1) {
            System.err.println("Needs these arguments:\n" +
                    "1 - the ca directory path\n");
            System.exit(1);
        }

        try {
            System.out.println(new FindCAFile().findCAPubFile(args[0]));
        } catch (Exception e) {
            System.err.println("Problem: " + e.getMessage());
            System.exit(1);
        }
    }
}
