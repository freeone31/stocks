package com.bkstest.stocks;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Asset implements Serializable {
    private List<CompanyStocks> stocks;

    public Asset() {
    }

    public Asset(List<CompanyStocks> stocks) {
        this.stocks = stocks;
    }

    public List<CompanyStocks> getStocks() {
        return stocks;
    }

    public void setStocks(List<CompanyStocks> stocks) {
        this.stocks = stocks;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (!(o instanceof Asset)) {
            return false;
        }

        Asset asset = (Asset) o;

        return Objects.equals(getStocks(), asset.getStocks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStocks());
    }

    @Override
    public String toString() {
        return "Asset{" + "stocks=" + stocks + '}';
    }
}
