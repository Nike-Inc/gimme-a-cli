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

import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import java.util.SortedMap;
import java.util.TreeMap;

import static com.nike.gimme.a.cli.NameUtils.getCommandNames;

/**
 * Self-initializing registry of Commands.
 * <p>
 * Scans for Command implementations and registers them as Spring beans.
 */
@Component
class Registry {

    private final GenericApplicationContext context;
    private final Reflections reflections;
    private final SortedMap<String, Command> registry = new TreeMap<>();

    @Autowired
    public Registry(GenericApplicationContext context,
                    Reflections reflections) {
        this.context = context;
        this.reflections = reflections;
        initializeRegistry();
    }

    /**
     * Get a Command by name
     */
    public Command getCommand(String commandName) {
        return registry.get(commandName);
    }

    /**
     * Get the entire registry as a SortedMap
     */
    public SortedMap<String, Command> asSortedMap() {
        return registry;
    }

    /**
     * Initialize the registry by scanning for Command implementations
     * and registering beans.
     */
    private void initializeRegistry() {
        for (Class<? extends Command> commandClass : reflections.getSubTypesOf(Command.class)) {
            context.registerBean(commandClass);
            addToRegistry(context.getBean(commandClass));
        }
    }

    private void addToRegistry(Command command) {
        for (String name : getCommandNames(command.getClass())) {
            registry.put(name, command);
        }
    }
}
