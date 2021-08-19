package no.demo.Dummy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.demo.Bankkort;
import no.demo.Kunde;
import no.demo.Logger;
import no.demo.Salg;
import no.demo.Vare;


class BetalingstjenesteTestzMockito {

    private Betalingstjeneste betalingstjeneste;
    // private LoggerDummy loggerDummy;
    private Logger logger;
    public static final Kunde BOB = new Kunde("BOBBY", "BOB");
    public static final Vare IPHONE = new Vare("iPhone X", 1000);
    public static final Vare IPHONE_LADER = new Vare("iPhone lader", 500);
    public static final Bankkort BOB_CREDIT_CARD = new Bankkort(BOB, "1");

    @BeforeEach
    void setup() {
//        loggerDummy = new LoggerDummy();
//        betalingstjeneste = new Betalingstjeneste(loggerDummy);
        logger = mock(Logger.class);
        betalingstjeneste = new Betalingstjeneste(logger);
    }


    @Test
    void verifiser_korrekt_sum_av_salg_ved_flere_varer() {
        var salg = new Salg(BOB, List.of(IPHONE, IPHONE_LADER));

        var betalingsforespørsel = betalingstjeneste.lagBetalingsforespørsel(salg, BOB_CREDIT_CARD);

        assertEquals(1500, betalingsforespørsel.beløp());
        assertEquals("1", betalingsforespørsel.bankkortNummer());
    }
}
