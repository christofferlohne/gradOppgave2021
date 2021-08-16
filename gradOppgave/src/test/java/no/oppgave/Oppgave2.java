package no.oppgave;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

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

class Oppgave2 {

    private InngangsvilkårSjekker underTest;
    private MedlemskapService medlemskapService = mock(MedlemskapService.class);
    private InntektdataService inntektdataService = mock(InntektdataService.class);
    private PersondataService persondataService = mock(PersondataService.class);

    @BeforeEach
    void setUp() {
        underTest = new InngangsvilkårSjekker(medlemskapService, inntektdataService, persondataService);

    }

    /*
    Oppgaven 2: Få testen til å gå grønt (ikke lengre en feil i foretningslogikken.)

    For at vi skal få innvilget viklår så må følgende gjelde:
        1) Personen må finnes
        2) MedlemskapResultat må være gyldig.
        3) Opptjeningsvilkåret må være oppfylt.

    Hint:
        - Mockito.when() for få stube ut input (på funksjonskall)
        - Mockito.thenReturn() for å stubbe ut output.
     */

    @Test
    public void burdeFåInnvilgetVilkår() throws IOException {
        var inngangsvilkårResultat = underTest.oppfyltVilkår(new Fødselsnummer("12312312312"), LocalDate.now());
        assertEquals(VilkårStatus.INNVILGET, inngangsvilkårResultat.getVilkårStatus());
    }

}
