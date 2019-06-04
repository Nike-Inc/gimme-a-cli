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

package com.nike.gimme.a.cli.commands;

import com.beust.jcommander.Parameter;
import com.nike.gimme.a.cli.Command;
import com.nike.gimme.a.cli.Terminal;
import org.springframework.beans.factory.annotation.Autowired;


public class HelloWorldCommand implements Command {

    @Parameter(names = {"--name"}, required = true)
    private String name;

    @Autowired
    private Terminal terminal;

    @Override
    public void execute() {
        terminal.info("Hello " + name);
    }
}
