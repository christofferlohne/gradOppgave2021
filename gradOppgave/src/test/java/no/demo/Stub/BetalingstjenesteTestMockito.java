package no.demo.Stub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.demo.Bankkort;
import no.demo.Kunde;
import no.demo.Logger;
import no.demo.Salg;
import no.demo.Vare;

class BetalingstjenesteTestMockito {

    private OperatorRate operatorRate;
    private Betalingstjeneste betalingstjeneste;
    private Logger logger;
    public static final Kunde BOB = new Kunde("BOBBY", "BOB");
    public static final Vare IPHONE = new Vare("iPhone X", 1000);
    public static final Bankkort BOB_KREDITTKORT = new Bankkort(BOB, "1");

    @BeforeEach
    void setup() {
        logger = mock(Logger.class);

        // operatorRate = new OperatorRateStub(10);
        operatorRate = mock(OperatorRate.class);
        when(operatorRate.feeRate(BOB_KREDITTKORT.kortnummer())).thenReturn(10);

        betalingstjeneste = new Betalingstjeneste(logger, operatorRate);
    }

    @Test
    void betalingsforespørslen_skal_inneholde_riktig_fee() {
        Salg sale = new Salg(BOB, List.of(IPHONE));

        Betalingsforespørsel actual = betalingstjeneste.lagBetalingsforespørsel(sale, BOB_KREDITTKORT);

        assertEquals(1000, actual.getBeløp());
        assertEquals(BOB_KREDITTKORT.kortnummer(), actual.getKredittkortNummer());
        assertEquals(100, actual.getFee());
    }
}
