import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

class Ticket {
    public Booking booking;
    public double discountedFare;
    public double discountPercent = 0.0;

    // constructor
    public Ticket(Booking booking) {
        this.booking = booking;
        this.discountedFare = applyDiscount(booking.getFare(), booking.getSeats());
    }

    // discount
    public double applyDiscount(double fare, int seats) {
        if (seats >= 3) {
            discountPercent = 10.0;
            System.out.println("\nFestival Offer Applied: 10% Discount on Total Fare!");
            return fare * 0.9;
        }
        return fare;
    }

    // show ticket
    public void showTicket(String filePath) throws IOException {
        Passenger p = booking.getPassenger();
        Bus b = booking.getBus();
        Payment payment = booking.getPayment();

        // filePath parameter
        FileOutputStream fos = new FileOutputStream(filePath);

        // print ticket
        System.out.println("\n======================================");
        fos.write("\n======================================\n".getBytes());

        System.out.println("BUS TICKET");
        fos.write("BUS TICKET\n".getBytes());

        System.out.println("======================================");
        fos.write("======================================\n".getBytes());

        System.out.println("Passenger Details:");
        fos.write("Passenger Details:\n".getBytes());
        System.out.println("  Name : " + p.getName());
        fos.write(("  Name    : " + p.getName() + "\n").getBytes());
        System.out.println("  Age: " + p.getAge());
        fos.write(("  Age     : " + p.getAge() + "\n").getBytes());
        System.out.println("  Gender: " + p.getGender());
        fos.write(("  Gender  : " + p.getGender() + "\n").getBytes());
        System.out.println("  Contact: " + p.getContact());
        fos.write(("  Contact : " + p.getContact() + "\n").getBytes());

        System.out.println("\nJourney Details:");
        fos.write("\nJourney Details:\n".getBytes());
        System.out.println("  Bus ID  : " + b.getBusId());
        fos.write(("  Bus ID : " + b.getBusId() + "\n").getBytes());
        System.out.println("  Bus Type : " + b.getClass().getSimpleName());
        fos.write(("  Bus Type: " + b.getClass().getSimpleName() + "\n").getBytes());
        System.out.println("  Pickup Point : " + b.getPickupPoint());
        fos.write(("  Pickup Point : " + b.getPickupPoint() + "\n").getBytes());
        System.out.println("  Drop Point   : " + b.getDropPoint());
        fos.write(("  Drop Point   : " + b.getDropPoint() + "\n").getBytes());

        System.out.println("\nBooking Details:");
        fos.write("\nBooking Details:\n".getBytes());
        System.out.println("  Booking ID        : DUT" + booking.getBookingId());
        fos.write(("  Booking ID  : DUT" + booking.getBookingId() + "\n").getBytes());
        System.out.println("Booking Date/time   : " + new Date());
        fos.write(("Booking Date/time   : " + new Date() + "\n").getBytes());
       System.out.println("  Journey Date       : " + booking.getJourneyDate());
        fos.write(("  Journey Date   : " + booking.getJourneyDate() + "\n").getBytes());
        System.out.println(" No of Seats Booked : " + booking.getSeats());
        fos.write((" No of Seats Booked : " + booking.getSeats() + "\n").getBytes());
        System.out.println("  Seat Numbers  : " + booking.getSeatNumbers());
        fos.write(("  Seat Numbers: " + booking.getSeatNumbers() + "\n").getBytes());
        System.out.println("  Total Fare: " + booking.getFare());
        fos.write(("  Total Fare: " + booking.getFare() + "\n").getBytes());

        if (discountPercent > 0) {
            System.out.println("  Discount Applied  : " + discountPercent + "%");
            fos.write(("  Discount Applied  : " + discountPercent + "%\n").getBytes());
            System.out.println("  Payable Amount    : " + discountedFare);
            fos.write(("  Payable Amount : " + discountedFare + "\n").getBytes());
        } else {
            System.out.println("  Payable Amount: " + discountedFare);
            fos.write(("  Payable Amount    : " + discountedFare + "\n").getBytes());
        }
String status = (payment != null && payment.isPaid()) ? "Paid" : "Pending";
System.out.println("Payment Status: " + status);
fos.write(("Payment Status: " + status + "\n").getBytes());
if ("Paid".equals(status)) {
    System.out.println("Payment Method: " + payment.getPaymentMethod());
    fos.write(("Payment Method: " + payment.getPaymentMethod() + "\n").getBytes());
}
        System.out.println("\nTerms & Conditions:");
        fos.write("\nTerms & Conditions:\n".getBytes());
        System.out.println("  1. Ticket is non-transferable.");
        fos.write("  1. Ticket is non-transferable.\n".getBytes());
        System.out.println("  2. In case of cancellation, refund rules apply.");
        fos.write("  2. In case of cancellation, refund rules apply.\n".getBytes());

        System.out.println("======================================");
        fos.write("======================================\n".getBytes());

        fos.close();
        System.out.println("Ticket saved at: " + filePath);
    }
}
