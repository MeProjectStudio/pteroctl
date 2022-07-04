package ru.meproject.pteroctl.commands;

import com.mattmalec.pterodactyl4j.PteroBuilder;
import picocli.CommandLine;
import ru.meproject.pteroctl.options.ApiKeyOptions;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "command",
        description = " Send console command to servers ")
public class SendCommand implements Callable<Integer> {
    @CommandLine.Mixin
    private ApiKeyOptions apiKeyOptions;

    @CommandLine.Option(names = {"--server", "-s"}, required = true,
            description = """
                    Pterodactyl server ID
                    """)
    private String serverId;

    @CommandLine.Parameters(index = "0", paramLabel = "COMMAND",
            description = """
                    Console command to send
                    """)
    private String command;

    @Override
    public Integer call() throws Exception {
        final var api = PteroBuilder.createClient(apiKeyOptions.panelUrl(), apiKeyOptions.apiKey());
        api.retrieveServerByIdentifier(serverId)
                .flatMap(clientServer -> clientServer.sendCommand(command))
                .execute();
        return 0;
    }
}
