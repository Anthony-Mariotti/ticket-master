package com.anthonymariotti.ticketmaster.configuration

import com.uchuhimo.konf.Config
import net.fabricmc.loader.api.FabricLoader
import java.nio.file.Path

const val MOD_ID = "ticketmaster"
const val NAME = "Ticket Master"
const val VERSION = "0.0.1"

val CONFIG_PATH: Path = FabricLoader.getInstance().configDir.resolve("ticketmaster.config.json")

val config: Config = Config {
    addSpec(DatabaseSpec)
}
    .from.json.resource("ticketmaster.config.json")
    .from.json.watchFile(CONFIG_PATH.toFile())
    .from.systemProperties()