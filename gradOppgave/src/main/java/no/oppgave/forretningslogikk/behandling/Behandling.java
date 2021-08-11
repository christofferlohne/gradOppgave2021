package no.oppgave.forretningslogikk.behandling;

import java.io.IOException;
import java.time.LocalDate;

import no.oppgave.forretningslogikk.behandling.beregning.BeregningSjekk;
import no.oppgave.forretningslogikk.behandling.inngangsvilkår.InngangsvilkårSjekker;
import no.oppgave.forretningslogikk.felles.Fødselsnummer;
import no.oppgave.forretningslogikk.felles.VilkårStatus;
import no.oppgave.forretningslogikk.felles.Årsak;

public class Behandling {

    private InngangsvilkårSjekker inngangsvilkårSjekker;
    private BeregningSjekk beregningSjekke;

    public Behandling(InngangsvilkårSjekker inngangsvilkårSjekker, BeregningSjekk beregningSjekke) {
        this.inngangsvilkårSjekker = inngangsvilkårSjekker;
        this.beregningSjekke = beregningSjekke;
    }

    public BehandlingResultat startBehandling(Fødselsnummer fødselsnummer, LocalDate startidspunkt)
            throws IOException {

        var inngangsvilkårResultat = inngangsvilkårSjekker.oppfyltVilkår(fødselsnummer, startidspunkt);

        if (inngangsvilkårResultat == null || inngangsvilkårResultat.equals(VilkårStatus.AVSLÅTT)) {
            return new BehandlingResultat(inngangsvilkårResultat, 0.0).medÅrsak(Årsak.INNGANGSVILKÅR);
        }

        var utbetaling = beregningSjekke.resultat(fødselsnummer, startidspunkt);
        if (utbetaling.getResultat() <= 0.0) {
            return new BehandlingResultat(VilkårStatus.AVSLÅTT, 0.0).medÅrsak(Årsak.BEREGNING);
        }

        return new BehandlingResultat(VilkårStatus.INNVILGET, utbetaling.getResultat());

    }

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
}
