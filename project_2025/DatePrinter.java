import java.util.Date;

public class DatePrinter extends Thread {
    public int time;

    public DatePrinter(int time) {
        this.time = time;
    }
    public void run() {
        for (int i = 0; i < time; i++) {
            Date now = new Date();
            System.out.println("Current Date & Time: " + now.toString());
            try {
                Thread.sleep(25000); // 25 seconds
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
