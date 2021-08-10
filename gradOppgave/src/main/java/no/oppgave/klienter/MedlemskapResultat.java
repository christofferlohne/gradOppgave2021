package no.oppgave.klienter;

import no.oppgave.forretningslogikk.behandling.inngangsvilkår.VilkårStatus;

public class MedlemskapResultat {

    private VilkårStatus vilkårStatus;

    public MedlemskapResultat(VilkårStatus vilkårStatus) {
        this.vilkårStatus = vilkårStatus;
    }

    public VilkårStatus getVilkårStatus() {
        return vilkårStatus;
    }
}
