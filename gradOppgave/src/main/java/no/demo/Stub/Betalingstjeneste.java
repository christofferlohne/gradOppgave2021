package no.demo.Stub;


import no.demo.Bankkort;
import no.demo.Logger;
import no.demo.Salg;

public class Betalingstjeneste {

    private Logger logger;
    private OperatorRate operatorRate;


    public Betalingstjeneste(Logger logger, OperatorRate operatorRate) {
        this.logger = logger;
        this.operatorRate = operatorRate;
    }

    public Betalingsforespørsel lagBetalingsforespørsel(Salg salg, Bankkort bankkort) {
        logger.append("Creating payment for salg " + salg.toString());

        int feeRate = operatorRate.feeRate(bankkort.kortnummer());
        int fee = (feeRate * salg.total()) / 100; // 10% fee

        return new Betalingsforespørsel(salg.total(), bankkort.kortnummer(), fee);
    }
}
