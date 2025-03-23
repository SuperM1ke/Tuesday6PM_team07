package fit5171.monash.edu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightCollectionTest {

    private FlightCollection flightCollection;
    private String inputStr;
    private int inputNum;

    @BeforeEach
    public void setup(){
        flightCollection = new FlightCollection();
        FlightCollection.flights = new ArrayList<>();
    }

    @Test
    @DisplayName("validate if the flight ID is not found")
    public void testGetFlightInfoByIDWithUnmachedID(){
        LocalDateTime dateFrom = LocalDateTime.of(2025, 3, 22, 11, 2, 0, 0);
        LocalDateTime dateTo = LocalDateTime.of(2025, 3, 22, 12, 1, 0, 0);
        Timestamp timestampFrom = Timestamp.valueOf(dateFrom);
        Timestamp timestampTo = Timestamp.valueOf(dateTo);
        Airplane airplane = new Airplane(1, "A", 1,2,3);
        Flight flight = new Flight(123, "Melbourne", "Sydney", "MS1234", "JetStar", timestampFrom, timestampTo, airplane);
        FlightCollection.flights.add(flight);
        assertEquals(null, flightCollection.getFlightInfoById(321));
    }

    @Test
    @DisplayName("validate if the flights are null")
    public void testGetFlightInfoByIDWithFlightsValueAreNull(){
        assertEquals(null, flightCollection.getFlightInfoById(123));
    }

    @Test
    @DisplayName("validate if the flight ID is matched")
    public void testGetFlightInfoByIDWithMachedID(){
        LocalDateTime dateFrom = LocalDateTime.of(2025, 3, 22, 11, 2, 0, 0);
        LocalDateTime dateTo = LocalDateTime.of(2025, 3, 22, 12, 1, 0, 0);
        Timestamp timestampFrom = Timestamp.valueOf(dateFrom);
        Timestamp timestampTo = Timestamp.valueOf(dateTo);
        Airplane airplane = new Airplane(1, "A", 1,2,3);
        Flight flight = new Flight(123, "Melbourne", "Sydney", "MS1234", "JetStar", timestampFrom, timestampTo, airplane);
        FlightCollection.flights.add(flight);
        assertEquals(flight, flightCollection.getFlightInfoById(123));
    }


}