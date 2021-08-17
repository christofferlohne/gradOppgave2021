package no.demo.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class PaymentEmailSenderMock implements PaymentEmailSender {

    private List<PaymentRequest> paymentRequestSent = new ArrayList<>();
    private List<PaymentRequest> expectedPaymentRequest = new ArrayList<>();

    @Override
    public void send(PaymentRequest paymentRequest) {
        paymentRequestSent.add(paymentRequest);
    }

    public void expect(PaymentRequest paymentRequest) {
        expectedPaymentRequest.add(paymentRequest);
    }

    public void verify() {
        assertEquals(paymentRequestSent, expectedPaymentRequest);
    }
}
