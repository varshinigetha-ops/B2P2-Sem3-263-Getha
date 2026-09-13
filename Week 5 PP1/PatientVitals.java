import java.util.Arrays;

public class PatientVitals {

    private double[] readings;
    private int size;

    public PatientVitals(double[] initialReadings) {

        readings = new double[500];
        size = 0;

        for (double r : initialReadings) {
            recordReading(r);
        }
    }

    public void recordReading(double reading) {

        if (reading <= 0 || reading > 45)
            return;

        readings[size++] = reading;
    }

    public double getAverage() {

        if (size == 0)
            return 0;

        double sum = 0;

        for (int i = 0; i < size; i++)
            sum += readings[i];

        return sum / size;
    }

    public double[] getAllReadings() {

        return Arrays.copyOf(readings, size);
    }

    public static void main(String[] args) {

        PatientVitals v =
                new PatientVitals(
                        new double[]{36.5, -2, 37.1});

        System.out.println(
                Arrays.toString(v.getAllReadings()));
    }
}