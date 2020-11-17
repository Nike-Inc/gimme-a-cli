/*
 * Copyright 2019-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 */

package com.nike.gimme.a.cli.commands;

import com.beust.jcommander.Parameter;
import com.nike.gimme.a.cli.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HelloWorldCommand implements Command {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Parameter(names = {"--name"}, required = true)
    private String name;

    @Override
    public void execute() {
        log.info("Hello " + name);
    }
}
