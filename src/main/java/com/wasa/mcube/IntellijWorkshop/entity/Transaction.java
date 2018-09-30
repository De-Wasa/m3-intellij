package com.wasa.mcube.IntellijWorkshop.entity;

import java.util.List;

public class Transaction {

    private final Item value;
    private final List<Coin> coins;

    public Transaction(Item value, List<Coin> coins) {
        this.value = value;
        this.coins = coins;
    }

    public Item getItem() {
        return value;
    }

    public List<Coin> getCoins() {
        return coins;
    }
}
