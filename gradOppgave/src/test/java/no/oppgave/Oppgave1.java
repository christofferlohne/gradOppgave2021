package no.oppgave;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.oppgave.forretningslogikk.behandling.inngangsvilkår.InngangsvilkårSjekker;
import no.oppgave.forretningslogikk.felles.VilkårStatus;
import no.oppgave.forretningslogikk.felles.Fødselsnummer;
import no.oppgave.klienter.MedlemskapService;
import no.oppgave.klienter.InntektdataService;
import no.oppgave.klienter.PersondataService;

class Oppgave1 {

    private InngangsvilkårSjekker underTest;
    private MedlemskapService medlemskapService;
    private InntektdataService inntektdataService;
    private PersondataService persondataService;

    @BeforeEach
    void setUp() {
        underTest = new InngangsvilkårSjekker(medlemskapService, inntektdataService, persondataService);

    }

    /*
    Oppgave 1:  Få testen til å kompiler (ikke få en java.lang.NullPointerException feil, at testen går gult er ok)
    Bruk Mockito mock() for å instansieres klassene/servicene som "underTest" er avhengig av.
    Kan se på https://site.mockito.org/ for mer info
     */

    @Test
    public void burdeFåInnvilgetVilkår() throws IOException {
        var inngangsvilkårResultat = underTest.oppfyltVilkår(new Fødselsnummer("12312312312"), LocalDate.now());
        assertEquals(VilkårStatus.INNVILGET, inngangsvilkårResultat);
    }

}
