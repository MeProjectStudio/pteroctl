package ru.meproject.pteroctl.commands;

import com.mattmalec.pterodactyl4j.PteroBuilder;
import picocli.CommandLine;
import ru.meproject.pteroctl.options.CredentialsOptions;
import ru.meproject.pteroctl.options.ServerIdsOption;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "command",
        description = " Send console command to servers ")
public class SendCommand implements Callable<Integer> {
    @CommandLine.Mixin
    private CredentialsOptions credentials;

    @CommandLine.Mixin
    private ServerIdsOption servers;

    @CommandLine.Parameters(index = "0", paramLabel = "COMMAND",
            description = """
                    Console command to send
                    """)
    private String command;

    @Override
    public Integer call() throws Exception {
        final var api = PteroBuilder.createClient(credentials.panelUrl(), credentials.apiKey());
        for(var server : servers.get()) {
            api.retrieveServerByIdentifier(server)
                    .flatMap(clientServer -> clientServer.sendCommand(command))
                    .execute();
        }
        return 0;
    }
}
