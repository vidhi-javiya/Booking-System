import java.util.*;

public class OnlineBusBookingSystem {
    static Scanner sc = new Scanner(System.in);
    static Bus[] buses = new Bus[50];
    static Booking[] bookings = new Booking[50];
    static int busCount = 0;
    static int bookingCount = 0;

    public static void main(String[] args) {

        // date and time class object (thred)
         DatePrinter dp = new DatePrinter(20); 
        dp.start(); 
   
        while (true) {
            System.out.println("\n==== MAIN MENU ====");
            System.out.println("1. Admin Login");
            System.out.println("2. User Menu");
            System.out.println("3. Exit");
            System.out.print("Enter Choice: ");
            int ch = sc.nextInt();
            sc.nextLine(); 

            if (ch == 1) {
                Admin admin = new Admin(buses);
                admin.adminLogin();
            } 
            else if (ch == 2) {
                User user = new User( buses, bookings, busCount, bookingCount);
                user.userMenu();
            } 
            else if (ch == 3) {
                System.out.println("Thank you for using the Bus Booking System!");
                dp.interrupt(); 
                break;
            } 
            else {
                System.out.println("Invalid Choice!");
            }
        } // while
    } // main
    static {
         System.out.print("WELCOME TO ONLINE BUS-BOOKING SYSTEM !");
    }
} // class
