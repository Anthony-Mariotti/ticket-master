package com.anthonymariotti.ticketmaster.configuration;

import com.anthonymariotti.ticketmaster.TicketMasterCore;
import com.anthonymariotti.ticketmaster.utilities.TicketMasterLogger;
import net.fabricmc.loader.api.FabricLoader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Pattern;

public final class TicketMasterConfigProvider {

    public static String VERSION = "1.0.0";
    public static TicketMasterConfig CONFIG;

    private static boolean DEBUG_CONFIG = true;

    public static void init() {
        final Path configDirectory = getTicketMasterConfigDirectory();

        if (Files.exists(configDirectory)) {
            CONFIG = TicketMasterConfig.load(configDirectory);

            if (!isCurrentVersion(CONFIG) || DEBUG_CONFIG){
                TicketMasterLogger.info("Updating configuration file");
                CONFIG.update(VERSION);
            }
            return;
        }

        try {
            CONFIG = TicketMasterConfig.load(Files.createDirectory(configDirectory));

        } catch (IOException e) {
            TicketMasterLogger.error(e, "Failed to load configuration file.");
        }
    }

    public static boolean isCurrentVersion(TicketMasterConfig config) {
        var configVersion = config.getVersion().split(Pattern.quote("."));
        var currentVersion = VERSION.split(Pattern.quote("."));

        return Arrays.equals(configVersion, currentVersion);
    }

    public static Path getTicketMasterConfigDirectory() {
        final Path basePath = FabricLoader.getInstance().getConfigDir();
        return basePath.resolve(TicketMasterCore.MOD_ID);
    }
}
