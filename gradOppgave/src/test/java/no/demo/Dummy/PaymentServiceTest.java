package no.demo.Dummy;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import no.demo.CreditCard;
import no.demo.Customer;
import no.demo.Item;
import no.demo.Sale;


class PaymentServiceTest {

    @Test
    void verify_correct_payment_request_returned() {
        LoggerDummy loggerDummy = new LoggerDummy();
        Customer customer= new Customer("name");
        Item item1 = new Item("IPHONE", 1000);
        Item item2 = new Item("Lader", 500);
        List<Item> items= asList(item1, item2);
        Sale sale = new Sale(customer, items);
        CreditCard creditCard = new CreditCard(customer, "1");

        PaymentService paymentService = new PaymentService(loggerDummy);
        PaymentRequest actual = paymentService.createPaymentRequest(sale, creditCard);

        assertEquals(1500, actual.getSaleTotal());
        assertEquals("1", actual.getCreditcardNumber());
    }
}
