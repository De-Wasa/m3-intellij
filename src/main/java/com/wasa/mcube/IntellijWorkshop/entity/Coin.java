package com.wasa.mcube.IntellijWorkshop.entity;

public enum Coin {

    NICKEL(0.05),
    QUARTER(0.25),
    DIME(0.10),
    DOLLAR(1.00);

    private final double value;

    Coin(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
