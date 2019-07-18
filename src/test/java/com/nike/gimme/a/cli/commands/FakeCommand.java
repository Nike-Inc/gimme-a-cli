/*
 * Copyright 2019-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 */

package com.nike.gimme.a.cli.commands;

import com.nike.gimme.a.cli.Command;


public class FakeCommand implements Command {

    private int executionCount = 0;

    @Override
    public void execute() {
        executionCount++;
    }

    public int getExecutionCount() {
        return executionCount;
    }
}
