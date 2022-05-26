package com.anthonymariotti.ticketmaster.configuration;

import com.anthonymariotti.ticketmaster.configuration.properties.TicketMasterSqlConfig;
import com.anthonymariotti.ticketmaster.utilities.TicketMasterLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TicketMasterConfig {
    private static Path configPath;

    @Expose
    private String version = TicketMasterConfigProvider.VERSION;

    public String getVersion() {
        return version;
    }

    @Expose
    private boolean enabled = true;

    @Expose
    public TicketMasterSqlConfig MYSQL = new TicketMasterSqlConfig();

    public boolean isTicketMasterEnabled() {
        return enabled;
    }

    public static TicketMasterConfig load(Path path) {
        var gson = getGson();

        configPath = Path.of(path.toString(), "config.json");

        if (!Files.exists(configPath)) {
            try {
                var writer = Files.newBufferedWriter(configPath);
                gson.toJson(new TicketMasterConfig(), writer);
                writer.close();

                return new TicketMasterConfig();
            } catch (IOException e) {
                TicketMasterLogger.error(e, "Failed to create configuration file.");
            }
        }

        try {
            var reader = Files.newBufferedReader(configPath);
            var tmConfig = gson.fromJson(reader, TicketMasterConfig.class);
            reader.close();

            return tmConfig;
        } catch (IOException e) {
            TicketMasterLogger.error(e, "Failed to load configuration file.");
        }

        throw new RuntimeException("Failed to initialize configuration.");
    }

    public void update(String newVersion) {
        var gson = getGson();

        try {
            version = newVersion;

            var writer = Files.newBufferedWriter(configPath);
            gson.toJson(this, writer);
            writer.close();
        } catch (IOException e) {
            TicketMasterLogger.error(e, "Failed to update configuration file.");
        }
    }

    private static Gson getGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }
}
