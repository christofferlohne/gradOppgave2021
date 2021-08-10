package no.oppgave.forretningslogikk.behandling.beregning;

import java.io.IOException;
import java.time.LocalDate;

import no.oppgave.forretningslogikk.felles.Fødselsnummer;
import no.oppgave.klienter.InntektdataService;

public class BeregningSjekk {
    private InntektdataService inntektdataService;

    public BeregningSjekk(InntektdataService inntektdataService) {
        this.inntektdataService = inntektdataService;
    }

    public BeregningResultat resultat(Fødselsnummer fødselsnummer, LocalDate startidspunkt)
        throws IOException {

        var inntektResultat = inntektdataService.hentBeregning(fødselsnummer, startidspunkt);

        if (inntektResultat == null) {
            return new BeregningResultat(0);
        }

        return new BeregningResultat(inntektResultat.getÅrsinntekt() / 12);
    }

}
