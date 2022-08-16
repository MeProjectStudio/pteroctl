package ru.meproject.pteroctl.commands;

import com.mattmalec.pterodactyl4j.PteroBuilder;
import picocli.CommandLine;
import ru.meproject.pteroctl.util.CredentialManager;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "setup",
        description = "Setup panel credentials for later usage")
public class SetupCredentials implements Callable<Integer> {
    @CommandLine.Option(names = {"--url", "-u"}, required = true,
            description = "Pterodactyl Panel URL")
    private String panelUrl;

    @CommandLine.Option(names = {"--api-key", "-k"}, required = true,
            description = "Pterodactyl API key")
    private String apiKey;

    @Override
    public Integer call() throws Exception {
        final var api = PteroBuilder.createClient(panelUrl, apiKey);
        // Test provided panel url and api keys
        final var account = api.retrieveAccount()
                .onErrorFlatMap(__ -> { throw new RuntimeException("Wrong credentials supplied"); })
                .execute();
        CredentialManager.INSTANCE.setupCredentials(panelUrl, apiKey);
        return 0;
    }
}
