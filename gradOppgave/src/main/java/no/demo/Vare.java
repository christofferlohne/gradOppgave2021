package no.demo;

public class Vare {

    private String navn;
    private Integer pris;

    public Vare(String navn, Integer pris) {
        this.navn = navn;
        this.pris = pris;
    }

    public String getNavn() {
        return navn;
    }

    public Integer getPris() {
        return pris;
    }
}
