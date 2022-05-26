package com.anthonymariotti.ticketmaster.sql.database;

import com.anthonymariotti.ticketmaster.sql.TicketMasterSqlDatabase;
import com.anthonymariotti.ticketmaster.utilities.TicketMasterLogger;

import java.sql.Connection;

public class TicketMasterMySql implements TicketMasterSqlDatabase {

    private Connection database = null;

    public Connection currentConnection() {
        return null;
    }

    public static void init() {
        TicketMasterLogger.info("Initializing MySQL database");
    }
}
