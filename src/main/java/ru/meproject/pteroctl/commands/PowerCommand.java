package ru.meproject.pteroctl.commands;

import com.mattmalec.pterodactyl4j.PowerAction;
import com.mattmalec.pterodactyl4j.PteroBuilder;
import picocli.CommandLine;
import ru.meproject.pteroctl.options.CredentialsOptions;
import ru.meproject.pteroctl.options.ServerIdsOption;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "power",
        description = " Send power action to servers ")
public class PowerCommand implements Callable<Integer> {
    @CommandLine.Mixin
    private CredentialsOptions credentials;

    @CommandLine.Mixin
    private ServerIdsOption servers;

    @CommandLine.Parameters(index = "0", paramLabel = "POWER-ACTION",
            description = """
                    Power Action to send
                    """)
    private PowerAction powerAction;

    @Override
    public Integer call() throws Exception {
        final var api = PteroBuilder.createClient(credentials.panelUrl(), credentials.apiKey());
        for(var server : servers.get()) {
            api.retrieveServerByIdentifier(server)
                    .flatMap(clientServer -> clientServer.setPower(powerAction))
                    .execute();
        }
        return 0;
    }
}
