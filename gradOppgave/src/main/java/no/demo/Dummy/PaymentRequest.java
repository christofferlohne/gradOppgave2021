package no.demo.Dummy;

import java.util.Objects;

class PaymentRequest {

    private Integer saleTotal;
    private String creditcardNumber;

    public PaymentRequest(Integer saleTotal, String creditcardNumber) {
        this.saleTotal = saleTotal;
        this.creditcardNumber = creditcardNumber;
    }

    public Integer getSaleTotal() {
        return saleTotal;
    }

    public String getCreditcardNumber() {
        return creditcardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentRequest that = (PaymentRequest) o;
        return Objects.equals(saleTotal, that.saleTotal) && Objects.equals(creditcardNumber, that.creditcardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saleTotal, creditcardNumber);
    }
}
