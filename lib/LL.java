package lib;

// Linked List
public class LL<T0> {
    public Node head;
    public int size;

    public LL(Node head) {
        this.head = head;
        this.size = 1;
    }

    public void add(T0 value) {
        if(head == null) {
            head = new Node(value, null);
        } else {
            Node curr = head;
            while(curr.next != null) { curr = curr.next; }
            curr.next = new Node(value, null);
        }
        size++;
    }

    public boolean contains(T0 data) {
        return contains(data, head);
    }
    private boolean contains(T0 data, Node node) {
        if(node == null) { return false; }
        if(node.data.equals(data)) {
            return true;
        } else {
            return contains(data, node.next);
        }
    }

    @Override
    public int hashCode() {
        return head == null ? 0 : head.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if(other == this) { return true; }
        if(other == null || other instanceof LL) { return false; }
        LL<?> o = (LL<?>) other;
        if(o.head == null || this.head == null || o.head.data.getClass() != this.head.data.getClass()) { return false; }
        return o.head.equals(this.head);
    }

    @Override
    public String toString() {
        return (head == null ? "" : head.toString());
    }

    public class Node {
        public Node next;
        public T0 data;

        public Node(T0 data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public int hashCode() {
            return data.hashCode()*17 + (next == null ? 0 : next.hashCode()*31);
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object other) {
            if(other == this) { return true; }
            if(other == null || other.getClass() != this.getClass()) { return false; }
            Node o = (Node) other;
            if(o.data.getClass() != this.data.getClass() ||
               this.next != null && o.next == null ||
               this.next == null && o.next != null) { return false; }
            return this.data.equals((T0) o.data) && (this.next != null && o.next != null && o.next.equals(this.next));
        }

        @Override
        public String toString() {
            return data.toString() + (next == null ? "" : ", " + next.toString());
        }
    }
}
