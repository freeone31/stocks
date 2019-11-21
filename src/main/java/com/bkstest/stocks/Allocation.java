package com.bkstest.stocks;

import java.util.Objects;

public class Allocation {
    private String sector;
    private double assetValue;
    private double proportion;

    public Allocation(String sector, double assetValue, double proportion) {
        this.sector = sector;
        this.assetValue = assetValue;
        this.proportion = proportion;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public double getAssetValue() {
        return assetValue;
    }

    public void setAssetValue(double assetValue) {
        this.assetValue = assetValue;
    }

    public double getProportion() {
        return proportion;
    }

    public void setProportion(double proportion) {
        this.proportion = proportion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (!(o instanceof Allocation)) {
            return false;
        }

        Allocation that = (Allocation) o;

        return Double.compare(that.getAssetValue(), getAssetValue()) == 0 && Double.compare(that.getProportion(), getProportion()) == 0 && Objects.equals(getSector(), that.getSector());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSector(), getAssetValue(), getProportion());
    }

    @Override
    public String toString() {
        return "Allocation{" + "sector='" + sector + '\'' + ", assetValue=" + assetValue + ", proportion=" + proportion + '}';
    }
}
