package no.demo.Mock;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.demo.CreditCard;
import no.demo.Customer;
import no.demo.Dummy.LoggerDummy;
import no.demo.Item;
import no.demo.Sale;
import no.demo.Stub.OperatorRate;
import no.demo.Stub.OperatorRateStub;

class PaymentServiceTest {

    private PaymentEmailSenderMock emailSender;
    private OperatorRate operatorRate;
    private PaymentService paymentService;
    private LoggerDummy loggerDummy;
    public static final Customer BOB = new Customer("Bob");
    public static final Item IPHONE = new Item("iPhone X", 1000);
    public static final CreditCard BOB_CREDIT_CARD = new CreditCard(BOB, "1");

    @BeforeEach
    void setup() {
        loggerDummy = new LoggerDummy();
        operatorRate = new OperatorRateStub(10);
        emailSender = new PaymentEmailSenderMock();
        paymentService = new PaymentService(loggerDummy, operatorRate, emailSender);
    }

    @Test
    void send_email_to_the_administration_if_sale_is_over_1000() {
        Item lader = new Item("Iphone lader", 200);
        Sale sale = new Sale(BOB, List.of(IPHONE, lader));

        paymentService.createPaymentRequest(sale, BOB_CREDIT_CARD);

        var paymentRequest = new PaymentRequest(1200, BOB_CREDIT_CARD.creditcardNumber(), 100);
        emailSender.expect(paymentRequest);
        emailSender.verify();
    }
}
