/*
 * Copyright 2019-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 */

package com.nike.gimme.a.cli;

import com.github.tomaslanger.chalk.Chalk;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

/**
 * This class is used for interacting with Terminal input/output.
 * <p>
 * It is a thin wrapper around System.out, System.err, and System.in that adds bold and color.
 * <p>
 * (Longer term the intention is there could be different implementations of this class for
 * better configurability)
 */
@Component
public class Terminal {

    /**
     * Info level newline to terminal output
     */
    public void info() {
        System.out.println();
    }

    /**
     * Info level message to terminal output.
     */
    public void info(String s) {
        System.out.println(s);
    }

    /**
     * Bold message to terminal output.
     */
    public void bold(String s) {
        System.out.println(Chalk.on(s).bold());
    }

    /**
     * Bold succes success message to terminal output
     */
    public void succes(String s) {
        System.out.println(Chalk.on(s).green().bold().toString());
    }

    /**
     * Warning level message to terminal output
     */
    public void warn(String s) {
        System.out.println(Chalk.on(s).yellow().bold().toString());
    }

    /**
     * Error level message to terminal output
     */
    public void error(String s) {
        System.err.println(Chalk.on(s).red().bold().toString());
    }

    /**
     * Error level message to terminal output
     */
    public void error(String message, Exception e) {
        System.err.println(Chalk.on(message).red().bold().toString());
        System.err.println(Chalk.on(ExceptionUtils.getStackTrace(e)).red().toString());
    }

    /**
     * Error level message to terminal output
     */
    public void error(Exception e) {
        System.err.println(Chalk.on(ExceptionUtils.getStackTrace(e)).red().bold().toString());
    }

    /**
     * Terminal OutputStream
     */
    public OutputStream getOutputStream() {
        return System.out;
    }

    /**
     * Terminal InputStream
     */
    public String getStandardInput() throws IOException {
        return IOUtils.toString(System.in);
    }
}
