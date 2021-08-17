package no.demo.Mock;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.demo.CreditCard;
import no.demo.Customer;
import no.demo.Item;
import no.demo.Logger;
import no.demo.Sale;
import no.demo.Stub.OperatorRate;

class PaymentServiceTestMockito {

    //private PaymentEmailSenderMock emailSender;
    private PaymentEmailSender emailSender;
    private OperatorRate operatorRate;
    private PaymentService paymentService;

    // private LoggerDummy loggerDummy;
    private Logger logger;

    public static final Customer BOB = new Customer("Bob");
    public static final Item IPHONE = new Item("iPhone X", 1000);
    public static final CreditCard BOB_CREDIT_CARD = new CreditCard(BOB, "1");

    @BeforeEach
    void setup() {
        /** Dummy **/
        // loggerDummy = new LoggerDummy();
        logger = mock(Logger.class);

        /** Stub **/
        // operatorRate = new OperatorRateStub(10);
        operatorRate = mock(OperatorRate.class);
        given(operatorRate.feeRate(BOB_CREDIT_CARD.creditcardNumber())).willReturn(10);

        emailSender = mock(PaymentEmailSender.class);

        // paymentService = new PaymentService(loggerDummy, operatorRate, emailSender);

        paymentService = new PaymentService(logger, operatorRate, emailSender);
    }

    @Test
    void send_email_to_the_administration_if_sale_is_over_1000() {
        Item lader = new Item("Iphone lader", 200);
        Sale sale = new Sale(BOB, List.of(IPHONE, lader));

        paymentService.createPaymentRequest(sale, BOB_CREDIT_CARD);

        var paymentRequest = new PaymentRequest(1200, BOB_CREDIT_CARD.creditcardNumber(), 120);

        /** Mocking */
        //emailSender.expect(paymentRequest);
        //emailSender.verify();
        verify(emailSender).send(paymentRequest);

    }
}
