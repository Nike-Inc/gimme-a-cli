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
