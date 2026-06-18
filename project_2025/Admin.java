import java.util.*;

//admin class
public class Admin {
      String adminId = "vidhi";
      String adminPass = "123";
      Scanner sc = new Scanner(System.in);

    public Bus[] buses;
    public int busCount;

    //constructer colling
   public Admin(Bus[] buses) {
    this.buses = buses;
    this.busCount = 0;
    // Count null bus
    for (int i = 0; i < buses.length; i++) {
        if (buses[i] != null) {
            busCount++;
        }
    }
}

    //admin login 
     void adminLogin() {
        System.out.print("Enter Admin ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        if (id.equals(adminId) && pass.equals(adminPass)) {
            System.out.println("\n Login Successful! Welcome, " + adminId);
            adminMenu();
        } else {
            System.out.println("\n Invalid id or password");
        }
    }

    //admin menu
    public void adminMenu() {
        while (true) {
            System.out.println("\n==== ADMIN MENU ====");
            System.out.println("1. Add Bus");
            System.out.println("2. View Buses");
            System.out.println("3. Update Bus");
            System.out.println("4. Delete Bus");
            System.out.println("5. Logout");

            int ch = readInt("Enter choice", -1);

            if (ch == 1) {
                addBus();
            } 
            else if (ch == 2) {
                viewBuses();
            } 
            else if (ch == 3) {
                updateBus();
            } 
            else if (ch == 4) {
                deleteBus();
            } 
            else if (ch == 5) {
                System.out.println(" Logged out from Admin Menu!");
                break;
            } 
            else {
                System.out.println(" Invalid choice! Try again...");
            }
        }
    }

    //add bus
    public void addBus() {
    System.out.print("Bus No: ");
    String no = sc.nextLine();

    System.out.print("Pickup: ");
    String p = sc.nextLine();

    System.out.print("Drop: ");
    String d = sc.nextLine();

    System.out.print("Distance (km): ");
    double dist = sc.nextDouble();

    System.out.print("Seats: ");
    int s = sc.nextInt();
    sc.nextLine();

    System.out.print("Type (AC/NonAC/Sleeper/Express): ");
    String type = sc.nextLine();

    Bus b=null;

    // ===== Dynamic Object Creation using Polymorphism =====
    if (type.equalsIgnoreCase("AC"))
        b = new ACBus(no, p, d, s, dist);
    else if (type.equalsIgnoreCase("NonAC"))
        b = new NonACBus(no, p, d, s, dist);
    else if (type.equalsIgnoreCase("Sleeper"))
        b = new SleeperBus(no, p, d, s, dist);
    else if (type.equalsIgnoreCase("Express"))
        b = new ExpressBus(no, p, d, s, dist);

    if (b != null) {
        buses[busCount++] = b;

        // Update in main class
        OnlineBusBookingSystem.busCount = busCount;
        System.out.println("\nBus Added Successfully!");
        System.out.println("Generated Bus ID: " + b.getBusId());
        System.out.println("Type: " + b.getClass().getSimpleName());
        System.out.println("------------------------------");

    } else {
        System.out.println("Invalid Bus Type!");
    }
}

 //view bus
    public void viewBuses() {
        if (busCount == 0) {
            System.out.println("No buses available!");
        } else {
            System.out.println("\n=== Available Bus List ===");
            for (int i = 0; i < busCount; i++) {
                buses[i].showBusDetails();
                System.out.println("------------------------------");
            }
        }
    }

    //update bus
    public void updateBus() {
        System.out.print("Enter Bus ID: ");
        String busId = sc.next();
        boolean found = false;

        for (int i = 0; i < busCount; i++) {
            if (buses[i].getBusId().equals(busId)) {
                System.out.print("Enter new Pickup Point: ");
                String p = sc.next();
                System.out.print("Enter new Drop Point: ");
                String d = sc.next();
                System.out.print("Enter Total Seats: ");
                int s = sc.nextInt();
                System.out.print("Enter Bus Type (ACBus/NonACBus/Express/Sleeper): ");
                String type = sc.next();

                buses[i].updateBus(p, d, s, type);
                System.out.println(" Bus updated successfully!");
                found = true;
                break;
            }
        }

        if (!found) System.out.println("Bus ID not found!");
        sc.nextLine();
    }

    //delete bus
    public void deleteBus() {
        System.out.print("Enter Bus ID to delete: ");
        String busId = sc.next();
        boolean found = false;

        for (int i = 0; i < busCount; i++) {
            if (buses[i].getBusId().equals(busId)) {
                for (int j = i; j < busCount - 1; j++) {
                    buses[j] = buses[j + 1];
                }
               busCount--;
              buses[busCount] = null;
                System.out.println(" Bus deleted successfully!");
                found = true;
                break;
            }
        }
        if (!found) System.out.println(" Bus ID not found!");
        sc.nextLine();
    }

    //method -1
    public int readInt(String msg, int def) {
        try {
            System.out.print(msg + ": ");
            return Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            return def;
        }
    }
}
