package ru.meproject.pteroctl;

import picocli.CommandLine;
import ru.meproject.pteroctl.commands.SendCommand;
import ru.meproject.pteroctl.commands.SetupCredentials;
import ru.meproject.pteroctl.commands.file.FileCommand;
import ru.meproject.pteroctl.commands.file.UploadFile;

@CommandLine.Command(name = "pteroctl", description = "CLI tool to manage Pterodactyl instance",
        subcommands = {
                FileCommand.class,
                SetupCredentials.class,
                SendCommand.class
        })
public class PteroCtl {

}
