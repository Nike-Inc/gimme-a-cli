/*
 * Copyright 2019-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 */

package com.nike.gimme.a.cli;

import com.beust.jcommander.JCommander;
import com.nike.gimme.a.cli.commands.FakeCommand;
import com.nike.gimme.a.cli.testutils.StringBufferTerminal;
import org.junit.Test;

import static org.junit.Assert.*;

public class GimmeACliTest {

    @Test
    public void testHelp() {
        Config config = Config.builder()
                .withCliName("my-cli")
                .withPackagesToScan("com.nike.gimme.a.cli.commands")
                .withSingleLineDescription("my-single-line-description")
                .withLongDescription("my-long-description")
                .build();

        GimmeACli gimmeACli = new GimmeACli(config);

        StringBufferTerminal terminal = new StringBufferTerminal();
        gimmeACli.getContext().registerBean(Terminal.class, () -> terminal);

        gimmeACli.run(new String[]{"--help"});

        assertTrue(terminal.getContents().contains("NAME\n    my-cli - my-single-line-description\n"));
        assertTrue(terminal.getContents().contains("USAGE\n    my-cli [--help] [<command-name> [command-args]]"));
        assertTrue(terminal.getContents().contains("my-long-description"));
        assertTrue(terminal.getContents().contains("fake"));
        assertTrue(terminal.getContents().contains("hello-world"));
    }

    @Test
    public void sanityTestHelpWithActualTerminal() {
        Config config = Config.builder()
                .withCliName("my-cli")
                .withPackagesToScan("com.nike.gimme.a.cli.commands")
                .build();

        GimmeACli gimmeACli = new GimmeACli(config);
        gimmeACli.run(new String[]{"--help"});

        // no assertions here but sanity test just means it didn't blow up
    }

    @Test
    public void testHelpLongFormat() {
        Config config = Config.builder()
                .withCliName("my-cli")
                .withPackagesToScan("com.nike.gimme.a.cli.commands")
                .build();

        GimmeACli gimmeACli = new GimmeACli(config);
        gimmeACli.run(new String[]{"--help-long"});
    }

    @Test
    public void testNoArgs() {
        Config config = Config.builder()
                .withCliName("my-cli")
                .withPackagesToScan("com.nike.gimme.a.cli.commands")
                .build();

        GimmeACli gimmeACli = new GimmeACli(config);

        StringBufferTerminal terminal = new StringBufferTerminal();
        gimmeACli.getContext().registerBean(Terminal.class, () -> terminal);

        gimmeACli.run(new String[]{});

        assertTrue(terminal.getContents().contains("NAME\n" +
                "    my-cli\n" +
                "\n" +
                "USAGE\n" +
                "    my-cli [--help] [<command-name> [command-args]]"));

        assertTrue(terminal.getContents().contains("fake"));
        assertTrue(terminal.getContents().contains("hello-world"));
    }

    @Test
    public void testFakeCommand() {
        Config config = Config.builder()
                .withCliName("my-cli")
                .withPackagesToScan("com.nike.gimme.a.cli.commands")
                .build();

        GimmeACli gimmeACli = new GimmeACli(config);
        gimmeACli.run(new String[]{"fake"});

        FakeCommand fakeCommand = gimmeACli.getContext().getBean(FakeCommand.class);
        assertEquals(1, fakeCommand.getExecutionCount());
    }

    @Test
    public void testHelpForACommand() {
        Config config = Config.builder()
                .withCliName("my-cli")
                .withPackagesToScan("com.nike.gimme.a.cli.commands")
                .withSingleLineDescription("my-single-line-description")
                .withLongDescription("my-long-description")
                .build();

        GimmeACli gimmeACli = new GimmeACli(config);
        gimmeACli.run(new String[]{"--help", "fake"});

        // command should be parsed but not ran

        JCommander jCommander = gimmeACli.getContext().getBean(JCommander.class);
        assertEquals("fake", jCommander.getParsedCommand());

        FakeCommand fakeCommand = gimmeACli.getContext().getBean(FakeCommand.class);
        assertEquals(0, fakeCommand.getExecutionCount());
    }

    @Test
    public void testHelloWorldCommand() {
        Config config = Config.builder()
                .withCliName("my-cli")
                .withPackagesToScan("com.nike.gimme.a.cli.commands")
                .build();

        GimmeACli gimmeACli = new GimmeACli(config);

        StringBufferTerminal terminal = new StringBufferTerminal();
        gimmeACli.getContext().registerBean(Terminal.class, () -> terminal);

        gimmeACli.run(new String[] {"hello-world", "--name", "foo"});
        assertEquals("Hello foo\n", terminal.getContents());
    }
}
