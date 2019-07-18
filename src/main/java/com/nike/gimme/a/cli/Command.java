/*
 * Copyright 2019-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 */

package com.nike.gimme.a.cli;

/**
 * A CLI Command.
 * <p>
 * To add a command to your CLI, implement this interface and optionally add JCommander annotations.
 */
public interface Command {

    /**
     * Perform the actual action of the Command
     *
     * @throws Exception on error
     */
    void execute() throws Exception;
}
