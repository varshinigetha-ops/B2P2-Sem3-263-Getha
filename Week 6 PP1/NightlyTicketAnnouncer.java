class EventTicket {

    protected double balance;

    public EventTicket(double balance) {
        this.balance = balance;
    }

    public String printTicket() {
        return "Standard | Balance: "
                + balance;
    }
}

class WorkshopTicket extends EventTicket {

    private String track;

    public WorkshopTicket(double balance,
                          String track) {
        super(balance);
        this.track = track;
    }

    public String getTrack() {
        return track;
    }

    @Override
    public String printTicket() {
        return "Workshop | Track: "
                + track
                + " | Balance: "
                + balance;
    }
}

public class NightlyTicketAnnouncer {

    static String batchPrint(EventTicket[] tickets) {

        StringBuilder sb =
                new StringBuilder();

        for (EventTicket t : tickets) {

            sb.append(
                    t.printTicket()
            );

            if (t instanceof WorkshopTicket) {

                WorkshopTicket w =
                        (WorkshopTicket) t;

                sb.append(
                        " [Track via downcast: "
                                + w.getTrack()
                                + "]"
                );
            }

            sb.append(" | ");
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        EventTicket[] arr = {
                new EventTicket(500),
                new WorkshopTicket(
                        1200,
                        "AI/ML")
        };

        System.out.println(
                batchPrint(arr)
        );
    }
}