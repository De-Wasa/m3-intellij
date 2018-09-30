package com.wasa.mcube.IntellijWorkshop.util;

import com.wasa.mcube.IntellijWorkshop.entity.Coin;
import org.apache.commons.lang3.mutable.MutableDouble;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class CoinUtils {

    public static double sum(List<Coin> money) {
        return money.stream()
                .mapToDouble(Coin::getValue)
                .sum();
    }

    public static List<Coin> calculateChange(double total, double cost) {
        MutableDouble moneyLeft = new MutableDouble(total - cost);
        return EnumSet.allOf(Coin.class).stream()
                .sorted(CoinUtils::moreValueFirst)
                .flatMap(coin -> IntStream
                        .range(0, floor(moneyLeft, coin))
                        .mapToObj(value -> coin))
                .peek(coin -> moneyLeft.subtract(coin.getValue()))
                .collect(toList());
    }

    private static int floor(MutableDouble moneyLeft, Coin coin) {
        return (int) Math.floor(moneyLeft.getValue() / coin.getValue());
    }

    private static int moreValueFirst(Coin o1, Coin o2) {
        return Double.compare(o2.getValue(), o1.getValue());
    }
}
