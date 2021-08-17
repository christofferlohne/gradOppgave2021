package no.demo.Stub;

public class OperatorRateStub implements OperatorRate {
    private int rate;

    public OperatorRateStub(int rate){

        this.rate = rate;
    }
    @Override
    public int feeRate(String operator) {
        return rate;
    }
}
