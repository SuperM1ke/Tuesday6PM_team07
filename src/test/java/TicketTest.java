package fit5171.monash.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class TicketTest {

    private Airplane validAirplane;
    private Flight validFlight1;
    private Flight validFlight2;


    @BeforeEach
    public void setUp() {
        validAirplane = new Airplane(1, "Boeing 737",
                20, 150, 6);

        LocalDateTime dateFrom = LocalDateTime.of(2025, 3, 22, 11, 2, 0, 0);
        LocalDateTime dateTo = LocalDateTime.of(2025, 3, 22, 12, 1, 0, 0);
        Timestamp timestampFrom = Timestamp.valueOf(dateFrom);
        Timestamp timestampTo = Timestamp.valueOf(dateTo);

        validFlight1 = new Flight(1, "Melbourne", "Sydney", "ASD123",
                "Jetstar Airlines", timestampFrom, timestampTo, validAirplane);
        validFlight2 = new Flight(2, "Melbourne", "Sydney", "QWE123",
                "Jetstar Airlines", timestampFrom, timestampTo, validAirplane);
    }

    @Test
    @DisplayName("Test tick status with pre-created tickets")
    public void testTicketStatus() {
        Passenger passenger = mock(Passenger.class);
        Ticket ticket = new Ticket(1, 123, validFlight1, true, false, null);
        Ticket ticket2 = new Ticket(2, 111, validFlight2, true, true, passenger);
        assertFalse(ticket.ticketStatus());
        assertTrue(ticket2.ticketStatus());
    }

    @Test
    @DisplayName("Test saleByAge method with age less than 15")             // 14, 8
    public void testSaleByAgeWithChildren(){
        Passenger passenger1 = mock(Passenger.class);
        when(passenger1.getAge()).thenReturn(14);
        Passenger passenger2 = mock(Passenger.class);
        when(passenger2.getAge()).thenReturn(1);

        Ticket ticket1 = new Ticket(1, 122, validFlight1, true, false, null);
        Ticket ticket2 = new Ticket(2, 222, validFlight2, true, false, null);

        ticket1.saleByAge(passenger1.getAge());
        assertEquals(61, ticket1.getPrice());
        ticket2.saleByAge(passenger2.getAge());
        assertEquals(111, ticket2.getPrice());
    }

    @Test
    @DisplayName("Test saleByAge method with age more than 59")             // 60, 80
    public void testSaleByAgeWithElderPeople(){
        Passenger passenger1 = mock(Passenger.class);
        when(passenger1.getAge()).thenReturn(60);
        Passenger passenger2 = mock(Passenger.class);
        when(passenger2.getAge()).thenReturn(80);

        Ticket ticket1 = new Ticket(1, 234, validFlight1, true, false, null);
        Ticket ticket2 = new Ticket(2, 246, validFlight2, true, false, null);

        ticket1.saleByAge(passenger1.getAge());
        assertEquals(0, ticket1.getPrice());
        ticket2.saleByAge(passenger2.getAge());
        assertEquals(0, ticket2.getPrice());
    }

    @Test
    @DisplayName("Test saleByAge method with age between 15 and 59")        // 15, 30, 59,
    public void testSaleByAgeWithcAdult(){
        Passenger passenger1 = mock(Passenger.class);
        when(passenger1.getAge()).thenReturn(15);
        Passenger passenger2 = mock(Passenger.class);
        when(passenger2.getAge()).thenReturn(30);
        Passenger passenger3 = mock(Passenger.class);
        when(passenger3.getAge()).thenReturn(59);

        Ticket ticket1 = new Ticket(1, 122, validFlight1, true, false, null);
        Ticket ticket2 = new Ticket(2, 222, validFlight2, true, false, null);

        ticket1.saleByAge(passenger1.getAge());
        assertEquals(122, ticket1.getPrice());
        ticket1.saleByAge(passenger2.getAge());
        assertEquals(122, ticket1.getPrice());
        ticket2.saleByAge(passenger3.getAge());
        assertEquals(222, ticket2.getPrice());

    }

    @Test
    @DisplayName("Test serviceTax method with age less than 15")             // 14, 11
    public void testServiceTaxWithChildren(){
        Passenger passenger1 = mock(Passenger.class);
        when(passenger1.getAge()).thenReturn(14);
        Passenger passenger2 = mock(Passenger.class);
        when(passenger2.getAge()).thenReturn(11);

        Ticket ticket1 = new Ticket(1, 200, validFlight1, true, false, passenger1);
        Ticket ticket2 = new Ticket(2, 400, validFlight2, true, false, passenger2);

        ticket1.setPrice(ticket1.getPrice());
        assertEquals(112, ticket1.getPrice());
        ticket2.setPrice(ticket2.getPrice());
        assertEquals(224, ticket2.getPrice());
    }

    @Test
    @DisplayName("Test serviceTax method with age more than 59")             // 60, 80
    public void testServiceTaxWithElderPeople(){
        Passenger passenger1 = mock(Passenger.class);
        when(passenger1.getAge()).thenReturn(60);
        Passenger passenger2 = mock(Passenger.class);
        when(passenger2.getAge()).thenReturn(80);

        Ticket ticket1 = new Ticket(1, 234, validFlight1, true, false, passenger1);
        Ticket ticket2 = new Ticket(2, 246, validFlight2, true, false, passenger2);

        ticket1.setPrice(ticket1.getPrice());
        assertEquals(0, ticket1.getPrice());
        ticket2.setPrice(ticket2.getPrice());
        assertEquals(0, ticket2.getPrice());
    }

    @Test
    @DisplayName("Test serviceTax method with age between 15 and 59")        // 15, 30, 59,
    public void testServiceTaxWithcAdult(){
        Passenger passenger1 = mock(Passenger.class);
        when(passenger1.getAge()).thenReturn(15);
        Passenger passenger2 = mock(Passenger.class);
        when(passenger2.getAge()).thenReturn(30);
        Passenger passenger3 = mock(Passenger.class);
        when(passenger3.getAge()).thenReturn(59);

        Ticket ticket1 = new Ticket(1, 200, validFlight1, true, false, passenger1);
        Ticket ticket2 = new Ticket(2, 400, validFlight2, true, false, passenger2);
        Ticket ticket3 = new Ticket(3, 100, validFlight2, true, false, passenger3);

        ticket1.setPrice(ticket1.getPrice());
        assertEquals(224, ticket1.getPrice());
        ticket2.setPrice(ticket2.getPrice());
        assertEquals(448, ticket2.getPrice());
        ticket3.setPrice(ticket3.getPrice());
        assertEquals(112, ticket3.getPrice());
    }

    @Test
    @DisplayName("Test ticket can receive valid flight and passenger information")
    public void testTicketReceivesValidInformation() {
        Passenger passenger = mock(Passenger.class);

        Ticket ticket1 = new Ticket(1, 200, validFlight1, true, false, null);
        ticket1.setFlight(validFlight2);
        ticket1.setPassenger(passenger);

        assertSame(validFlight2, ticket1.getFlight());
        assertSame(passenger, ticket1.getPassenger());

        Ticket ticket2 = new Ticket(2, 300, validFlight1, false, true, passenger);

        assertSame(validFlight1, ticket2.getFlight());
        assertSame(passenger, ticket2.getPassenger());
    }
}
