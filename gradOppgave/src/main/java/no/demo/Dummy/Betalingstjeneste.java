package no.demo.Dummy;


import no.demo.Bankkort;
import no.demo.Logger;
import no.demo.Salg;

public class Betalingstjeneste {

    private Logger logger;

    public Betalingstjeneste(Logger logger) {
        this.logger = logger;
    }

    /** Betalingsforespørsel lages og sendes ut til bruker slik at bruker kan gjennomføre betalingen gjennom sin
     *  foretrukket betalingsserver **/
    public Betalingsforespørsel lagBetalingsforespørsel(Salg salg, Bankkort bankkort) {
        logger.append("Lager betaling for salg " + salg.toString());

        return new Betalingsforespørsel(salg.total(), bankkort.kortnummer());
    }
}
