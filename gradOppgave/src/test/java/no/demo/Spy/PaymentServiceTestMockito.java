package no.demo.Spy;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.demo.CreditCard;
import no.demo.Customer;
import no.demo.Item;
import no.demo.Logger;
import no.demo.Mock.PaymentEmailSender;
import no.demo.Mock.PaymentRequest;
import no.demo.Mock.PaymentService;
import no.demo.Sale;
import no.demo.Stub.OperatorRate;

class PaymentServiceTestMockito {

    //private PaymentEmailSenderSpy emailSenderSpy;
    private PaymentEmailSender emailSender;
    private OperatorRate operatorRate;
    private PaymentService paymentService;
    private Logger logger;

    public static final Customer BOB = new Customer("Bob");
    public static final Item IPHONE = new Item("iPhone X", 1000);
    public static final CreditCard BOB_CREDIT_CARD = new CreditCard(BOB, "1");

    @BeforeEach
    void setup() {
        logger = mock(Logger.class);

        operatorRate = mock(OperatorRate.class);
        given(operatorRate.feeRate(BOB_CREDIT_CARD.creditcardNumber())).willReturn(10);

        /** Mock/Spy */
        // emailSender = new PaymentEmailSenderMock();
        emailSender = mock(PaymentEmailSender.class);

        paymentService = new PaymentService(logger, operatorRate, emailSender);
    }

    @Test
    void not_send_email_for_sales_under_eller_lik_1000() {
        Item lader = new Item("Iphone lader", 200);
        Sale sale = new Sale(BOB, List.of(lader));

        PaymentRequest paymentRequest1 = paymentService.createPaymentRequest(sale, BOB_CREDIT_CARD);

        PaymentRequest paymentRequest = new PaymentRequest(200, BOB_CREDIT_CARD.creditcardNumber(), 20);
        verify(emailSender, times(0)).send(paymentRequest);
//        assertEquals(0, emailSenderSpy.timesCalled());
    }
}
