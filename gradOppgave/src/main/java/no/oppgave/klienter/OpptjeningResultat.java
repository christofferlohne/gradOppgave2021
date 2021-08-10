package no.oppgave.klienter;

import no.oppgave.forretningslogikk.felles.VilkårStatus;

public class OpptjeningResultat {

    private VilkårStatus vilkårStatus;

    public OpptjeningResultat(VilkårStatus vilkårStatus) {
        this.vilkårStatus = vilkårStatus;
    }

    public VilkårStatus getVilkårStatus() {
        return vilkårStatus;
    }
}
