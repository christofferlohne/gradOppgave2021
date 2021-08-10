package no.oppgave.eksterne;

import java.io.IOException;
import java.time.LocalDate;

public interface OpptjeningService {
    OpptjeningResultat harOpptjening(String personID, LocalDate startidspunkt) throws IOException;
}
