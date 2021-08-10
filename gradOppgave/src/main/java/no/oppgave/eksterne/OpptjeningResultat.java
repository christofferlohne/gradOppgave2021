package no.oppgave.eksterne;

import no.oppgave.VilkårStatus;

public class OpptjeningResultat {

    private VilkårStatus vilkårStatus;

    public OpptjeningResultat(VilkårStatus vilkårStatus) {
        this.vilkårStatus = vilkårStatus;
    }

    public VilkårStatus getVilkårStatus() {
        return vilkårStatus;
    }
}
