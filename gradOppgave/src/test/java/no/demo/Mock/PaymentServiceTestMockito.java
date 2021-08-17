package no.demo.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    private Logger logger;

    public static final Customer BOB = new Customer("Bob");
    public static final Item IPHONE = new Item("iPhone X", 1000);
    public static final CreditCard BOB_CREDIT_CARD = new CreditCard(BOB, "1");

    @BeforeEach
    void setup() {
        logger = mock(Logger.class);

        operatorRate = mock(OperatorRate.class);
        when(operatorRate.feeRate(BOB_CREDIT_CARD.creditcardNumber())).thenReturn(10);

        /** Mock */
        // emailSender = new PaymentEmailSenderMock();
        emailSender = mock(PaymentEmailSender.class);

        paymentService = new PaymentService(logger, operatorRate, emailSender);
    }

    @Test
    void send_email_to_the_administration_if_sale_is_over_1000() {
        Item lader = new Item("Iphone lader", 200);
        Sale sale = new Sale(BOB, List.of(IPHONE, lader));

        paymentService.createPaymentRequest(sale, BOB_CREDIT_CARD);

        var paymentRequest = new PaymentRequest(1200, BOB_CREDIT_CARD.creditcardNumber(), 120);

        /** Mock */
        //emailSender.expect(paymentRequest);
        //emailSender.verify();
        verify(emailSender).send(paymentRequest);

    }
}