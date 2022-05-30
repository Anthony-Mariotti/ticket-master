package com.anthonymariotti.ticketmaster.core

import com.anthonymariotti.ticketmaster.configuration.CONFIG_PATH
import com.anthonymariotti.ticketmaster.configuration.MOD_ID
import com.anthonymariotti.ticketmaster.utilities.logInfo
import com.uchuhimo.konf.Config
import net.fabricmc.api.EnvType
import net.fabricmc.loader.api.FabricLoader
import java.nio.file.Files
import com.anthonymariotti.ticketmaster.configuration.config as tmConfig

open class TicketMasterCore {
    lateinit var config: Config

    fun initCore() {
        val version = FabricLoader.getInstance().getModContainer(MOD_ID).get().metadata.version
        logInfo("Initializing Ticket Master ${version.friendlyString}")

        if (!Files.exists(CONFIG_PATH)) {
            logInfo("Creating configuration file")
            Files.copy(
                FabricLoader.getInstance().getModContainer(MOD_ID).get().findPath("ticketmaster.config.json").get(),
                FabricLoader.getInstance().configDir.resolve("ticketmaster.config.json")
            )
        }

        tmConfig.validateRequired()
        config = tmConfig
    }

    fun isServer(): Boolean {
        return FabricLoader.getInstance().environmentType == EnvType.SERVER
    }

    fun isClient(): Boolean {
        return FabricLoader.getInstance().environmentType == EnvType.CLIENT
    }
}