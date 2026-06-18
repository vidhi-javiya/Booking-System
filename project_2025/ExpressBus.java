//inheritance
// ExpressBus class
class ExpressBus extends Bus {
    ExpressBus(String busNo, String pickupPoint, String dropPoint, int totalSeats, double distance) {
        super(busNo, pickupPoint, dropPoint, totalSeats, distance);
    }
    public double calculateFare() {
        double fperkm = 2;
        return distance * fperkm;
    }
}