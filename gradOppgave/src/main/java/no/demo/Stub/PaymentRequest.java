package no.demo.Stub;

import java.util.Objects;

class PaymentRequest {

    private Integer saleTotal;
    private String creditcardNumber;
    private Integer fee;

    public PaymentRequest(Integer saleTotal, String creditcardNumber, Integer fee) {
        this.saleTotal = saleTotal;
        this.creditcardNumber = creditcardNumber;
        this.fee = fee;
    }

    public Integer getSaleTotal() {
        return saleTotal;
    }

    public String getCreditcardNumber() {
        return creditcardNumber;
    }

    public Integer getFee() {
        return fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentRequest that = (PaymentRequest) o;
        return Objects.equals(saleTotal, that.saleTotal) && Objects.equals(creditcardNumber, that.creditcardNumber) && Objects.equals(fee, that.fee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saleTotal, creditcardNumber, fee);
    }
}
