package no.oppgave;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.oppgave.forretningslogikk.behandling.beregning.Beregning;
import no.oppgave.forretningslogikk.felles.Fødselsnummer;
import no.oppgave.klienter.InntektdataService;

class Oppgave4 {

    public static final Fødselsnummer PERSON_ID = new Fødselsnummer("12312312312");
    public static final LocalDate STARTIDSPUNKT = LocalDate.now();

    private Beregning underTest;
    private InntektdataService inntektdataService; //fiks meg


    @BeforeEach
    void setUp() {
        //Her mangler det noe
    }

    /*
    Oppgave 4: skirv ferdig testen for Beregning
    Hint: Det er kode som er kommentert ut og kode som mangler.
     */

    @Test
    public void riktigUtregningAvBeregning() throws IOException {

        //Sett inn kode her

        //(Hint) En årsinntekt på 240_000 gir forventet utbetaling 20_000

        //(Kommenter inn det under)
//        var resultat = underTest.resultat(PERSON_ID, STARTIDSPUNKT);
//         assertEquals(20_000.0, resultat.getResultat());
    }



}
