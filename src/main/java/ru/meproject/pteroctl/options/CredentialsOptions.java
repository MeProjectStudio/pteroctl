package ru.meproject.pteroctl.options;

import lombok.Getter;
import lombok.experimental.Accessors;
import picocli.CommandLine;
import ru.meproject.pteroctl.util.DefaultCredentialsProvider;

@Getter
@Accessors(fluent = true)
@CommandLine.Command(defaultValueProvider = DefaultCredentialsProvider.class)
public class CredentialsOptions {
    @CommandLine.Option(names = {"--url", "-u"},
            descriptionKey = "panel-url",
            description = "Pterodactyl Panel URL")
    private String panelUrl;

    @CommandLine.Option(names = {"--api-key", "-k"},
            descriptionKey = "api-key",
            description = "Pterodactyl API key")
    private String apiKey;
}
