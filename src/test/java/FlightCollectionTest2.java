package fit5171.monash.edu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FlightCollectionTest2 {

    private Airplane validAirplane1;
    private Airplane validAirplane2;
    private Airplane invalidAirplane1;
    private Airplane invalidAirplane2;
    private Airplane invalidAirplane3;
    private Timestamp timestampFrom1;
    private Timestamp timestampTo1;
    private Timestamp timestampFrom2;
    private Timestamp timestampTo2;

    @BeforeEach
    public void setUp() {

        validAirplane1 = new Airplane(1, "Boeing 737",
                20, 150, 6);
        validAirplane2 = new Airplane(2, "Boeing 737",
                30, 180, 10);
        invalidAirplane1 = new Airplane(-1, "Boeing 737",
                20, 150, 6);
        invalidAirplane2 = new Airplane(1, null,
                20, -150, 6);
        invalidAirplane3 = new Airplane(1, "   ",
                -20, 150, 6);

        LocalDateTime dateFrom1 = LocalDateTime.of(2025, 3, 22, 11, 2, 0, 0);
        LocalDateTime dateTo1 = LocalDateTime.of(2025, 3, 22, 14, 1, 0, 0);
        timestampFrom1 = Timestamp.valueOf(dateFrom1);
        timestampTo1 = Timestamp.valueOf(dateTo1);
        LocalDateTime dateFrom2 = LocalDateTime.of(2025, 3, 23, 11, 22, 0, 0);
        LocalDateTime dateTo2 = LocalDateTime.of(2025, 3, 23, 15, 1, 0, 0);
        timestampFrom2 = Timestamp.valueOf(dateFrom2);
        timestampTo2 = Timestamp.valueOf(dateTo2);

        FlightCollection.flights = new ArrayList<>();
    }

    @Test
    @DisplayName("Test addFlights method with valid flights")
    public void testAddFlightsWithVaildFlights() {
        ArrayList<Flight> flights = new ArrayList<>();

        Flight flight1 = new Flight(1, "Melbourne", "Sydney", "ASD123",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);
        Flight flight2 = new Flight(2, "Melbourne", "Sydney", "QWE123",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane2);

        flights.add(flight1);
        flights.add(flight2);
        FlightCollection.addFlights(flights);

        assertEquals(2, FlightCollection.flights.size());
        assertEquals(1, FlightCollection.flights.get(0).getFlightID());
        assertEquals(2, FlightCollection.flights.get(1).getFlightID());
    }

    @Test
    @DisplayName("Test addFlight with invalid flight ID")
    public void testAddFlightsWithInvalidflightID() {
        ArrayList<Flight> flights = new ArrayList<>();

        Flight flight1 = new Flight(-1, "Melbourne", "Sydney", "ASD123",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);
        Flight flight2 = new Flight(0, "Melbourne", "Sydney", "QWE234",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);
        flights.add(flight1);
        flights.add(flight2);
        FlightCollection.addFlights(flights);
        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Equivalence class test addFlight with null code or company")
    public void testAddFlightsWithNullCodeOrCompany() {
        ArrayList<Flight> flights = new ArrayList<>();
        Flight flight1 = new Flight(1, "Melbourne", "Sydney", null,
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);
        Flight flight2 = new Flight(10, "Melbourne", "Sydney", "QWE234",
                null, timestampFrom1, timestampTo1, validAirplane1);

        flights.add(flight1);
        flights.add(flight2);
        FlightCollection.addFlights(flights);
        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Equivalence class test addFlight with code or company empty")
    public void testAddFlightsWithEmptyCodeOrCompany() {
        ArrayList<Flight> flights = new ArrayList<>();
        Flight flight1 = new Flight(1, "Melbourne", "Sydney", "   ",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);
        Flight flight2 = new Flight(2, "Melbourne", "Sydney", "QWE234",
                " ", timestampFrom1, timestampTo1, validAirplane1);

        flights.add(flight1);
        flights.add(flight2);
        FlightCollection.addFlights(flights);
        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Equivalence class test addFlight with invalid code or company")
    public void testAddFlightsWithInvalidCodeOrCompany() {
        ArrayList<Flight> flights = new ArrayList<>();
        Flight flight1 = new Flight(1, "Melbourne", "Sydney", "132QWE",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);
        Flight flight2 = new Flight(20, "Melbourne", "Sydney", "QEW21!",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);
        Flight flight3 = new Flight(30, "Melbourne", "Sydney", "QEW 21",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);
        Flight flight4 = new Flight(10, "Melbourne", "Sydney", "QEW321",
                "Jetstar! Airlines", timestampFrom1, timestampTo1, validAirplane1);
        Flight flight5 = new Flight(12, "Melbourne", "Sydney", "QEW521",
                "555", timestampFrom1, timestampTo1, validAirplane1);

        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
        flights.add(flight5);
        FlightCollection.addFlights(flights);
        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Equivalence class test addFlight with null city name")
    public void testAddFlightsWithNullCity() {
        ArrayList<Flight> flights = new ArrayList<>();
        Flight flight1 = new Flight(1, null, "Sydney", "QWE123",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);
        Flight flight2 = new Flight(10, "Melbourne", null, "QWE234",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);

        flights.add(flight1);
        flights.add(flight2);
        FlightCollection.addFlights(flights);
        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Equivalence class test addFlight with empty city name")
    public void testAddFlightsWithEmptyCity() {
        ArrayList<Flight> flights = new ArrayList<>();
        Flight flight1 = new Flight(1, "  ", "Sydney", "QWE345",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);
        Flight flight2 = new Flight(2, "Melbourne", "     ", "QWE234",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);

        flights.add(flight1);
        flights.add(flight2);
        FlightCollection.addFlights(flights);
        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Equivalence class test addFlight with invalid city name")
    public void testAddFlightsWithInvalidCity() {
        ArrayList<Flight> flights = new ArrayList<>();
        Flight flight1 = new Flight(1, "Mel 1 bou rne", "Sydney", "WRE354",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);
        Flight flight2 = new Flight(2, "Melbou!!!rne", "Sydney", "QEW211",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);
        Flight flight3 = new Flight(12, "!!!", "Sydney", "QEW121",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);
        Flight flight4 = new Flight(21, "Melbourne", "24243", "QEW321",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);
        Flight flight5 = new Flight(11, "Melbourne", "    ", "QEW521",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);

        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
        flights.add(flight5);
        FlightCollection.addFlights(flights);
        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Test addFlights with invalid time")
    public void testAddFlightsWithInvalidTime() {
        ArrayList<Flight> flights = new ArrayList<>();

        Flight flight1 = new Flight(1, "Melbourne", "Sydney", "ASD123",
                "Jetstar Airlines", null, timestampTo1, validAirplane1);
        Flight flight2 = new Flight(2, "Melbourne", "Sydney", "QWE123",
                "Jetstar Airlines", timestampFrom1, null, validAirplane2);

        flights.add(flight1);
        flights.add(flight2);
        FlightCollection.addFlights(flights);
        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Test addFlights with arrival time before departure time")
    public void testAddFlightsWithInvalidTimeRange() {
        ArrayList<Flight> flights = new ArrayList<>();

        Flight flight1 = new Flight(1, "Melbourne", "Sydney", "ASD123",
                "Jetstar Airlines", timestampFrom2, timestampTo1, validAirplane1);

        flights.add(flight1);
        FlightCollection.addFlights(flights);
        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Test addFlights with invalid airplane id")
    public void testAddFlightsWithInvalidAirplaneID() {
        ArrayList<Flight> flights = new ArrayList<>();

        Flight flight1 = new Flight(1, "Melbourne", "Sydney", "ASD123",
                "Jetstar Airlines", timestampFrom1, timestampTo1, invalidAirplane1);

        flights.add(flight1);
        FlightCollection.addFlights(flights);
        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Test addFlights with invalid airplane model")
    public void testAddFlightsWithInvalidAirplaneModel() {
        ArrayList<Flight> flights = new ArrayList<>();

        Flight flight1 = new Flight(1, "Melbourne", "Sydney", "ASD123",
                "Jetstar Airlines", timestampFrom1, timestampTo1, invalidAirplane2);
        Flight flight2 = new Flight(1, "Melbourne", "Sydney", "ASD123",
                "Jetstar Airlines", timestampFrom1, timestampTo1, invalidAirplane3);

        flights.add(flight1);
        flights.add(flight2);
        FlightCollection.addFlights(flights);
        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Equivalance class test addFlights with invalid airplane seats")
    public void testAddFlightsWithInvalidAirplaneSeats() {
        ArrayList<Flight> flights = new ArrayList<>();
        Airplane airplane1 = new Airplane(1, "Boeing 737",
                -20, 150, 6);
        Airplane airplane2 = new Airplane(1, "Boeing 737",
                20, -150, 6);
        Airplane airplane3 = new Airplane(1, "Boeing 737",
                20, 150, -1);
        Airplane airplane4 = new Airplane(1, "Boeing 737",
                20, 150, 0);

        Flight flight1 = new Flight(1, "Melbourne", "Sydney", "ASD123",
                "Jetstar Airlines", timestampFrom1, timestampTo1, airplane1);
        Flight flight2 = new Flight(1, "Melbourne", "Sydney", "ASD123",
                "Jetstar Airlines", timestampFrom1, timestampTo1, airplane2);
        Flight flight3 = new Flight(1, "Melbourne", "Sydney", "ASD123",
                "Jetstar Airlines", timestampFrom1, timestampTo1, airplane3);
        Flight flight4 = new Flight(1, "Melbourne", "Sydney", "ASD123",
                "Jetstar Airlines", timestampFrom1, timestampTo1, airplane4);

        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);

        FlightCollection.addFlights(flights);
        assertEquals(0, FlightCollection.flights.size());
    }

    @Test
    @DisplayName("Test getFlightInfo method with decision table testing")
    public void testGetFlightInfoWithDecisionTable() {
        ArrayList<Flight> flights = new ArrayList<>();
        FlightCollection.addFlights(flights);

        // 1. Flights is empty
        assertNull(FlightCollection.getFlightInfo("Melbourne", "Sydney"));

        // Add flight into flights
        Flight flight1 = new Flight(1, "Melbourne", "Sydney", "ASD123",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);
        Flight flight2 = new Flight(2, "Melbourne", "Sydney", "QWE123",
                "Jetstar Airlines", timestampFrom2, timestampTo2, validAirplane2);
        flights.add(flight1);
        flights.add(flight2);
        FlightCollection.addFlights(flights);

        // 2. Flights is not empty, both departure and destination match
        assertNotNull(FlightCollection.getFlightInfo("Sydney", "Melbourne"));
        assertEquals(2, FlightCollection.flights.size());

        // 3. Flights is not empty, departure does not match but destination match
        assertNull(FlightCollection.getFlightInfo("Perth", "Melbourne"));

        // 4. Flights is not empty, departure matches but destination does not match
        assertNull(FlightCollection.getFlightInfo("Sydney", "Perth"));

        // 5. Flights is not empty, both departure and destination do not match
        assertNull(FlightCollection.getFlightInfo("Perth", "London"));
    }

    @Test
    @DisplayName("Test getFlightsToCity method with decision table testing")
    public void testGgetFlightsToCityWithDecisionTable() {
        ArrayList<Flight> flights = new ArrayList<>();
        FlightCollection.addFlights(flights);

        // 1. Flights is empty
        assertEquals(0, FlightCollection.getFlightsToCity("Perth").size());

        // Add flight into flights
        Flight flight1 = new Flight(1, "Melbourne", "Sydney", "ASD123",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);
        flights.add(flight1);
        Flight flight2 = new Flight(2, "Melbourne", "Sydney", "QWE123",
                "Jetstar Airlines", timestampFrom2, timestampTo2, validAirplane2);
        flights.add(flight2);
        FlightCollection.addFlights(flights);

        // 2. Flights is not empty, destination matches
        assertEquals(2, FlightCollection.getFlightsToCity("Melbourne").size());
        assertEquals("Melbourne", FlightCollection.getFlightsToCity("Melbourne").get(0).getDepartTo());
        assertEquals("Melbourne", FlightCollection.getFlightsToCity("Melbourne").get(1).getDepartTo());

        // 3. Flights is not empty but destination does not match
        assertEquals(0, FlightCollection.getFlightsToCity("Sydney").size());
    }

    @Test
    @DisplayName("Test getFlightInfoById method")
    public void testGetFlightInfoByIdWithValidId() {
        ArrayList<Flight> flights = new ArrayList<>();
        Flight flight1 = new Flight(1, "Melbourne", "Sydney", "ASD123",
                "Jetstar Airlines", timestampFrom1, timestampTo1, validAirplane1);
        flights.add(flight1);
        Flight flight2 = new Flight(2, "Melbourne", "Sydney", "QWE123",
                "Jetstar Airlines", timestampFrom2, timestampTo2, validAirplane2);
        flights.add(flight2);
        FlightCollection.addFlights(flights);

        assertNull(FlightCollection.getFlightInfoById(11));    //non-exist
        assertNotNull(FlightCollection.getFlightInfoById(1));  //valid id
        assertNull(FlightCollection.getFlightInfoById(0));     //boundary value
        assertNull(FlightCollection.getFlightInfoById(-1));    //invalid id
    }

}
