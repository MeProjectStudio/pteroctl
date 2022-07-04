package ru.meproject.pteroctl.options;

import picocli.CommandLine;

public class DefaultCredentialsProvider implements CommandLine.IDefaultValueProvider {
    @Override
    public String defaultValue(CommandLine.Model.ArgSpec argSpec) throws Exception {
        return CredentialManager.INSTANCE.getConfig().node(argSpec.descriptionKey()).getString();
    }
}
