package fit5171.monash.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    // Since Person is abstract, we need a concrete implementation for testing
    static class TestPerson extends Person {
        public TestPerson(String firstName, String secondName, int age, String gender) {
            super(firstName, secondName, age, gender);
        }

        public TestPerson() {
            super();
        }
    }

    private Person validPerson;

    @BeforeEach
    void setUp() {
        // Setup a valid person object for tests
        validPerson = new TestPerson("John", "Doe", 30, "Man");
    }

    @Test
    @DisplayName("Test valid person creation")
    void testValidPersonCreation() {
        assertNotNull(validPerson);
        assertEquals("John", validPerson.getFirstName());
        assertEquals("Doe", validPerson.getSecondName());
        assertEquals(30, validPerson.getAge());
        assertEquals("Man", validPerson.getGender());
    }

    @Test
    @DisplayName("Test null first name")
    void testNullFirstName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestPerson(null, "Doe", 30, "Man");
        });
    }

    @Test
    @DisplayName("Test empty first name")
    void testEmptyFirstName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestPerson("", "Doe", 30, "Man");
        });
    }

    @Test
    @DisplayName("Test null second name")
    void testNullSecondName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestPerson("John", null, 30, "Man");
        });
    }

    @Test
    @DisplayName("Test empty second name")
    void testEmptySecondName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestPerson("John", "", 30, "Man");
        });
    }

    @Test
    @DisplayName("Test negative age")
    void testNegativeAge() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestPerson("John", "Doe", -5, "Man");
        });
    }

    @Test
    @DisplayName("Test null gender")
    void testNullGender() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestPerson("John", "Doe", 30, null);
        });
    }

    @Test
    @DisplayName("Test empty gender")
    void testEmptyGender() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestPerson("John", "Doe", 30, "");
        });
    }

    @Test
    @DisplayName("Test valid gender Woman")
    void testValidGenderWoman() {
        Person person = new TestPerson("Jane", "Doe", 28, "Woman");
        assertEquals("Woman", person.getGender());
    }

    @Test
    @DisplayName("Test valid gender Man")
    void testValidGenderMan() {
        Person person = new TestPerson("John", "Doe", 30, "Man");
        assertEquals("Man", person.getGender());
    }

    @Test
    @DisplayName("Test valid gender Non-Binary")
    void testValidGenderNonBinary() {
        Person person = new TestPerson("Alex", "Smith", 25, "Non-Binary");
        assertEquals("Non-Binary", person.getGender());
    }

    @Test
    @DisplayName("Test valid gender Prefer not to say")
    void testValidGenderPreferNotToSay() {
        Person person = new TestPerson("Taylor", "Johnson", 40, "Prefer not to say");
        assertEquals("Prefer not to say", person.getGender());
    }

    @Test
    @DisplayName("Test valid gender Other")
    void testValidGenderOther() {
        Person person = new TestPerson("Sam", "Brown", 35, "Other");
        assertEquals("Other", person.getGender());
    }

    @Test
    @DisplayName("Test invalid gender option")
    void testInvalidGenderOption() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestPerson("John", "Doe", 30, "InvalidGender");
        });
    }

    @Test
    @DisplayName("Test first name starting with digit")
    void testFirstNameStartingWithDigit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestPerson("1John", "Doe", 30, "Man");
        });
    }

    @Test
    @DisplayName("Test first name starting with symbol")
    void testFirstNameStartingWithSymbol() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestPerson("@John", "Doe", 30, "Man");
        });
    }

    @Test
    @DisplayName("Test second name starting with digit")
    void testSecondNameStartingWithDigit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestPerson("John", "1Doe", 30, "Man");
        });
    }

    @Test
    @DisplayName("Test second name starting with symbol")
    void testSecondNameStartingWithSymbol() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestPerson("John", "#Doe", 30, "Man");
        });
    }

    @Test
    @DisplayName("Test to string method")
    void testToString() {
        String expected = "Person{firstName='John', secondName='Doe', age=30, gender='Man'}";
        assertEquals(expected, validPerson.toString());
    }
}