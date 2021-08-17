package no.oppgave.forretningslogikk.behandling.inngangsvilkår;

import no.oppgave.forretningslogikk.felles.VilkårStatus;
import no.oppgave.forretningslogikk.felles.Årsak;

public class InngangsvilkårResultat {

    private final VilkårStatus vilkårStatus;
    private final Årsak årsak;

    public InngangsvilkårResultat(VilkårStatus vilkårStatus, Årsak årsak) {
        this.vilkårStatus = vilkårStatus;
        this.årsak = årsak;
    }

    public VilkårStatus getVilkårStatus() {
        return vilkårStatus;
    }

    public Årsak getÅrsak() {
        return årsak;
    }
}
