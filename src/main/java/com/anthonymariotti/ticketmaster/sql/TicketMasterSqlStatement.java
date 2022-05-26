package com.anthonymariotti.ticketmaster.sql;

import com.anthonymariotti.ticketmaster.utilities.TicketMasterLogger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TicketMasterSqlStatement implements AutoCloseable {

    private final PreparedStatement sqlStatement;

    private final String rawStatement;

    private boolean runInBatch;

    TicketMasterSqlStatement(TicketMasterSqlDatabase database, String preparedStatement) throws SQLException {
        this(database, preparedStatement, true);
    }

    TicketMasterSqlStatement(TicketMasterSqlDatabase database, String preparedStatement, boolean runInBatch) throws SQLException {
        this.rawStatement = preparedStatement;
        var connection = database.currentConnection();

        this.sqlStatement = connection.prepareStatement(preparedStatement, Statement.RETURN_GENERATED_KEYS);
        this.runInBatch = runInBatch;
    }

    public boolean isClosed(){
        if (sqlStatement == null) {
            return true;
        }

        try {
            var connection = sqlStatement.getConnection();

            if (connection.isClosed() | (!connection.isValid(5))) {
                sqlStatement.close();
            }

            return sqlStatement.isClosed();
        } catch (SQLException e) {
            return true;
        }
    }

    @Override
    public void close() {
        try {
            if (sqlStatement != null) {
                if (!sqlStatement.isClosed()){
                    sqlStatement.close();
                }
            }
        } catch (SQLException e) {
            TicketMasterLogger.error(e, "Error handling sql statements");
        }
    }
}
