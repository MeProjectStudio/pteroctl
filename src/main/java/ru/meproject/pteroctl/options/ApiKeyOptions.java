package ru.meproject.pteroctl.options;

import lombok.Getter;
import lombok.experimental.Accessors;
import picocli.CommandLine;

@Getter
@Accessors(fluent = true)
@CommandLine.Command(
        synopsisHeading      = "%nUsage:%n%n",
        descriptionHeading   = "%nDescription:%n%n",
        parameterListHeading = "%nParameters:%n%n",
        optionListHeading    = "%nOptions:%n%n",
        commandListHeading   = "%nCommands:%n%n")
public class ApiKeyOptions {
    @CommandLine.Option(names = {"--url", "-u"}, required = true,
            description = "Pterodactyl Panel URL")
    private String panelUrl;

    @CommandLine.Option(names = {"--api-key", "-k"}, required = true,
            description = "Pterodactyl API key")
    private String apiKey;
}
