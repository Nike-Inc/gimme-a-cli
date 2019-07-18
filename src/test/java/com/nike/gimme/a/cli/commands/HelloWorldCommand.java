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
import com.nike.gimme.a.cli.Terminal;
import org.springframework.beans.factory.annotation.Autowired;


public class HelloWorldCommand implements Command {

    @Parameter(names = {"--name"}, required = true)
    private String name;

    @Autowired
    private Terminal terminal;

    @Override
    public void execute() {
        terminal.info("Hello " + name);
    }
}
