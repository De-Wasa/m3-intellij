package com.wasa.mcube.IntellijWorkshop.service;

import com.wasa.mcube.IntellijWorkshop.entity.Coin;

import java.util.List;

public class CoinUtils {

    public static double sum(List<Coin> money) {
        return money.stream()
                .mapToDouble(Coin::getValue)
                .sum();
    }
}
