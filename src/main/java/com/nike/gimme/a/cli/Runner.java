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

package com.nike.gimme.a.cli;

import com.beust.jcommander.JCommander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Parses arguments and runs commands
 */
@Component
public class Runner {

    private final JCommander jCommander;
    private final Terminal terminal;
    private final Help help;
    private final Registry registry;

    /**
     * Parses arguments and runs commands
     */
    @Autowired
    public Runner(JCommander jCommander,
                  Terminal terminal,
                  Help help,
                  Registry registry) {

        this.jCommander = jCommander;
        this.terminal = terminal;
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
            terminal.error(e);
            help.printUsage(commandName);
        }
    }
}
