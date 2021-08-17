package no.fasit;

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

class Oppgave3Fasit {

    public static final Fødselsnummer PERSON_ID = new Fødselsnummer("12312312312");
    public static final LocalDate STARTIDSPUNKT = LocalDate.now();
    private InngangsvilkårSjekker underTest;
    private MedlemskapService medlemskapService = mock(MedlemskapService.class);
    private InntektdataService inntektdataService = mock(InntektdataService.class);
    private PersondataService persondataService = mock(PersondataService.class);

    @BeforeEach
    void setUp() {
        underTest = new InngangsvilkårSjekker(medlemskapService, inntektdataService, persondataService);
    }

    @Test
    public void burdeFåInnvilgetVilkår() throws IOException {
        when(persondataService.personFinnes(PERSON_ID))
                .thenReturn(true);
        when(medlemskapService.harMedlemskap(PERSON_ID, STARTIDSPUNKT))
                .thenReturn(new MedlemskapResultat(VilkårStatus.INNVILGET));
        when(inntektdataService.harOpptjening(PERSON_ID, STARTIDSPUNKT))
                .thenReturn(new OpptjeningResultat(VilkårStatus.INNVILGET));
        var inngangsvilkårResultat = underTest.oppfyltVilkår(PERSON_ID, STARTIDSPUNKT);
        assertEquals(VilkårStatus.INNVILGET, inngangsvilkårResultat.getVilkårStatus());
    }

    @Test
    public void burdeFåAvslagNårOpptjeningIkkeErOppfylt() throws IOException {
        when(persondataService.personFinnes(PERSON_ID))
                .thenReturn(true);
        when(medlemskapService.harMedlemskap(PERSON_ID, STARTIDSPUNKT))
                .thenReturn(new MedlemskapResultat(VilkårStatus.INNVILGET));
        when(inntektdataService.harOpptjening(PERSON_ID, STARTIDSPUNKT))
                .thenReturn(new OpptjeningResultat(VilkårStatus.AVSLÅTT));

        var inngangsvilkårResultat = underTest.oppfyltVilkår(PERSON_ID, STARTIDSPUNKT);
        assertEquals(VilkårStatus.AVSLÅTT, inngangsvilkårResultat.getVilkårStatus());
        assertEquals(Årsak.OPPTJENING, inngangsvilkårResultat.getÅrsak());
    }

    @Test
    public void burdeFåAvslagNårPersonIkkeFinnes() throws IOException {
        when(persondataService.personFinnes(PERSON_ID))
                .thenReturn(false);
        var inngangsvilkårResultat = underTest.oppfyltVilkår(PERSON_ID, STARTIDSPUNKT);
        assertEquals(VilkårStatus.AVSLÅTT, inngangsvilkårResultat.getVilkårStatus());
        assertEquals(Årsak.PERSONDATA, inngangsvilkårResultat.getÅrsak());
    }

}
