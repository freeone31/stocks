package com.bkstest.stocks;

import java.util.List;

public class Portfolio {
    private double value;
    private List<Allocation> allocations;

    public Portfolio() {
    }

    public Portfolio(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public List<Allocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<Allocation> allocations) {
        this.allocations = allocations;
    }
}
