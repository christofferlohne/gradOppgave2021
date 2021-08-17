package no.demo.Dummy;

import java.util.Objects;

class Betalingsforespørsel {

    private Integer beløp;
    private String kredittkortNummer;

    public Betalingsforespørsel(Integer beløp, String kredittkortNummer) {
        this.beløp = beløp;
        this.kredittkortNummer = kredittkortNummer;
    }

    public Integer beløp() {
        return beløp;
    }

    public String bankkortNummer() {
        return kredittkortNummer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Betalingsforespørsel that = (Betalingsforespørsel) o;
        return Objects.equals(beløp, that.beløp) && Objects.equals(kredittkortNummer, that.kredittkortNummer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beløp, kredittkortNummer);
    }
}
