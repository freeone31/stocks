package com.bkstest.stocks;

import java.io.Serializable;
import java.util.Objects;

public class Stock implements Serializable {
    private String symbol;
    private int volume;

    public Stock() {
    }

    public Stock(String symbol, int volume) {
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
        if (!(o instanceof Stock)) {
            return false;
        }
        Stock stock = (Stock) o;
        return getVolume() == stock.getVolume() && Objects.equals(getSymbol(), stock.getSymbol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSymbol(), getVolume());
    }

    @Override
    public String toString() {
        return "Stock{" + "symbol='" + symbol + '\'' + ", volume=" + volume + '}';
    }
}
