package ru.meproject.pteroctl.options;

import picocli.CommandLine;

import java.util.List;

@CommandLine.Command()
public class ServerIdsOption {
    @CommandLine.Option(names = {"--servers", "-s"}, required = true,
            split = ",",
            description = """
                    Pterodactyl servers that command needs to be ran against
                    """)
    private List<String> serverIds;

    public List<String> get() {
        return this.serverIds;
    }
}
