package ru.mtu;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Parser {
    private static final String RATES_XPATH = "//*[@id=\"widget_exchange\"]/div/table/tbody";
    private static final String USD_XPATH = RATES_XPATH + "/tr[2]/td[2]";
    private static final String EUR_XPATH = RATES_XPATH + "/tr[3]/td[2]";
    private static final String CBR_URL = "http://cbr.ru";

    public Parser() {
        Configuration.browser = "chrome";
        Configuration.headless = true;
    }

    public Rates getRates() {
        open(CBR_URL);
        String usdRate = getRateByXpath(USD_XPATH);
        String eurRate = getRateByXpath(EUR_XPATH);
        return new Rates(Float.parseFloat(usdRate), Float.parseFloat(eurRate));
    }

    private String getRateByXpath(String xpath) {
        return $(By.xpath(xpath)).getText()
                .replace("руб. ", "")
                .replace(",", ".");
    }
}
