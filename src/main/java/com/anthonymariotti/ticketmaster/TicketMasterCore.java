package com.anthonymariotti.ticketmaster;

import com.anthonymariotti.ticketmaster.configuration.TicketMasterConfigProvider;
import com.anthonymariotti.ticketmaster.sql.database.TicketMasterMySql;
import com.anthonymariotti.ticketmaster.sql.database.TicketMasterSqlite;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

public abstract class TicketMasterCore {
	public static final String MOD_ID = "ticketmaster";

	public static boolean isServer() {
		return FabricLoader.getInstance()
				.getEnvironmentType() == EnvType.SERVER;
	}

	public static boolean isClient() {
		return FabricLoader.getInstance()
				.getEnvironmentType() == EnvType.CLIENT;
	}

	public void init() {
		TicketMasterConfigProvider.init();

		if (TicketMasterConfigProvider.CONFIG.MYSQL.isEnabled()) {
			TicketMasterMySql.init();
		} else {
			TicketMasterSqlite.init();
		}
	}
}
