# Changelog

All notable changes to `Gimme a CLI` will be documented in this file.

`Gimme a CLI` adheres to [semantic versioning](http://semver.org/).

## Release Notes

### 1.0.0 - November 17, 2020

- Upgraded several dependencies to current versions:
  - `commons-io:commons-io:2.8.0`
  - `org.apache.commons:commons-lang3:3.11`
  - `org.apache.commons:commons-collections4:4.4`
  - `org.reflections:reflections:0.9.12`
  - `org.springframework:spring-context:5.2.11.RELEASE`
  - `org.slf4j:slf4j-api:1.7.30`
- Upgraded gradle to `6.7.1`.
- Removed `Terminal` class in preference for `SLF4J`.
- Started publishing this library to [JCenter](https://jcenter.bintray.com/com/nike/gimme-a-cli/).

### 0.1.0 - November 16, 2020

- While this project has not been active, it has been used in multiple internal tools and those tools have been very active.
- Finally published this library to [JFrog Bintray](https://bintray.com/nike/maven/gimme-a-cli).
- CLI now exits with an appropriate exit code on error.

### 0.0.1 - June 2019

- Initial version of this library
