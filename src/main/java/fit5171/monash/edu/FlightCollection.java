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
    	//display the flights where there is a direct flight from city 1 to city2
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
    public static Flight getFlightInfo(int flight_id) {
    	//SELECT a flight with a particular flight id
    	return null;

    }
    

}
