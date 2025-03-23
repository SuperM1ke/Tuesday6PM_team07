package fit5171.monash.edu;

import java.util.ArrayList;

public class TicketCollection {
	
	public static ArrayList<Ticket> tickets;

	public static ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public static void addTickets(ArrayList<Ticket> tickets_db) {
		TicketCollection.tickets.addAll(tickets_db);
	}
	
	public static void getAllTickets() {
    	//display all available tickets from the Ticket collection
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
		if (tickets == null) {
			return null;
		}

		ArrayList<Ticket> flightTickets = new ArrayList<>();

		for (Ticket ticket : tickets) {
			if (ticket.getFlight().getFlightID() == flight_id && !ticket.ticketStatus()) {
				flightTickets.add(ticket);
			}
		}

		return flightTickets.isEmpty() ? null : flightTickets;
	}
	

}
