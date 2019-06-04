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

import com.beust.jcommander.Parameters;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility methods for getting names for commands.
 */
public class NameUtils {

    /**
     * Get the command name from the @Parameters annotation but if no name has been defined
     * then build one.
     */
    public static String[] getCommandNames(Class<? extends Command> commandClass) {
        Parameters p = commandClass.getAnnotation(Parameters.class);
        if (p != null && p.commandNames().length > 0) {
            return p.commandNames();
        } else {
            // if command wasn't annotated with a @Parameters name then generate a name from the class
            return new String[]{createCommandNameFromClass(commandClass)};
        }
    }

    /**
     * Creates a sensible default command name from the class.
     * <p>
     * E.g. HelloWorld.class becomes "hello-world",
     * BackupCommand.class becomes "backup".
     */
    static String createCommandNameFromClass(Class<? extends Command> commandClass) {
        String name = commandClass.getSimpleName();
        Matcher m = Pattern.compile("(?<=[a-z0-9])[A-Z]").matcher(name);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "-" + m.group());
        }
        m.appendTail(sb);
        return StringUtils.removeEnd(sb.toString().toLowerCase(), "-command");
    }
}
