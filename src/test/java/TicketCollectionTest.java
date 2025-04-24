package fit5171.monash.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import java.util.ArrayList;

public class TicketCollectionTest {

    private Airplane validAirplane;
    private Airplane invalidAirplane1;
    private Airplane invalidAirplane2;
    private Flight validFlight1;
    private Flight validFlight2;
    private Flight invalidFlight1;
    private Flight invalidFlight2;
    private Passenger validPassenger;
    private Passenger invalidPassenger;


    @BeforeEach
    public void setUp() {

        validAirplane = new Airplane(1, "Boeing 737",
                20, 150, 6);
        invalidAirplane1 = new Airplane(-1, "Boeing 737",
                -20, -150, -6);
        invalidAirplane2 = new Airplane(1, null,
                20, 150, 6);

        LocalDateTime dateFrom = LocalDateTime.of(2025, 3, 22, 11, 2, 0, 0);
        LocalDateTime dateTo = LocalDateTime.of(2025, 3, 22, 12, 1, 0, 0);
        Timestamp timestampFrom = Timestamp.valueOf(dateFrom);
        Timestamp timestampTo = Timestamp.valueOf(dateTo);

        validFlight1 = new Flight(1, "Melbourne", "Sydney", "ASD123",
                "Jetstar Airlines", timestampFrom, timestampTo, validAirplane);
        validFlight2 = new Flight(2, "Melbourne", "Sydney", "QWE123",
                "Jetstar Airlines", timestampFrom, timestampTo, validAirplane);
        invalidFlight1 = new Flight(12, null, null, "",
                "", null, null, invalidAirplane2);
        invalidFlight2 = new Flight(-1, "Melbourne", "Sydney", "ASD123",
                "Jetstar", timestampFrom, timestampTo, invalidAirplane1);

        validPassenger = new Passenger("Jack", "Green", 23, "Man",
                "test@example.com", "1234567890", "AS213412",
                "1234123412341234", 231);
        invalidPassenger = new Passenger("", "", -23, "",
                "", "", "", "", -231);

        TicketCollection.tickets = new ArrayList<>();
    }

    @Test
    @DisplayName("Test adding valid tickets to collection")
    public void testAddValidTickets() {
        ArrayList<Ticket> tickets = new ArrayList<>();

        Ticket validTicket1 = new Ticket(1, 100, validFlight1, false, false, null);
        Ticket validTicket2 = new Ticket(2, 123, validFlight1, true, false, null);
        Ticket validTicketWithPassenger = new Ticket(3, 321, validFlight1, false, true, validPassenger);

        tickets.add(validTicket1);
        tickets.add(validTicket2);
        tickets.add(validTicketWithPassenger);

        TicketCollection.addTickets(tickets);
        assertEquals(3, TicketCollection.getTickets().size());
        assertEquals(validTicket1, TicketCollection.getTicketInfoById(1));
        assertEquals(validTicket2, TicketCollection.getTicketInfoById(2));
        assertEquals(validTicketWithPassenger, TicketCollection.getTicketInfoById(3));
    }

    @Test
    @DisplayName("Test adding tickets with invalid IDs")
    public void testAddTicketsWithInvalidIDs() {
        ArrayList<Ticket> tickets = new ArrayList<>();

        Ticket invalidTicket1 = new Ticket(-1, 100, validFlight1, false, false, null);
        Ticket invalidTicket2 = new Ticket(0, 123, validFlight1, true, false, null);

        tickets.add(invalidTicket1);
        tickets.add(invalidTicket2);

        assertThrows(IllegalArgumentException.class, () -> {
            TicketCollection.addTickets(tickets);
        });
    }

    @Test
    @DisplayName("Test adding tickets with invalid prices")
    public void testAddTicketsWithInvalidprices() {
        ArrayList<Ticket> tickets = new ArrayList<>();

        Ticket invalidTicket1 = new Ticket(1, -100, validFlight1, false, false, null);
        Ticket invalidTicket2 = new Ticket(2, -1, validFlight1, true, false, null);

        tickets.add(invalidTicket1);
        tickets.add(invalidTicket2);

        assertThrows(IllegalArgumentException.class, () -> {
            TicketCollection.addTickets(tickets);
        });
    }

    @Test
    @DisplayName("Test adding tickets with invalid flights")
    public void testAddTicketsWithInvalidflights() {
        ArrayList<Ticket> tickets = new ArrayList<>();

        Ticket invalidTicket1 = new Ticket(1, 100, invalidFlight1, false, false, null);
        Ticket invalidTicket2 = new Ticket(1, 100, invalidFlight2, false, false, null);
        Ticket invalidTicket3 = new Ticket(1, 100, null, false, false, null);

        tickets.add(invalidTicket1);
        tickets.add(invalidTicket2);
        tickets.add(invalidTicket3);

        assertThrows(IllegalArgumentException.class, () -> {
            TicketCollection.addTickets(tickets);
        });
    }

