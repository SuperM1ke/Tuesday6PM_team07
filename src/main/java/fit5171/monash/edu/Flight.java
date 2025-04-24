package fit5171.monash.edu;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

public class Flight {
    private int flightID;
    private String departTo;
    private String departFrom;
    private String code;
    private String company;
    private Timestamp dateFrom;
    private Timestamp dateTo;
    private Airplane airplane;

    public Flight() {}

    public Flight(int flightID, String departTo, String departFrom, String code, String company,
                  Timestamp dateFrom, Timestamp dateTo, Airplane airplane) {
        validateFlightID(flightID);
        validateNotNullOrEmpty(departTo, "Departure destination");
        validateNotNullOrEmpty(departFrom, "Departure origin");
        validateNotNullOrEmpty(code, "Flight code");
        validateNotNullOrEmpty(company, "Airline company");
        validateDate(dateFrom, "Departure date");
        validateDate(dateTo, "Arrival date");
        validateAirplane(airplane);
        validateDateOrder(dateFrom, dateTo);

        this.flightID = flightID;
        this.departTo = departTo;
        this.departFrom = departFrom;
        this.code = code;
        this.company = company;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.airplane = airplane;
    }

    private void validateFlightID(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Flight ID must be positive");
        }
    }

    private void validateNotNullOrEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
    }

    private void validateDate(Timestamp date, String fieldName) {
        if (date == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }

        // Convert Timestamp to string to validate format
        String dateString = date.toString();

        // Check if the date format is YYYY-MM-DD
        if (!Pattern.matches("\\d{4}-\\d{2}-\\d{2}.*", dateString)) {
            throw new IllegalArgumentException(fieldName + " must be in YYYY-MM-DD format");
        }

        // Check if the time format is HH:MM:SS
        if (!Pattern.matches(".*\\d{2}:\\d{2}:\\d{2}.*", dateString)) {
            throw new IllegalArgumentException(fieldName + " time must be in HH:MM:SS format");
        }
    }

    private void validateAirplane(Airplane airplane) {
        if (airplane == null) {
            throw new IllegalArgumentException("Airplane cannot be null");
        }
    }

    private void validateDateOrder(Timestamp dateFrom, Timestamp dateTo) {
        if (dateFrom != null && dateTo != null && dateFrom.after(dateTo)) {
            throw new IllegalArgumentException("Departure date cannot be after arrival date");
        }
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        validateFlightID(flightID);
        this.flightID = flightID;
    }

    public String getDepartTo() {
        return departTo;
    }

    public void setDepartTo(String departTo) {
        validateNotNullOrEmpty(departTo, "Departure destination");
        this.departTo = departTo;
    }

    public String getDepartFrom() {
        return departFrom;
    }

    public void setDepartFrom(String departFrom) {
        validateNotNullOrEmpty(departFrom, "Departure origin");
        this.departFrom = departFrom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        validateNotNullOrEmpty(code, "Flight code");
        this.code = code;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        validateNotNullOrEmpty(company, "Airline company");
        this.company = company;
    }

    public Timestamp getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Timestamp dateFrom) {
        validateDate(dateFrom, "Departure date");
        if (this.dateTo != null) {
            validateDateOrder(dateFrom, this.dateTo);
        }
        this.dateFrom = dateFrom;
    }

    public Timestamp getDateTo() {
        return dateTo;
    }

    public void setDateTo(Timestamp dateTo) {
        validateDate(dateTo, "Arrival date");
        if (this.dateFrom != null) {
            validateDateOrder(this.dateFrom, dateTo);
        }
        this.dateTo = dateTo;
    }

    public void setAirplane(Airplane airplane) {
        validateAirplane(airplane);
        this.airplane = airplane;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public String toString() {
        return "Flight{" + airplane.toString() +
                ", date to=" + getDateTo() + ", " + '\'' +
                ", date from='" + getDateFrom() + '\'' +
                ", depart from='" + getDepartFrom() + '\'' +
                ", depart to='" + getDepartTo() + '\'' +
                ", code=" + getCode() + '\'' +
                ", company=" + getCompany() + '\'' +
                ", code=" + getCode() + '\'' +
                '}';
    }

    // Method to check if a flight with same details already exists in the system
    // This would typically connect to a repository or database
    // For unit testing purposes, it's best to mock this functionality
    public static boolean flightExists(Flight flight) {
        // In a real implementation, this would check a database or collection
        return false;
    }
}