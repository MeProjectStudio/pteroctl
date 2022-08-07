package ru.meproject.pteroctl.commands.file;

import com.mattmalec.pterodactyl4j.PteroBuilder;
import picocli.CommandLine;
import ru.meproject.pteroctl.options.CredentialsOptions;
import ru.meproject.pteroctl.options.ServerIdsOption;
import ru.meproject.pteroctl.util.PteroctlUtils;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "remove", aliases = { "rm" },
        description = "Remove specified file or directory on remote" )
public class RemoveFile implements Callable<Integer> {
    @CommandLine.Mixin
    private CredentialsOptions credentials;

    @CommandLine.Mixin
    private ServerIdsOption servers;

    @CommandLine.Parameters(index = "0", paramLabel = "PATH", split = "/",
            description = "Path to resource that needs to be removed ")
    private String[] remotePath;

    @Override
    public Integer call() throws Exception {
        final var api = PteroBuilder.createClient(credentials.panelUrl(), credentials.apiKey());

        for (var server : servers.get()) {
            final var parentDir = api.retrieveServerByIdentifier(server)
                    .flatMap(clientServer -> clientServer.retrieveDirectory(PteroctlUtils.extractParent(remotePath)))
                    .execute();
            parentDir.getGenericFileByName(PteroctlUtils.extractChild(remotePath), true)
                    .ifPresentOrElse(file -> file.delete().execute(), () -> System.out.println("No such remote resource"));
        }
        return 0;
    }
}
