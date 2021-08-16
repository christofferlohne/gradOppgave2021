package no.oppgave.forretningslogikk.behandling.inngangsvilkår;

import java.io.IOException;
import java.time.LocalDate;

import no.oppgave.forretningslogikk.felles.Fødselsnummer;
import no.oppgave.forretningslogikk.felles.VilkårStatus;
import no.oppgave.forretningslogikk.felles.Årsak;
import no.oppgave.klienter.MedlemskapService;
import no.oppgave.klienter.InntektdataService;
import no.oppgave.klienter.PersondataService;

public class InngangsvilkårSjekker {

    private MedlemskapService medlemskapService;
    private InntektdataService inntektdataService;
    private PersondataService persondataService;

    public InngangsvilkårSjekker(MedlemskapService medlemskapService,
                                 InntektdataService inntektdataService,
                                 PersondataService persondataService) {
        this.medlemskapService = medlemskapService;
        this.inntektdataService = inntektdataService;
        this.persondataService = persondataService;
    }

    public InngangsvilkårResultat oppfyltVilkår(Fødselsnummer fødselsnummer, LocalDate startidspunkt)
            throws IOException {

        var personFinnes = persondataService.personFinnes(fødselsnummer);
        if (!personFinnes) {
            return new InngangsvilkårResultat(VilkårStatus.AVSLÅTT, Årsak.PERSONDATA);
        }

        var medlemskapResultat = medlemskapService.harMedlemskap(fødselsnummer, startidspunkt);
        if (medlemskapResultat == null || medlemskapResultat.getVilkårStatus().equals(VilkårStatus.AVSLÅTT)) {
            return new InngangsvilkårResultat(VilkårStatus.AVSLÅTT, Årsak.MEDLEMSKAP);
        }

        var opptjeningResultat = inntektdataService.harOpptjening(fødselsnummer, startidspunkt);
        if (opptjeningResultat == null || opptjeningResultat.getVilkårStatus().equals(VilkårStatus.AVSLÅTT)) {
            return new InngangsvilkårResultat(VilkårStatus.AVSLÅTT, Årsak.OPPTJENING);
        }

        return new InngangsvilkårResultat(VilkårStatus.INNVILGET, null);
    }
}
