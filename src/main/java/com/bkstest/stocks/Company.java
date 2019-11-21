package com.bkstest.stocks;

public class Company {
    private Stock stock;
    private double latestPrice;
    private double assetValue;
    private String sector;

    public Company(Stock stock, double latestPrice, String sector) {
        this.stock = stock;
        this.latestPrice = latestPrice;
        this.sector = sector;
        assetValue = latestPrice * stock.getVolume();
    }
}
