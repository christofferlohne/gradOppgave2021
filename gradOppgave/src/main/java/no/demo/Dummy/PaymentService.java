package no.demo.Dummy;


import no.demo.CreditCard;
import no.demo.Logger;
import no.demo.Sale;

public class PaymentService {

    private Logger logger;

    public PaymentService(Logger logger) {
        this.logger = logger;
    }

    public PaymentRequest createPaymentRequest(Sale sale, CreditCard creditCard) {
        logger.append("Creating payment for sale " + sale.toString());

        return new PaymentRequest(sale.total(), creditCard.creditcardNumber());
    }
}
