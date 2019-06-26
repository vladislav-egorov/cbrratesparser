package ru.mtu.db;

import org.dalesbred.query.SqlQuery;
import ru.mtu.Rates;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RatesDbExecutor {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/rate_parser_db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "r00t";
    private static final DBUtil DB_UTIL = new DBUtil(DB_URL, DB_USER, DB_PASS);

    public List<Rates> getAllRates() {
        return DB_UTIL.fetchAll(Query.SELECT_ALL.getStringQuery(), Rates.class);
    }

    public void insertRates(Rates rates) {
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("usdRate", rates.getUsdRate());
        valueMap.put("eurRate", rates.getEurRate());
        if (rates.getTimeStamp() == null) {
            DB_UTIL.update(SqlQuery.namedQuery(Query.INSERT_RATE_WO_TIME.getStringQuery(), valueMap));
        } else {
            valueMap.put("timestamp", rates.getTimeStamp());
            DB_UTIL.update(SqlQuery.namedQuery(Query.INSERT_RATE.getStringQuery(), valueMap));
        }
    }

    public Rates getLastRecord() {
        return DB_UTIL.executeQuery(Query.SELECT_LAST.getStringQuery(), Rates.class);
    }
}
