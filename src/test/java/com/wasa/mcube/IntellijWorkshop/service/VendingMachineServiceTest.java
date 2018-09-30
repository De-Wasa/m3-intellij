package com.wasa.mcube.IntellijWorkshop.service;

import com.wasa.mcube.IntellijWorkshop.entity.Coin;
import com.wasa.mcube.IntellijWorkshop.entity.Item;
import com.wasa.mcube.IntellijWorkshop.exception.NotEnoughMoneyException;
import org.junit.Test;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

public class VendingMachineServiceTest {

    @Test
    public void takesMoney_givesItem() {
        VendingMachine vendingMachine = new VendingMachineImpl();

        vendingMachine.insert(Coin.DOLLAR);
        Item item = vendingMachine.order(Item.B);

        assertNotNull(item);
    }

    @Test
    public void coinReturn_coinInserted_returnsMoney() {
        VendingMachine vendingMachine = new VendingMachineImpl();
        vendingMachine.insert(Coin.QUARTER);
        List<Coin> coins = vendingMachine.coinReturn();

        assertEquals(singletonList(Coin.QUARTER), coins);
    }

    @Test
    public void coinReturn_manyCoinInserted_returnsAllMoney() {
        VendingMachine vendingMachine = new VendingMachineImpl();
        vendingMachine.insert(Coin.QUARTER, Coin.DOLLAR, Coin.DIME, Coin.DIME, Coin.DIME);

        List<Coin> coins = vendingMachine.coinReturn();

        assertEquals(1.55, countValue(coins), 0.0001);
    }

    @Test
    public void order_itemDelivered_returnsRemainingMoney() {
        VendingMachine vendingMachine = new VendingMachineImpl();
        vendingMachine.insert(Coin.QUARTER, Coin.DOLLAR, Coin.DIME);

        Item item = vendingMachine.order(Item.A);

        List<Coin> coins = vendingMachine.coinReturn();

        assertEquals(Item.A, item);
        assertEquals(0.7, countValue(coins), 0.0001);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void order_notEnoughCoins_crash() {
        VendingMachine vendingMachine = new VendingMachineImpl();
        vendingMachine.insert(Coin.QUARTER, Coin.DIME);

        vendingMachine.order(Item.B);
    }

    @Test
    public void coinReturn_calledTwice_shouldNotGiveTooMuch() {
        VendingMachine vendingMachine = new VendingMachineImpl();
        vendingMachine.insert(Coin.QUARTER, Coin.DOLLAR, Coin.DIME);

        vendingMachine.coinReturn();
        List<Coin> coins = vendingMachine.coinReturn();

        assertTrue(coins.isEmpty());
    }

    private double countValue(List<Coin> coins) {
        return coins.stream()
                .mapToDouble(Coin::getValue)
                .sum();
    }
}
