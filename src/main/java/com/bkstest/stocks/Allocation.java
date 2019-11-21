package com.bkstest.stocks;

import java.math.BigDecimal;
import java.util.Objects;

public class Allocation {
    private String sector;
    private BigDecimal assetValue;
    private BigDecimal proportion;

    public Allocation(String sector, BigDecimal assetValue, BigDecimal proportion) {
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

    public BigDecimal getAssetValue() {
        return assetValue;
    }

    public void setAssetValue(BigDecimal assetValue) {
        this.assetValue = assetValue;
    }

    public BigDecimal getProportion() {
        return proportion;
    }

    public void setProportion(BigDecimal proportion) {
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

        return Objects.equals(getSector(), that.getSector()) && Objects.equals(getAssetValue(), that.getAssetValue()) && Objects.equals(getProportion(), that.getProportion());
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
