package ru.meproject.pteroctl.commands.file;

import com.mattmalec.pterodactyl4j.PteroBuilder;
import com.mattmalec.pterodactyl4j.client.entities.GenericFile;
import picocli.CommandLine;
import ru.meproject.pteroctl.options.CredentialsOptions;
import ru.meproject.pteroctl.options.ServerIdsOption;
import ru.meproject.pteroctl.util.PteroctlUtils;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "compress",
        description = "Compress remote directory" )
public class CompressDirectory implements Callable<Integer> {
    @CommandLine.Mixin
    private CredentialsOptions credentials;

    @CommandLine.Mixin
    private ServerIdsOption servers;

    @CommandLine.Parameters(index = "0", paramLabel = "PATH", split = "/",
            description = "Path to directory that needs to be compressed")
    private String[] remotePath;

    @Override
    public Integer call() throws Exception {
        final var api = PteroBuilder.createClient(credentials.panelUrl(), credentials.apiKey());

        for (var server : servers.get()) {
            final var parentDir = api.retrieveServerByIdentifier(server)
                    .flatMap(clientServer -> clientServer.retrieveDirectory(PteroctlUtils.extractParent(remotePath)))
                    .execute();
            final var resourceOpt = parentDir.getGenericFileByName(PteroctlUtils.extractChild(remotePath), true);
            if (resourceOpt.isEmpty()) {
                throw new RuntimeException("No such remote resource");
            }
            final var archiveName = parentDir.compress()
                    .addFile(resourceOpt.get())
                    .map(GenericFile::getName).execute();
            System.out.println(archiveName);
        }
        return 0;
    }
}
