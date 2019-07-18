/*
 * Copyright 2019-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
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
