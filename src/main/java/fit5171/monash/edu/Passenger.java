package fit5171.monash.edu;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Passenger extends Person {
    private String email;
    private String phoneNumber;
    private String cardNumber;
    private int securityCode;
    private String passport;

    // RegEx pattern for email validation
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    // Phone number format validators
    private static final Pattern AUS_PHONE_PATTERN = Pattern.compile("^(\\+61|0)4\\d{2}\\s?\\d{3}\\s?\\d{3}$");
    private static final Pattern US_PHONE_PATTERN = Pattern.compile("^(\\+1|1)?\\s?\\(?(\\d{3})\\)?[-\\s]?(\\d{3})[-\\s]?(\\d{4})$");
    private static final Pattern UK_PHONE_PATTERN = Pattern.compile("^(\\+44|0)7\\d{9}$");

    // Passport format validators
    private static final Pattern AUS_PASSPORT_PATTERN = Pattern.compile("^[A-Z]{1,2}\\d{6}$");
    private static final Pattern US_PASSPORT_PATTERN = Pattern.compile("^\\d{9}$");
    private static final Pattern UK_PASSPORT_PATTERN = Pattern.compile("^\\d{9}$");

    public Passenger() {}

    public Passenger(String firstName, String secondName, int age, String gender,
                     String email, String phoneNumber, String passport) {
        super(firstName, secondName, age, gender);
        validateEmail(email);
        validatePhoneNumber(phoneNumber);
        validatePassport(passport);

        this.email = email;
        this.phoneNumber = standardizePhoneNumber(phoneNumber);
        this.passport = passport;
    }

    public Passenger(String firstName, String secondName, int age, String gender,
                     String email, String phoneNumber, String passport,
                     String cardNumber, int securityCode) {
        super(firstName, secondName, age, gender);
        validateEmail(email);
        validatePhoneNumber(phoneNumber);
        validatePassport(passport);

        this.email = email;
        this.phoneNumber = standardizePhoneNumber(phoneNumber);
        this.passport = passport;
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
    }

    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Email must follow the pattern: abc@domain.com");
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }

        // Remove all spaces and special characters for consistent validation
        String cleanNumber = phoneNumber.replaceAll("[\\s()-]", "");

        // Check against all supported formats
        if (!AUS_PHONE_PATTERN.matcher(cleanNumber).matches() &&
                !US_PHONE_PATTERN.matcher(cleanNumber).matches() &&
                !UK_PHONE_PATTERN.matcher(cleanNumber).matches()) {
            throw new IllegalArgumentException("Phone number must be in a valid format for Australia, US, or UK");
        }
    }

    private String standardizePhoneNumber(String phoneNumber) {
        // Remove all spaces and special characters
        String cleanNumber = phoneNumber.replaceAll("[\\s()-]", "");

        // Convert to international format with country code
        if (cleanNumber.startsWith("04")) {
            // Australian number without country code
            return "+61" + cleanNumber.substring(1);
        } else if (cleanNumber.startsWith("+614")) {
            // Already in international format
            return cleanNumber;
        } else if (cleanNumber.matches("^\\d{10}$") && !cleanNumber.startsWith("0")) {
            // US number without country code
            return "+1" + cleanNumber;
        } else if (cleanNumber.startsWith("07") && cleanNumber.length() == 11) {
            // UK number without country code
            return "+44" + cleanNumber.substring(1);
        }

        // Already in international format or not recognized
        return cleanNumber;
    }

    private void validatePassport(String passport) {
        if (passport == null || passport.trim().isEmpty()) {
            throw new IllegalArgumentException("Passport number cannot be null or empty");
        }

        // Check against all supported formats
        if (!AUS_PASSPORT_PATTERN.matcher(passport).matches() &&
                !US_PASSPORT_PATTERN.matcher(passport).matches() &&
                !UK_PASSPORT_PATTERN.matcher(passport).matches()) {
            throw new IllegalArgumentException("Passport number must be in a valid format for Australia, US, or UK");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        validatePhoneNumber(phoneNumber);
        this.phoneNumber = standardizePhoneNumber(phoneNumber);
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        validatePassport(passport);
        this.passport = passport;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    @Override
    public String toString() {
        return "Passenger{ Fullname= " + super.getFirstName() + " " + super.getSecondName() +
                " ,email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passport='" + passport + '\'' +
                '}';
    }
}