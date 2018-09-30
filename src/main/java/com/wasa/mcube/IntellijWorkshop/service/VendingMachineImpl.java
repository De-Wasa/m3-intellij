package com.wasa.mcube.IntellijWorkshop.service;

import com.wasa.mcube.IntellijWorkshop.entity.Coin;
import com.wasa.mcube.IntellijWorkshop.entity.Item;
import com.wasa.mcube.IntellijWorkshop.exception.NotEnoughMoneyException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.wasa.mcube.IntellijWorkshop.service.CoinUtils.sum;
import static java.util.Arrays.asList;

@Component
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

        money = CoinUtils.calculateChange(total, cost);

        return item;
    }

    @Override
    public List<Coin> coinReturn() {
        List<Coin> money = this.money;
        this.money = new ArrayList<>();
        return money;
    }
}
