package com.bkstest.stocks;

import java.io.Serializable;
import java.util.Objects;

public class CompanyStocks implements Serializable {
    private String symbol;
    private int volume;

    public CompanyStocks() {
    }

    public CompanyStocks(String symbol, int volume) {
        this.symbol = symbol;
        this.volume = volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (!(o instanceof CompanyStocks)) {
            return false;
        }

        CompanyStocks companyStocks = (CompanyStocks) o;

        return getVolume() == companyStocks.getVolume() && Objects.equals(getSymbol(), companyStocks.getSymbol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSymbol(), getVolume());
    }

    @Override
    public String toString() {
        return "CompanyStocks{" + "symbol='" + symbol + '\'' + ", volume=" + volume + '}';
    }
}
