package com.wasa.mcube.IntellijWorkshop.service;

import com.wasa.mcube.IntellijWorkshop.entity.Coin;
import com.wasa.mcube.IntellijWorkshop.entity.Item;
import com.wasa.mcube.IntellijWorkshop.entity.Transaction;

import java.util.List;

public class FoodVendingMachine implements VendingMachineTransaction {

    private final VendingMachineTransactionImpl vendingMachine = new VendingMachineTransactionImpl();

    public void insert(Coin... coin) {
        vendingMachine.insert(coin);
    }

    public Transaction order(Item item) {
        return new Transaction(vendingMachine.order(item).getItem(), coinReturn());
    }

    public List<Coin> coinReturn() {
        return vendingMachine.coinReturn();
    }
}
