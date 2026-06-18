//acbus class
class ACBus extends Bus {
    private static final  double fperkm = 3.0; 
    ACBus(String busNo, String pickupPoint, String dropPoint, int totalSeats, double distance) {
        super(busNo, pickupPoint, dropPoint, totalSeats, distance);
    }
    //Fare Calculate the fare 
    public double calculateFare() {
        return distance * fperkm;
    }
    
}


   

