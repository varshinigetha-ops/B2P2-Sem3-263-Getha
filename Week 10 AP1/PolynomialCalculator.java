class Node {
    int coefficient;
    int exponent;
    Node next;

    Node(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.next = null;
    }
}

public class PolynomialCalculator {

    public static Node addPolynomials(Node p, Node q) {
        Node dummy = new Node(0, 0);
        Node tail = dummy;

        while (p != null && q != null) {
            if (p.exponent > q.exponent) {
                tail.next = new Node(p.coefficient, p.exponent);
                p = p.next;
            } else if (p.exponent < q.exponent) {
                tail.next = new Node(q.coefficient, q.exponent);
                q = q.next;
            } else {
                int sum = p.coefficient + q.coefficient;

                if (sum != 0) {
                    tail.next = new Node(sum, p.exponent);
                }

                p = p.next;
                q = q.next;

                if (sum == 0) {
                    continue;
                }
            }

            if (tail.next != null) {
                tail = tail.next;
            }
        }

        while (p != null) {
            tail.next = new Node(p.coefficient, p.exponent);
            tail = tail.next;
            p = p.next;
        }

        while (q != null) {
            tail.next = new Node(q.coefficient, q.exponent);
            tail = tail.next;
            q = q.next;
        }

        return dummy.next;
    }

    public static void printPolynomial(Node head) {
        boolean first = true;

        while (head != null) {
            if (!first) {
                System.out.print(head.coefficient > 0 ? " + " : " - ");
            } else if (head.coefficient < 0) {
                System.out.print("-");
            }

            int c = Math.abs(head.coefficient);

            if (head.exponent == 0) {
                System.out.print(c);
            } else {
                if (c != 1) {
                    System.out.print(c);
                }
                System.out.print("x");

                if (head.exponent != 1) {
                    System.out.print("^" + head.exponent);
                }
            }

            first = false;
            head = head.next;
        }

        if (first) {
            System.out.print("0");
        }

        System.out.println();
    }
}