package no.oppgave;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.oppgave.forretningslogikk.behandling.beregning.BeregningSjekk;
import no.oppgave.forretningslogikk.felles.Fødselsnummer;
import no.oppgave.klienter.InntektdataResultat;
import no.oppgave.klienter.InntektdataService;

class Oppgave4Fasit {

    public static final Fødselsnummer PERSON_ID = new Fødselsnummer("12312312312");
    public static final LocalDate STARTIDSPUNKT = LocalDate.now();
    private BeregningSjekk underTest;
    private InntektdataService inntektdataService = mock(InntektdataService.class);

    @BeforeEach
    void setUp() {
        underTest = new BeregningSjekk(inntektdataService);
    }

    @Test
    public void riktigUtregningAvBeregning() throws IOException {
        when(inntektdataService.hentBeregning(PERSON_ID, STARTIDSPUNKT))
                .thenReturn(new InntektdataResultat().medÅrsinntekt(240_000.0));
        var resultat = underTest.resultat(PERSON_ID, STARTIDSPUNKT);

        assertEquals(20_000.0, resultat.getResultat());
    }



}
