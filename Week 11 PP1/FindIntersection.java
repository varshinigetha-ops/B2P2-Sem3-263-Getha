class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class FindIntersection {

    public static Node findIntersection(Node a, Node b) {
        int lenA = 0;
        int lenB = 0;

        Node tempA = a;
        Node tempB = b;

        while (tempA != null) {
            lenA++;
            tempA = tempA.next;
        }

        while (tempB != null) {
            lenB++;
            tempB = tempB.next;
        }

        tempA = a;
        tempB = b;

        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; i++) {
                tempA = tempA.next;
            }
        } else {
            for (int i = 0; i < lenB - lenA; i++) {
                tempB = tempB.next;
            }
        }

        while (tempA != tempB) {
            tempA = tempA.next;
            tempB = tempB.next;
        }

        return tempA;
    }

    public static void main(String[] args) {

        // Shared part of both lists
        Node common = new Node(8);
        common.next = new Node(4);
        common.next.next = new Node(5);

        // First list: 4 -> 1 -> 8 -> 4 -> 5
        Node a = new Node(4);
        a.next = new Node(1);
        a.next.next = common;

        // Second list: 5 -> 6 -> 1 -> 8 -> 4 -> 5
        Node b = new Node(5);
        b.next = new Node(6);
        b.next.next = new Node(1);
        b.next.next.next = common;

        Node result = findIntersection(a, b);

        if (result != null) {
            System.out.println("Node with value " + result.data);
        } else {
            System.out.println("null");
        }
    }
}