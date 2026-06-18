import java.util.*;
import java.io.IOException;

public class User {
    public Bus[] buses;
    public Booking[] bookings;
    public int busCount;
    public int bookingCount;

    // ===== Constructor =====
    
    public User( Bus[] buses, Booking[] bookings, int busCount, int bookingCount) {
        this.buses = buses;
        this.bookings = bookings;
        this.busCount = busCount;
        this.bookingCount = bookingCount;
    }
   Scanner sc=new Scanner(System.in);
  
    // ===== PASSENGER MENU =====
    public void userMenu() {
        while (true) {
            System.out.println("\n==== USER MENU ====");
            System.out.println("1. View Available Buses");
            System.out.println("2. Search Bus by Route");
            System.out.println("3. Select Bus Type");
            System.out.println("4. Book Ticket");
            System.out.println("5. Make Payment");
            System.out.println("6. Cancel Ticket");
            System.out.println("7. View My Bookings");
            System.out.println("8. Back to Main Menu");

            int ch = readInt("Enter choice", -1);

            if (ch == 1) {
                Admin admin = new Admin(buses);
                admin.viewBuses();
            } else if (ch == 2){
                 searchBus(buses, busCount, sc);
            }else if (ch == 3){
                selectBusType();
            }else if (ch == 4) {
    try {
        bookTicket();
    } catch (TooManyTicketsException e) {
        System.out.println("Booking Error: " + e.getMessage());
    }
    break;
            } else if (ch == 5){
              makePayment();
            }else if (ch == 6) {
                cancelTicket();
            }else if (ch == 7){
               myBookings();
            }else if (ch == 8)
             break;
            else
            {
                 System.out.println("Invalid choice!");
        }
    }
    }

    // ===== Helper Method =====
     public int readInt(String msg, int def) {
        try {
            System.out.print(msg + ": ");
            return Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            return def;
        }
    }

    // searchBus
   public static final void searchBus(Bus[] buses, int busCount, Scanner sc) {
    System.out.print("Enter Pickup: ");
    String p = sc.nextLine();
    System.out.print("Enter Drop: ");
    String d = sc.nextLine();
    boolean found = false;
    for (int i = 0; i < busCount; i++) {
        if (buses[i].pickupPoint.equalsIgnoreCase(p) && buses[i].dropPoint.equalsIgnoreCase(d)) 
        {
            buses[i].showBusDetails();
            found = true;
        }
    }
    if (!found) {
        System.out.println("No buses found on this route!");
    }
}

