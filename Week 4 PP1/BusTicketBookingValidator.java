import java.util.HashSet;

class BusTicket {
    private String passengerName;
    private String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {
        if (passengerName == null || destination == null)
            throw new IllegalArgumentException("Null value not allowed");

        passengerName = passengerName.trim();
        destination = destination.trim();

        if (passengerName.isEmpty() || destination.isEmpty())
            throw new IllegalArgumentException("Blank value not allowed");

        for (char ch : passengerName.toCharArray()) {
            if (!Character.isLetter(ch) && ch != ' ')
                throw new IllegalArgumentException("Invalid passenger name");
        }

        for (char ch : destination.toCharArray()) {
            if (!Character.isLetter(ch) && ch != ' ')
                throw new IllegalArgumentException("Invalid destination");
        }

        this.passengerName = passengerName;
        this.destination = destination;
        this.checkedIn = false;
    }

    public void markCheckedIn() {
        if (checkedIn) {
            System.out.println("Already checked in");
        } else {
            checkedIn = true;
            System.out.println("Checked in successfully");
        }
    }

    public String getKey() {
        return passengerName.toLowerCase() + "|" + destination.toLowerCase();
    }

    public static void processBatch(String[][] rawBookings) {
        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        HashSet<String> accepted = new HashSet<>();

        for (String[] booking : rawBookings) {
            try {
                BusTicket ticket =
                        new BusTicket(booking[0], booking[1]);

                if (accepted.contains(ticket.getKey())) {
                    duplicates++;
                } else {
                    accepted.add(ticket.getKey());
                    valid++;
                }

            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid
                + " | Rejected: " + rejected
                + " | Duplicates skipped: " + duplicates);
    }
}

public class BusTicketBookingValidator {
    public static void main(String[] args) {

        String[][] bookings = {
                {"Divya", "Chennai"},
                {"", "Bangalore"},
                {"Ravi123", "Pune"},
                {"Divya", "Chennai"},
                {" ", " "}
        };

        BusTicket.processBatch(bookings);
    }
}
