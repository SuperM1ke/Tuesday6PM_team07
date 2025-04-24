package fit5171.monash.edu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AirplaneTest {

    private Airplane validAirplane;

    @BeforeEach
    void setUp() {
        // Setup a valid airplane object for tests
        validAirplane = new Airplane(101, "Boeing 737", 30, 150, 10);
    }

    @Test
    @DisplayName("Test valid airplane creation")
    void testValidAirplaneCreation() {
        assertNotNull(validAirplane);
        assertEquals(101, validAirplane.getAirplaneID());
        assertEquals("Boeing 737", validAirplane.getAirplaneModel());
        assertEquals(30, validAirplane.getBusinessSitsNumber());
        assertEquals(150, validAirplane.getEconomySitsNumber());
        assertEquals(10, validAirplane.getCrewSitsNumber());
    }

    @Test
    @DisplayName("Test invalid airplane ID")
    void testInvalidAirplaneID() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Airplane(-1, "Boeing 737", 30, 150, 10);
        });
    }

    @Test
    @DisplayName("Test null airplane model")
    void testNullAirplaneModel() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Airplane(101, null, 30, 150, 10);
        });
    }

    @Test
    @DisplayName("Test empty airplane model")
    void testEmptyAirplaneModel() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Airplane(101, "", 30, 150, 10);
        });
    }

    @Test
    @DisplayName("Test negative business seats")
    void testNegativeBusinessSeats() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Airplane(101, "Boeing 737", -5, 150, 10);
        });
    }

    @Test
    @DisplayName("Test negative economy seats")
    void testNegativeEconomySeats() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Airplane(101, "Boeing 737", 30, -10, 10);
        });
    }

    @Test
    @DisplayName("Test negative crew seats")
    void testNegativeCrewSeats() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Airplane(101, "Boeing 737", 30, 150, -2);
        });
    }

    @Test
    @DisplayName("Test seat row configuration")
    void testSeatRowConfiguration() {
        Airplane airplane = new Airplane(101, "Boeing 737", 30, 150, 10);

        char[] expectedRows = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        assertArrayEquals(expectedRows, airplane.getSeatRowLabels());

        assertEquals(7, airplane.getSeatsPerRow());
    }

    @Test
    @DisplayName("Test toString method")
    void testToString() {
        String expected = "Airplane{model=Boeing 737', business sits=30', economy sits=150', crew sits=10'}";
        assertEquals(expected, validAirplane.toString());
    }
}