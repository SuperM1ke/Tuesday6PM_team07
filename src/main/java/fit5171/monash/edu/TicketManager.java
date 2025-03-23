package fit5171.monash.edu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class TicketManager {
        private Passenger passenger;
        private Ticket ticket;
        private Flight flight;
        private Scanner in;

    public TicketManager() {
        passenger = new Passenger();
        ticket = new Ticket();
        flight = new Flight();
        in = new Scanner(System.in);
        }
    public void showTicket()
    {
        try
        {
            System.out.println("You have bought a ticket for flight " + ticket.flight.getDepartFrom() + " - " + ticket.flight.getDepartTo() + "\n\nDetails:");
            System.out.println(this.ticket.toString());
        }
        catch (NullPointerException e)
        {
            System.out.println("No ticket information available.");
            return;
        }
    }

    public void chooseAndBuyTicket(String departureCity, String destinationCity) throws Exception {
        int counter = 0;
        int firstFlightId = 0;
        int secondFlightId = 0;

        // Search for direct flight
        Flight directFlight = FlightCollection.getFlightInfo(departureCity, destinationCity);

        if (directFlight != null) {
            System.out.println("Direct flight found from " + departureCity + " to " + destinationCity);
            System.out.println("Available tickets for this flight:");

            // Get and display all available tickets for this flight
            ArrayList<Ticket> availableTickets = TicketCollection.getTicketsForFlight(directFlight.getFlightID());

            if (availableTickets == null || availableTickets.isEmpty()) {
                System.out.println("No tickets available for this flight.");
                return;
            }

            // Display tickets
            for (Ticket t : availableTickets) {
                System.out.println("Ticket ID: " + t.getTicket_id() +
                        ", Class: " + (t.getClassVip() ? "Business" : "Economy") +
                        ", Price: " + t.getPrice());
            }

            // Select and buy ticket
            System.out.println("\nEnter ID of ticket you want to purchase:");
            int ticketId = in.nextInt();
            buyDirectTicket(ticketId);
        } else {
            // Look for connecting flights
            System.out.println("No direct flights found. Searching for connecting flights...");

            // Find potential destination flights (arriving at final destination)
            ArrayList<Flight> destinationFlights = FlightCollection.getFlightsToCity(destinationCity);

            if (destinationFlights == null || destinationFlights.isEmpty()) {
                System.out.println("No flights to " + destinationCity + " found.");
                return;
            }

            System.out.println("Possible connecting routes:");

            // For each destination flight, check if there's a connecting flight from departure city
            for (Flight destFlight : destinationFlights) {
                String connectingCity = destFlight.getDepartFrom();

                // Skip if connecting city is the same as departure city (would be a direct flight)
                if (connectingCity.equals(departureCity)) continue;

                Flight connectingFlight = FlightCollection.getFlightInfo(departureCity, connectingCity);

                if (connectingFlight != null) {
                    counter++;
                    System.out.println("Option " + counter + ": " +
                            departureCity + " -> " + connectingCity + " -> " + destinationCity);

                    // Store flight IDs for the first valid option
                    if (counter == 1) {
                        firstFlightId = connectingFlight.getFlightID();
                        secondFlightId = destFlight.getFlightID();
                    }
                }
            }

            if (counter == 0) {
                System.out.println("No connecting flights available between " + departureCity + " and " + destinationCity);
                return;
            }

            System.out.println("Do you want to purchase tickets for option 1?\n1-YES 0-NO");
            int choice = in.nextInt();

            if (choice == 1) {
                // Get tickets for these flights
                ArrayList<Ticket> firstLegTickets = TicketCollection.getTicketsForFlight(firstFlightId);
                ArrayList<Ticket> secondLegTickets = TicketCollection.getTicketsForFlight(secondFlightId);

                if (firstLegTickets == null || firstLegTickets.isEmpty() ||
                        secondLegTickets == null || secondLegTickets.isEmpty()) {
                    System.out.println("No tickets available for one or both legs of the journey.");
                    return;
                }

                // Display first leg tickets
                System.out.println("First leg tickets (from " + departureCity + "):");
                for (Ticket t : firstLegTickets) {
                    System.out.println("Ticket ID: " + t.getTicket_id() +
                            ", Class: " + (t.getClassVip() ? "Business" : "Economy") +
                            ", Price: " + t.getPrice());
                }

                System.out.println("Enter ID of first ticket:");
                int firstTicketId = in.nextInt();

                // Display second leg tickets
                System.out.println("Second leg tickets (to " + destinationCity + "):");
                for (Ticket t : secondLegTickets) {
                    System.out.println("Ticket ID: " + t.getTicket_id() +
                            ", Class: " + (t.getClassVip() ? "Business" : "Economy") +
                            ", Price: " + t.getPrice());
                }

                System.out.println("Enter ID of second ticket:");
                int secondTicketId = in.nextInt();

                buyConnectingTickets(firstTicketId, secondTicketId);
            }
        }
    }
    private void collectPassengerInfo() {
        in.nextLine(); // Clear buffer

        System.out.println("Enter your First Name: ");
        String firstName = in.nextLine();
        passenger.setFirstName(firstName);

        System.out.println("Enter your Second name:");
        String secondName = in.nextLine();
        passenger.setSecondName(secondName);

        System.out.println("Enter your age:");
        int age = in.nextInt();
        in.nextLine(); // Clear buffer
        passenger.setAge(age);

        System.out.println("Enter your gender: ");
        String gender = in.nextLine();
        passenger.setGender(gender);

        System.out.println("Enter your e-mail address:");
        String email = in.nextLine();
        passenger.setEmail(email);

        System.out.println("Enter your phone number (+7):");
        String phoneNumber = in.nextLine();
        passenger.setPhoneNumber(phoneNumber);

        System.out.println("Enter your passport number:");
        String passportNumber = in.nextLine();
        passenger.setPassport(passportNumber);
    }

    private void processPayment() {
        System.out.println("Your bill: " + ticket.getPrice() + "\n");

        in.nextLine(); // Clear buffer
        System.out.println("Enter your card number:");
        String cardNumber = in.nextLine();
        passenger.setCardNumber(cardNumber);

        System.out.println("Enter your security code:");
        int securityCode = in.nextInt();
        passenger.setSecurityCode(securityCode);
    }

    public void buyDirectTicket(int ticketId) throws Exception {
        // Get ticket info
        Ticket selectedTicket = TicketCollection.getTicketInfo(ticketId);

        // Validate ticket
        if (selectedTicket == null) {
            System.out.println("This ticket does not exist.");
            return;
        }

        // Get flight info
        int flightId = selectedTicket.getFlight().getFlightID();
        Flight flight = FlightCollection.getFlightInfo(flightId);

        if (flight == null) {
            System.out.println("Flight information not found.");
            return;
        }

        try {
            // Collect passenger information
            collectPassengerInfo();

            System.out.println("Do you want to purchase?\n1-YES 0-NO");
            int confirmPurchase = in.nextInt();

            if (confirmPurchase == 0) {
                return;
            }

            // Get airplane info and update seat availability
            int airplaneId = flight.getAirplane().getAirplaneID();
            Airplane airplane = Airplane.getAirPlaneInfo(airplaneId);

            if (airplane == null) {
                System.out.println("Airplane information not found.");
                return;
            }

            // Update ticket
            this.ticket = selectedTicket;
            ticket.setPassenger(passenger);
            ticket.setFlight(flight);
            ticket.setTicketStatus(true);

            // Update airplane seat availability
            if (ticket.getClassVip()) {
                airplane.setBusinessSitsNumber(airplane.getBusinessSitsNumber() - 1);
            } else {
                airplane.setEconomySitsNumber(airplane.getEconomySitsNumber() - 1);
            }

            // Process payment
            processPayment();

            System.out.println("Ticket purchased successfully!");

        } catch (PatternSyntaxException e) {
            System.out.println("Error processing input: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void buyConnectingTickets(int firstTicketId, int secondTicketId) throws Exception {
        // Get tickets info
        Ticket firstTicket = TicketCollection.getTicketInfo(firstTicketId);
        Ticket secondTicket = TicketCollection.getTicketInfo(secondTicketId);

        // Validate tickets
        if (firstTicket == null || secondTicket == null) {
            System.out.println("One or both tickets do not exist.");
            return;
        }

        // Get flights info
        int firstFlightId = firstTicket.getFlight().getFlightID();
        int secondFlightId = secondTicket.getFlight().getFlightID();

        Flight firstFlight = FlightCollection.getFlightInfo(firstFlightId);
        Flight secondFlight = FlightCollection.getFlightInfo(secondFlightId);

        if (firstFlight == null || secondFlight == null) {
            System.out.println("Flight information not found for one or both flights.");
            return;
        }

        try {
            // Collect passenger information
            collectPassengerInfo();

            System.out.println("Do you want to purchase?\n1-YES 0-NO");
            int confirmPurchase = in.nextInt();

            if (confirmPurchase == 0) {
                return;
            }

            // Process first ticket
            int firstAirplaneId = firstFlight.getAirplane().getAirplaneID();
            Airplane firstAirplane = Airplane.getAirPlaneInfo(firstAirplaneId);

            firstTicket.setPassenger(passenger);
            firstTicket.setTicketStatus(true);

            if (firstTicket.getClassVip()) {
                firstAirplane.setBusinessSitsNumber(firstAirplane.getBusinessSitsNumber() - 1);
            } else {
                firstAirplane.setEconomySitsNumber(firstAirplane.getEconomySitsNumber() - 1);
            }

            // Process second ticket
            int secondAirplaneId = secondFlight.getAirplane().getAirplaneID();
            Airplane secondAirplane = Airplane.getAirPlaneInfo(secondAirplaneId);

            secondTicket.setPassenger(passenger);
            secondTicket.setTicketStatus(true);

            if (secondTicket.getClassVip()) {
                secondAirplane.setBusinessSitsNumber(secondAirplane.getBusinessSitsNumber() - 1);
            } else {
                secondAirplane.setEconomySitsNumber(secondAirplane.getEconomySitsNumber() - 1);
            }

            // Set combined price for reference (though we keep both tickets separate)
            double totalPrice = firstTicket.getPrice() + secondTicket.getPrice();
            System.out.println("Your total bill: " + totalPrice + "\n");

            // Process payment
            processPayment();

            // Store the first ticket as the main reference ticket
            this.ticket = firstTicket;

            System.out.println("Connecting tickets purchased successfully!");

        } catch (PatternSyntaxException e) {
            System.out.println("Error processing input: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

