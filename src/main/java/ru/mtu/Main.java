package ru.mtu;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ru.mtu.db.RatesDbExecutor;

@Slf4j
public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        while(true){
            Parser parser = new Parser();
            Rates rates = parser.getRates();
            log.info("Rates parse success!");
            log.info(rates.toString());
            RatesDbExecutor executor = new RatesDbExecutor();
            executor.insertRates(rates);
            log.info("Rates write in db success!");
            log.info("Last record in db: " + executor.getLastRecord());
            log.info("Waiting 30 minutes for next parse");
            Thread.sleep(30 *   // minutes to sleep
                    60 *              // seconds to a minute
                    1000);            // milliseconds to a second
        }

    }
}
