package no.demo.Stub;


import no.demo.CreditCard;
import no.demo.Logger;
import no.demo.Sale;

public class PaymentService {

    private Logger logger;
    private OperatorRate operatorRate;


    public PaymentService(Logger logger, OperatorRate operatorRate) {
        this.logger = logger;
        this.operatorRate = operatorRate;
    }

    public PaymentRequest createPaymentRequest(Sale sale, CreditCard creditCard) {
        logger.append("Creating payment for sale " + sale.toString());

        int feeRate = operatorRate.feeRate(creditCard.creditcardNumber());
        int fee = (feeRate * sale.total()) / 100; // 10% fee

        return new PaymentRequest(sale.total(), creditCard.creditcardNumber(), fee);
    }
}
