import java.util.*;

interface Capability {
    String getName();
    boolean apply(int value);
}

class PowerCapability implements Capability {
    private boolean on = false;

    public String getName() {
        return "Power";
    }

    public boolean apply(int value) {
        if (value != 0 && value != 1)
            return false;

        on = value == 1;
        return true;
    }

    public String state() {
        return on ? "ON" : "OFF";
    }
}

class BrightnessCapability implements Capability {
    private int brightness = 0;

    public String getName() {
        return "Brightness";
    }

    public boolean apply(int value) {
        if (value < 0 || value > 100)
            return false;

        brightness = value;
        return true;
    }

    public int getBrightness() {
        return brightness;
    }
}

class TemperatureCapability implements Capability {
    private int temperature = 16;

    public String getName() {
        return "Temperature";
    }

    public boolean apply(int value) {
        if (value < 16 || value > 30)
            return false;

        temperature = value;
        return true;
    }

    public int getTemperature() {
        return temperature;
    }
}

class Device {
    String name;
    Map<String, Capability> capabilities = new HashMap<>();

    Device(String name) {
        this.name = name;
    }

    public void addCapability(Capability capability) {
        capabilities.put(capability.getName(), capability);
    }

    public Capability getCapability(String name) {
        return capabilities.get(name);
    }

    public void apply(String capability, int value) {

        Capability c = capabilities.get(capability);

        if (c == null)
            return;

        if (c.apply(value)) {

            if (capability.equals("Power")) {
                System.out.println(name + ": " +
                        (value == 1 ? "ON" : "OFF"));
            } else if (capability.equals("Brightness")) {
                System.out.println(name +
                        ": brightness set to " + value + "%.");
            } else if (capability.equals("Temperature")) {
                System.out.println(name +
                        ": temperature set to " + value + "°C.");
            }

        } else {
            if (capability.equals("Temperature")) {
                System.out.println(
                        "Rejected: " + name +
                        " temperature must be between 16°C and 30°C.");
            } else if (capability.equals("Brightness")) {
                System.out.println(
                        "Rejected: " + name +
                        " brightness must be between 0% and 100%.");
            }
        }
    }
}

class SceneStep {
    String capability;
    int value;

    SceneStep(String capability, int value) {
        this.capability = capability;
        this.value = value;
    }

    public int execute(List<Device> devices) {
        int count = 0;

        for (Device device : devices) {
            if (device.getCapability(capability) != null) {
                device.apply(capability, value);
                count++;
            }
        }

        return count;
    }
}

class Scene {
    String name;
    List<SceneStep> steps = new ArrayList<>();

    Scene(String name) {
        this.name = name;
    }

    public void addStep(SceneStep step) {
        steps.add(step);
    }

    public void execute(List<Device> devices) {

        System.out.println("Scene '" + name + "' started.");

        int actions = 0;

        for (SceneStep step : steps) {
            actions += step.execute(devices);
        }

        System.out.println(
                "Scene '" + name +
                "' completed: " + actions +
                " actions applied.");
    }
}

public class SmartLabControlPanel {

    public static void main(String[] args) {

        Device ac = new Device("Lab AC");
        ac.addCapability(new PowerCapability());
        ac.addCapability(new TemperatureCapability());

        Device lights = new Device("Ceiling Lights");
        lights.addCapability(new PowerCapability());
        lights.addCapability(new BrightnessCapability());

        Device projector = new Device("Projector");
        projector.addCapability(new PowerCapability());

        List<Device> devices =
                Arrays.asList(ac, lights, projector);

        Scene lecture = new Scene("Lecture Mode");

        lecture.addStep(new SceneStep("Power", 1));
        lecture.addStep(new SceneStep("Brightness", 40));
        lecture.addStep(new SceneStep("Temperature", 24));

        lecture.execute(devices);

        ac.apply("Temperature", 12);

        projector.addCapability(new BrightnessCapability());
        System.out.println("Projector: Brightness capability added.");

        projector.apply("Brightness", 70);
    }
}