package ru.mtu.db;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Query {
    INSERT_RATE_WO_TIME("INSERT into cbr.rates (time, usd_rate, eur_rate) VALUES (current_timestamp, :usdRate, :eurRate);"),
    INSERT_RATE("INSERT into cbr.rates (time, usd_rate, eur_rate) VALUES (:timestamp, :usdRate, :eurRate);"),
    SELECT_ALL("SELECT * FROM cbr.rates"),
    SELECT_LAST("select * from cbr.rates ORDER BY id DESC LIMIT 1;");

    @Getter
    private String stringQuery;
}
