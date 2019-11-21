package com.bkstest.stocks;

import java.util.List;
import java.util.Objects;

public class Portfolio {
    private double value;
    private List<Allocation> allocations;

    public Portfolio() {
    }

    public Portfolio(double value) {
        this.value = value;
    }

    public Portfolio(double value, List<Allocation> allocations) {
        this.value = value;
        this.allocations = allocations;
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

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (!(o instanceof Portfolio)) {
            return false;
        }

        Portfolio portfolio = (Portfolio) o;

        return Double.compare(portfolio.getValue(), getValue()) == 0 && Objects.equals(getAllocations(), portfolio.getAllocations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getAllocations());
    }

    @Override
    public String toString() {
        return "Portfolio{" + "value=" + value + ", allocations=" + allocations + '}';
    }
}
