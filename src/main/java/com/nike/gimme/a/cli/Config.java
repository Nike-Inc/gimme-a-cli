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

import org.apache.commons.lang3.StringUtils;

/**
 * Configuration for your CLI that gets passed to the GimmeACli constructor
 */
public class Config {

    // required parameters
    private String cliName;
    private String[] packagesToScan;

    // optional parameters
    private String singleLineDescription;
    private String longDescription;

    /**
     * Use builder instead
     */
    private Config(String cliName,
                   String[] packagesToScan,
                   String singleLineDescription,
                   String longDescription) {
        this.cliName = cliName;
        this.packagesToScan = packagesToScan;
        this.singleLineDescription = singleLineDescription;
        this.longDescription = longDescription;
    }

    public String getCliName() {
        return cliName;
    }

    public String[] getPackagesToScan() {
        return packagesToScan;
    }

    public String getSingleLineDescription() {
        return singleLineDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for creating Config objects for GimmeACli
     */
    public static class Builder {
        // required parameters
        private String cliName;
        private String[] packagesToScan;

        // optional parameters
        private String singleLineDescription;
        private String longDescription;

        public String getCliName() {
            return cliName;
        }

        /**
         * The name of your CLI.
         * <p>
         * This parameter is required.
         */
        public Builder withCliName(String cliName) {
            this.cliName = cliName;
            return this;
        }

        public String[] getPackagesToScan() {
            return packagesToScan;
        }

        /**
         * List of packagesToScan to look for Spring components and Command implementations, e.g. "com.nike"
         * <p>
         * This parameter is required.
         */
        public Builder withPackagesToScan(String... basePackagesToScan) {
            this.packagesToScan = basePackagesToScan;
            return this;
        }

        public String getSingleLineDescription() {
            return singleLineDescription;
        }

        public Builder withSingleLineDescription(String singleLineDescription) {
            this.singleLineDescription = singleLineDescription;
            return this;
        }

        public String getLongDescription() {
            return longDescription;
        }

        public Builder withLongDescription(String longDescription) {
            this.longDescription = longDescription;
            return this;
        }

        /**
         * Build a Config object.
         */
        public Config build() {
            validate();
            return new Config(
                    cliName,
                    packagesToScan,
                    singleLineDescription,
                    longDescription
            );
        }

        /**
         * Validate required parameters are set and throw exception otherwise.
         */
        private void validate() {
            if (StringUtils.isBlank(cliName)) {
                throw new RuntimeException("cliName cannot be blank!");
            }
            if (packagesToScan == null || packagesToScan.length == 0) {
                throw new RuntimeException("packagesToScan must not be empty!");
            }
        }
    }
}
