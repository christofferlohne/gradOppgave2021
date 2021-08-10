package no.oppgave.eksterne;

import no.oppgave.VilkårStatus;

public class MedlemskapResultat {

    private VilkårStatus vilkårStatus;

    public MedlemskapResultat(VilkårStatus vilkårStatus) {
        this.vilkårStatus = vilkårStatus;
    }

    public VilkårStatus getVilkårStatus() {
        return vilkårStatus;
    }
}
