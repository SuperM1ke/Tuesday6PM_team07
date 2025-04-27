package fit5171.monash.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PassengerTest {

    @Mock
    private Person mockPerson;

    private Passenger validPassenger;

    @BeforeEach
    void setUp() {
        // Initialize mockito annotations
        MockitoAnnotations.openMocks(this);

        // Setup mock person
        when(mockPerson.getFirstName()).thenReturn("John");
        when(mockPerson.getSecondName()).thenReturn("Doe");
        when(mockPerson.getAge()).thenReturn(30);
        when(mockPerson.getGender()).thenReturn("Man");

        // Setup a valid passenger for tests
        validPassenger = new Passenger("John", "Doe", 30, "Man",
                "john.doe@example.com", "+61412345678", "PA123456");
    }

    @Test
    @DisplayName("Test valid passenger creation")
    void testValidPassengerCreation() {
        assertNotNull(validPassenger);
        assertEquals("John", validPassenger.getFirstName());
        assertEquals("Doe", validPassenger.getSecondName());
        assertEquals(30, validPassenger.getAge());
        assertEquals("Man", validPassenger.getGender());
        assertEquals("john.doe@example.com", validPassenger.getEmail());
        assertEquals("+61412345678", validPassenger.getPhoneNumber());
        assertEquals("PA123456", validPassenger.getPassport());
    }

    @Test
    @DisplayName("Test passenger creation with null email")
    void testNullEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("John", "Doe", 30, "Man",
                    null, "+61412345678", "PA123456");
        });
    }

    @Test
    @DisplayName("Test passenger creation with empty email")
    void testEmptyEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("John", "Doe", 30, "Man",
                    "", "+61412345678", "PA123456");
        });
    }

    @Test
    @DisplayName("Test passenger creation with invalid email format")
    void testInvalidEmailFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("John", "Doe", 30, "Man",
                    "invalidemail", "+61412345678", "PA123456");
        });
    }

    @Test
    @DisplayName("Test passenger creation with null phone number")
    void testNullPhoneNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("John", "Doe", 30, "Man",
                    "john.doe@example.com", null, "PA123456");
        });
    }

    @Test
    @DisplayName("Test passenger creation with empty phone number")
    void testEmptyPhoneNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("John", "Doe", 30, "Man",
                    "john.doe@example.com", "", "PA123456");
        });
    }

    @Test
    @DisplayName("Test valid Australian phone number format with country code")
    void testValidAustralianPhoneNumberWithCountryCode() {
        Passenger passenger = new Passenger("John", "Doe", 30, "Man",
                "john.doe@example.com", "+61412345678", "PA123456");
        assertEquals("+61412345678", passenger.getPhoneNumber());
    }

    @Test
    @DisplayName("Test valid Australian phone number format without country code")
    void testValidAustralianPhoneNumberWithoutCountryCode() {
        Passenger passenger = new Passenger("John", "Doe", 30, "Man",
                "john.doe@example.com", "0412345678", "PA123456");
        assertEquals("+61412345678", passenger.getPhoneNumber());
    }

    @Test
    @DisplayName("Test valid US phone number format with country code")
    void testValidUSPhoneNumberWithCountryCode() {
        Passenger passenger = new Passenger("John", "Doe", 30, "Man",
                "john.doe@example.com", "+12125551234", "123456789");
        assertEquals("+12125551234", passenger.getPhoneNumber());
    }

    @Test
    @DisplayName("Test valid US phone number format without country code")
    void testValidUSPhoneNumberWithoutCountryCode() {
        Passenger passenger = new Passenger("John", "Doe", 30, "Man",
                "john.doe@example.com", "2125551234", "123456789");
        assertEquals("+12125551234", passenger.getPhoneNumber());
    }

    @Test
    @DisplayName("Test valid UK phone number format with country code")
    void testValidUKPhoneNumberWithCountryCode() {
        Passenger passenger = new Passenger("John", "Doe", 30, "Man",
                "john.doe@example.com", "+447911123456", "123456789");
        assertEquals("+447911123456", passenger.getPhoneNumber());
    }

    @Test
    @DisplayName("Test valid UK phone number format without country code")
    void testValidUKPhoneNumberWithoutCountryCode() {
        Passenger passenger = new Passenger("John", "Doe", 30, "Man",
                "john.doe@example.com", "07911123456", "123456789");
        assertEquals("+447911123456", passenger.getPhoneNumber());
    }

    @Test
    @DisplayName("Test invalid phone number format")
    void testInvalidPhoneNumberFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("John", "Doe", 30, "Man",
                    "john.doe@example.com", "12345", "PA123456");
        });
    }

    @Test
    @DisplayName("Test passenger creation with null passport")
    void testNullPassport() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("John", "Doe", 30, "Man",
                    "john.doe@example.com", "+61412345678", null);
        });
    }

    @Test
    @DisplayName("Test passenger creation with empty passport")
    void testEmptyPassport() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("John", "Doe", 30, "Man",
                    "john.doe@example.com", "+61412345678", "");
        });
    }

    @Test
    @DisplayName("Test valid Australian passport format")
    void testValidAustralianPassportFormat() {
        Passenger passenger = new Passenger("John", "Doe", 30, "Man",
                "john.doe@example.com", "+61412345678", "PA123456");
        assertEquals("PA123456", passenger.getPassport());
    }

    @Test
    @DisplayName("Test valid US passport format")
    void testValidUSPassportFormat() {
        Passenger passenger = new Passenger("John", "Doe", 30, "Man",
                "john.doe@example.com", "+12125551234", "123456789");
        assertEquals("123456789", passenger.getPassport());
    }

    @Test
    @DisplayName("Test valid UK passport format")
    void testValidUKPassportFormat() {
        Passenger passenger = new Passenger("John", "Doe", 30, "Man",
                "john.doe@example.com", "+447911123456", "123456789");
        assertEquals("123456789", passenger.getPassport());
    }

    @Test
    @DisplayName("Test invalid passport format")
    void testInvalidPassportFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("John", "Doe", 30, "Man",
                    "john.doe@example.com", "+61412345678", "12345");
        });
    }

    @Test
    @DisplayName("Test setEmail with valid email")
    void testSetEmailValid() {
        validPassenger.setEmail("new.email@example.com");
        assertEquals("new.email@example.com", validPassenger.getEmail());
    }

    @Test
    @DisplayName("Test setEmail with invalid email")
    void testSetEmailInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            validPassenger.setEmail("invalid-email");
        });
    }

    @Test
    @DisplayName("Test setPhoneNumber with valid phone number")
    void testSetPhoneNumberValid() {
        validPassenger.setPhoneNumber("+61498765432");
        assertEquals("+61498765432", validPassenger.getPhoneNumber());
    }

    @Test
    @DisplayName("Test setPhoneNumber with invalid phone number")
    void testSetPhoneNumberInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            validPassenger.setPhoneNumber("12345");
        });
    }

    @Test
    @DisplayName("Test setPassport with valid passport")
    void testSetPassportValid() {
        validPassenger.setPassport("PB654321");
        assertEquals("PB654321", validPassenger.getPassport());
    }

    @Test
    @DisplayName("Test setPassport with invalid passport")
    void testSetPassportInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            validPassenger.setPassport("12345");
        });
    }

    @Test
    @DisplayName("Test toString method")
    void testToString() {
        // Get the actual string representation
        String actual = validPassenger.toString();

        // Assert that it contains all the required information instead of exact matching
        assertTrue(actual.contains("Fullname= John Doe"));
        assertTrue(actual.contains("email='john.doe@example.com'"));
        assertTrue(actual.contains("phoneNumber='+61412345678'"));
        assertTrue(actual.contains("passport='PA123456'"));
    }
}