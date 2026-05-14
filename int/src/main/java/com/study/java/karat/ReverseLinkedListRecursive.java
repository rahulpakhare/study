package com.study.java.karat;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class ReverseLinkedListRecursive {

    public static ListNode reverseList(ListNode head) {
        // Base case: empty list OR single node list
        if (head == null || head.next == null) {
            return head;
        }

        // Reverse the rest of the list
        ListNode newHead = reverseList(head.next);

        // Flip the link:
        // head -> next becomes next -> head
        head.next.next = head;

        // Break the original link to avoid cycle
        head.next = null;

        return newHead;
    }

    // Helper: print linked list
    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" -> ");
            curr = curr.next;
        }
        System.out.println(" -> null");
    }

    public static void main(String[] args) {
        // Create: 1 -> 2 -> 3 -> 4 -> 5 -> null
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Original:");
        printList(head);

        head = reverseList(head);

        System.out.println("Reversed:");
        printList(head);
    }
}
