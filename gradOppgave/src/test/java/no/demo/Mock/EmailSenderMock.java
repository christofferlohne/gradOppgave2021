package no.demo.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class EmailSenderMock implements EmailSender {

    private List<Betalingsforespørsel> sendteBetalingsforespørsler = new ArrayList<>();
    private List<Betalingsforespørsel> forventetBetalingsforespørsler = new ArrayList<>();

    @Override
    public void send(Betalingsforespørsel sendteBetalingsforespørsler) {
        this.sendteBetalingsforespørsler.add(sendteBetalingsforespørsler);
    }

    public void expect(Betalingsforespørsel forventetBetalingsforespørsler) {
        this.forventetBetalingsforespørsler.add(forventetBetalingsforespørsler);
    }

    public void verify() {
        assertEquals(sendteBetalingsforespørsler, forventetBetalingsforespørsler);
    }
}
