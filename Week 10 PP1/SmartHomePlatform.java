import java.util.*;

interface Dimmable {
    void dim(int level);
}

interface Schedulable {
    void schedule(String time);
}

interface EnergyMonitor {
    void showEnergy();
}

abstract class Device {
    String id;
    boolean on = false;

    Device(String id) {
        this.id = id;
    }

    void turnOn() {
        on = true;
        System.out.println(id + " is ON");
    }

    void turnOff() {
        on = false;
        System.out.println(id + " is OFF");
    }
}

class Light extends Device implements Dimmable, Schedulable {
    Light(String id) {
        super(id);
    }

    public void dim(int level) {
        if (level >= 0 && level <= 100)
            System.out.println(id + " dimmed to " + level);
        else
            System.out.println(id + " rejected: invalid dim level");
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Fan extends Device implements Schedulable {
    Fan(String id) {
        super(id);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Plug extends Device implements EnergyMonitor {
    double energy;

    Plug(String id, double energy) {
        super(id);
        this.energy = energy;
    }

    public void showEnergy() {
        System.out.println(id + " energy " + energy + " kWh");
    }
}

public class SmartHomePlatform {
    public static void main(String[] args) {
        Map<String, Device> devices = new HashMap<>();

        devices.put("L1", new Light("L1"));
        devices.put("F1", new Fan("F1"));
        devices.put("P1", new Plug("P1", 12));

        command(devices, "ON", "L1", null);
        command(devices, "DIM", "L1", "40");
        command(devices, "DIM", "F1", "30");
        command(devices, "SCHEDULE", "F1", "22:00");
        command(devices, "ENERGY", "P1", null);
        command(devices, "ENERGY", "L1", null);
    }

    static void command(Map<String, Device> devices,
                        String action, String id, String value) {
        Device device = devices.get(id);

        if (device == null) {
            System.out.println(id + " rejected: device not found");
            return;
        }

        switch (action) {
            case "ON":
                device.turnOn();
                break;

            case "OFF":
                device.turnOff();
                break;

            case "DIM":
                if (device instanceof Dimmable)
                    ((Dimmable) device).dim(Integer.parseInt(value));
                else
                    System.out.println(id + " rejected: DIM unsupported");
                break;

            case "SCHEDULE":
                if (device instanceof Schedulable)
                    ((Schedulable) device).schedule(value);
                else
                    System.out.println(id + " rejected: SCHEDULE unsupported");
                break;

            case "ENERGY":
                if (device instanceof EnergyMonitor)
                    ((EnergyMonitor) device).showEnergy();
                else
                    System.out.println(id + " rejected: ENERGY unsupported");
                break;

            default:
                System.out.println("Unknown command");
        }
    }
}