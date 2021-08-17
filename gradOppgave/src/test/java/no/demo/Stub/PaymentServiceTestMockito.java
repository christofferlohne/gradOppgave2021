package no.demo.Stub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.demo.CreditCard;
import no.demo.Customer;
import no.demo.Item;
import no.demo.Logger;
import no.demo.Sale;

class PaymentServiceTestMockito {

    private OperatorRate operatorRate;
    private PaymentService paymentService;
    private Logger logger;
    public static final Customer BOB = new Customer("Bob");
    public static final Item IPHONE = new Item("iPhone X", 1000);
    public static final CreditCard BOB_CREDIT_CARD = new CreditCard(BOB, "1");

    @BeforeEach
    void setup() {
        logger = mock(Logger.class);

        // operatorRate = new OperatorRateStub(10);
        operatorRate = mock(OperatorRate.class);
        when(operatorRate.feeRate(BOB_CREDIT_CARD.creditcardNumber())).thenReturn(10);

        paymentService = new PaymentService(logger, operatorRate);
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
