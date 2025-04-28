package fit5171.monash.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlightCollectionTest {

    @Mock
    private Airplane mockAirplane;
    @Mock
    private Flight mockValidFlight;
    @Mock
    private Flight mockInvalidFlightID;
    @Mock
    private Flight mockInvalidDepartTo;
    @Mock
    private Flight mockInvalidDepartFrom;
    @Mock
    private Flight mockInvalidCode;
    @Mock
    private Flight mockInvalidCompany;
    @Mock
    private Flight mockInvalidDateFrom;
    @Mock
    private Flight mockInvalidDateTo;
    @Mock
    private Flight mockInvalidDateOrder;
    @Mock
    private Flight mockInvalidAirplane;

    private Timestamp validTimestampFrom;
    private Timestamp validTimestampTo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        FlightCollection.flights = new ArrayList<>();

        LocalDateTime dateFrom = LocalDateTime.of(2025, 3, 22, 11, 0);
        LocalDateTime dateTo = LocalDateTime.of(2025, 3, 22, 14, 0);
        validTimestampFrom = Timestamp.valueOf(dateFrom);
        validTimestampTo = Timestamp.valueOf(dateTo);

        when(mockAirplane.getAirplaneID()).thenReturn(1);
        when(mockAirplane.getAirplaneModel()).thenReturn("Model");
        when(mockAirplane.getBusinessSitsNumber()).thenReturn(1);
        when(mockAirplane.getEconomySitsNumber()).thenReturn(1);
        when(mockAirplane.getCrewSitsNumber()).thenReturn(1);

        when(mockValidFlight.getFlightID()).thenReturn(1);
        when(mockValidFlight.getDepartTo()).thenReturn("Sydney");
        when(mockValidFlight.getDepartFrom()).thenReturn("Melbourne");
        when(mockValidFlight.getCode()).thenReturn("ABC123");
        when(mockValidFlight.getCompany()).thenReturn("Qantas");
        when(mockValidFlight.getDateFrom()).thenReturn(validTimestampFrom);
        when(mockValidFlight.getDateTo()).thenReturn(validTimestampTo);
        when(mockValidFlight.getAirplane()).thenReturn(mockAirplane);

        when(mockInvalidFlightID.getFlightID()).thenReturn(-1);

        when(mockInvalidDepartTo.getFlightID()).thenReturn(2);
        when(mockInvalidDepartTo.getDepartTo()).thenReturn(null);

        when(mockInvalidDepartFrom.getFlightID()).thenReturn(3);
        when(mockInvalidDepartFrom.getDepartFrom()).thenReturn("");

        when(mockInvalidCode.getFlightID()).thenReturn(4);
        when(mockInvalidCode.getDepartTo()).thenReturn("Sydney");
        when(mockInvalidCode.getDepartFrom()).thenReturn("Melbourne");
        when(mockInvalidCode.getCode()).thenReturn("123");

        when(mockInvalidCompany.getFlightID()).thenReturn(5);
        when(mockInvalidCompany.getDepartTo()).thenReturn("Sydney");
        when(mockInvalidCompany.getDepartFrom()).thenReturn("Melbourne");
        when(mockInvalidCompany.getCode()).thenReturn("ABC123");
        when(mockInvalidCompany.getCompany()).thenReturn("");

        when(mockInvalidDateFrom.getFlightID()).thenReturn(6);
        when(mockInvalidDateFrom.getDepartTo()).thenReturn("Sydney");
        when(mockInvalidDateFrom.getDepartFrom()).thenReturn("Melbourne");
        when(mockInvalidDateFrom.getCode()).thenReturn("ABC123");
        when(mockInvalidDateFrom.getCompany()).thenReturn("Qantas");
        when(mockInvalidDateFrom.getDateFrom()).thenReturn(null);

        when(mockInvalidDateTo.getFlightID()).thenReturn(7);
        when(mockInvalidDateTo.getDepartTo()).thenReturn("Sydney");
        when(mockInvalidDateTo.getDepartFrom()).thenReturn("Melbourne");
        when(mockInvalidDateTo.getCode()).thenReturn("ABC123");
        when(mockInvalidDateTo.getCompany()).thenReturn("Qantas");
        when(mockInvalidDateTo.getDateFrom()).thenReturn(validTimestampFrom);
        when(mockInvalidDateTo.getDateTo()).thenReturn(null);

        when(mockInvalidDateOrder.getFlightID()).thenReturn(8);
        when(mockInvalidDateOrder.getDepartTo()).thenReturn("Sydney");
        when(mockInvalidDateOrder.getDepartFrom()).thenReturn("Melbourne");
        when(mockInvalidDateOrder.getCode()).thenReturn("ABC123");
        when(mockInvalidDateOrder.getCompany()).thenReturn("Qantas");
        when(mockInvalidDateOrder.getDateFrom()).thenReturn(validTimestampTo);
        when(mockInvalidDateOrder.getDateTo()).thenReturn(validTimestampFrom);

        when(mockInvalidAirplane.getFlightID()).thenReturn(9);
        when(mockInvalidAirplane.getDepartTo()).thenReturn("Sydney");
        when(mockInvalidAirplane.getDepartFrom()).thenReturn("Melbourne");
        when(mockInvalidAirplane.getCode()).thenReturn("ABC123");
        when(mockInvalidAirplane.getCompany()).thenReturn("Qantas");
        when(mockInvalidAirplane.getDateFrom()).thenReturn(validTimestampFrom);
        when(mockInvalidAirplane.getDateTo()).thenReturn(validTimestampTo);
        when(mockInvalidAirplane.getAirplane()).thenReturn(null);
    }

    @Test
    @DisplayName("Test addFlights with valid flight")
    public void testAddFlightsWithValidFlight() {
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(mockValidFlight);

        FlightCollection.addFlights(flights);

        assertEquals(1, FlightCollection.flights.size());
        assertEquals(1, FlightCollection.flights.get(0).getFlightID());
    }

    @Test
    @DisplayName("Test addFlights with invalid flight ID")
    public void testAddFlightsWithInvalidFlightID() {
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(mockInvalidFlightID);

        FlightCollection.addFlights(flights);

        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Test addFlights with invalid departure destination")
    public void testAddFlightsWithInvalidDepartTo() {
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(mockInvalidDepartTo);

        FlightCollection.addFlights(flights);

        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Test addFlights with invalid departure origin")
    public void testAddFlightsWithInvalidDepartFrom() {
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(mockInvalidDepartFrom);

        FlightCollection.addFlights(flights);

        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Test addFlights with invalid flight code")
    public void testAddFlightsWithInvalidCode() {
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(mockInvalidCode);

        FlightCollection.addFlights(flights);

        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Test addFlights with invalid company")
    public void testAddFlightsWithInvalidCompany() {
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(mockInvalidCompany);

        FlightCollection.addFlights(flights);

        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Test addFlights with invalid departure date")
    public void testAddFlightsWithInvalidDateFrom() {
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(mockInvalidDateFrom);

        FlightCollection.addFlights(flights);

        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Test addFlights with invalid arrival date")
    public void testAddFlightsWithInvalidDateTo() {
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(mockInvalidDateTo);

        FlightCollection.addFlights(flights);

        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Test addFlights with invalid date order")
    public void testAddFlightsWithInvalidDateOrder() {
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(mockInvalidDateOrder);

        FlightCollection.addFlights(flights);

        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Test addFlights with invalid airplane")
    public void testAddFlightsWithInvalidAirplane() {
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(mockInvalidAirplane);

        FlightCollection.addFlights(flights);

        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Test getFlightInfo with valid cities")
    public void testGetFlightInfoWithValidCities() {
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(mockValidFlight);
        FlightCollection.addFlights(flights);

        Flight result = FlightCollection.getFlightInfo("Melbourne", "Sydney");

        assertNotNull(result);
        assertEquals(1, result.getFlightID());
    }

    @Test
    @DisplayName("Test getFlightInfo with non-existent route")
    public void testGetFlightInfoWithNonExistentRoute() {
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(mockValidFlight);
        FlightCollection.addFlights(flights);

        Flight result = FlightCollection.getFlightInfo("Sydney", "Melbourne"); // 反向路线

        assertNull(result);
    }

    @Test
    @DisplayName("Test getFlightsToCity with valid city")
    public void testGetFlightsToCityWithValidCity() {
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(mockValidFlight);
        FlightCollection.addFlights(flights);

        ArrayList<Flight> results = FlightCollection.getFlightsToCity("Sydney");

        assertEquals(1, results.size());
        assertEquals(1, results.get(0).getFlightID());
    }

    @Test
    @DisplayName("Test getFlightsToCity with non-existent city")
    public void testGetFlightsToCityWithNonExistentCity() {
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(mockValidFlight);
        FlightCollection.addFlights(flights);

        ArrayList<Flight> results = FlightCollection.getFlightsToCity("Brisbane");

        assertEquals(0, results.size());
    }

    @Test
    @DisplayName("Test getFlightInfoById with valid ID")
    public void testGetFlightInfoByIdWithValidId() {
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(mockValidFlight);
        FlightCollection.addFlights(flights);

        Flight result = FlightCollection.getFlightInfoById(1);

        assertNotNull(result);
        assertEquals(1, result.getFlightID());
    }

    @Test
    @DisplayName("Test getFlightInfoById with non-existent ID")
    public void testGetFlightInfoByIdWithNonExistentId() {
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(mockValidFlight);
        FlightCollection.addFlights(flights);

        Flight result = FlightCollection.getFlightInfoById(999);

        assertNull(result);
    }
}