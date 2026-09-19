abstract class Shape {
    private static int counter = 1;
    private final String shapeId;

    public Shape() {
        shapeId = "SHAPE" + counter++;
    }

    public String getShapeId() {
        return shapeId;
    }

    public abstract double calculateArea();

    public void scale(double factor) {
        System.out.println("Scaling by factor: " + factor);
    }

    public void scale(double xFactor, double yFactor) {
        System.out.println("Scaling by xFactor: " + xFactor +
                           " and yFactor: " + yFactor);
    }
}

class CircleShape extends Shape {
    private double radius;

    public CircleShape(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void scale(double factor) {
        radius *= factor;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        radius *= (xFactor + yFactor) / 2.0;
    }
}

class SquareShape extends Shape {
    private double side;

    public SquareShape(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public void scale(double factor) {
        side *= factor;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        side *= (xFactor + yFactor) / 2.0;
    }
}

public class BasicDrawingCanvas {

    public static void printArea(Shape s) {
        System.out.println("Shape ID: " + s.getShapeId());
        System.out.println("Area: " + s.calculateArea());
    }

    public static void main(String[] args) {

        CircleShape c = new CircleShape(5.0);
        SquareShape sq = new SquareShape(4.0);

        System.out.println("Circle Area: " + c.calculateArea());
        System.out.println("Square Area: " + sq.calculateArea());

        sq.scale(2.0);

        System.out.println("Square Area after scaling: "
                           + sq.calculateArea());

        printArea(c);
        printArea(sq);

        // Shape s = new Shape(); // Compilation Error
    }
}