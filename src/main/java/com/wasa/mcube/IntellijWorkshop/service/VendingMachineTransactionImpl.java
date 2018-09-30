package com.wasa.mcube.IntellijWorkshop.service;

import com.wasa.mcube.IntellijWorkshop.entity.Coin;
import com.wasa.mcube.IntellijWorkshop.entity.Item;
import com.wasa.mcube.IntellijWorkshop.entity.Transaction;
import com.wasa.mcube.IntellijWorkshop.exception.NotEnoughMoneyException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.wasa.mcube.IntellijWorkshop.util.CoinUtils.calculateChange;
import static com.wasa.mcube.IntellijWorkshop.util.CoinUtils.sum;
import static java.util.Arrays.asList;

@Component
public class VendingMachineTransactionImpl implements VendingMachineTransaction {

    private List<Coin> transactionChange = new ArrayList<>();

    @Override
    public void insert(Coin... coin) {
        transactionChange.addAll(asList(coin));
    }

    @Override
    public Transaction order(Item item) {
        double changeValue = sum(transactionChange);
        double cost = item.getValue();

        if (changeValue < cost) {
            throw new NotEnoughMoneyException();
        }

        transactionChange = calculateChange(changeValue, cost);

        return new Transaction(item, coinReturn());
    }

    @Override
    public List<Coin> coinReturn() {
        List<Coin> money = this.transactionChange;
        this.transactionChange = new ArrayList<>();
        return money;
    }
}
