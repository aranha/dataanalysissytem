package br.com.aranha.dataanalysissystem.stub;

import br.com.aranha.dataanalysissystem.domain.input.Sale;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SaleStub {
    public static Sale createSale() {
        return new Sale("10", Arrays.asList(ItemStub.createItem1(), ItemStub.createItem2(), ItemStub.createItem3()), "Diego");
    }

    public static List<Sale> createSaleList() {
        return Collections.singletonList(createSale());
    }
}