class EventTicket {
    protected String attendeeId;
    protected double basePrice;
    protected double paidAmount;

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().isEmpty()
                || attendeeId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid Attendee ID");
        }

        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
        this.paidAmount = 0;
    }

    public void pay(double amount) {
        paidAmount += amount;
    }

    public double getBalanceDue() {
        return basePrice - paidAmount;
    }

    public static String registerBatch(String[] attendeeIds, double basePrice) {
        int registered = 0;
        int rejected = 0;

        for (String id : attendeeIds) {
            try {
                new EventTicket(id, basePrice);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(String attendeeId,
                          double basePrice,
                          String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }
}

public class TicketHierarchyFoundation {
    public static void main(String[] args) {

        WorkshopTicket w =
                new WorkshopTicket("STU2", 1200, "AI/ML");

        w.pay(500);

        System.out.println(w.getBalanceDue());

        String[] ids = {
                "STU1",
                "ST1",
                "STU2",
                " ",
                "STU3"
        };

        System.out.println(
                EventTicket.registerBatch(ids, 500)
        );
    }
}