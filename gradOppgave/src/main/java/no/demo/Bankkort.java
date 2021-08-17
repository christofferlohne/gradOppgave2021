package no.demo;

public class Bankkort {

    private Kunde kunde;
    private String kortnummer;

    public Bankkort(Kunde kunde, String kortnummer) {
        this.kunde = kunde;
        this.kortnummer = kortnummer;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public String kortnummer() {
        return kortnummer;
    }
}
