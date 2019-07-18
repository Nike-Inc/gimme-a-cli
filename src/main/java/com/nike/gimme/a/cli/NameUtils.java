/*
 * Copyright 2019-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
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
