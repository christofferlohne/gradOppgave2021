package no.oppgave.klienter;

import no.oppgave.forretningslogikk.felles.VilkårStatus;

public class MedlemskapResultat {

    private VilkårStatus vilkårStatus;

    public MedlemskapResultat(VilkårStatus vilkårStatus) {
        this.vilkårStatus = vilkårStatus;
    }

    public VilkårStatus getVilkårStatus() {
        return vilkårStatus;
    }
}
