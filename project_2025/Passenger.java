//Passenger class

class Passenger
 {
    private String name;
    private int age;
    private String gender;
    private String contact;

    // Constructor
    Passenger(String name, int age, String gender, String contact) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
    }

    // ===== Getter Methods =====
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getContact() {
        return contact;
    }

    // show pessanger details
    public void showPassenger() {
        System.out.println("----------------------------------------");
        System.out.println("        PASSENGER DETAILS");
        System.out.println("----------------------------------------");
        System.out.println("Name      : " + name);
        System.out.println("Age       : " + age);
        System.out.println("Gender    : " + gender);
        System.out.println("Contact   : " + contact);
        System.out.println("----------------------------------------");
    }
}