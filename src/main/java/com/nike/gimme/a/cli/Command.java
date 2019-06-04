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
