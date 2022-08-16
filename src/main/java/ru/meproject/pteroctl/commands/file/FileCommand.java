package ru.meproject.pteroctl.commands.file;

import picocli.CommandLine;

@CommandLine.Command(name = "file", description = "Set of commands for remote file management",
        subcommands = {
                UploadFile.class,
                RemoveFile.class,
                CompressDirectory.class,
                DecompressFile.class
        })
public class FileCommand {
}
