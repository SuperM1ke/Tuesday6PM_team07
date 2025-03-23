package fit5171.monash.edu;

import java.util.ArrayList;

public class FlightCollection {
	
	public static ArrayList<Flight> flights;

	public static ArrayList<Flight> getFlights() {
		return flights;
	}

	public static void addFlights(ArrayList<Flight> flights) {
		FlightCollection.flights.addAll(flights);
	}

	public static Flight getFlightInfo(String city1, String city2) {
		// Method to find a direct flight from city1 to city2

		// Check for null/empty collection
		if (flights == null || flights.isEmpty()) {
			return null;
		}

		// Search for a direct flight that matches the departure and destination cities
		for (Flight flight : flights) {
			if (flight.getDepartFrom().equalsIgnoreCase(city1) &&
					flight.getDepartTo().equalsIgnoreCase(city2)) {
				return flight; // Return the first matching flight
			}
		}

		// No direct flight found
		return null;
	}

	public static ArrayList<Flight> getFlightsToCity(String city) {
		// Method to get all flights arriving at the specified city
		// SELECT * from flight where depart_to = city

		ArrayList<Flight> flightsToCity = new ArrayList<>();

		// Check for null/empty collection
		if (flights == null || flights.isEmpty()) {
			return flightsToCity; // Return empty list
		}

		// Filter flights arriving at the specified city
		for (Flight flight : flights) {
			if (flight.getDepartTo().equalsIgnoreCase(city)) {
				flightsToCity.add(flight);
			}
		}

		return flightsToCity;
	}
	public static Flight getFlightInfoById(int flight_id) {
		// Method to find a flight with a specific flight ID


		// Check for null/empty collection
		if (flights == null || flights.isEmpty()) {
			return null;
		}

		// Search for the flight with the matching ID
		for (Flight flight : flights) {
			if (flight.getFlightID() == flight_id) {
				return flight; // Return the flight if ID matches
			}
		}

		// No flight found with this ID
		return null;
	}
    

}
