package fit5171.monash.edu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightCollectionTest {

    private FlightCollection flightCollection;
    private String inputStr;
    private int inputNum

    @BeforeEach
    public void setup(){
        flightCollection = new FlightCollection();
    }

    @Test
    @DisplayName("validate if the flight exists")
    public void testGetFlightInfoByID(){
        LocalDateTime dateFrom = LocalDateTime.of(2025, 3, 22, 11, 2, 0, 0);
        LocalDateTime dateTo = LocalDateTime.of(2025, 3, 22, 12, 1, 0, 0);
        Timestamp timestampFrom = Timestamp.valueOf(dateFrom);
        Timestamp timestampTo = Timestamp.valueOf(dateTo);
        Airplane airplane = new Airplane();
        Flight flight = new Flight(123, "Melbourne", "Sydney", "MS1234", "JetStar", timestampFrom, timestampTo, airplane);
        assertEquals(123, flightCollection.getFlightInfoById(123));
    }

}