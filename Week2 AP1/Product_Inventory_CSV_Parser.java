import java.util.Scanner;

public class Product_Inventory_CSV_Parser {

    static void parseInventoryRecord(String csvLine) {

        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.println("Product: " + fields[0].trim()
                + " | SKU: " + fields[1].trim()
                + " | Qty: " + fields[2].trim());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter CSV Record: ");
        String csvLine = sc.nextLine();

        parseInventoryRecord(csvLine);
    }
}
