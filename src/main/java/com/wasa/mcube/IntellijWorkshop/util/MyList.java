package com.wasa.mcube.IntellijWorkshop.util;

import java.util.ArrayList;

public class MyList extends ArrayList {

    @Override
    public Object get(int index) {
        System.out.println("gettting at index" + index);
        return super.get(index);
    }
}
