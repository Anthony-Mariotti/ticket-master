package com.anthonymariotti.ticketmaster;

import com.anthonymariotti.ticketmaster.utilities.TicketMasterLogger;
import net.fabricmc.api.DedicatedServerModInitializer;

public class TicketMasterServer extends TicketMasterCore implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        TicketMasterLogger.info("Initializing Ticket Master");
        super.init();
    }
}
