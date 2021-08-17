package no.demo.Mock;


import no.demo.CreditCard;
import no.demo.Logger;
import no.demo.Sale;
import no.demo.Stub.OperatorRate;

public class PaymentService {

    private Logger logger;
    private OperatorRate operatorRate;
    private final PaymentEmailSender emailSender;

    public PaymentService(Logger logger, OperatorRate operatorRate, PaymentEmailSender emailSender) {
        this.logger = logger;
        this.operatorRate = operatorRate;
        this.emailSender = emailSender;
    }

    public PaymentRequest createPaymentRequest(Sale sale, CreditCard creditCard) {
        logger.append("Creating payment for sale " + sale.toString());

        var feeRate = operatorRate.feeRate(creditCard.creditcardNumber());
        var fee = (feeRate * sale.total()) / 100;

        var paymentRequest = new PaymentRequest(sale.total(), creditCard.creditcardNumber(), fee);

        if (sale.total() >= 1000) {
            emailSender.send(paymentRequest);
        }

        return paymentRequest;
    }
}
