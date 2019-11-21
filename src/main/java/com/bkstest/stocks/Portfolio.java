package com.bkstest.stocks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Portfolio {
    private BigDecimal value;
    private List<Allocation> allocations;

    public Portfolio() {
    }

    public Portfolio(BigDecimal value) {
        this.value = value;
    }

    public Portfolio(BigDecimal value, List<Allocation> allocations) {
        this.value = value;
        this.allocations = allocations;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
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

        return Objects.equals(getValue(), portfolio.getValue()) && Objects.equals(getAllocations(), portfolio.getAllocations());
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
