/*
 * Copyright 2019-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 */

package com.nike.gimme.a.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.internal.Lists;
import com.github.tomaslanger.chalk.Chalk;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Print help and usage information for your CLI.
 * <p>
 * (I found that the default JCommander usage screen gets too big when you
 * have many commands with many options and extensive documentation so this
 * class changes the summary output).
 */
@Component
public class Help {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final String INDENT = "    ";

    private final Config config;
    private final GlobalOptions globalOptions;
    private final JCommander commander;

    @Autowired
    public Help(Config config, GlobalOptions globalOptions, JCommander commander) {
        this.config = config;
        this.globalOptions = globalOptions;
        this.commander = commander;
    }

    /**
     * True if --help parameter was passed in the args
     */
    public boolean isHelp(String[] args) {
        // slight hack for the --help global option, this is a more full-proof way of detecting if help was invoked.
        return CollectionUtils.containsAny(Arrays.asList(args), Lists.newArrayList("--help", "--help-long"));
    }

    /**
     * Print usage for either the command that was invoked or all commands
     */
    public void printUsage(@Nullable String commandName) {
        log.info("");
        if (commandName != null) {
            commander.usage(commandName);
        } else {
            if (globalOptions.isHelpLong()) {
                // this is a long format for generating doc
                commander.usage();
            } else {
                // pretty format for users
                printGeneralUsage();
            }
        }
    }

    /**
     * Print a brief usage for all commands.
     * <p>
     * (I found that the default JCommander usage screen gets too big when you
     * have many commands with many options and extensive documentation so this
     * class changes the summary output).
     */
    private void printGeneralUsage() {

        log.info(Chalk.on("NAME").bold().toString());
        log.info(INDENT + config.getCliName()
                + (config.getSingleLineDescription() != null ? " - " + config.getSingleLineDescription() : ""));
        log.info("");
        if (StringUtils.isNotBlank(config.getLongDescription())) {
            log.info(Chalk.on("DESCRIPTION").bold().toString());
            log.info(config.getLongDescription());
            log.info("");
        }
        log.info(Chalk.on("USAGE").bold().toString());
        log.info(INDENT + config.getCliName() + " [--help] [<command-name> [command-args]]");
        log.info("");
        log.info(Chalk.on("COMMANDS").bold().toString());
        commander.getCommands().keySet().forEach(command -> {
            String msg = INDENT + Chalk.on(command).bold();
            String description = commander.getCommandDescription(command);
            if (StringUtils.isNotBlank(description)) {
                // Use the first line of the description only for this summary page
                msg = msg + " - " + StringUtils.substringBefore(description, ".") + ".";
            }
            log.info(msg);
        });
        log.info("");
    }

}
