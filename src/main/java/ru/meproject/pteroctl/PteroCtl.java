package ru.meproject.pteroctl;

import picocli.CommandLine;
import ru.meproject.pteroctl.commands.UploadFile;

@CommandLine.Command(name = "pterocli", description = "CLI tool to manage Pterodactyl instance",
        subcommands = {
                UploadFile.class
        })
public class PteroCtl {

}
