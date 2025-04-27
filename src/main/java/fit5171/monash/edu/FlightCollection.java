package fit5171.monash.edu;

import java.util.ArrayList;

public class FlightCollection {
	
	public static ArrayList<Flight> flights;

	public static ArrayList<Flight> getFlights() {
		return flights;
	}

	public static void addFlights(ArrayList<Flight> flights) {
		ArrayList<Flight> validFlights = new ArrayList();
		for (Flight flight : flights) {
			try {
				// validate flight ID
				if (flight.getFlightID() <= 0) {
					throw new IllegalArgumentException("Flight ID must be positive");
				}
				// validate flight destination
				if (flight.getDepartTo() == null || flight.getDepartTo().trim().isEmpty()
						|| !flight.getDepartTo().matches("^[A-Za-z ]+$")) {
					throw new IllegalArgumentException("Flight must have a valid destination");
				}
				// validate flight departure location
				if (flight.getDepartFrom() == null || flight.getDepartFrom().trim().isEmpty()
						|| !flight.getDepartFrom().matches("^[A-Za-z ]+$")) {
					throw new IllegalArgumentException("Flight must have a valid departure location");
				}
				// validate flight code
				if (flight.getCode() == null || flight.getCode().trim().isEmpty()
						|| !flight.getCode().matches("[A-Z]{3}\\d{3}")) {
					throw new IllegalArgumentException("Flight must have a valid code");
				}
				// validate flight company
				if (flight.getCompany() == null || flight.getCompany().trim().isEmpty()
						|| !flight.getCompany().matches("^[A-Za-z ]+$")) {
					throw new IllegalArgumentException("Flight must have a valid company");
				}
				// validate flight departure date
				if (flight.getDateFrom() == null) {
					throw new IllegalArgumentException("Flight must have a departure date");
				}
				// validate flight arrival date
				if (flight.getDateTo() == null) {
					throw new IllegalArgumentException("Flight must have an arrival date");
				}
				// validate departure date is before arrival date
				if (flight.getDateFrom().after(flight.getDateTo())) {
					throw new IllegalArgumentException("Departure time must be before arrival time");
				}

				//validate airplane information of flight
				Airplane airplane = flight.getAirplane();
				if (airplane == null) {
					throw new IllegalArgumentException("Flight must have an airplane");
				}
				// validate airplane ID
				if (airplane.getAirplaneID() <= 0) {
					throw new IllegalArgumentException("Airplane ID must be positive");
				}
				// validate airplane model
				if (airplane.getAirplaneModel() == null || airplane.getAirplaneModel().trim().isEmpty()) {
					throw new IllegalArgumentException("Airplane must have a model");
				}
				// validate airplane business class seat number
				if (airplane.getBusinessSitsNumber() < 0) {
					throw new IllegalArgumentException("Airplane business seats cannot be negative");
				}
				// validate airplane economy class seat number
				if (airplane.getEconomySitsNumber() < 0) {
					throw new IllegalArgumentException("Airplane economy seats cannot be negative");
				}
				// validate airplane crew seat number
				if (airplane.getCrewSitsNumber() < 1) {
					throw new IllegalArgumentException("Airplane must have crew seats ");
				}
				validFlights.add(flight);
			} catch (IllegalArgumentException  e) {
				System.out.print("Cannot add this flight. " + e.getMessage() + "! \n");
			}
		}

		FlightCollection.flights.addAll(validFlights);
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
