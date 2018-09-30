package com.wasa.mcube.IntellijWorkshop.service;

import com.wasa.mcube.IntellijWorkshop.entity.Coin;
import com.wasa.mcube.IntellijWorkshop.entity.Item;

import java.util.List;

public class VendingMachineProxy implements VendingMachine {

    private final VendingMachineImpl vendingMachine = new VendingMachineImpl();

    public void insert(Coin... coin) {
        System.out.println("totally inserting coin ");
        vendingMachine.insert(coin);
    }

    public Item order(Item item) {
        System.out.println("totally ordering item ");
        return vendingMachine.order(item);
    }

    public List<Coin> coinReturn() {
        System.out.println("totally coinReturning ");
        return vendingMachine.coinReturn();
    }
}
