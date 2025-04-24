package fit5171.monash.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightTest {

    private Flight validFlight;

    @Mock
    private Airplane mockAirplane;

    private Timestamp validDateFrom;
    private Timestamp validDateTo;

    @BeforeEach
    void setUp() {
        // Initialize mockito annotations
        MockitoAnnotations.openMocks(this);

        // Setup mock airplane
        when(mockAirplane.getAirplaneID()).thenReturn(101);
        when(mockAirplane.getAirplaneModel()).thenReturn("Boeing 737");

        // Create valid timestamps for testing
        validDateFrom = Timestamp.valueOf("2025-05-01 10:00:00");
        validDateTo = Timestamp.valueOf("2025-05-01 12:30:00");

        // Setup a valid flight object for tests
        validFlight = new Flight(1001, "Melbourne", "Sydney", "FL001",
                "Qantas", validDateFrom, validDateTo, mockAirplane);
    }

    @Test
    @DisplayName("Test valid flight creation")
    void testValidFlightCreation() {
        assertNotNull(validFlight);
        assertEquals(1001, validFlight.getFlightID());
        assertEquals("Melbourne", validFlight.getDepartTo());
        assertEquals("Sydney", validFlight.getDepartFrom());
        assertEquals("FL001", validFlight.getCode());
        assertEquals("Qantas", validFlight.getCompany());
        assertEquals(validDateFrom, validFlight.getDateFrom());
        assertEquals(validDateTo, validFlight.getDateTo());
        assertEquals(mockAirplane, validFlight.getAirplane());
    }

    @Test
    @DisplayName("Test null departure to")
    void testNullDepartTo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Flight(1001, null, "Sydney", "FL001",
                    "Qantas", validDateFrom, validDateTo, mockAirplane);
        });
    }

    @Test
    @DisplayName("Test empty departure to")
    void testEmptyDepartTo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Flight(1001, "", "Sydney", "FL001",
                    "Qantas", validDateFrom, validDateTo, mockAirplane);
        });
    }

    @Test
    @DisplayName("Test null departure from")
    void testNullDepartFrom() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Flight(1001, "Melbourne", null, "FL001",
                    "Qantas", validDateFrom, validDateTo, mockAirplane);
        });
    }

    @Test
    @DisplayName("Test empty departure from")
    void testEmptyDepartFrom() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Flight(1001, "Melbourne", "", "FL001",
                    "Qantas", validDateFrom, validDateTo, mockAirplane);
        });
    }

    @Test
    @DisplayName("Test null flight code")
    void testNullCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Flight(1001, "Melbourne", "Sydney", null,
                    "Qantas", validDateFrom, validDateTo, mockAirplane);
        });
    }

    @Test
    @DisplayName("Test empty flight code")
    void testEmptyCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Flight(1001, "Melbourne", "Sydney", "",
                    "Qantas", validDateFrom, validDateTo, mockAirplane);
        });
    }

    @Test
    @DisplayName("Test null company")
    void testNullCompany() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Flight(1001, "Melbourne", "Sydney", "FL001",
                    null, validDateFrom, validDateTo, mockAirplane);
        });
    }

    @Test
    @DisplayName("Test empty company")
    void testEmptyCompany() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Flight(1001, "Melbourne", "Sydney", "FL001",
                    "", validDateFrom, validDateTo, mockAirplane);
        });
    }

    @Test
    @DisplayName("Test null date from")
    void testNullDateFrom() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Flight(1001, "Melbourne", "Sydney", "FL001",
                    "Qantas", null, validDateTo, mockAirplane);
        });
    }

    @Test
    @DisplayName("Test null date to")
    void testNullDateTo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Flight(1001, "Melbourne", "Sydney", "FL001",
                    "Qantas", validDateFrom, null, mockAirplane);
        });
    }

    @Test
    @DisplayName("Test null airplane")
    void testNullAirplane() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Flight(1001, "Melbourne", "Sydney", "FL001",
                    "Qantas", validDateFrom, validDateTo, null);
        });
    }

    @Test
    @DisplayName("Test invalid date format")
    void testInvalidDateFormat() {
        // This test will depend on how you implement date validation
        // For example, if you're validating the string before creating the Timestamp:
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);

        assertThrows(ParseException.class, () -> {
            // Invalid date: 13th month
            dateFormat.parse("2025-13-01 10:00:00");
        });
    }

    @Test
    @DisplayName("Test invalid time format")
    void testInvalidTimeFormat() {
        // Similar to date format testing
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeFormat.setLenient(false);

        assertThrows(ParseException.class, () -> {
            // Invalid time: 25 hours
            timeFormat.parse("25:00:00");
        });
    }

    @Test
    @DisplayName("Test date from is after date to")
    void testDateFromAfterDateTo() {
        // Date from is after date to
        Timestamp laterDate = Timestamp.valueOf("2025-05-02 10:00:00");

        assertThrows(IllegalArgumentException.class, () -> {
            new Flight(1001, "Melbourne", "Sydney", "FL001",
                    "Qantas", laterDate, validDateTo, mockAirplane);
        });
    }

    @Test
    @DisplayName("Test toString method")
    void testToString() {
        String expected = "Flight{" + mockAirplane.toString() +
                ", date to=" + validDateTo + ", " + '\'' +
                ", date from='" + validDateFrom + '\'' +
                ", depart from='" + "Sydney" + '\'' +
                ", depart to='" + "Melbourne" + '\'' +
                ", code=" + "FL001" + '\'' +
                ", company=" + "Qantas" + '\'' +
                ", code=" + "FL001" + '\'' +
                '}';

        assertEquals(expected, validFlight.toString());
    }
}