    // selectBus
    public void selectBusType() {
        System.out.print("Enter Bus Type (AC/NonAC/Seater/Sleeper/Express): ");
        String t = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < busCount; i++) {
            if (buses[i].getClass().getSimpleName().equalsIgnoreCase(t + "Bus"))
             {
                buses[i].showBusDetails();
                found = true;
            }
        }
        if (!found) {
        System.out.println("No buses found on this route!");
    }
    }

    // ===== Book Ticket =====
 public void bookTicket() throws TooManyTicketsException {
    Admin admin = new Admin(buses);
    admin.viewBuses();
    System.out.print("Enter Bus ID: ");
    String id = sc.nextLine();
    Bus b = null;
    for (int i = 0; i < buses.length; i++) {
        if (buses[i] != null && buses[i].busId.equalsIgnoreCase(id)) {
            b = buses[i];
            break;
        }
    }
    if (b == null) {
        System.out.println("Bus not found!");
        return;
    }
    System.out.print("Enter Name: ");
    String n = sc.nextLine();
    System.out.print("Enter Gender: ");
    String g = sc.nextLine();
    System.out.print("Enter Age: ");
    int a = sc.nextInt();
    sc.nextLine();
    System.out.print("Enter Contact: ");
    String c = sc.nextLine();
//offer
    System.out.println("(Offer: Book more than 3 seats to get a 10% discount!)");
    System.out.print("Enter Number of Seats to Book: ");
    int s = sc.nextInt();
    sc.nextLine();
    //custom exception
    if (s > 5) {
        throw new TooManyTicketsException("You cannot book more than 5 seats in one booking!");
    }
//check avilable
    if (s > b.getAvailableSeats()) {
    throw new TooManyTicketsException(
        "You cannot book " + s + " seats. Only " + b.getAvailableSeats() + " seats available."
    );
}
    b.showAvailableSeats();
    int[] chosenSeats = new int[s];
    for (int i = 0; i < s; i++) {
        System.out.print("Choose Seat Number " + (i + 1) + ": ");
        String seatInput = sc.nextLine().trim().toUpperCase();
        if (seatInput.endsWith("W")) seatInput = seatInput.substring(0, seatInput.length() - 1);
        int seatNo;
        try {
            seatNo = Integer.parseInt(seatInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid seat format! Enter like 2 or 2W only.");
            i--;
            continue;
        }
        if (!b.chooseSeat(seatNo)) {
            i--;
        } else {
            chosenSeats[i] = seatNo;
        }
    }

    Passenger p = new Passenger(n, a, g, c);
    Booking bk = new Booking(p, b, s, chosenSeats);
    bookings[bookingCount++] = bk;

    System.out.println("\nBooking Successful!");
    System.out.print("Seats Booked: ");
    for (int i = 0; i < s; i++) {
        if (i > 0) System.out.print(", ");
        if (chosenSeats[i] % 2 == 0)
            System.out.print(chosenSeats[i] + "W");
        else
            System.out.print(chosenSeats[i]);
    }
    System.out.println();

    Ticket t = new Ticket(bk);
    try {
        t.showTicket("D:\\project_2025_java\\project_2025\\ticket\\bus_ticket_" + bk.getBookingId() + ".txt");
    } catch (IOException e) {
        System.out.println("Error saving ticket: " + e.getMessage());
    }
    userMenu();   
return;
}
   //payment
public void makePayment() {
    int id = readInt("Enter Booking ID (without DUT)", -1);
    boolean found = false;

    for (int i = 0; i < bookingCount; i++) {
        Booking b = bookings[i];
        if (b != null && b.getBookingId() == id) {
            found = true;
            Payment p = b.getPayment();

            if (p == null) {
                System.out.println(" Payment not available (ticket may be cancelled).");
                return;
            }
            if (p.isPaid()) {
                System.out.println(" Already Paid!");
                return;
            }
            System.out.println("Select Payment Mode: 1.Cash 2.Card 3.UPI");
            int option = readInt("Enter Option", -1);

            switch (option) {
                case 1:
                    p.makePayment(b.getTotalFare());
                    break;
                case 2:
                    System.out.print("Enter Card Number: ");
                    String card = sc.nextLine();
                    p.makePayment(b.getTotalFare(), card);
                    break;
                case 3:
                    System.out.print("Enter UPI ID: ");
                    String upi = sc.nextLine();
                    p.makePayment(b.getTotalFare(), upi, true);
                    break;
                default:
                    System.out.println("Invalid Option!");
                    break;
            }
            return;
        }
    }
    if (!found)
        System.out.println(" Booking not found or ticket already cancelled!");
}

//cancle ticket
public void cancelTicket() {
    System.out.print("Enter Booking ID (without T): ");
    int id = sc.nextInt();
    sc.nextLine();  
    boolean found = false;
    for (int i = 0; i < bookingCount; i++) {
        if (bookings[i] != null && bookings[i].getBookingId() == id) {
            // Cancel seats and payment
            bookings[i].getBus().cancelSeat(bookings[i].getSeats());
            bookings[i].getPayment().cancelPayment();

            // Shift array left
            for (int j = i; j < bookingCount - 1; j++) {
                bookings[j] = bookings[j + 1];
            }

            // Clear last slot
            bookings[bookingCount - 1] = null;

            bookingCount--;
            System.out.println(" Ticket Cancelled Successfully!");
            found = true;
            break;
        }
    }
    if (!found) {
        System.out.println(" Booking not found!");
    }
}
//booking
public void myBookings() {
    System.out.print("Enter Passenger Name: ");
    String name = sc.nextLine();
    boolean found = false;

    for (int i = 0; i < bookingCount; i++) {
        Booking booking = bookings[i];
        if (booking != null && booking.getPassenger().getName().equalsIgnoreCase(name)) {
            Ticket t = new Ticket(booking);
            try {
                t.showTicket("D:\\project_2025_java\\project_2025\\ticket\\bus_ticket_" + booking.getBookingId() + ".txt");
            } catch (IOException e) {
                System.out.println("Error saving ticket: " + e.getMessage());
            }
            found = true;
        }
    }
    if (!found) {
        System.out.println(" No active bookings found!");
    }
}
}

