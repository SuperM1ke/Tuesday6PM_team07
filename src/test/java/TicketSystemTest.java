package fit5171.monash.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TicketSystemTest {

    private TicketSystem ticketSystem;

    private Airplane validAirplane1;
    private Airplane validAirplane2;
    private Timestamp timestampFrom1;
    private Timestamp timestampTo1;
    private Timestamp timestampFrom2;
    private Timestamp timestampTo2;
    private Flight flight1;
    private Flight flight2;
    private Passenger validPassenger;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final InputStream originalInput = System.in;
    private final PrintStream originalOutput = System.out;

    @BeforeEach
    public void setUp() {
        ticketSystem = new TicketSystem();

        validAirplane1 = new Airplane(1, "Boeing 737",
                20, 150, 6);
        validAirplane2 = new Airplane(2, "Boeing 737",
                30, 180, 10);
        LocalDateTime dateFrom1 = LocalDateTime.of(2025, 3, 22, 11, 2, 0, 0);
        LocalDateTime dateTo1 = LocalDateTime.of(2025, 3, 22, 14, 1, 0, 0);
        timestampFrom1 = Timestamp.valueOf(dateFrom1);
        timestampTo1 = Timestamp.valueOf(dateTo1);
        LocalDateTime dateFrom2 = LocalDateTime.of(2025, 3, 23, 11, 22, 0, 0);
        LocalDateTime dateTo2 = LocalDateTime.of(2025, 3, 23, 15, 1, 0, 0);
        timestampFrom2 = Timestamp.valueOf(dateFrom2);
        timestampTo2 = Timestamp.valueOf(dateTo2);
        validPassenger = new Passenger("Jack", "Green", 23, "Man",
                "test@example.com", "1234567890", "AS213412",
                "1234123412341234", 231);
        flight1 = new Flight(1, "Melbourne", "Sydney", "ASD123",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);
        flight2 = new Flight(2, "Sydney", "Perth", "QWE123",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane2);
        FlightCollection.flights = new ArrayList<>();

        ArrayList<Flight> validFlights = new ArrayList<>();
        validFlights.add(flight1);
        validFlights.add(flight2);
        FlightCollection.addFlights(validFlights);
    }

    @Test
    @DisplayName("Test chooseAndBuyTicket with valid city names")
    public void testChooseAndBuyTicketWithValidCityNames() throws Exception {
        assertDoesNotThrow(() -> {
            ticketSystem.chooseAndBuyTicket("Melbourne", "Sydney");
        });
        assertDoesNotThrow(() -> {
            ticketSystem.chooseAndBuyTicket("Los Angeles", "Sydney");
        });
    }

    @Test
    @DisplayName("Test chooseAndBuyTicket with invalid city names")
    public void testChooseAndBuyTicketWithInvalidCityNames() throws Exception {
        // One city name is empty
        assertThrows(IllegalArgumentException.class, () -> {
            ticketSystem.chooseAndBuyTicket("   ", "Perth");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ticketSystem.chooseAndBuyTicket("Melbourne", "");
        });
        // One city name is null
        assertThrows(IllegalArgumentException.class, () -> {
            ticketSystem.chooseAndBuyTicket(null, "Perth");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ticketSystem.chooseAndBuyTicket("Melbourne", null);
        });
        // One invalid city name
        assertThrows(IllegalArgumentException.class, () -> {
            ticketSystem.chooseAndBuyTicket("Perth", "!!!");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ticketSystem.chooseAndBuyTicket("123", "Sydney");
        });
        // City name is longer than 20 characters
        assertThrows(IllegalArgumentException.class, () -> {
            ticketSystem.chooseAndBuyTicket("Darwin", "PerthPerthPerthPerthPerth");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ticketSystem.chooseAndBuyTicket("PerthPerthPerthPerthPerth", "Sydney");
        });
    }

    @Test
    @DisplayName("Test buyDirectTicket with sold ticket")
    public void testBuyDirectTicketWithSoldTicket() throws Exception {
        ArrayList<Ticket> tickets = new ArrayList<>();

        Ticket validTicket1 = new Ticket(1, 100, flight1, false, false, null);
        Ticket validTicket2 = new Ticket(2, 123, flight1, true, false, null);
        Ticket validTicketWithPassenger = new Ticket(3, 321, flight1, false, true, validPassenger);
        tickets.add(validTicket1);
        tickets.add(validTicket2);
        tickets.add(validTicketWithPassenger);
        TicketCollection.addTickets(tickets);

        try{
            System.setOut(new PrintStream(outputStream));
            ticketSystem.buyDirectTicket(3);
            String output = outputStream.toString();
            assertTrue(output.contains("This ticket have already been sold."));
        } finally {
            System.setOut(originalOutput);
        }
    }

    @Test
    @DisplayName("Test buyConnectingTickets with sold ticket")
    public void testBuyConnectingTicketsWithSoldTicket() throws Exception {
        ArrayList<Ticket> tickets = new ArrayList<>();

        Ticket validTicket1 = new Ticket(1, 100, flight1, false, false, null);
        Ticket validTicket2 = new Ticket(2, 123, flight2, true, false, null);
        Ticket validTicketWithPassenger1 = new Ticket(3, 321, flight1, false, true, validPassenger);
        Ticket validTicketWithPassenger2 = new Ticket(4, 321, flight2, false, true, validPassenger);
        tickets.add(validTicket1);
        tickets.add(validTicket2);
        tickets.add(validTicketWithPassenger1);
        tickets.add(validTicketWithPassenger2);
        TicketCollection.addTickets(tickets);

        try{
            System.setOut(new PrintStream(outputStream));
            ticketSystem.buyConnectingTickets(1,4);
            String output1 = outputStream.toString();
            assertTrue(output1.contains("The second ticket have already been sold."));

            outputStream.reset();
            ticketSystem.buyConnectingTickets(3,2);
            String output2 = outputStream.toString();
            assertTrue(output2.contains("The first ticket have already been sold."));
        } finally {
            System.setOut(originalOutput);
        }
    }

}
