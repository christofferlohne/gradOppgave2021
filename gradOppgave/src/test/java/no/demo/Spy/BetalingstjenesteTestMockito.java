package no.demo.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.demo.Bankkort;
import no.demo.Kunde;
import no.demo.Logger;
import no.demo.Mock.Betalingsforespørsel;
import no.demo.Mock.Betalingstjeneste;
import no.demo.Mock.EmailSender;
import no.demo.Salg;
import no.demo.Stub.OperatorRate;
import no.demo.Vare;

class BetalingstjenesteTestMockito {

    //private PaymentEmailSenderSpy emailSenderSpy;
    private EmailSender emailSender;
    private OperatorRate operatorRate;
    private Betalingstjeneste betalingstjeneste;
    private Logger logger;

    public static final Kunde BOB = new Kunde("BOBBY", "BOB");
    public static final Vare IPHONE = new Vare("iPhone X", 1000);
    public static final Vare IPHONE_LADER = new Vare("iPhone lader", 500);
    public static final Bankkort BOB_CREDIT_CARD = new Bankkort(BOB, "1");

    @BeforeEach
    void setup() {
        logger = mock(Logger.class);

        operatorRate = mock(OperatorRate.class);
        when(operatorRate.feeRate(BOB_CREDIT_CARD.kortnummer())).thenReturn(10);

        /** Mock/Spy **/
        // emailSender = new PaymentEmailSenderMock();
        emailSender = mock(EmailSender.class);

        betalingstjeneste = new Betalingstjeneste(logger, operatorRate, emailSender);
    }

    @Test
    void send_email_til_administrasjon_hvis_salg_er_over_1000_totalt() {
        var salg = new Salg(BOB, List.of(IPHONE, IPHONE_LADER));

        var betalingsforespørsel = betalingstjeneste.lagBetalingsforespørsel(salg, BOB_CREDIT_CARD);

        assertEquals(1500, betalingsforespørsel.getBeløp());
        assertEquals(BOB_CREDIT_CARD.kortnummer(), betalingsforespørsel.getKredittkortNummer());
        assertEquals(150, betalingsforespørsel.getFee());

        verify(emailSender, times(1)).send(betalingsforespørsel);
    }

    @Test
    void ikke_send_email_til_administrasjon_hvis_salg_er_under_1000_totalt() {
        var salg = new Salg(BOB, List.of(IPHONE_LADER));

        betalingstjeneste.lagBetalingsforespørsel(salg, BOB_CREDIT_CARD);

        var betalingsforespørsel = new Betalingsforespørsel(500, BOB_CREDIT_CARD.kortnummer(), 50);
        verify(emailSender, times(0)).send(betalingsforespørsel);
//        assertEquals(0, emailSenderSpy.timesCalled());
    }
}
