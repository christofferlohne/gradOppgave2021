package no.oppgave.klienter;

import java.io.IOException;
import java.time.LocalDate;

import no.oppgave.forretningslogikk.felles.Fødselsnummer;

public interface OpptjeningService {
    OpptjeningResultat harOpptjening(Fødselsnummer fødselsnummer, LocalDate startidspunkt) throws IOException;
}
