class EventTicket {
    protected String attendeeId;
    protected double basePrice;
    protected double paidAmount;

    public EventTicket(String attendeeId, double basePrice) {
        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
    }

    public double getBalanceDue() {
        return basePrice - paidAmount;
    }

    public String printTicket() {
        return "Standard Event Ticket | Balance Due: "
                + getBalanceDue();
    }
}

class WorkshopTicket extends EventTicket {
    protected String track;

    public WorkshopTicket(String attendeeId,
                          double basePrice,
                          String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }

    @Override
    public String printTicket() {
        return "Workshop Ticket | Track: "
                + track
                + " | Balance Due: "
                + getBalanceDue();
    }
}

class PremiumWorkshopTicket extends WorkshopTicket {
    private double kitFee;

    public PremiumWorkshopTicket(String attendeeId,
                                 double basePrice,
                                 String track,
                                 double kitFee) {
        super(attendeeId, basePrice, track);
        this.kitFee = kitFee;
    }

    @Override
    public String printTicket() {
        return "Premium Workshop Ticket | Track: "
                + track
                + " | Kit Fee: "
                + kitFee
                + " | Balance Due: "
                + getBalanceDue();
    }
}

class HackathonTicket extends EventTicket {
    private String teamName;

    public HackathonTicket(String attendeeId,
                           double basePrice,
                           String teamName) {
        super(attendeeId, basePrice);
        this.teamName = teamName;
    }

    @Override
    public String printTicket() {
        return "Hackathon Ticket | Team: "
                + teamName
                + " | Balance Due: "
                + getBalanceDue();
    }
}

public class TicketFamilyTree {

    static String classifyGeneration(EventTicket ticket) {

        if (ticket instanceof PremiumWorkshopTicket) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (ticket instanceof HackathonTicket) {
            return "Hierarchical sibling (independent branch)";
        }

        return "Base Ticket";
    }

    static double getTotalBalanceDue(EventTicket[] tickets) {

        double total = 0;

        for (EventTicket t : tickets) {
            total += t.getBalanceDue();
        }

        return total;
    }

    public static void main(String[] args) {

        EventTicket e =
                new EventTicket("STU1", 500);

        WorkshopTicket w =
                new WorkshopTicket("STU2",
                        1200,
                        "AI/ML");

        PremiumWorkshopTicket p =
                new PremiumWorkshopTicket(
                        "STU3",
                        2000,
                        "Cloud Native",
                        300);

        HackathonTicket h =
                new HackathonTicket(
                        "STU4",
                        800,
                        "Byte Force");

        System.out.println(e.printTicket());
        System.out.println(w.printTicket());
        System.out.println(p.printTicket());
        System.out.println(h.printTicket());

        System.out.println(classifyGeneration(p));
        System.out.println(classifyGeneration(h));

        EventTicket[] arr = {e, w, p, h};

        System.out.println(getTotalBalanceDue(arr));
    }
}
