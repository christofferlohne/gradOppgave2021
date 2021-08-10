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
import no.oppgave.klienter.OpptjeningService;
import no.oppgave.klienter.PersondataService;

class Oppgave1 {

    private InngangsvilkårSjekker underTest;
    private MedlemskapService medlemskapService;
    private OpptjeningService opptjeningService;
    private PersondataService persondataService;

    @BeforeEach
    void setUp() {
        underTest = new InngangsvilkårSjekker(medlemskapService, opptjeningService, persondataService);

    }

    @Test
    public void burdeFåInnvilgetVilkår() throws IOException {
        var vilkårStatus = underTest.oppfyltVilkår(new Fødselsnummer("12312312312"), LocalDate.now());
        assertEquals(VilkårStatus.INNVILGET, vilkårStatus);
    }

}
