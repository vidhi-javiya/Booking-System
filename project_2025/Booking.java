import java.util.*;

//booking class
class Booking {
    public Passenger passenger;
    public Bus bus;
    public int numSeats;
    public double totalFare;
    public static int bookingCounter = 25001;
    public int bookingId;
    public String bookingDate;
    public String journeyDate;
    public Payment payment;
    public int[] seatNumbers;

    //Constructor
    Booking(Passenger passenger, Bus bus, int numSeats, int[] seatNumbers) throws TooManyTicketsException {
        if (numSeats > 5)
            throw new TooManyTicketsException("You cannot book more than 5 tickets at a time");

        this.passenger = passenger;
        this.bus = bus;
        this.numSeats = numSeats;
        this.seatNumbers = seatNumbers;
        this.bookingId = bookingCounter++;
        this.totalFare = numSeats * bus.calculateFare();
        this.bookingDate = new Date().toString();
        //journey date
        System.out.print("Enter Journey Date (dd-MM-yyyy): ");
        this.journeyDate = new Scanner(System.in).nextLine();
        this.payment = new Payment();
    }

    //getter method
    public int getBookingId() {
        return bookingId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Bus getBus() {
        return bus;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public Payment getPayment() {
        return payment;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public int getSeats() {   
        return numSeats;
    }

    public double getFare() { 
        return totalFare;
    }

    public String getJourneyDate() {
        return journeyDate;
    }

    public String getSeatNumbers() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seatNumbers.length; i++) {
            sb.append(seatNumbers[i]);
            if (i < seatNumbers.length - 1)
                sb.append(", ");
        }
        return sb.toString();
    }

    //Display Booking detailes
    void displayBooking() {
        System.out.println("-------------------------------------");
        System.out.println("Booking ID   : DUT" + bookingId);
        System.out.println("Passenger    : " + passenger.getName());
        System.out.println("Bus No       : " + bus.getBusNo());
        System.out.println("Seats Booked : " + numSeats + " (" + getSeatNumbers() + ")");
        System.out.println("Total Fare   : Rs. " + totalFare);
        System.out.println("Journey Date : " + journeyDate);
        System.out.println("Booking Date : " + bookingDate);
        System.out.println("-------------------------------------");
    }
}
