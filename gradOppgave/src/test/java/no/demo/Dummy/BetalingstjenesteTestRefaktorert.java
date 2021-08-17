package no.demo.Dummy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.demo.Bankkort;
import no.demo.Kunde;
import no.demo.Salg;
import no.demo.Vare;


class BetalingstjenesteTestRefaktorert {

    private Betalingstjeneste betalingstjeneste;
    private LoggerDummy loggerDummy;
    public static final Kunde BOB = new Kunde("Bob");
    public static final Vare IPHONE = new Vare("iPhone X", 1000);
    public static final Vare IPHONE_LADER = new Vare("iPhone lader", 500);
    public static final Bankkort BOB_BANKKORT = new Bankkort(BOB, "1");

    @BeforeEach
    void setup() {
        loggerDummy = new LoggerDummy();
        betalingstjeneste = new Betalingstjeneste(loggerDummy);
    }


    @Test
    void verifiser_korrekt_sum_av_salg_ved_flere_varer() {
        var salg = new Salg(BOB, List.of(IPHONE, IPHONE_LADER));

        var betalingsforespørsel = betalingstjeneste.lagBetalingsforespørsel(salg, BOB_BANKKORT);

        assertEquals(1500, betalingsforespørsel.beløp());
        assertEquals("1", betalingsforespørsel.bankkortNummer());
    }
}
