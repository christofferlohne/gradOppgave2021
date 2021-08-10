package no.oppgave.klienter;

import java.io.IOException;

import no.oppgave.forretningslogikk.felles.Fødselsnummer;

public interface PersondataService {
    Boolean personFinnes(Fødselsnummer fødselsnummer) throws IOException;
}
