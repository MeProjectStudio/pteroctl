package ru.meproject.pteroctl.commands.file;

import com.mattmalec.pterodactyl4j.PteroBuilder;
import picocli.CommandLine;
import ru.meproject.pteroctl.options.CredentialsOptions;
import ru.meproject.pteroctl.options.ServerIdsOption;
import ru.meproject.pteroctl.util.PteroctlUtils;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "decompress",
        description = "Decompress remote file" )
public class DecompressFile implements Callable<Integer> {
    @CommandLine.Mixin
    private CredentialsOptions credentials;

    @CommandLine.Mixin
    private ServerIdsOption servers;

    @CommandLine.Parameters(index = "0", paramLabel = "PATH", split = "/",
            description = "Path to file that needs to be decompressed")
    private String[] remotePath;

    @Override
    public Integer call() throws Exception {
        final var api = PteroBuilder.createClient(credentials.panelUrl(), credentials.apiKey());

        for (var server : servers.get()) {
            final var parentDir = api.retrieveServerByIdentifier(server)
                    .flatMap(clientServer -> clientServer.retrieveDirectory(PteroctlUtils.extractParent(remotePath)))
                    .execute();
            final var resourceOpt = parentDir.getFileByName(PteroctlUtils.extractChild(remotePath));
            if (resourceOpt.isEmpty()) {
                throw new RuntimeException("No such remote resource");
            }
            final var archiveName = parentDir.decompress(resourceOpt.get()).execute();
            System.out.println(archiveName);
        }
        return 0;
    }
}
