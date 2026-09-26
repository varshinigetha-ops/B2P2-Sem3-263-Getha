import java.util.*;

abstract class Vehicle {
    protected String name;
    protected boolean available = true;

    Vehicle(String name) {
        this.name = name;
    }

    public abstract double calculateCharge(int days);

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }
}

class StandardCar extends Vehicle {
    StandardCar(String name) {
        super(name);
    }

    public double calculateCharge(int days) {
        return days * 50;
    }
}

class LuxuryCar extends Vehicle {
    LuxuryCar(String name) {
        super(name);
    }

    public double calculateCharge(int days) {
        return days * 100;
    }
}

class SUV extends Vehicle {
    SUV(String name) {
        super(name);
    }

    public double calculateCharge(int days) {
        return days * 80;
    }
}

class Customer {
    private String name;

    Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Rental {
    private Customer customer;
    private Vehicle vehicle;
    private int days;
    private double amount;

    Rental(Customer customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
        this.amount = vehicle.calculateCharge(days);
    }

    public double getAmount() {
        return amount;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}

class RentalService {

    public Rental rentVehicle(Customer customer, Vehicle vehicle, int days) {

        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getName() + " is not available.");
            return null;
        }

        vehicle.setAvailable(false);

        Rental rental = new Rental(customer, vehicle, days);

        System.out.println(vehicle.getName() +
                " rented for " + days + " days.");
        System.out.printf("Total charge: $%.2f%n",
                rental.getAmount());

        return rental;
    }

    public void returnVehicle(Rental rental) {

        if (rental != null) {
            rental.getVehicle().setAvailable(true);

            System.out.println(rental.getVehicle().getName() +
                    " returned. Now available.");
        }
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {

        Customer customer = new Customer("John");

        Vehicle luxury = new LuxuryCar("Luxury Car A");
        Vehicle standard = new StandardCar("Standard Car B");

        RentalService service = new RentalService();

        Rental r1 = service.rentVehicle(customer, luxury, 3);
        Rental r2 = service.rentVehicle(customer, standard, 5);

        service.returnVehicle(r1);
    }
}
