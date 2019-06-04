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
import org.reflections.Reflections;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Arrays;

/**
 * Create and run a CLI using JCommander and Spring.
 * <p>
 * It is easy to build your own CLI.
 * <p>
 * First, define a command by implementing an interface, e.g.
 * <pre>
 * {@code
 *     public class HelloWorld implements Command {
 *
 *        public void execute() {
 *            System.out.println("Hello World!");
 *        }
 *     }
 *
 * }
 * </pre>
 * Optionally, add <a href="http://jcommander.org/">JCommander</a> annotations to your command class.
 * <p>
 * Next, invoke GimmeACli from your Java main, e.g.
 * <pre>
 * {@code
 *     public static void main(String[] args) {
 *         new GimmeACli(
 *                 Config.builder()
 *                         .withCliName("my-cli-name")
 *                         .withPackagesToScan("com.nike")
 *                         .build()
 *         ).run(args);
 *     }
 * }
 * </pre>
 */
public class GimmeACli {

    private final Config config;
    private final AnnotationConfigApplicationContext context;

    public GimmeACli(Config config) {
        this.config = config;
        this.context = new AnnotationConfigApplicationContext();
    }

    /**
     * Start-up the CLI and run a command
     *
     * @param args the arguments from your main() method
     */
    public void run(String[] args) {
        context.registerBean(GlobalOptions.class);
        context.registerBean(Reflections.class, Arrays.asList(config.getPackagesToScan()));
        context.registerBean(Config.class, () -> config);
        context.registerBean(JCommander.class, () -> JCommander.newBuilder()
                .programName(config.getCliName())
                .columnSize(120)
                .addObject(context.getBean(GlobalOptions.class))
                .build());

        context.scan(this.getClass().getPackage().getName());
        context.scan(config.getPackagesToScan());
        context.refresh();

        context.getBean(Runner.class).run(args);
    }

    /**
     * Mainly exposed for testing
     */
    public GenericApplicationContext getContext() {
        return context;
    }

}
