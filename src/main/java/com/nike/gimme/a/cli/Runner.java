/*
 * Copyright 2019-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 */

package com.nike.gimme.a.cli;

import com.beust.jcommander.JCommander;
import com.github.tomaslanger.chalk.Chalk;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Parses arguments and runs commands
 */
@Component
public class Runner {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final JCommander jCommander;
    private final Help help;
    private final Registry registry;

    /**
     * Parses arguments and runs commands
     */
    @Autowired
    public Runner(JCommander jCommander,
                  Help help,
                  Registry registry) {

        this.jCommander = jCommander;
        this.help = help;
        this.registry = registry;

        // add sorted registry to jCommander
        registry.asSortedMap().forEach(jCommander::addCommand);
    }

    /**
     * Parse arguments and run the command
     */
    public void run(String[] args) {
        String commandName = null;
        try {
            try {
                jCommander.parse(args);
            } catch (Exception e) {
                // Invoking help may cause other parse errors but we only want
                // to throw those errors if help wasn't invoked.
                if (!help.isHelp(args)) {
                    throw e;
                }
            } finally {
                // Always try to determine the command, if possible
                commandName = jCommander.getParsedCommand();
            }
            if (help.isHelp(args) || commandName == null) {
                help.printUsage(commandName);
            } else {
                Command command = registry.getCommand(commandName);
                command.execute();
            }
        } catch (Exception e) {
            log.error(Chalk.on(ExceptionUtils.getStackTrace(e)).red().bold().toString());
            help.printUsage(commandName);
            System.exit(1);
        }
    }
}
