package com.wasa.mcube.IntellijWorkshop.service;

import com.wasa.mcube.IntellijWorkshop.entity.Coin;
import com.wasa.mcube.IntellijWorkshop.entity.Item;

import java.util.List;

public interface VendingMachine {
    void insert(Coin... coin);

    Item order(Item item);

    List<Coin> coinReturn();
}
