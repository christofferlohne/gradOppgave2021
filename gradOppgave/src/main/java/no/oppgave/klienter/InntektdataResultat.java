package no.oppgave.klienter;

import no.oppgave.forretningslogikk.felles.VilkårStatus;

public class InntektdataResultat {
    private double årsinntekt;
    private VilkårStatus vilkårStatus;

    public InntektdataResultat() {}

    public double getÅrsinntekt() {
        return årsinntekt;
    }

    public VilkårStatus getVilkårStatus() {
        return vilkårStatus;
    }

    public InntektdataResultat medÅrsinntekt(Double årsinntekt) {
        this.årsinntekt = årsinntekt;
        return this;
    }

    public InntektdataResultat medVilkårStatus(VilkårStatus vilkårStatus) {
        this.vilkårStatus = vilkårStatus;
        return this;
    }
}
