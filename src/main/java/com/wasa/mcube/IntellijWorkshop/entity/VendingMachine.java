package com.wasa.mcube.IntellijWorkshop.entity;

import java.util.List;

public class VendingMachine {

    private final List<Coin> change;
    private final List<Item> stock;

    public VendingMachine(List<Coin> change, List<Item> stock) {
        this.change = change;
        this.stock = stock;
    }

    public List<Coin> getChange() {
        return change;
    }

    public List<Item> getStock() {
        return stock;
    }

}
