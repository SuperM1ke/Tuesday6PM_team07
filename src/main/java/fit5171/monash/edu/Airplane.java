package fit5171.monash.edu;

public class Airplane {
    private int airplaneID;
    private String airplaneModel;
    private int businessSitsNumber;
    private int economySitsNumber;
    private int crewSitsNumber;
    private static final char[] SEAT_ROW_LABELS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    private static final int SEATS_PER_ROW = 7;

    public Airplane(int airplaneID, String airplaneModel, int businessSitsNumber, int economySitsNumber, int crewSitsNumber) {
        validateAirplaneID(airplaneID);
        validateAirplaneModel(airplaneModel);
        validateSeatCount(businessSitsNumber, "Business seats");
        validateSeatCount(economySitsNumber, "Economy seats");
        validateSeatCount(crewSitsNumber, "Crew seats");

        this.airplaneID = airplaneID;
        this.airplaneModel = airplaneModel;
        this.businessSitsNumber = businessSitsNumber;
        this.economySitsNumber = economySitsNumber;
        this.crewSitsNumber = crewSitsNumber;
    }

    private void validateAirplaneID(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Airplane ID must be positive");
        }
    }

    private void validateAirplaneModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Airplane model cannot be null or empty");
        }
    }

    private void validateSeatCount(int count, String seatType) {
        if (count < 0) {
            throw new IllegalArgumentException(seatType + " count cannot be negative");
        }
    }

    public int getAirplaneID() {
        return airplaneID;
    }

    public void setAirplaneID(int airplaneID) {
        validateAirplaneID(airplaneID);
        this.airplaneID = airplaneID;
    }

    public String getAirplaneModel() {
        return airplaneModel;
    }

    public void setAirplaneModel(String airplaneModel) {
        validateAirplaneModel(airplaneModel);
        this.airplaneModel = airplaneModel;
    }

    public int getBusinessSitsNumber() {
        return businessSitsNumber;
    }

    public void setBusinessSitsNumber(int businessSitsNumber) {
        validateSeatCount(businessSitsNumber, "Business seats");
        this.businessSitsNumber = businessSitsNumber;
    }

    public int getEconomySitsNumber() {
        return economySitsNumber;
    }

    public void setEconomySitsNumber(int economySitsNumber) {
        validateSeatCount(economySitsNumber, "Economy seats");
        this.economySitsNumber = economySitsNumber;
    }

    public int getCrewSitsNumber() {
        return crewSitsNumber;
    }

    public void setCrewSitsNumber(int crewSitsNumber) {
        validateSeatCount(crewSitsNumber, "Crew seats");
        this.crewSitsNumber = crewSitsNumber;
    }

    public char[] getSeatRowLabels() {
        return SEAT_ROW_LABELS.clone(); // Return a copy to prevent modification
    }

    public int getSeatsPerRow() {
        return SEATS_PER_ROW;
    }

    public String toString() {
        return "Airplane{" +
                "model=" + getAirplaneModel() + '\'' +
                ", business sits=" + getBusinessSitsNumber() + '\'' +
                ", economy sits=" + getEconomySitsNumber() + '\'' +
                ", crew sits=" + getCrewSitsNumber() + '\'' +
                '}';
    }

    public static Airplane getAirPlaneInfo(int airplane_id) {
        // In a real implementation, this would fetch data from a database
        // For now, returning null as a placeholder
        return null;
    }
}