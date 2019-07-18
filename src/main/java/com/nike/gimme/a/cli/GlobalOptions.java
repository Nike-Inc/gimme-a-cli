/*
 * Copyright 2019-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 */

package com.nike.gimme.a.cli;

import com.beust.jcommander.Parameter;

/**
 * Global Options for JCommander
 */
public class GlobalOptions {

    @Parameter(names = {"--help"}, description = "Prints the usage screen for the command.", help = true)
    private boolean help = false;

    @Parameter(names = {"--help-long"}, description = "Prints full usage in long format (good for generating documentation)", help = true, hidden = true)
    private boolean helpLong = false;

    /**
     * True if --help option was passed
     */
    public boolean isHelp() {
        return help;
    }

    /**
     * True if --help-long option was passed
     */
    public boolean isHelpLong() {
        return helpLong;
    }

}
