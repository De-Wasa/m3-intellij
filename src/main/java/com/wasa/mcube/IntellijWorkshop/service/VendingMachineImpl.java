package com.wasa.mcube.IntellijWorkshop.service;

import com.wasa.mcube.IntellijWorkshop.entity.Coin;
import com.wasa.mcube.IntellijWorkshop.entity.Item;
import com.wasa.mcube.IntellijWorkshop.exception.NotEnoughMoneyException;
import org.apache.commons.lang3.mutable.MutableDouble;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.IntStream;

import static com.wasa.mcube.IntellijWorkshop.service.CoinUtils.sum;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class VendingMachineImpl implements VendingMachine {

    private List<Coin> money = new ArrayList<>();

    @Override
    public void insert(Coin... coin) {
        money.addAll(asList(coin));
    }

    @Override
    public Item order(Item item) {
        double total = sum(money);
        double cost = item.getValue();

        if (total < cost) {
            throw new NotEnoughMoneyException();
        }

        MutableDouble moneyLeft = new MutableDouble(total - cost);
        money = EnumSet.allOf(Coin.class).stream()
                .sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()))
                .flatMap(coin -> IntStream
                        .range(0, (int) Math.floor(moneyLeft.getValue() / coin.getValue()))
                        .mapToObj(value -> coin))
                .peek(coin -> moneyLeft.subtract(coin.getValue()))
                .collect(toList());

        return item;
    }

    @Override
    public List<Coin> coinReturn() {
        List<Coin> money = this.money;
        this.money = new ArrayList<>();
        return money;
    }
}
