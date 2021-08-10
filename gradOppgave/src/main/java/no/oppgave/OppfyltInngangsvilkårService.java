package no.oppgave;

import no.oppgave.eksterne.MedlemskapService;
import no.oppgave.eksterne.OpptjeningService;
import no.oppgave.eksterne.PersondataService;

import java.io.IOException;
import java.time.LocalDate;

public class OppfyltInngangsvilkårService {

    private MedlemskapService medlemskapService;
    private OpptjeningService opptjeningService;
    private PersondataService persondataService;

    public OppfyltInngangsvilkårService(MedlemskapService medlemskapService,
                                        OpptjeningService opptjeningService,
                                        PersondataService persondataService) {
        this.medlemskapService = medlemskapService;
        this.opptjeningService = opptjeningService;
        this.persondataService = persondataService;
    }

    public VilkårStatus oppfyltVilkår(String personID, LocalDate startidspunkt)
            throws IOException {

        var personFinnes = persondataService.personFinnes(personID);

        if (!personFinnes) {
            return VilkårStatus.AVSLÅTT;
        }

        var medlemskapResultat = medlemskapService.harMedlemskap(personID,
                startidspunkt);
        if (medlemskapResultat == null || medlemskapResultat.getVilkårStatus().equals(VilkårStatus.AVSLÅTT)) {
            return VilkårStatus.AVSLÅTT;
        }

        var opptjeningResultat = opptjeningService.harOpptjening(personID, startidspunkt);
        if (opptjeningResultat == null || opptjeningResultat.getVilkårStatus().equals(VilkårStatus.AVSLÅTT)) {
            return VilkårStatus.AVSLÅTT;
        }

        return VilkårStatus.INNVILGET;
    }
}
