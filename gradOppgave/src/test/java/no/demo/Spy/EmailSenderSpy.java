package no.demo.Spy;

import java.util.ArrayList;
import java.util.List;

import no.demo.Mock.Betalingsforespørsel;
import no.demo.Mock.EmailSender;

public class EmailSenderSpy implements EmailSender {

    private List<Betalingsforespørsel> betalingsforespørsler = new ArrayList<>();

    @Override
    public void send(Betalingsforespørsel betalingsforespørsel) {
        betalingsforespørsler.add(betalingsforespørsel);
    }

    public int antallGangerKalt() {
        return betalingsforespørsler.size();
    }

    public boolean calledWith(Betalingsforespørsel betalingsforespørsel) {
        return betalingsforespørsler.contains(betalingsforespørsel);
    }
}
