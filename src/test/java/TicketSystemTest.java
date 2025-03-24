package fit5171.monash.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicketSystemTest {

    private TicketSystem ticketSystem;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        ticketSystem = new TicketSystem();
        FlightCollection.flights = new ArrayList<>();
        TicketCollection.tickets = new ArrayList<>();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Test showTicket method when ticket information is null")
    public void testShowTicketWithNullTicketInfo() {

        ticketSystem.showTicket();

        String output = outputStream.toString();
        assertTrue(output.contains("No ticket information available."));
    }

    @Test
    @DisplayName("Test chooseAndBuyTicket when no flights are available")
    public void testChooseAndBuyTicketWithNoFlightsAvailable() throws Exception {

        FlightCollection.flights = new ArrayList<>();
        String simulatedInput = "0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        ticketSystem.chooseAndBuyTicket("Melbourne", "Sydney");

        String output = outputStream.toString();
        assertTrue(output.contains("No direct flights found"));
    }

    @Test
    @DisplayName("Test buyDirectTicket with non-existent ticket ID")
    public void testBuyDirectTicketWithNonExistentTicket() throws Exception {

        TicketCollection.tickets = new ArrayList<>();

        ticketSystem.buyDirectTicket(999);
        String output = outputStream.toString();
        assertTrue(output.contains("This ticket does not exist"));
    }
}

