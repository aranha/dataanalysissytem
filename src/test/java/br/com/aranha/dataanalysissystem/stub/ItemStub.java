package br.com.aranha.dataanalysissystem.stub;

import br.com.aranha.dataanalysissystem.domain.input.Item;

public class ItemStub {
    public static Item createItem1() {
        return new Item("1", 10, 100);
    }

    public static Item createItem2() {
        return new Item("2", 30, 2.5);
    }

    public static Item createItem3() {
        return new Item("3", 40, 3.1);
    }
}
