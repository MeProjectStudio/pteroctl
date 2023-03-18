package ru.meproject.pteroctl.commands.file;

import com.mattmalec.pterodactyl4j.PteroBuilder;
import picocli.CommandLine;
import ru.meproject.pteroctl.options.CredentialsOptions;
import ru.meproject.pteroctl.options.ServerIdsOption;

import java.io.File;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "upload", aliases = { "up" },
        description = "Upload file to specified server" )
public class UploadFile implements Callable<Integer> {
    @CommandLine.Mixin
    private CredentialsOptions credentials;

    @CommandLine.Mixin
    private ServerIdsOption servers;

    @CommandLine.Parameters(index = "0", paramLabel = "LOCAL-PATH",
            description = "Path to local resource that needs to be uploaded. Must be a file")
    private File localFile;

    @CommandLine.Parameters(index = "1", paramLabel = "REMOTE-DIR",
            description = "Path to directory on the panel")
    private String remotePath;

    @Override
    public Integer call() throws Exception {
        if (localFile.isDirectory()) {
            throw new RuntimeException("Directory upload is not supported");
        }
        if (!localFile.exists()) {
            throw new RuntimeException(localFile.getName() + " not found");
        }
        final var api = PteroBuilder.createClient(credentials.panelUrl(), credentials.apiKey());
        for (var server : servers.get()) {
            api.retrieveServerByIdentifier(server)
                    .flatMap(clientServer -> clientServer.retrieveDirectory(remotePath))
                    .flatMap(directory -> directory.upload().addFile(localFile))
                    .execute();
        }
        return 0;
    }
}
