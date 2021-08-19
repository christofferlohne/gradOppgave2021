package no.demo;

import java.util.List;

public class Salg {

    private Kunde kunde;
    private List<Vare> items;

    public Salg(Kunde kunde, List<Vare> items) {
        this.kunde = kunde;
        this.items = items;
    }

    public Integer total() {
        return items.stream().mapToInt(Vare::getPris).sum();
    }
}
