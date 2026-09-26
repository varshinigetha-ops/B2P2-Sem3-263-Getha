import java.time.LocalDate;
import java.util.*;

abstract class Room {
    protected String name;
    protected double pricePerDay;
    protected List<Reservation> reservations = new ArrayList<>();

    Room(String name, double pricePerDay) {
        this.name = name;
        this.pricePerDay = pricePerDay;
    }

    public boolean isAvailable(LocalDate start, LocalDate end) {
        for (Reservation r : reservations) {
            if (r.isActive() &&
                    start.isBefore(r.getEndDate()) &&
                    end.isAfter(r.getStartDate())) {
                return false;
            }
        }
        return true;
    }

    public double calculatePrice(long days) {
        return days * pricePerDay;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public String getName() {
        return name;
    }
}

class StandardRoom extends Room {
    StandardRoom(String name) {
        super(name, 150);
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(String name) {
        super(name, 200);
    }
}

class SuiteRoom extends Room {
    SuiteRoom(String name) {
        super(name, 300);
    }
}

class Customer {
    private String name;

    Customer(String name) {
        this.name = name;
    }
}

class Reservation {
    private Room room;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active = true;

    Reservation(Room room, Customer customer,
                LocalDate startDate, LocalDate endDate) {
        this.room = room;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void cancel() {
        active = false;
    }

    public Room getRoom() {
        return room;
    }
}

class BookingManager {

    public Reservation bookRoom(Room room, Customer customer,
                                 LocalDate start, LocalDate end) {

        if (!room.isAvailable(start, end)) {
            System.out.println("Booking failed: " +
                    room.getName() + " is not available.");
            return null;
        }

        long days = java.time.temporal.ChronoUnit.DAYS.between(
                start, end);

        Reservation reservation =
                new Reservation(room, customer, start, end);

        room.addReservation(reservation);

        System.out.println(room.getName() +
                " booked from " + start + " to " + end + ".");

        System.out.printf("Total price: $%.2f%n",
                room.calculatePrice(days));

        return reservation;
    }

    public void cancelReservation(Reservation reservation) {

        if (reservation != null && reservation.isActive()) {
            reservation.cancel();

            System.out.println("Reservation for " +
                    reservation.getRoom().getName() +
                    " cancelled successfully.");
        }
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {

        Customer customer = new Customer("John");

        Room deluxe = new DeluxeRoom("Deluxe Room 101");
        Room standard = new StandardRoom("Standard Room 205");

        BookingManager manager = new BookingManager();

        Reservation r1 = manager.bookRoom(
                deluxe,
                customer,
                LocalDate.of(2024, 12, 1),
                LocalDate.of(2024, 12, 5));

        Reservation r2 = manager.bookRoom(
                standard,
                customer,
                LocalDate.of(2024, 12, 3),
                LocalDate.of(2024, 12, 7));

        manager.bookRoom(
                deluxe,
                customer,
                LocalDate.of(2024, 12, 3),
                LocalDate.of(2024, 12, 7));

        manager.cancelReservation(r1);
    }
}