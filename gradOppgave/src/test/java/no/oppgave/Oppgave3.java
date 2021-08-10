package no.oppgave;

import no.oppgave.eksterne.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Oppgave3 {

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
        when(persondataService.personFinnes("12312312312"))
                .thenReturn(true);
        when(medlemskapService.harMedlemskap("12312312312", LocalDate.now()))
                .thenReturn(new MedlemskapResultat(VilkårStatus.INNVILGET));
        when(opptjeningService.harOpptjening("12312312312", LocalDate.now()))
                .thenReturn(new OpptjeningResultat(VilkårStatus.INNVILGET));

        var vilkårStatus = underTest.oppfyltVilkår("12312312312", LocalDate.now());
        assertEquals(VilkårStatus.INNVILGET, vilkårStatus);
    }

}