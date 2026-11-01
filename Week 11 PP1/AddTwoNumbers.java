class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class AddTwoNumbers {

    public static Node addNumbers(Node a, Node b) {
        Node dummy = new Node(0);
        Node tail = dummy;

        int carry = 0;

        while (a != null || b != null || carry != 0) {
            int sum = carry;

            if (a != null) {
                sum += a.data;
                a = a.next;
            }

            if (b != null) {
                sum += b.data;
                b = b.next;
            }

            carry = sum / 10;

            tail.next = new Node(sum % 10);
            tail = tail.next;
        }

        return dummy.next;
    }

    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data);

            if (head.next != null) {
                System.out.print(" -> ");
            }

            head = head.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Node a = new Node(2);
        a.next = new Node(4);
        a.next.next = new Node(3);

        Node b = new Node(5);
        b.next = new Node(6);
        b.next.next = new Node(4);

        Node result = addNumbers(a, b);

        printList(result);
    }
}