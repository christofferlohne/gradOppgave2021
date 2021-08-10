package no.oppgave;

import no.oppgave.eksterne.MedlemskapService;
import no.oppgave.eksterne.OpptjeningService;
import no.oppgave.eksterne.PersondataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class Oppgave2 {

    private OppfyltInngangsvilkårService underTest;
    private MedlemskapService medlemskapService = mock(MedlemskapService.class);
    private OpptjeningService opptjeningService = mock(OpptjeningService.class);
    private PersondataService persondataService = mock(PersondataService.class);

    @BeforeEach
    void setUp() {
        underTest = new OppfyltInngangsvilkårService(medlemskapService, opptjeningService, persondataService);

    }

    /*
    Oppgaven 2: Få testen til å gå grønt!!!
    Bruk Mockito.when()


     */
    @Test
    public void burdeFåInnvilgetVilkår() throws IOException {
        var vilkårStatus = underTest.oppfyltVilkår("12312312312", LocalDate.now());
        assertEquals(VilkårStatus.INNVILGET, vilkårStatus);
    }

}