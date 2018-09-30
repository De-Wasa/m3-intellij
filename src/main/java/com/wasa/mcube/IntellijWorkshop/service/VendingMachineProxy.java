package com.wasa.mcube.IntellijWorkshop.service;

import com.wasa.mcube.IntellijWorkshop.entity.Coin;
import com.wasa.mcube.IntellijWorkshop.entity.Item;
import com.wasa.mcube.IntellijWorkshop.entity.Transaction;

import java.util.List;

public class VendingMachineProxy implements VendingMachineTransaction {

    private final VendingMachineTransactionImpl vendingMachine = new VendingMachineTransactionImpl();

    public void insert(Coin... coin) {
        System.out.println("totally inserting coin ");
        vendingMachine.insert(coin);
    }

    public Transaction order(Item item) {
        System.out.println("totally ordering item ");
        return new Transaction(vendingMachine.order(item).getItem(), coinReturn());
    }

    public List<Coin> coinReturn() {
        System.out.println("totally coinReturning ");
        return vendingMachine.coinReturn();
    }
}
