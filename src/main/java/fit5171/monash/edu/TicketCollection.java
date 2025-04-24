package fit5171.monash.edu;

import java.util.ArrayList;

public class TicketCollection {
	
	public static ArrayList<Ticket> tickets = new ArrayList<>();

	public static ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public static void addTickets(ArrayList<Ticket> tickets_db) {

		for (Ticket ticket : tickets_db) {
			// validate ticket ID
			if (ticket.getTicket_id() <= 0) {
				throw new IllegalArgumentException("Ticket ID must be positive");
			}
			// validate ticket price
			if (ticket.getPrice() < 0) {
				throw new IllegalArgumentException("Ticket price cannot be negative");
			}

			// validate flight information of ticket
			Flight flight = ticket.getFlight();
			if (flight == null) {
				throw new IllegalArgumentException("Ticket must have a flight");
			}
			// validate flight ID
			if (flight.getFlightID() <= 0) {
				throw new IllegalArgumentException("Flight ID must be positive");
			}
			// validate flight destination
			if (flight.getDepartTo() == null || flight.getDepartTo().trim().isEmpty()) {
				throw new IllegalArgumentException("Flight must have a destination");
			}
			// validate flight departure location
			if (flight.getDepartFrom() == null || flight.getDepartFrom().trim().isEmpty()) {
				throw new IllegalArgumentException("Flight must have a departure location");
			}
			// validate flight code
			if (flight.getCode() == null || flight.getCode().trim().isEmpty()) {
				throw new IllegalArgumentException("Flight must have a code");
			}
			// validate flight company
			if (flight.getCompany() == null || flight.getCompany().trim().isEmpty()) {
				throw new IllegalArgumentException("Flight must have a company");
			}
			// validate flight departure date
			if (flight.getDateFrom() == null) {
				throw new IllegalArgumentException("Flight must have a departure date");
			}
			// validate flight arrival date
			if (flight.getDateTo() == null) {
				throw new IllegalArgumentException("Flight must have an arrival date");
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
			if (airplane.getCrewSitsNumber() < 0) {
				throw new IllegalArgumentException("Airplane crew seats cannot be negative");
			}

			// validate if ticket status matches passenger information
			if (ticket.ticketStatus() && ticket.getPassenger() == null) {
				throw new IllegalArgumentException("Sold tickets must have passenger information");
			}
			if (!ticket.ticketStatus() && ticket.getPassenger() != null) {
				throw new IllegalArgumentException("Unold tickets shouldn't have passenger information");
			}

		}
		TicketCollection.tickets.addAll(tickets_db);
	}
	
	public static void getAllTickets() {
    	//display all available tickets from the Ticket collection
		if (tickets == null || tickets.isEmpty()) {
			System.out.println("No tickets available.");
			return;
		}

		System.out.println("Available tickets:");

		for (Ticket ticket : tickets) {
			if (!ticket.ticketStatus()) {  // 只显示未售出的票据
				System.out.println("Ticket ID: " + ticket.getTicket_id());
				System.out.println("Price: " + ticket.getPrice());
				System.out.println("Flight: " + ticket.getFlight().getCode() +
						" (" + ticket.getFlight().getDepartFrom() +
						" to " + ticket.getFlight().getDepartTo() + ")");
				System.out.println("Class: " + (ticket.getClassVip() ? "Business" : "Economy"));
				System.out.println("-------------------");
			}
		}

    }

	public static Ticket getTicketInfoById(int ticket_id) {
		// Method to find a ticket with a specific ticket ID
		// SELECT a ticket where ticket id = ticket_id

		// Check for null/empty collection
		if (tickets == null || tickets.isEmpty()) {
			return null;
		}

		// Search for the ticket with the matching ID
		for (Ticket ticket : tickets) {
			if (ticket.getTicket_id() == ticket_id) {
				return ticket; // Return the ticket if ID matches
			}
		}

		// No ticket found with this ID
		return null;
	}

	public static ArrayList<Ticket> getTicketsForFlight(int flight_id) {

		ArrayList<Ticket> flightTickets = new ArrayList<>();

		for (Ticket ticket : tickets) {
			if (ticket.getFlight().getFlightID() == flight_id && !ticket.ticketStatus()) {
				flightTickets.add(ticket);
			}
		}

		return flightTickets.isEmpty() ? null : flightTickets;
	}
	

}
