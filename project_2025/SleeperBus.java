
class SleeperBus extends Bus {
    SleeperBus(String busNo, String pickupPoint, String dropPoint, int totalSeats, double distance) {
        super(busNo, pickupPoint, dropPoint, totalSeats, distance);
    }
    // Fare Calculation
    public double calculateFare() {
        double fperkm = 2.5; 
        return distance * fperkm;
    }
}