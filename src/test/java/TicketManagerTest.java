package fit5171.monash.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicketManagerTest {

    private TicketManager ticketManager;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        ticketManager = new TicketManager();
        // Initialize collections to avoid NullPointerException
        FlightCollection.flights = new ArrayList<>();
        TicketCollection.tickets = new ArrayList<>();
        // Redirect System.out to capture console output
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Test showTicket method when ticket information is null")
    public void testShowTicketWithNullTicketInfo() {
        // Call the method - the ticket is null by default in the newly created TicketManager
        ticketManager.showTicket();

        // Verify the output contains the expected message
        String output = outputStream.toString();
        assertTrue(output.contains("No ticket information available."),
                "Expected output to contain 'No ticket information available.' but was: " + output);
    }

    @Test
    @DisplayName("Test chooseAndBuyTicket when no flights are available")
    public void testChooseAndBuyTicketWithNoFlightsAvailable() throws Exception {
        // Setup - ensure FlightCollection.flights is empty
        FlightCollection.flights = new ArrayList<>();

        // Prepare simulated user input (for Scanner)
        String simulatedInput = "0\n"; // User inputs "0" when prompted
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Execute - attempt to find and buy tickets between two cities
        ticketManager.chooseAndBuyTicket("Melbourne", "Sydney");

        // Verify - check that appropriate message is shown
        String output = outputStream.toString();
        assertTrue(output.contains("No direct flights found"),
                "Expected output to contain 'No direct flights found' but was: " + output);
    }


    @org.junit.jupiter.api.AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(System.in); // Restore original System.in
    }
}