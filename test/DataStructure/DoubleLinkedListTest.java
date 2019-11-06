package DataStructure;

import DataTypes.Person;
import DataTypes.Priority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * For testing if the functionality of the underlining Data Structure a double linked List works correctly and under stress.
 */
class DoubleLinkedListTest {

    private DoubleLinkedList doubleLinkedList;

    @BeforeEach
    void BeforeEach() {
        doubleLinkedList = new DoubleLinkedList();
        Calendar cal = Calendar.getInstance();
        doubleLinkedList.add(0, new Person("ada", "sever", LocalDate.of(2017, Month.APRIL, 12), "123", Priority.LOW));
        doubleLinkedList.add(1, new Person("second", "sever", LocalDate.of(2017, Month.APRIL, 12), "123", Priority.LOW));
        doubleLinkedList.add(2, new Person("third", "sever", LocalDate.of(2017, Month.APRIL, 12), "123", Priority.MEDIUM));
        doubleLinkedList.add(0, new Person("foruth", "sever", LocalDate.of(2017, Month.APRIL, 12), "123", Priority.HIGH));
        doubleLinkedList.add(4, new Person("fifth", "sever", LocalDate.of(2017, Month.APRIL, 12), "123", Priority.HIGH));

    }

    /**
     * Ensure a element can be added
     */
    @Test
    void addTest() {
        Iterator<Person> itr = doubleLinkedList.iterator();
        assertEquals(doubleLinkedList.getSize(), 5);
        Person current = itr.next();
    }

    /**
     * Ensure a element can be add to a doubleLinkedList at a given index
     */
    @Test
    void elementAddedAtIndex() {
        doubleLinkedList.add(0, new Person("added", "sever", LocalDate.of(2017, Month.APRIL, 12), "123", Priority.LOW));
        Person first = doubleLinkedList.getFirst();
        assertEquals(first.getFname(), "added");
        doubleLinkedList.add(2, new Person("third", "sever", LocalDate.of(2017, Month.APRIL, 12), "123", Priority.LOW));
        Person third = doubleLinkedList.get(2);
        assertEquals(third.getFname(), "third");
    }

    /**
     * Ensures that element can be deleted
     */
    @Test
    void elementRemove() {
        int indexToTest = 0;
        Person personAt0 = doubleLinkedList.get(indexToTest);
        doubleLinkedList.remove(indexToTest);
        assertNotEquals(personAt0, doubleLinkedList.get(indexToTest));
    }

    /**
     * Ensures when adding a element at a given index pushes the old element back.
     */
    @Test
    void elementReIndexAfterElementAdded() {
        Person oldFirst = doubleLinkedList.getFirst();
        doubleLinkedList.add(0, new Person("added", "sever", LocalDate.of(2017, Month.APRIL, 12), "123", Priority.LOW));
        Person secondElement = doubleLinkedList.get(1);
        assertEquals(secondElement, oldFirst);
    }

}