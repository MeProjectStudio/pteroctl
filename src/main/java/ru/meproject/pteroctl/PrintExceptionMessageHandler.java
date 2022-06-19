package ru.meproject.pteroctl;

import picocli.CommandLine;

class PrintExceptionMessageHandler implements CommandLine.IExecutionExceptionHandler {
    public int handleExecutionException(Exception ex,
                                        CommandLine commandLine,
                                        CommandLine.ParseResult parseResult) {

        commandLine.getErr().println(ex.getMessage());

        return commandLine.getExitCodeExceptionMapper() != null
                ? commandLine.getExitCodeExceptionMapper().getExitCode(ex)
                : commandLine.getCommandSpec().exitCodeOnExecutionException();
    }
}
