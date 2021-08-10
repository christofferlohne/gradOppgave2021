package no.oppgave.klienter;

import java.io.IOException;
import java.time.LocalDate;

import no.oppgave.forretningslogikk.felles.Fødselsnummer;

public interface InntektdataService {
    InntektdataResultat harOpptjening(Fødselsnummer fødselsnummer, LocalDate startidspunkt) throws IOException;

    InntektdataResultat hentBeregning(Fødselsnummer fødselsnummer, LocalDate startidspunkt) throws IOException;
}
