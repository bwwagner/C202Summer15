/*
 *  C202/I211 Summer 2015
 *  Lab6.java
 *  Written By   : Branden Wagner and Tim McEndoo
 *  Date Written : 07/04/15
 *  Purpose      : Program 4 and Lab 6                 
 *                 Custom class for a singly-linked list. 
 */
package program4;

/**
 *
 * @author Branden Wagner
 * @param <E>
 */
public class MyLinkedList<E> extends MyAbstractList<E> {

    private Node<E> head, tail;

    /**
     * Create a default list
     */
    public MyLinkedList() {
    }

    /**
     * Create a list from an array of objects
     * @param objects generic type to be 
     */
    public MyLinkedList(E[] objects) {
        super(objects);
    }

    /**
     * Return the head element in the list
     * @return the generic object in the first position of the list
     */
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    /**
     * Return the last element in the list
     * @return the generic object in the last position of the list
     */
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    /**
     * Add an element to the beginning of the list
     * @param e generic object to be added to the first position of the list
     */
    public void addFirst(E e) {
        Node<E> newNode = new Node<E>(e); // Create a new node
        newNode.next = head; // link the new node with the head
        head = newNode; // head points to the new node
        size++; // Increase list size

        if (tail == null) // the new node is the only node in list
        {
            tail = head;
        }

    }

    /**
     * Add an element to the end of the list
     * @param e generic object to be added to the last position of the list
     */
    public void addLast(E e) {
        Node<E> newNode = new Node<E>(e); // Create a new for element e
        if (tail == null) {
            head = tail = newNode; // The new node is the only node in list
        } else {
            tail.next = newNode; // Link the new with the last node
            tail = tail.next; // tail now points to the last node
        }
        size++; // Increase size
    }

    /**
     * Add a new element at the specified index in this list The index of the
     * head element is 0
     * @param index int index of the position to be changed
     * @param e generic object to be added to the specified position in the list
     */
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<E>(e);
            (current.next).next = temp;
            size++;
        }

    }

    /**
     * Remove the head node and return the object that is contained in the
     * removed node.
     * @return generic object contained by the node which was removed
     */
    public E removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node<E> temp = head;
            head = head.next;
            size--;
            if (head == null) {
                tail = null;
            }
            return temp.element;
        }
    }

    /**
     * Remove the last node and return the object that is contained in the
     * removed node.
     * @return generic object contained by the node which was removed
     */
    public E removeLast() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        } else {
            Node<E> current = head;
            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }
            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    /**
     * Remove the element at the specified position in this list. Return the
     * element that was removed from the list.
     * @param index int index of the node to be removed from the list
     * @return generic object contained by the node which was removed
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }
            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }

    /**
     * Override toString() to return elements in the list
     * @return String representation of all elements currently in the list
     */
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            } else {
                result.append("]"); // Insert the closing ] in the string
            }
        }
        return result.toString();
    }

    /**
     * Clear the list
     */
    public void clear() {
        head = tail = null;
    }

    /**
     * Replace the element at the specified position in this list with the
     * specified element and returns the new set.
     * @param index int index in the list to be replaced
     * @param e generic object to replace the data at index
     * @return 
     */
    public E set(int index, E e) {
        Node<E> temp = head;
        if (index < 0 || index >= size) {
            return null;
        } else if (index == 0) {
            head.element = e;
            return temp.element;
        } else if (index == size - 1) {
            temp = tail;
            tail.element = e;
            return temp.element;
        } else {
            Node<E> previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }
            temp = previous;
            previous.element = e;
            return temp.element;
        }
    }//set    

    /**
     * Return the index of the last matching element in this list Return -1 if
     * no match.
     * @param e generic object to be found in the list
     * @return int index of the last occurence of the object in the data structure
     */
    public int lastIndexOf(E e) {
        int index = 0;
        int lastIndex = -1;
        Node<E> previous = head;
        while (previous != null) {
            if (previous.element.equals(e)) {
                lastIndex = index;
            }
            index++;
            previous = previous.next;
        }//while
        return lastIndex;
    }//lastIndexOf

    /**
     * Return the index of the first matching element in this list. Return -1 if
     * no match.
     * @param e generic object to be found in the list
     * @return int index of the first match within the list
     */
    public int indexOf(E e) {
        int index = 0;

        if (head.element.equals(e)) {
            return index;
        } else if (tail.element.equals(e)) {
            return size - 1;
        } else {
            Node<E> previous = head;
            for (int i = 1; i < size; i++) {
                index++;
                previous = previous.next;
                if (previous.element.equals(e)) {
                    return index;
                }
            }//for
        }//else
        return -1;
    }//indexOf

    /**
     * Return the element from this list at the specified index
     * @param index index of the list with data to be returned
     * @return the element from this list at the specified index
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else if (index == 0) {
            return head.element;
        } else if (index == size - 1) {
            return tail.element;
        } else {
            Node<E> previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }//for
            return previous.element;
        }//else
    }//get

    /**
     * Return true if this list contains the element
     * @param e generic object to be found in the list
     * @return true if this list contains the element, otherwise false
     */
    public boolean contains(E e) {
        Node<E> previous = head;
        for (int i = 1; i < size; i++) {
            if (previous.element.equals(e)) {
                return true;
            }
            previous = previous.next;
        }//for
        return false;
    }//contains

    /**
     *
     * @param e generic object to be found in the list
     * @return long number of comparisons made to find the object
     */
    public long containsCount(E e) {
        Node<E> previous = head;
        long count = 0;
        while (previous!=null) {
            count++;
            if (previous.element.equals(e)) {
                return count;
            }
            previous = previous.next;
        }//while
        return count;
    }//contains    

    private static class Node<E> {

        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }
}
/*
====*OUTPUT*====
run:
(1) [America]
(2) [Canada, America]
(3) [Canada, America, Russia]
(4) [Canada, America, Russia, France]
(5) [Canada, America, Germany, Russia, France]
(6) [Canada, America, Germany, Russia, France, Norway]
(7) [Poland, Canada, America, Germany, Russia, France, Norway]
(8) [Canada, America, Germany, Russia, France, Norway]
(9) [Canada, America, Russia, France, Norway]
(10) [Canada, America, Russia, France]
(11) The list does not contain Germany
(12) Invalid position
(13) The list element Franceis at position 3
(14) [India, Canada, America, Russia, France]
(15) [India, Canada, America, Russia, France, America]
(16) The list element America occurs last at 5
(17) [India, Canada, America, Russia, France, China]
BUILD SUCCESSFUL (total time: 0 seconds)

====*OUTPUT*====
*/
