package ru.meproject.pteroctl;

import picocli.CommandLine;

public class Application {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new PteroCtl()).
                setExecutionExceptionHandler(new PrintExceptionMessageHandler()).execute(args);
        System.exit(exitCode);
    }
}
