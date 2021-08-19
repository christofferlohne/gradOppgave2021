package no.demo.Dummy;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import no.demo.Bankkort;
import no.demo.Kunde;
import no.demo.Salg;
import no.demo.Vare;


class BetalingstjenesteTest {

    @Test
    void verifiser_korrekt_sum_av_salg_ved_flere_varer() {
        var loggerDummy = new LoggerDummy();

        var kunde = new Kunde("Ola", "Nordman");
        var vare1 = new Vare("IPHONE", 1000);
        var vare2 = new Vare("Lader", 500);
        List<Vare> items= asList(vare1, vare2);
        Salg sale = new Salg(kunde , items);
        Bankkort bankkort = new Bankkort(kunde , "1");

        //Betalingstjeneste betalingstjeneste = new Betalingstjeneste(null);
        Betalingstjeneste betalingstjeneste = new Betalingstjeneste(loggerDummy);
        Betalingsforespørsel betalingsforespørsel = betalingstjeneste.lagBetalingsforespørsel(sale, bankkort);

        assertEquals(1500, betalingsforespørsel.beløp());
        assertEquals("1", betalingsforespørsel.bankkortNummer());
    }
}
