package com.anthonymariotti.ticketmaster.sql;

import java.sql.Connection;
import java.sql.SQLException;

public interface TicketMasterSqlDatabase {
    Connection currentConnection() throws SQLException;

    default TicketMasterSqlStatement prepare(String preparedStatement) throws SQLException {
        return new TicketMasterSqlStatement(this, preparedStatement);
    }

    default TicketMasterSqlStatement prepare(String preparedStatement, boolean runInBatch) throws SQLException {
        return new TicketMasterSqlStatement(this, preparedStatement, runInBatch);
    }
}
