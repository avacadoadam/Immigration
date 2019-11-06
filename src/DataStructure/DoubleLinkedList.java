package DataStructure;

import DataTypes.Person;

import java.util.NoSuchElementException;


public class DoubleLinkedList implements Iterable {

    private Node head;
    private Node tail;
    private int size;


    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    /**
     * Remove Node at given Index
     *
     * @param index The index of the node to be removed
     */
    public void remove(int index) {
        if (size == 0) return;
        if (isInbound(index)) throw new IndexOutOfBoundsException();
        Node current = getNodeAtIndex(index);
        if (size == 1) {
            tail = head = null;
        } else if (current == tail) {
            tail = current.prev;
        } else if (current == head) {
            head = current.next;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        size--;
    }

    public void removeLast() {
        remove(size);
    }

    public void removeFirst() {
        remove(0);
    }

    /**
     * @param index  The index where the person should be added
     * @param person The Person's Data
     */
    public void add(int index, Person person) {
        if (isInbound(index)) throw new IndexOutOfBoundsException();
        if (isEmpty()) {
            Node node = new Node(null, null, person);
            tail = head = node;
        } else if (index == 0) {         //set to first
            Node node = new Node(head, null, person);           //set prev of head
            head.prev = node;                                       //new node to point to old head
            head = node;                                            //set head to point to new node
        } else if (index == size) {      //set to last
            Node node = new Node(null, tail, person);           //New Node that points back to old tail
            tail.next = node;                                        //Old tail point to new node
            tail = node;                                             //set tail to new Node
        } else {
            Node current = getNodeAtIndex(index);
            current.next = new Node(current.next, current, person);
        }
        size++;
    }

    /**
     * Used to get the person at the 0 index of the list
     *
     * @return
     */
    public Person getFirst() {
        if (head == null) return null;
        return head.data;
    }

    public Person get(int index) {
        if (getNodeAtIndex(index) != null)
            return getNodeAtIndex(index).data;
        else return null;

    }

    /**
     * Will return the node at the index given in the LinkedList
     *
     * @param index The index
     * @return node at given index or null if does not exist
     */
    private Node getNodeAtIndex(int index) {
        if (index < 0 || index > size) return null;
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * Checks if a index is within the bounds of List size
     *
     * @param index The Index to check
     * @return True if Index is valid in List, False otherwise
     */
    private boolean isInbound(int index) {
        return index < 0 || index > size;
    }

    private boolean isEmpty() {
        return head == null && tail == null;
    }


    @Override
    public Iterator iterator() {
        return new Iterator();
    }

    class Iterator implements java.util.Iterator<Person> {

        Node current = null;

        @Override
        public boolean hasNext() {
            if (current == null && head != null) {
                return true;
            } else if (current != null) {
                return current.next != null;
            }

            return false;
        }

        @Override
        public Person next() {
            if (current == null && head != null) {
                current = head;
                return head.data;
            } else if (current != null) {
                Node nextNode = current.next;
                current = current.next;
                return nextNode.data;
            }
            throw new NoSuchElementException();

        }
    }

    /**
     * The node that will be used by the doubleLinkedList
     * This should only be accessed by the doubleLinkedList
     */
    private class Node {

        private Node next;
        private Node prev;
        private Person data;

        public Node(Node next, Node prev, Person data) {
            this.next = next;
            this.prev = prev;
            this.data = data;
        }


    }
}
