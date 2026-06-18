// NonACBus class extend bus using inheritance
class NonACBus extends Bus {
    private static final  double fperkm = 1.5; 
    NonACBus(String busNo, String pickupPoint, String dropPoint, int totalSeats, double distance) {
        super(busNo, pickupPoint, dropPoint, totalSeats, distance);
    }
    //calculate the ticket fare
    public double calculateFare() {
        return distance * fperkm;
    }
}