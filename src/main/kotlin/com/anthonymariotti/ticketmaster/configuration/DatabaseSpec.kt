package com.anthonymariotti.ticketmaster.configuration

import com.uchuhimo.konf.ConfigSpec

object DatabaseSpec : ConfigSpec() {
    val enabled by optional(false)
    val host by optional("0.0.0.0")
    val database by optional("")
    val username by optional("")
    val password by optional("")
}