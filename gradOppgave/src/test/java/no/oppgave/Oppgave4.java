package no.oppgave;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import no.oppgave.forretningslogikk.felles.Fødselsnummer;

class Oppgave4 {

    public static final Fødselsnummer PERSON_ID = new Fødselsnummer("12312312312");
    public static final LocalDate STARTIDSPUNKT = LocalDate.now();


    /* commentert ut siden det mangler variabler.
    @BeforeEach
    void setUp() {
        underTest = new BeregningSjekk(inntektdataService);
    }
     */


    @Test
    public void riktigUtregningAvBeregning() throws IOException {

        //Sett inn kode her

        // assertEquals(20_000.0, resultat.getResultat());
    }



}
