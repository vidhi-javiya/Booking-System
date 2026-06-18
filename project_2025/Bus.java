abstract class Bus {
    public static int idCounter = 101;  
    public String busId;  
    public String busNo;
    public String pickupPoint;
    public String dropPoint;
    public int totalSeats;
    public int availableSeats;
    public double distance;
    public boolean[] seatStatus;

    //Constructor
    Bus(String busNo, String pickup, String drop, int seats, double distance) {
        this.busId = "DU" + idCounter;  //auto generate bus id
        idCounter++;
        this.busNo = busNo;
        this.pickupPoint = pickup;
        this.dropPoint = drop;
        this.totalSeats = seats;
        this.availableSeats = seats;
        this.distance = distance;
        this.seatStatus = new boolean[seats];//arry seat
    }

    //abstract methos no body part
    public abstract double calculateFare();

    //show bus details
    public void showBusDetails() {
        System.out.println("Bus ID: " + busId + " || Busno: " + busNo + " || " +pickupPoint + " => " + dropPoint + " || Seats: " + availableSeats + "/" + totalSeats + " || Type: " + this.getClass().getSimpleName());
    }

    //show showAvailableSeats
    public void showAvailableSeats() {
        System.out.print("Available Seats: [");
        boolean flag = true;
        for (int i = 0; i < totalSeats; i++) {
            if (!seatStatus[i])
             { 
                if (!flag) System.out.print(", ");
                if ((i + 1) % 2 == 0)
                    System.out.print((i + 1) + "W"); // window seat
                else
                    System.out.print((i + 1));
                flag = false;
            }
        }
        System.out.println("]  (W - Window Seat)");
    }

    // book seat
    public boolean chooseSeat(int seatNum) {
        if (seatNum < 1 || seatNum > totalSeats) {
            System.out.println(" wrong seat number");
            return false;
        }
        if (seatStatus[seatNum - 1]) {
            System.out.println(" Seat already booked select next");
            return false;
        }
        seatStatus[seatNum - 1] = true;//book seat
        availableSeats--;//update count
        return true;
    }

    // ===== Cancel Seat =====
    public void cancelSeat(int seatNum) {
        if (seatNum < 1 || seatNum > totalSeats) {
            System.out.println(" wrong seat number");
            return;
        }
        if (!seatStatus[seatNum - 1]) {
            System.out.println(" Seat not booked");
            return;
        }
        seatStatus[seatNum - 1] = false;//unbook
        availableSeats++;
    }

    //update bus
    public void updateBus(String pickup, String drop, int seats, String time) {
        this.pickupPoint = pickup;
        this.dropPoint = drop;
        this.totalSeats = seats;
        this.availableSeats = seats;
        this.seatStatus = new boolean[seats];
    }

    //geetr method
    public String getBusId() {
        return busId;
    }

    public String getBusNo() {
        return busNo;
    }

    public String getPickupPoint() {
        return pickupPoint;
    }

    public String getDropPoint() {
        return dropPoint;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public double getDistance() {
        return distance;
    }
}