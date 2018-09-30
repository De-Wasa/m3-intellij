package com.wasa.mcube.IntellijWorkshop.service;

import com.wasa.mcube.IntellijWorkshop.entity.Coin;
import com.wasa.mcube.IntellijWorkshop.entity.Item;

import java.util.List;

public class FoodVendingMachine implements VendingMachine {

    private final VendingMachineImpl vendingMachine = new VendingMachineImpl();

    public void insert(Coin... coin) {
        vendingMachine.insert(coin);
    }

    public Item order(Item item) {
        return vendingMachine.order(item);
    }

    public List<Coin> coinReturn() {
        return vendingMachine.coinReturn();
    }
}
