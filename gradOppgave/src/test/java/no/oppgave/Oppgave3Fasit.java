package no.oppgave;

import no.oppgave.eksterne.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Oppgave3Fasit {

    public static final String PERSON_ID = "12312312312";
    public static final LocalDate STARTIDSPUNKT = LocalDate.now();
    private OppfyltInngangsvilkårService underTest;
    private MedlemskapService medlemskapService = mock(MedlemskapService.class);
    private OpptjeningService opptjeningService = mock(OpptjeningService.class);
    private PersondataService persondataService = mock(PersondataService.class);

    @BeforeEach
    void setUp() {
        underTest = new OppfyltInngangsvilkårService(medlemskapService, opptjeningService, persondataService);

    }

    @Test
    public void burdeFåInnvilgetVilkår() throws IOException {
        when(persondataService.personFinnes(PERSON_ID))
                .thenReturn(true);
        when(medlemskapService.harMedlemskap(PERSON_ID, STARTIDSPUNKT))
                .thenReturn(new MedlemskapResultat(VilkårStatus.INNVILGET));
        when(opptjeningService.harOpptjening(PERSON_ID, STARTIDSPUNKT))
                .thenReturn(new OpptjeningResultat(VilkårStatus.INNVILGET));
        var vilkårStatus = underTest.oppfyltVilkår(PERSON_ID, STARTIDSPUNKT);
        assertEquals(VilkårStatus.INNVILGET, vilkårStatus);
    }

    @Test
    public void burdeFåAvslagNårOpptjeningIkkeErOppfylt() throws IOException {
        when(persondataService.personFinnes(PERSON_ID))
                .thenReturn(true);
        when(medlemskapService.harMedlemskap(PERSON_ID, STARTIDSPUNKT))
                .thenReturn(new MedlemskapResultat(VilkårStatus.INNVILGET));
        when(opptjeningService.harOpptjening(PERSON_ID, STARTIDSPUNKT))
                .thenReturn(new OpptjeningResultat(VilkårStatus.AVSLÅTT));

        var vilkårStatus = underTest.oppfyltVilkår(PERSON_ID, STARTIDSPUNKT);
        assertEquals(VilkårStatus.AVSLÅTT, vilkårStatus);
    }

    @Test
    public void burdeFåAvslagNårPersonIkkeFinnes() throws IOException {
        when(persondataService.personFinnes(PERSON_ID))
                .thenReturn(true);
        var vilkårStatus = underTest.oppfyltVilkår(PERSON_ID, STARTIDSPUNKT);
        assertEquals(VilkårStatus.AVSLÅTT, vilkårStatus);
    }

}