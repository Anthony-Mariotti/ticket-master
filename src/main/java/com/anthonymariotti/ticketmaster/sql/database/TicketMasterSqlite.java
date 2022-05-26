package com.anthonymariotti.ticketmaster.sql.database;

import com.anthonymariotti.ticketmaster.configuration.TicketMasterConfigProvider;
import com.anthonymariotti.ticketmaster.sql.TicketMasterSqlDatabase;
import com.anthonymariotti.ticketmaster.utilities.TicketMasterLogger;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;

public class TicketMasterSqlite implements TicketMasterSqlDatabase {
    private Connection database = null;

    public Connection currentConnection() {
        try {
            if (database != null) {
                if (!database.isClosed() && database.isValid(5)) {
                    return database;
                }

                if (database.isClosed()) {
                    database.close();
                }
            }

            final Path configDirectory = TicketMasterConfigProvider.getTicketMasterConfigDirectory();
            final var jdbc = configDirectory
                    .toAbsolutePath()
                    .normalize()
                    .resolve("sqlite.db");

            var database = DriverManager.getConnection("jdbc:sqlite:" + jdbc);

            if (database != null){
                TicketMasterLogger.info(database.getMetaData().getDriverName());
            }
        } catch (Exception e) {
            TicketMasterLogger.error(e, "Error connecting to database");
        }

        return database;
    }

    public static void init() {
        TicketMasterLogger.info("Initializing SQLite database");
        var database = new TicketMasterSqlite();
        var connection = database.currentConnection();
    }
}
