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
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

        OutputCapture.begin();

        gimmeACli.run(new String[]{"--help"});

        String output = OutputCapture.getStdOut();
        OutputCapture.restoreSystemOut();

        System.out.println(output);

        assertTrue(output.contains("    my-cli - my-single-line-description\n"));
        assertTrue(output.contains("    my-cli [--help] [<command-name> [command-args]]"));
        assertTrue(output.contains("my-long-description"));
        assertTrue(output.contains("fake"));
        assertTrue(output.contains("hello-world"));
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

        OutputCapture.begin();

        gimmeACli.run(new String[]{});

        String output = OutputCapture.getStdOut();
        OutputCapture.restoreSystemOut();

        System.out.println(output);
        assertTrue(output.contains("NAME"));
        assertTrue(output.contains("    my-cli [--help] [<command-name> [command-args]]"));
        assertTrue(output.contains("fake"));
        assertTrue(output.contains("hello-world"));
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


        OutputCapture.begin();

        gimmeACli.run(new String[]{"hello-world", "--name", "foo"});

        String output = OutputCapture.getStdOut();
        OutputCapture.restoreSystemOut();
        assertEquals("Hello foo\n", output);
    }
}
