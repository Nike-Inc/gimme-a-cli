![Java CI with Gradle](https://github.com/Nike-Inc/gimme-a-cli/workflows/Java%20CI%20with%20Gradle/badge.svg)
![CodeQL](https://github.com/Nike-Inc/gimme-a-cli/workflows/CodeQL/badge.svg)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[ ![Download](https://api.bintray.com/packages/nike/maven/gimme-a-cli/images/download.svg) ](https://bintray.com/nike/maven/gimme-a-cli/_latestVersion)

# Gimme a CLI

Gimme a CLI is a Java library for creating quick and easy command line interfaces (CLIs) using [JCommander](http://jcommander.org/) and 
[Spring's IoC Container](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#spring-core).

- JCommander is great for creating command-style CLI argument parsers. Examples of command-style CLIs include git and the AWS CLI.
- Spring's IoC Container provides dependency injection which helps reduce boiler plate and makes it easier to write testable code.
- Gimme a Cli eliminates the tedious setup you would otherwise have to do.

## Getting Started

[Example starter project](https://github.com/Nike-Inc/gimme-a-cli-starter-project) can simply be cloned and modified.

## Usage

1. Define one or more commands for your CLI by implementing the Command interface.
    ```java
    import com.nike.gimme.a.cli.Command;
    
    public class HelloWorld implements Command {
    
        @Override
        public void execute() {
            System.out.println("Hello World!");
        }
    }
    ```
    Commands are automatically instantiated as Spring beans (e.g. dependencies can be supplied via constructor injection, etc).
2. Optionally use JCommander annotations to define command arguments and options.
    ```java
    import com.beust.jcommander.Parameter;
    import com.beust.jcommander.Parameters;
    import com.nike.gimme.a.cli.Command;
    
    @Parameters(commandNames = "hello-world",
                commandDescription = "Prints \"Hello <name>\" to the terminal")
    public class HelloWorld implements Command {
    
        @Parameter(names = {"--name"}, required = true)
        private String name;
        
        @Override
        public void execute() {
            System.out.println("Hello " + name);
        }
    }
    ```

3. Define a main class and run the GimmeACli program.
    ```java
    import com.nike.gimme.a.cli.Config;
    import com.nike.gimme.a.cli.GimmeACli;
    
    public class Main {
    
        public static void main(String[] args) {
            new GimmeACli(
                    Config.builder()
                            .withCliName("my-cli-name")
                            .withPackagesToScan("com.nike")
                            .build()
            ).run(args);
        }
    }
    ```
    Spring setup is done for you using classpath scanning.
4. Configure SLF4J logging, e.g.,
     1. Include logback as a dependency.
        ``` groovy
        dependencies {
            implementation "ch.qos.logback:logback-classic:1.1.11"
        }
        ```
     2. Provide a [logback.xml](https://github.com/Nike-Inc/gimme-a-cli-starter-project/blob/master/src/main/resources/logback.xml).

All of these steps have been done for you in the 
[example starter project](https://github.com/Nike-Inc/gimme-a-cli-starter-project) which can simply be cloned and
modified to fit your needs..

## Additional Features

- Global `--help` option gives nicely formatted output.

## References

- [JCommander](http://jcommander.org/) - a library for CLI argument parsing.
- [Spring's IoC](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#spring-core) - a library for easy dependency injection.
- [gimme-a-cli-starter-project](https://github.com/Nike-Inc/gimme-a-cli-starter-project) - An example starter project that can simply be cloned and modified.
