package no.demo;

import java.util.List;

public class Sale {

    private Customer customer;
    private List<Item> items;

    public Sale(Customer customer, List<Item> items) {
        this.customer = customer;
        this.items = items;
    }

    public Integer total() {
        return items.stream().mapToInt(Item::getPrice).sum();
    }
}
