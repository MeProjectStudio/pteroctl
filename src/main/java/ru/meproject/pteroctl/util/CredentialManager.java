package ru.meproject.pteroctl.util;

import org.spongepowered.configurate.BasicConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.gson.GsonConfigurationLoader;

import java.io.File;
import java.nio.file.Path;
import java.util.Base64;

public enum CredentialManager {
    INSTANCE;

    private static final String CONFIG_NAME = "pteroctl.json";
    private final GsonConfigurationLoader loader;

    CredentialManager() {
        loader = GsonConfigurationLoader.builder().path(Path.of(CONFIG_NAME)).build();
    }

    public void setupCredentials(String panelUrl, String apiKey) throws ConfigurateException {
        final var config = loader.load();
        config.node("panel-url").set(panelUrl);
        config.node("api-key").set(apiKey);
        loader.save(config);
    }

    public BasicConfigurationNode getConfig() throws ConfigurateException {
        if (!this.exists()) {
            throw new RuntimeException("No credentials file found. Run pteroctl setup or supply credentials with the command");
        }
        return loader.load();
    }

    public boolean exists() {
        return new File(CONFIG_NAME).exists();
    }
}
