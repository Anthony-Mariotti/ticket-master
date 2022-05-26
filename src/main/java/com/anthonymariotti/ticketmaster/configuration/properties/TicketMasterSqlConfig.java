package com.anthonymariotti.ticketmaster.configuration.properties;

import com.google.gson.annotations.Expose;

public class TicketMasterSqlConfig {

    @Expose
    private boolean enabled = false;

    public boolean isEnabled() {
        return enabled;
    }

    @Expose
    private String host = "";

    @Expose
    private String database = "";

    @Expose
    private String username = "";

    @Expose
    private String password = "";
}
