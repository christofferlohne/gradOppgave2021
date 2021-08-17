package no.demo.Dummy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.demo.CreditCard;
import no.demo.Customer;
import no.demo.Item;
import no.demo.Logger;
import no.demo.Sale;


class PaymentServiceTestzMockito {

    private PaymentService paymentService;
    // private LoggerDummy loggerDummy;
    private Logger logger;
    public static final Customer BOB = new Customer("Bob");
    public static final Item IPHONE = new Item("iPhone X", 1000);
    public static final Item IPHONE_LADER = new Item("iPhone lader", 500);
    public static final CreditCard BOB_CREDIT_CARD = new CreditCard(BOB, "1");

    @BeforeEach
    void setup() {
//        loggerDummy = new LoggerDummy();
//        paymentService = new PaymentService(loggerDummy);
        logger = mock(Logger.class);
        paymentService = new PaymentService(logger);
    }


    @Test
    void verify_correct_payment_request_returned() {
        var sale = new Sale(BOB, List.of(IPHONE, IPHONE_LADER));

        var actual = paymentService.createPaymentRequest(sale, BOB_CREDIT_CARD);

        assertEquals(1500, actual.getSaleTotal());
        assertEquals("1", actual.getCreditcardNumber());
    }
}
