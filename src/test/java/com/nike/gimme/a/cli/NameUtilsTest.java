/*
 * Copyright 2019-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 */

package com.nike.gimme.a.cli;

import com.beust.jcommander.Parameters;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class NameUtilsTest {

    @Parameters(commandNames = {"one", "two"})
    class FakeCommand implements Command {

        @Override
        public void execute() throws Exception {
        }
    }

    class SecondFakeCommand implements Command {

        @Override
        public void execute() throws Exception {
        }
    }

    @Test
    public void testGetCommandNames() {
        List<String> names = Arrays.asList(NameUtils.getCommandNames(FakeCommand.class));
        assertTrue(names.contains("one"));
        assertTrue(names.contains("two"));
    }

    @Test
    public void testGetCommandNames2() {
        List<String> names = Arrays.asList(NameUtils.getCommandNames(SecondFakeCommand.class));
        assertTrue(names.contains("second-fake"));
    }
}
