package no.demo.Mock;

import java.util.Objects;

public class Betalingsforespørsel {

    private Integer beløp;
    private String kredittkortNummer;
    private Integer fee;

    public Betalingsforespørsel(Integer beløp, String kredittkortNummer, Integer fee) {
        this.beløp = beløp;
        this.kredittkortNummer = kredittkortNummer;
        this.fee = fee;
    }

    public Integer getBeløp() {
        return beløp;
    }

    public String getKredittkortNummer() {
        return kredittkortNummer;
    }

    public Integer getFee() {
        return fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Betalingsforespørsel that = (Betalingsforespørsel) o;
        return Objects.equals(beløp, that.beløp) && Objects.equals(kredittkortNummer, that.kredittkortNummer) && Objects.equals(fee, that.fee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beløp, kredittkortNummer, fee);
    }
}
