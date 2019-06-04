/*
 * Copyright (c) 2019 Nike, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nike.gimme.a.cli.testutils;

import com.nike.gimme.a.cli.Terminal;

/**
 * Terminal implementation that just appends to a StringBuffer for testing.
 */
public class StringBufferTerminal extends Terminal {

    private StringBuffer buffer = new StringBuffer();

    public void info() {
        buffer.append("\n");
    }

    public void info(String s) {
        buffer.append(s).append('\n');
    }

    public void bold(String s) {
        buffer.append(s).append('\n');
    }

    public void succes(String s) {
        buffer.append(s).append('\n');
    }

    public void warn(String s) {
        buffer.append(s).append('\n');
    }

    public void error(String s) {
        buffer.append(s).append('\n');
    }

    public void error(String message, Exception e) {
        buffer.append(message).append('\n');
    }

    public void error(Exception e) {
    }

    public String getContents() {
        return buffer.toString();
    }
}
