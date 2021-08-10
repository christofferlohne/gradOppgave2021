package no.oppgave.eksterne;

import java.io.IOException;
import java.time.LocalDate;

public interface MedlemskapService {
    MedlemskapResultat harMedlemskap(String personID, LocalDate startidspunkt) throws IOException;
}
