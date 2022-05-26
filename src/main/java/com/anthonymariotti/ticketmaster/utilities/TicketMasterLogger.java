package com.anthonymariotti.ticketmaster.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicketMasterLogger {
    private static Logger LOGGER = LoggerFactory.getLogger("TicketMaster");

    public static void debug(String message) {
        LOGGER.debug(message);
    }

    public static void info(String message) {
        LOGGER.info(message);
    }

    public  static void error(Throwable e, String message) {
        LOGGER.error(message, e);
    }
}
