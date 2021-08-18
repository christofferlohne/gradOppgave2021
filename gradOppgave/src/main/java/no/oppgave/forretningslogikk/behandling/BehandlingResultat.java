package no.oppgave.forretningslogikk.behandling;

import no.oppgave.forretningslogikk.felles.VilkårStatus;
import no.oppgave.forretningslogikk.felles.Årsak;

public class BehandlingResultat {

    private VilkårStatus vilkårStatus;
    private double utbetaling;
    private Årsak årsak;

    public BehandlingResultat(VilkårStatus vilkårStatus, double utbetaling) {
        this.vilkårStatus = vilkårStatus;
        this.utbetaling = utbetaling;
    }

    public BehandlingResultat medÅrsak(Årsak årsak){
        this.årsak = årsak;
        return this;
    }

    public VilkårStatus getVilkårStatus() {
        return vilkårStatus;
    }

    public double getUtbetaling() {
        return utbetaling;
    }

    public Årsak getÅrsak() {
        return årsak;
    }
}
