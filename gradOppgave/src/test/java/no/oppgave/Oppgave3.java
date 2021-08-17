package no.oppgave;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.oppgave.forretningslogikk.behandling.inngangsvilkår.InngangsvilkårSjekker;
import no.oppgave.forretningslogikk.felles.Fødselsnummer;
import no.oppgave.forretningslogikk.felles.VilkårStatus;
import no.oppgave.forretningslogikk.felles.Årsak;
import no.oppgave.klienter.InntektdataService;
import no.oppgave.klienter.MedlemskapResultat;
import no.oppgave.klienter.MedlemskapService;
import no.oppgave.klienter.OpptjeningResultat;
import no.oppgave.klienter.PersondataService;

class Oppgave3 {

    public static Fødselsnummer PERSON_ID; //fiks her
    public static LocalDate STARTIDSPUNKT; //fiks her

    private InngangsvilkårSjekker underTest;
    private MedlemskapService medlemskapService = mock(MedlemskapService.class);
    private InntektdataService inntektdataService = mock(InntektdataService.class);
    private PersondataService persondataService = mock(PersondataService.class);

    @BeforeEach
    void setUp() {
        underTest = new InngangsvilkårSjekker(medlemskapService, inntektdataService, persondataService);
    }

    /*
    Oppgave 3:
        - Endre på input til funksjonen slik at den tar inn en public static konstant.
        - Lag to tester til:
            - burdeFåAvslagNårOpptjeningIkkeErOppfylt()
            - burdeFåAvslagNårPersonIkkeFinnes()
     */

    @Test
    public void burdeFåInnvilgetVilkår() throws IOException {

        //Skriv om testen til å bruke PERSON_ID og STARTIDSPUNKT
        var fødselsnummer = new Fødselsnummer("12312312312");
        when(persondataService.personFinnes(fødselsnummer))
                .thenReturn(true);
        when(medlemskapService.harMedlemskap(fødselsnummer, LocalDate.now()))
                .thenReturn(new MedlemskapResultat(VilkårStatus.INNVILGET));
        when(inntektdataService.harOpptjening(fødselsnummer, LocalDate.now()))
                .thenReturn(new OpptjeningResultat(VilkårStatus.INNVILGET));

        var inngangsvilkårResultat = underTest.oppfyltVilkår(PERSON_ID, STARTIDSPUNKT);
        assertEquals(VilkårStatus.INNVILGET, inngangsvilkårResultat.getVilkårStatus());
    }

    @Test
    public void burdeFåAvslagNårOpptjeningIkkeErOppfylt() throws IOException {
        //Skriv ferdig testen.
        //Skal få avslag siden Oppthening ikke er oppfylt.

        var inngangsvilkårResultat = underTest.oppfyltVilkår(PERSON_ID, STARTIDSPUNKT);
        assertEquals(VilkårStatus.AVSLÅTT, inngangsvilkårResultat.getVilkårStatus());
        assertEquals(Årsak.OPPTJENING, inngangsvilkårResultat.getÅrsak());
    }

    @Test
    public void burdeFåAvslagNårPersonIkkeFinnes() throws IOException {
        //Skriv ferdig testen.
        //Skal få avslag når person ikke finnes.

        var inngangsvilkårResultat = underTest.oppfyltVilkår(PERSON_ID, STARTIDSPUNKT);
        assertEquals(VilkårStatus.AVSLÅTT, inngangsvilkårResultat.getVilkårStatus());
        assertEquals(Årsak.PERSONDATA, inngangsvilkårResultat.getÅrsak());

    }
}
