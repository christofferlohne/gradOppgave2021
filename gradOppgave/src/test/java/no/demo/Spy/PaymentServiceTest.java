package no.demo.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.demo.CreditCard;
import no.demo.Customer;
import no.demo.Dummy.LoggerDummy;
import no.demo.Item;
import no.demo.Mock.PaymentService;
import no.demo.Sale;
import no.demo.Stub.OperatorRate;
import no.demo.Stub.OperatorRateStub;

class PaymentServiceTest {

    private PaymentEmailSenderSpy emailSenderSpy;
    private OperatorRate operatorRate;
    private PaymentService paymentService;
    private LoggerDummy loggerDummy;
    public static final Customer BOB = new Customer("Bob");
    public static final Item IPHONE = new Item("iPhone X", 1000);
    public static final Item IPHONE_LADER = new Item("iPhone lader", 500);
    public static final CreditCard BOB_CREDIT_CARD = new CreditCard(BOB, "1");

    @BeforeEach
    void setup() {
        loggerDummy = new LoggerDummy();
        operatorRate = new OperatorRateStub(10);
        emailSenderSpy = new PaymentEmailSenderSpy();
        paymentService = new PaymentService(loggerDummy, operatorRate, emailSenderSpy);
    }

    @Test
    void send_email_to_the_administration_if_sale_is_over_1000() {
        var sale = new Sale(BOB, List.of(IPHONE, IPHONE_LADER));

        paymentService.createPaymentRequest(sale, BOB_CREDIT_CARD);

        assertEquals(1, emailSenderSpy.timesCalled());
    }

    @Test
    void not_send_email_for_sales_under_eller_lik_1000() {
        var sale = new Sale(BOB, List.of(IPHONE_LADER));

        paymentService.createPaymentRequest(sale, BOB_CREDIT_CARD);

        assertEquals(0, emailSenderSpy.timesCalled());
    }
}
