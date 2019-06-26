package ru.mtu.db;

import lombok.extern.slf4j.Slf4j;
import org.dalesbred.Database;
import org.dalesbred.annotation.SQL;
import org.dalesbred.query.SqlQuery;
import org.dalesbred.result.ResultTable;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class DBUtil {
    private Database database;

    public DBUtil(String url, String username, String password
    ) {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        database = Database.forUrlAndCredentials(url, username, password);
    }

    public <T> List<T> fetchAll(String sqlQuery, Class<T> returnClass) {
        log.info("SQL EXEC: " + sqlQuery);
        return database.findAll(returnClass, sqlQuery);
    }

    public <T> T executeQuery(SqlQuery sqlQuery, Class<T> tClass) {
        log.info("SQL EXEC: " + sqlQuery);
        return database.findOptional(tClass, sqlQuery).orElse(null);
    }

    public <T> T executeQuery(@SQL String sqlQuery, Class<T> tClass) {
        return executeQuery(SqlQuery.query(sqlQuery), tClass);
    }

    public void update(SqlQuery sqlQuery) {
        log.info("SQL EXEC: " + sqlQuery);
        database.update(sqlQuery);
    }

    public void update(@SQL String sqlSting) {
        update(SqlQuery.query(sqlSting));
    }

}
