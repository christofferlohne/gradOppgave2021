package no.demo;

public class CreditCard {

    private Customer costumer;
    private String creditcardNumber;

    public CreditCard(Customer costumer, String creditcardNumber) {
        this.costumer = costumer;
        this.creditcardNumber = creditcardNumber;
    }

    public Customer getCostumer() {
        return costumer;
    }

    public String creditcardNumber() {
        return creditcardNumber;
    }
}
