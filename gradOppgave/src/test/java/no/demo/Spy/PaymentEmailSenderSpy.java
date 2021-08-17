package no.demo.Spy;

import java.util.ArrayList;
import java.util.List;

import no.demo.Mock.PaymentEmailSender;
import no.demo.Mock.PaymentRequest;

public class PaymentEmailSenderSpy implements PaymentEmailSender {

    private List<PaymentRequest> paymentRequests = new ArrayList<>();

    @Override
    public void send(PaymentRequest paymentRequest) {
        paymentRequests.add(paymentRequest);
    }

    public int timesCalled() {
        return paymentRequests.size();
    }

    public boolean calledWith(PaymentRequest paymentRequest) {
        return paymentRequests.contains(paymentRequest);
    }
}
