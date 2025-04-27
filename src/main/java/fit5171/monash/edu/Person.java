package fit5171.monash.edu;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public abstract class Person {
    private String firstName;
    private String secondName;
    private int age;
    private String gender;

    // Valid gender options
    private static final Set<String> VALID_GENDER_OPTIONS = new HashSet<>(
            Arrays.asList("Woman", "Man", "Non-Binary", "Prefer not to say", "Other"));

    // Pattern for validating name (must start with a letter)
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z].*$");

    public Person() {}

    public Person(String firstName, String secondName, int age, String gender) {
        validateName(firstName, "First name");
        validateName(secondName, "Second name");
        validateAge(age);
        validateGender(gender);

        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.gender = gender;
    }

    private void validateName(String name, String fieldName) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }

        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(fieldName + " must start with a letter");
        }
    }

    private void validateAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }

    private void validateGender(String gender) {
        if (gender == null || gender.trim().isEmpty()) {
            throw new IllegalArgumentException("Gender cannot be null or empty");
        }

        if (!VALID_GENDER_OPTIONS.contains(gender)) {
            throw new IllegalArgumentException("Gender must be one of: " + VALID_GENDER_OPTIONS);
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        validateAge(age);
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        validateGender(gender);
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setFirstName(String firstName) {
        validateName(firstName, "First name");
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        validateName(secondName, "Second name");
        this.secondName = secondName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}