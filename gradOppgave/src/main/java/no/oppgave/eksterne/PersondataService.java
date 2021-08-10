package no.oppgave.eksterne;

import java.io.IOException;

public interface PersondataService {
    Boolean personFinnes(String personID) throws IOException;
}
