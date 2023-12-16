package lib;

// Linked List
public class LL<T0> {
    public Node head;

    public LL(Node head) {
        this.head = head;
    }

    public void add(T0 value) {
        if(head == null) {
            head = new Node(value, null);
        } else {
            Node curr = head;
            while(curr.next != null) { curr = curr.next; }
            curr.next = new Node(value, null);
        }
    }

    public class Node {
        public Node next;
        public T0 data;

        public Node(T0 data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
