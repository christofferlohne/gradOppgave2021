package no.demo.Stub;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.demo.Bankkort;
import no.demo.Dummy.LoggerDummy;
import no.demo.Kunde;
import no.demo.Salg;
import no.demo.Vare;

class BetalingstjenesteTest {

    private OperatorRate operatorRate;
    private Betalingstjeneste betalingstjeneste;
    private LoggerDummy loggerDummy;
    public static final Kunde BOB = new Kunde("Bob");
    public static final Vare IPHONE = new Vare("iPhone X", 1000);
    public static final Bankkort BOB_CREDIT_CARD = new Bankkort(BOB, "1");

    @BeforeEach
    void setup() {
        loggerDummy = new LoggerDummy();
        operatorRate = new OperatorRateStub(10);
        betalingstjeneste = new Betalingstjeneste(loggerDummy, operatorRate);
    }

    @Test
    void betalingsforespørslen_skal_inneholde_riktig_fee() {
        Salg salg = new Salg(BOB, List.of(IPHONE));

        Betalingsforespørsel betalingsforespørsel = betalingstjeneste.lagBetalingsforespørsel(salg, BOB_CREDIT_CARD);

        assertEquals(1000, betalingsforespørsel.getBeløp());
        assertEquals(BOB_CREDIT_CARD.kortnummer(), betalingsforespørsel.getKredittkortNummer());
        assertEquals(100, betalingsforespørsel.getFee());
    }
}
