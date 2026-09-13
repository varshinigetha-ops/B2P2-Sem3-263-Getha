class FoodOrder {

    private String studentName;
    private String dishName;
    private boolean delivered;

    public FoodOrder(String studentName, String dishName) {

        if(studentName == null || dishName == null)
            throw new IllegalArgumentException();

        if(studentName.trim().isEmpty() ||
           dishName.trim().isEmpty())
            throw new IllegalArgumentException();

        this.studentName = studentName;
        this.dishName = dishName;
        delivered = false;
    }

    public void markDelivered() {

        if(delivered) {
            System.out.println("Already delivered");
        } else {
            delivered = true;
            System.out.println("Delivered successfully");
        }
    }

    public static void processBatch(String[][] rawOrders) {

        int valid = 0;
        int rejected = 0;

        for(String[] order : rawOrders) {

            try {
                new FoodOrder(order[0], order[1]);
                valid++;
            }
            catch(Exception e) {
                rejected++;
            }
        }

        System.out.println(
                "Valid: " + valid +
                " | Rejected: " + rejected);
    }
}

public class GhostOrderValidator {

    public static void main(String[] args) {

        String[][] orders = {
                {"Ravi","Paneer Butter Masala"},
                {"","Chole Bhature"},
                {"Meera"," "},
                {"Divya","Veg Biryani"}
        };

        FoodOrder.processBatch(orders);
    }
}