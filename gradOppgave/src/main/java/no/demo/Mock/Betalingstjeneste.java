package no.demo.Mock;


import no.demo.Bankkort;
import no.demo.Logger;
import no.demo.Salg;
import no.demo.Stub.OperatorRate;

public class Betalingstjeneste {

    private Logger logger;
    private OperatorRate operatorRate;
    private final EmailSender emailSender;

    public Betalingstjeneste(Logger logger, OperatorRate operatorRate, EmailSender emailSender) {
        this.logger = logger;
        this.operatorRate = operatorRate;
        this.emailSender = emailSender;
    }

    public Betalingsforespørsel lagBetalingsforespørsel(Salg salg, Bankkort bank) {
        logger.append("Lager betaling for salg " + salg.toString());

        var feeRate = operatorRate.feeRate(bank.kortnummer());
        var fee = (feeRate * salg.total()) / 100; // 10% fee

        var betalingsforespørsel = new Betalingsforespørsel(salg.total(), bank.kortnummer(), fee);

        if (salg.total() >= 1000) {
            emailSender.send(betalingsforespørsel);
        }

        return betalingsforespørsel;
    }
}
