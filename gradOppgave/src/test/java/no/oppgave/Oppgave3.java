package no.oppgave;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.oppgave.forretningslogikk.behandling.inngangsvilkår.InngangsvilkårSjekker;
import no.oppgave.forretningslogikk.behandling.inngangsvilkår.VilkårStatus;
import no.oppgave.forretningslogikk.felles.Fødselsnummer;
import no.oppgave.klienter.MedlemskapResultat;
import no.oppgave.klienter.MedlemskapService;
import no.oppgave.klienter.OpptjeningResultat;
import no.oppgave.klienter.OpptjeningService;
import no.oppgave.klienter.PersondataService;

class Oppgave3 {

    private InngangsvilkårSjekker underTest;
    private MedlemskapService medlemskapService = mock(MedlemskapService.class);
    private OpptjeningService opptjeningService = mock(OpptjeningService.class);
    private PersondataService persondataService = mock(PersondataService.class);

    @BeforeEach
    void setUp() {
        underTest = new InngangsvilkårSjekker(medlemskapService, opptjeningService, persondataService);

    }

    @Test
    public void burdeFåInnvilgetVilkår() throws IOException {
        var fødselsnummer = new Fødselsnummer("12312312312");
        when(persondataService.personFinnes(fødselsnummer))
                .thenReturn(true);
        when(medlemskapService.harMedlemskap(fødselsnummer, LocalDate.now()))
                .thenReturn(new MedlemskapResultat(VilkårStatus.INNVILGET));
        when(opptjeningService.harOpptjening(fødselsnummer, LocalDate.now()))
                .thenReturn(new OpptjeningResultat(VilkårStatus.INNVILGET));

        var vilkårStatus = underTest.oppfyltVilkår(fødselsnummer, LocalDate.now());
        assertEquals(VilkårStatus.INNVILGET, vilkårStatus);
    }

}
