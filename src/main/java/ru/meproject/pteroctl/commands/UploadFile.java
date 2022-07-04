package ru.meproject.pteroctl.commands;

import com.mattmalec.pterodactyl4j.PteroBuilder;
import com.mattmalec.pterodactyl4j.client.entities.ClientServer;
import picocli.CommandLine;
import ru.meproject.pteroctl.options.CredentialsOptions;
import ru.meproject.pteroctl.options.ServerIdsOption;

import java.io.File;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "upload",
        description = " Upload files to specified server " )
public class UploadFile implements Callable<Integer> {
    @CommandLine.Mixin
    private CredentialsOptions credentials;

    @CommandLine.Mixin
    private ServerIdsOption servers;

    @CommandLine.Parameters(index = "0", paramLabel = "PATH",
            description = "Path to directory on the panel ")
    private String containerPath;

    @CommandLine.Parameters(index = "1", paramLabel = "FILE",
            description = "Pterodactyl server ID")
    private File file;

    @Override
    public Integer call() throws Exception {
        final var api = PteroBuilder.createClient(credentials.panelUrl(), credentials.apiKey());
        for (var server : servers.get()) {
            api.retrieveServerByIdentifier(server)
                    .flatMap(ClientServer::retrieveDirectory)
                    .flatMap(rootDir -> {
                        if (rootDir.getDirectoryByName(containerPath).isEmpty()) {
                            throw new RuntimeException("No such remote folder %s".formatted(containerPath));
                        }
                        return rootDir.into(rootDir.getDirectoryByName(containerPath).get());
                    })
                    .flatMap(dir -> dir.upload().addFile(file))
                    .execute();
        }
        return 0;
    }
}
