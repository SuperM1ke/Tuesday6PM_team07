package fit5171.monash.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicketManagerTest {

    private TicketManager ticketManager;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        ticketManager = new TicketManager();
        FlightCollection.flights = new ArrayList<>();
        TicketCollection.tickets = new ArrayList<>();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Test showTicket method when ticket information is null")
    public void testShowTicketWithNullTicketInfo() {

        ticketManager.showTicket();

        String output = outputStream.toString();
        assertTrue(output.contains("No ticket information available."),
                "Expected output to contain 'No ticket information available.' but was: " + output);
    }

    @Test
    @DisplayName("Test chooseAndBuyTicket when no flights are available")
    public void testChooseAndBuyTicketWithNoFlightsAvailable() throws Exception {

        FlightCollection.flights = new ArrayList<>();
        String simulatedInput = "0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        ticketManager.chooseAndBuyTicket("Melbourne", "Sydney");

        String output = outputStream.toString();
        assertTrue(output.contains("No direct flights found"),
                "Expected output to contain 'No direct flights found' but was: " + output);
    }
}

