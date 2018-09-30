package com.wasa.mcube.IntellijWorkshop.service;

import com.wasa.mcube.IntellijWorkshop.entity.Coin;
import com.wasa.mcube.IntellijWorkshop.entity.Item;
import com.wasa.mcube.IntellijWorkshop.entity.Transaction;

import java.util.List;

public interface VendingMachineTransaction {
    void insert(Coin... coin);

    Transaction order(Item item);

    List<Coin> coinReturn();
}
