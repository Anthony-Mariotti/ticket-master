package com.anthonymariotti.ticketmaster

import com.anthonymariotti.ticketmaster.core.TicketMasterCore
import net.fabricmc.api.DedicatedServerModInitializer

object TicketMasterServer : TicketMasterCore(), DedicatedServerModInitializer {

    override fun onInitializeServer() {
        initCore()


    }

}