package ru.meproject.pteroctl.commands.file;

import com.mattmalec.pterodactyl4j.PteroBuilder;
import com.mattmalec.pterodactyl4j.client.entities.ClientServer;
import picocli.CommandLine;
import ru.meproject.pteroctl.options.CredentialsOptions;
import ru.meproject.pteroctl.options.ServerIdsOption;
import ru.meproject.pteroctl.util.PteroctlUtils;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "upload", aliases = { "up" },
        description = "Upload files to specified server" )
public class UploadFile implements Callable<Integer> {
    @CommandLine.Mixin
    private CredentialsOptions credentials;

    @CommandLine.Mixin
    private ServerIdsOption servers;

    @CommandLine.Parameters(index = "0", paramLabel = "REMOTE-DIR", split = "/",
            description = "Path to directory on the panel")
    private String[] remotePath;

    @CommandLine.Parameters(index = "1", paramLabel = "LOCAL-PATH",
            description = "Path to local resource that needs to be uploaded. Can be file or directory.")
    private File localResource;

    @Override
    public Integer call() throws Exception {
        final var api = PteroBuilder.createClient(credentials.panelUrl(), credentials.apiKey());
        /*for (var server : servers.get()) {
            api.retrieveServerByIdentifier(server)
                    .flatMap(ClientServer::retrieveDirectory)
                    .flatMap(rootDir -> {
                        if (rootDir.getDirectoryByName(PteroctlUtils.extractParent(remotePath)).isEmpty()) {
                            throw new RuntimeException("No such remote folder /home/container/%s".formatted(remotePath[0]));
                        }
                        return rootDir.into(rootDir.getDirectoryByName(remotePath[0]).get());
                    })
                    .execute();
        }*/
        return 0;
    }
}
