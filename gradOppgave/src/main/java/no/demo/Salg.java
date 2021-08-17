package no.demo;

import java.util.List;

public class Salg {

    private Kunde customer;
    private List<Vare> items;

    public Salg(Kunde customer, List<Vare> items) {
        this.customer = customer;
        this.items = items;
    }

    public Integer total() {
        return items.stream().mapToInt(Vare::getPris).sum();
    }
}
