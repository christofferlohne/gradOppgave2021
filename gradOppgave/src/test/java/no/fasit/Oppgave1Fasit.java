package no.fasit;

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

class Oppgave1Fasit {

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
        var inngangsvilkårResultat = underTest.oppfyltVilkår(new Fødselsnummer("12312312312"), LocalDate.now());
        assertEquals(VilkårStatus.INNVILGET, inngangsvilkårResultat);
    }

}
