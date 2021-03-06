package com.wasa.mcube.IntellijWorkshop.service;

import com.google.common.collect.ImmutableList;
import com.wasa.mcube.IntellijWorkshop.entity.Coin;
import com.wasa.mcube.IntellijWorkshop.entity.Item;
import com.wasa.mcube.IntellijWorkshop.entity.Transaction;
import com.wasa.mcube.IntellijWorkshop.exception.NotEnoughMoneyException;
import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

public class VendingMachineTransactionImplTest {

    @Test
    public void codeFragmentJson() {
        List<String> strings = ImmutableList.of("{\n" +
                "  \"name\": \"workshop\"\n" +
                "}", "world");

        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    public void codeFragmentRegex() {
        Pattern pattern = Pattern.compile("[a-z]+@[a-z]+\\.com");

        Matcher matcher = pattern.matcher("david@marfeel.com");

        assertTrue(matcher.find());
    }

    @Test
    public void takesMoney_givesItem() {
        VendingMachineTransaction vendingMachine = new VendingMachineTransactionImpl();

        vendingMachine.insert(Coin.DOLLAR);
        Item item = vendingMachine.order(Item.B).getItem();

        assertNotNull(item);
    }

    @Test
    public void coinReturn_coinInserted_returnsMoney() {
        VendingMachineTransaction vendingMachine = new VendingMachineTransactionImpl();
        vendingMachine.insert(Coin.QUARTER);
        List<Coin> coins = vendingMachine.coinReturn();

        assertEquals(singletonList(Coin.QUARTER), coins);
    }

    @Test
    public void coinReturn_manyCoinInserted_returnsAllMoney() {
        VendingMachineTransaction vendingMachine = new VendingMachineTransactionImpl();
        vendingMachine.insert(Coin.QUARTER, Coin.DOLLAR, Coin.DIME, Coin.DIME, Coin.DIME);

        List<Coin> coins = vendingMachine.coinReturn();

        assertEquals(1.55, countValue(coins), 0.0001);
    }

    @Test
    public void order_itemDelivered_returnsRemainingMoney() {
        VendingMachineTransaction vendingMachine = new VendingMachineTransactionImpl();
        vendingMachine.insert(Coin.QUARTER, Coin.DOLLAR, Coin.DIME);

        Transaction transactionResult = vendingMachine.order(Item.A);

        assertEquals(Item.A, transactionResult.getItem());
        assertEquals(0.7, countValue(transactionResult.getCoins()), 0.0001);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void order_notEnoughCoins_crash() {
        VendingMachineTransaction vendingMachine = new VendingMachineTransactionImpl();
        vendingMachine.insert(Coin.QUARTER, Coin.DIME);

        vendingMachine.order(Item.B).getItem();
    }

    @Test
    public void coinReturn_calledTwice_shouldNotGiveTooMuch() {
        VendingMachineTransaction vendingMachine = new VendingMachineTransactionImpl();
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
