package no.oppgave;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.oppgave.forretningslogikk.behandling.Behandling;
import no.oppgave.forretningslogikk.behandling.beregning.Beregning;
import no.oppgave.forretningslogikk.behandling.inngangsvilkår.InngangsvilkårSjekker;
import no.oppgave.forretningslogikk.felles.Fødselsnummer;
import no.oppgave.forretningslogikk.felles.VilkårStatus;
import no.oppgave.forretningslogikk.felles.Årsak;
import no.oppgave.klienter.InntektdataService;
import no.oppgave.klienter.MedlemskapService;
import no.oppgave.klienter.PersondataService;

class Oppgave5 {

    public static final Fødselsnummer PERSON_ID = new Fødselsnummer("12312312312");
    public static final LocalDate STARTIDSPUNKT = LocalDate.now();

    private Behandling underTest;
    private final MedlemskapService medlemskapService = mock(MedlemskapService.class);
    private final InntektdataService inntektdataService = mock(InntektdataService.class);
    private final PersondataService persondataService = mock(PersondataService.class);

    private final InngangsvilkårSjekker inngangsvilkårSjekker =
            new InngangsvilkårSjekker(medlemskapService, inntektdataService, persondataService);
    private final Beregning beregning =
            new Beregning(inntektdataService);


    @BeforeEach
    void setUp() {
        underTest = new Behandling(inngangsvilkårSjekker, beregning);
    }

    /*
    Oppgave 5:


    Enheten som skal testes er utvidet til å være mer enn en klasse.
    Nå er underTest Behandling og den har avhengighet til InngangsvilkårSjekker og Beregning, de skal ikke mockes eller stubbes ut.
    Bruk mock og stub for InngangsvilkårSjekker og Beregning sine avhengigheter, slik som det er gjort i tidligere oppgaver.

     */

    @Test
    public void burdeFåInnvilgetVilkår() throws IOException {
        //sett inn kode her

        var behandlingResultat = underTest.startBehandling(PERSON_ID, STARTIDSPUNKT);
        assertEquals(VilkårStatus.INNVILGET, behandlingResultat.getVilkårStatus());
    }

    @Test
    public void avslagMedBeregningSomÅrsak() throws IOException {
        //sett inn kode her

        var behandlingResultat = underTest.startBehandling(PERSON_ID, STARTIDSPUNKT);
        assertEquals(VilkårStatus.AVSLÅTT, behandlingResultat.getVilkårStatus());
        assertEquals(Årsak.BEREGNING, behandlingResultat.getÅrsak());
    }


}
