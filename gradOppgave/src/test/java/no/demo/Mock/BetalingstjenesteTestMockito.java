package no.demo.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.demo.Bankkort;
import no.demo.Kunde;
import no.demo.Logger;
import no.demo.Salg;
import no.demo.Stub.OperatorRate;
import no.demo.Vare;

class BetalingstjenesteTestMockito {

    //private PaymentEmailSenderMock emailSender;
    private EmailSender emailSender;
    private OperatorRate operatorRate;
    private Betalingstjeneste betalingstjeneste;
    private Logger logger;

    public static final Kunde BOB = new Kunde("BOBBY", "BOB");
    public static final Vare IPHONE = new Vare("iPhone X", 1000);
    public static final Vare IPHONE_LADER = new Vare("iPhone lader", 500);
    public static final Bankkort BOB_KREDITTKORT = new Bankkort(BOB, "1");

    @BeforeEach
    void setup() {
        logger = mock(Logger.class);

        operatorRate = mock(OperatorRate.class);
        when(operatorRate.feeRate(BOB_KREDITTKORT.kortnummer())).thenReturn(10);

        /** Mock **/
        // emailSender = new PaymentEmailSenderMock();
        emailSender = mock(EmailSender.class);

        betalingstjeneste = new Betalingstjeneste(logger, operatorRate, emailSender);
    }

    @Test
    void send_email_til_administrasjon_hvis_salg_er_over_1000_totalt() {
        var salg = new Salg(BOB, List.of(IPHONE, IPHONE_LADER));

        betalingstjeneste.lagBetalingsforespørsel(salg, BOB_KREDITTKORT);

        var betalingsforespørsel = new Betalingsforespørsel(1500, BOB_KREDITTKORT.kortnummer(), 150);

        /** Mock **/
        //emailSender.expect(betalingsforespørsel);
        //emailSender.verify();
        verify(emailSender).send(betalingsforespørsel);
    }

    @Test
    void ikke_send_email_til_administrasjon_hvis_salg_er_under_1000_totalt() {
        var salg = new Salg(BOB, List.of(IPHONE_LADER));

        var betalingsforespørsel = betalingstjeneste.lagBetalingsforespørsel(salg, BOB_KREDITTKORT);

        assertEquals(500, betalingsforespørsel.getBeløp());
        assertEquals(BOB_KREDITTKORT.kortnummer(), betalingsforespørsel.getKredittkortNummer());
        assertEquals(50, betalingsforespørsel.getFee());

        /** Mock **/
        //emailSender.verify();
        verify(emailSender, never()).send(betalingsforespørsel);
    }
}
