package no.oppgave.forretningslogikk.behandling;

import java.io.IOException;
import java.time.LocalDate;

import no.oppgave.forretningslogikk.behandling.beregning.Beregning;
import no.oppgave.forretningslogikk.behandling.inngangsvilkår.InngangsvilkårSjekker;
import no.oppgave.forretningslogikk.felles.Fødselsnummer;
import no.oppgave.forretningslogikk.felles.VilkårStatus;
import no.oppgave.forretningslogikk.felles.Årsak;

public class Behandling {

    private InngangsvilkårSjekker inngangsvilkårSjekker;
    private Beregning beregningSjekke;

    public Behandling(InngangsvilkårSjekker inngangsvilkårSjekker, Beregning beregningSjekke) {
        this.inngangsvilkårSjekker = inngangsvilkårSjekker;
        this.beregningSjekke = beregningSjekke;
    }

    public BehandlingResultat startBehandling(Fødselsnummer fødselsnummer, LocalDate startidspunkt)
            throws IOException {

        var inngangsvilkårResultat = inngangsvilkårSjekker.oppfyltVilkår(fødselsnummer, startidspunkt);

        if (inngangsvilkårResultat == null || inngangsvilkårResultat.getVilkårStatus().equals(VilkårStatus.AVSLÅTT)) {
            return new BehandlingResultat(VilkårStatus.AVSLÅTT, 0.0).medÅrsak(Årsak.INNGANGSVILKÅR);
        }

        var utbetaling = beregningSjekke.resultat(fødselsnummer, startidspunkt);
        if (utbetaling.getResultat() <= 0.0) {
            return new BehandlingResultat(VilkårStatus.AVSLÅTT, 0.0).medÅrsak(Årsak.BEREGNING);
        }

        return new BehandlingResultat(VilkårStatus.INNVILGET, utbetaling.getResultat());

    }


}
