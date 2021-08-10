package no.oppgave;

import no.oppgave.eksterne.MedlemskapService;
import no.oppgave.eksterne.OpptjeningService;
import no.oppgave.eksterne.PersondataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class Oppgave1 {

    private OppfyltInngangsvilkårService underTest;
    private MedlemskapService medlemskapService;
    private OpptjeningService opptjeningService;
    private PersondataService persondataService;

    @BeforeEach
    void setUp() {
        underTest = new OppfyltInngangsvilkårService(medlemskapService, opptjeningService, persondataService);

    }

    @Test
    public void burdeFåInnvilgetVilkår() throws IOException {
        var vilkårStatus = underTest.oppfyltVilkår("12312312312", LocalDate.now());
        assertEquals(VilkårStatus.INNVILGET, vilkårStatus);
    }

}