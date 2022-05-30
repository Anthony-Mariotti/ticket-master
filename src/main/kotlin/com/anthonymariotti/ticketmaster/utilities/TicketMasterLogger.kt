package com.anthonymariotti.ticketmaster.utilities

import org.slf4j.LoggerFactory

private val LOGGER = LoggerFactory.getLogger("TicketMaster")

fun logInfo(message: String) {
    LOGGER.info(message)
}

fun logWarning(message: String) {
    LOGGER.warn(message)
}

fun logError(message: String) {
    LOGGER.error(message)
}

fun logError(message: String, error: Throwable) {
    LOGGER.error(message, error)
}