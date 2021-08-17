package no.demo.Stub;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.demo.CreditCard;
import no.demo.Customer;
import no.demo.Dummy.LoggerDummy;
import no.demo.Item;
import no.demo.Sale;

class PaymentServiceTest {

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
        paymentService = new PaymentService(loggerDummy, operatorRate);
    }

    @Test
    void create_payment_request_verify_correct_fee() {
        Sale sale = new Sale(BOB, List.of(IPHONE));

        PaymentRequest actual = paymentService.createPaymentRequest(sale, BOB_CREDIT_CARD);

        assertEquals(1000, actual.getSaleTotal());
        assertEquals(BOB_CREDIT_CARD.creditcardNumber(), actual.getCreditcardNumber());
        assertEquals(100, actual.getFee());
    }
}