    @Test
    @DisplayName("Test adding tickets with inconsistent status and passenger")
    public void testAddTicketsWithInconsistentStatusAndPassenger() {
        ArrayList<Ticket> tickets = new ArrayList<>();

        Ticket inconsistentTicket1 = new Ticket(1, 100, validFlight1, false, true, null);
        Ticket inconsistentTicket2 = new Ticket(1, 100, validFlight1, false, false, invalidPassenger);

        tickets.add(inconsistentTicket1);
        tickets.add(inconsistentTicket2);

        assertThrows(IllegalArgumentException.class, () -> {
            TicketCollection.addTickets(tickets);
        });
    }

    @Test
    @DisplayName("Test getTicketInfoById with valid ID")
    public void testGetTicketInfoByIdWithValidId() {
        ArrayList<Ticket> tickets = new ArrayList<>();

        Ticket ticket1 = new Ticket(1, 100, validFlight1, false, false, null);
        Ticket ticket2 = new Ticket(2, 200, validFlight1, true, false, null);

        tickets.add(ticket1);
        tickets.add(ticket2);
        TicketCollection.addTickets(tickets);

        Ticket result = TicketCollection.getTicketInfoById(2);

        assertNotNull(result);
        assertEquals(2, result.getTicket_id());
        assertEquals(200, result.getPrice());
        assertEquals(validFlight1, result.getFlight());
        assertTrue(result.getClassVip());
        assertFalse(result.ticketStatus());

    }

    @Test
    @DisplayName("Test getTicketInfoById with non-existent ID")
    public void testGetTicketInfoByIdWithNonExistentId() {
        ArrayList<Ticket> tickets = new ArrayList<>();

        Ticket ticket1 = new Ticket(1, 100, validFlight1, false, false, null);
        Ticket ticket2 = new Ticket(2, 200, validFlight1, true, false, null);

        tickets.add(ticket1);
        tickets.add(ticket2);

        TicketCollection.addTickets(tickets);

        assertNull(TicketCollection.getTicketInfoById(999));
        assertNull(TicketCollection.getTicketInfoById(-1));
        assertNull(TicketCollection.getTicketInfoById(0));
    }

    @Test
    @DisplayName("Test getTicketInfoById with empty collection")
    public void testGetTicketInfoByIdWithEmptyCollection() {
        ArrayList<Ticket> tickets = new ArrayList<>();

        TicketCollection.addTickets(tickets);

        assertNull(TicketCollection.getTicketInfoById(2));
    }

    @Test
    @DisplayName("Test getTicketsForFlight with valid flight ID and available tickets")
    public void testGetTicketsForFlightWithAvailableTickets() {
        ArrayList<Ticket> tickets = new ArrayList<>();

        Ticket ticket1 = new Ticket(1, 100, validFlight1, true, false, null);
        Ticket ticket2 = new Ticket(2, 200, validFlight1, true, false, null);
        Ticket ticket3 = new Ticket(3, 200, validFlight1, true, true, validPassenger);

        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);
        TicketCollection.addTickets(tickets);

        ArrayList<Ticket> result = TicketCollection.getTicketsForFlight(1);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(ticket1));
        assertTrue(result.contains(ticket2));
    }

    @Test
    @DisplayName("Test getTicketsForFlight with valid flight ID and unavailable tickets")
    public void testGetTicketsForFlightWithUnavailableTickets() {
        ArrayList<Ticket> tickets = new ArrayList<>();

        Ticket ticket1 = new Ticket(1, 100, validFlight1, false, true, validPassenger);
        Ticket ticket2 = new Ticket(2, 200, validFlight1, true, true, validPassenger);
        Ticket ticket3 = new Ticket(3, 300, validFlight2, true, false, null);

        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);
        TicketCollection.addTickets(tickets);

        assertNull(TicketCollection.getTicketsForFlight(1));
    }

    @Test
    @DisplayName("Test getTicketsForFlight with invalid flight ID")
    public void testGetTicketsForFlightWithInvalidFlightID() {
        ArrayList<Ticket> tickets = new ArrayList<>();

        Ticket ticket1 = new Ticket(1, 100, validFlight1, false, true, validPassenger);
        Ticket ticket2 = new Ticket(2, 200, validFlight1, true, true, validPassenger);
        Ticket ticket3 = new Ticket(3, 300, validFlight2, true, false, null);

        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);
        TicketCollection.addTickets(tickets);

        assertNull(TicketCollection.getTicketsForFlight(-1));
    }

    @Test
    @DisplayName("Test getTicketsForFlight with empty tickets collection")
    public void testGetTicketsForFlightWithEmptyCollection() {
        ArrayList<Ticket> tickets = new ArrayList<>();

        TicketCollection.addTickets(tickets);

        assertNull(TicketCollection.getTicketsForFlight(1));
    }



}
