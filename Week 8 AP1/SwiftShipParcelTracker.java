import java.util.*;

interface ShippingType {
    double calculateCharge(double weight);
}

class StandardShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return 40 + (10 * weight);
    }
}

class ExpressShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return 80 + (15 * weight);
    }
}

class FragileShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return new StandardShipping().calculateCharge(weight) + 50;
    }
}

interface NotificationChannel {
    void notify(String parcelId, String status);
}

class SmsChannel implements NotificationChannel {
    public void notify(String parcelId, String status) {
        System.out.println("[SMS] " + parcelId + " is now " + status + ".");
    }
}

class EmailChannel implements NotificationChannel {
    public void notify(String parcelId, String status) {
        System.out.println("[Email] " + parcelId + " is now " + status + ".");
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Parcel {
    String id;
    double weight;
    ShippingType shippingType;
    String status = "BOOKED";
    List<NotificationChannel> channels = new ArrayList<>();

    Parcel(String id, double weight, ShippingType shippingType) {
        this.id = id;
        this.weight = weight;
        this.shippingType = shippingType;
    }

    public void subscribe(NotificationChannel channel) {
        channels.add(channel);
    }

    public void notifyChannels() {
        for (NotificationChannel channel : channels) {
            channel.notify(id, status);
        }
    }

    public void changeStatus(String newStatus) {

        String next = "";

        if (status.equals("BOOKED"))
            next = "PICKED_UP";
        else if (status.equals("PICKED_UP"))
            next = "IN_TRANSIT";
        else if (status.equals("IN_TRANSIT"))
            next = "OUT_FOR_DELIVERY";
        else if (status.equals("OUT_FOR_DELIVERY"))
            next = "DELIVERED";

        if (next.equals(newStatus)) {
            status = newStatus;
            notifyChannels();
        } else {
            System.out.println(
                    "Invalid transition: " + status +
                    " → " + newStatus + " is not allowed.");
        }
    }

    public void cancel() {
        if (status.equals("BOOKED")) {
            System.out.println("Parcel " + id + " cancelled.");
        } else {
            System.out.println(
                    "Cancellation failed: " + id +
                    " can be cancelled only while BOOKED.");
        }
    }
}

class ParcelService {

    public Parcel bookParcel(String id, double weight,
                             ShippingType shippingType) {

        Parcel parcel = new Parcel(id, weight, shippingType);

        System.out.println(
                "Parcel " + id + " booked (" +
                shippingType.getClass().getSimpleName()
                .replace("Shipping", "") +
                ", " + weight + " kg).");

        System.out.printf("Charge: ₹%.2f%n",
                shippingType.calculateCharge(weight));

        return parcel;
    }
}

public class SwiftShipParcelTracker {

    public static void main(String[] args) {

        ParcelService service = new ParcelService();

        Parcel parcel = service.bookParcel(
                "P101", 2, new ExpressShipping());

        parcel.subscribe(new SmsChannel());
        parcel.subscribe(new EmailChannel());

        parcel.notifyChannels();

        parcel.changeStatus("PICKED_UP");

        parcel.cancel();

        parcel.changeStatus("IN_TRANSIT");

        parcel.changeStatus("DELIVERED");
    }
}