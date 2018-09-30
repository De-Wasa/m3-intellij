package com.wasa.mcube.IntellijWorkshop.entity;

public enum Item {

    A(0.65),
    B(1),
    C(1.50);

    private final double value;

    Item(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
