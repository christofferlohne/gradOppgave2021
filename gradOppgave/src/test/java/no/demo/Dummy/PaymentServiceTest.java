package no.demo.Dummy;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.demo.CreditCard;
import no.demo.Customer;
import no.demo.Item;
import no.demo.Sale;


class PaymentServiceTest {

    @BeforeEach
    void setup() {
        LoggerDummy loggerDummy = new LoggerDummy();

    }

    @Test
    void create_payment_request() {
        LoggerDummy loggerDummy = new LoggerDummy();
        Customer customer= new Customer("name");
        Item item = new Item("IPHONE", 1000);
        List<Item> items= asList(item);
        Sale sale = new Sale(customer, items);
        CreditCard creditCard = new CreditCard(customer, "1");

        PaymentService paymentService = new PaymentService(loggerDummy);
        PaymentRequest actual = paymentService.createPaymentRequest(sale, creditCard);
        assertEquals(new PaymentRequest(1000, "1"), actual);
    }
}
