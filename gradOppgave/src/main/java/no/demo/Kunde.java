package no.demo;

public class Kunde {

    private String fornavn;
    private String etternavn;

    public Kunde(String fornavn, String etternavn) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }
}
