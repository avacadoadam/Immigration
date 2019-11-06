package DataStructure;

import DataTypes.Person;
import DataTypes.Priority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ImmigrationQueueTest {


    private ImmigrationQueue queue;

    @BeforeEach
    void BeforeEach() {
        queue = new ImmigrationQueue();

    }

    /**
     * A test to ensure that a person with high priority is added to the queue after low and medium But before any previous Highs
     * To pass Person with name "High" should be first
     * Also "HighTest" should be second after the high that was added previous but before the Low and Medium priority Persons
     */
    @Test
    void addPersonHighPriorityTest() {

        Person HIGH = new Person("High", "high", LocalDate.now(), "123", Priority.HIGH);
        Person HIGH_TEST = new Person("HighTest", "highTest", LocalDate.now(), "123", Priority.HIGH);

        queue.addPerson(new Person("low1", "low1", LocalDate.now(), "123", Priority.LOW));
        queue.addPerson(new Person("low2", "low2", LocalDate.now(), "123", Priority.LOW));
        queue.addPerson(new Person("low2", "low2", LocalDate.now(), "123", Priority.MEDIUM));
        queue.addPerson(HIGH);
        queue.addPerson(HIGH_TEST);
        assertEquals(HIGH, queue.next());
        assertEquals(HIGH_TEST, queue.next());
    }

    /**
     * Ensure that the queue pops a person after next() method.
     * And returns the correct Person
     */
    @Test
    void ensurePersonGetsPopedAfterNext() {
        Person toBePoped1 = new Person("toBePoped1", "toBePoped1", LocalDate.now(), "123", Priority.LOW);
        queue.addPerson(toBePoped1);
        queue.addPerson(new Person("toBePoped2", "toBePoped2", LocalDate.now(), "123", Priority.LOW));
        Person next = queue.next();
        assertEquals(next, toBePoped1);
    }

    /**
     * Ensures if next is called on a empty list that null is returned
     */
    @Test
    void nextOnAEmptyQueue() {
        assertNull(queue.next());
    }

    /**
     * Ensures that medium comes before all previous mediums and highs but after all lows
     */
    @Test
    void addPersonMediumPriorityTest() {
        Person HIGH = new Person("High", "high", LocalDate.now(), "123", Priority.HIGH);
        Person MEDIUM = new Person("low2", "low2", LocalDate.now(), "123", Priority.MEDIUM);
        Person MEDIUM_TEST = new Person("low2", "low2", LocalDate.now(), "123", Priority.MEDIUM);
        Person LOW = new Person("low2", "low2", LocalDate.now(), "123", Priority.LOW);
        queue.addPerson(LOW);
        queue.addPerson(HIGH);
        queue.addPerson(MEDIUM);
        queue.addPerson(MEDIUM_TEST);
        queue.next();
        assertEquals(queue.next(), MEDIUM);
        assertEquals(queue.next(), MEDIUM_TEST);
    }

    /**
     * Ensure that Low always goes to end of queue
     */
    @Test
    void addPersonLowPriorityTest() {
        Person HIGH = new Person("High", "high", LocalDate.now(), "123", Priority.HIGH);
        Person MEDIUM = new Person("low2", "low2", LocalDate.now(), "123", Priority.MEDIUM);
        Person MEDIUM_TEST = new Person("low2", "low2", LocalDate.now(), "123", Priority.MEDIUM);
        Person LOW = new Person("low2", "low2", LocalDate.now(), "123", Priority.LOW);
        queue.addPerson(LOW);
        queue.addPerson(HIGH);
        queue.addPerson(MEDIUM);
        queue.addPerson(MEDIUM_TEST);
        queue.next();
        queue.next();
        queue.next();
        assertEquals(queue.next(), LOW);
    }

    /**
     * Ensure a Person can be added
     */
    @Test
    void addPersonIDTest() {
        Person TEST_PERSON = new Person("High", "high", LocalDate.now(), "123", Priority.HIGH);
        queue.addPerson(TEST_PERSON);
        assertEquals(queue.next(), TEST_PERSON);
    }

    /**
     * Ensures a person can be removed from the queue
     */
    @Test
    void removePersonTest() {
        Person TEST_PERSON = new Person("High", "high", LocalDate.now(), "123", Priority.HIGH);
        int index = queue.addPerson(TEST_PERSON);
        queue.removePerson(index);
        assertNull(queue.next());
    }

    /**
     * False is returned if invalid ID was given
     */
    @Test
    void removeWithInvalidIndex() {
        assertFalse(queue.removePerson(234234));
    }

    /**
     * Ensure that the correct ID from the global counter in main is correctly returned from the getNextID() method
     */
    @Test
    void ensureIDReturnedFromAddIsGlobalCounter() {
        Person TEST_PERSON = new Person("High", "high", LocalDate.now(), "123", Priority.HIGH);
        int index = queue.addPerson(TEST_PERSON);
        assertEquals(index, ImmigrationQueue.getNextID() - 1);

    }

    @Test
    void updatePersonFnameTest() {
        Person TEST_PERSON = new Person("High", "high", LocalDate.now(), "123", Priority.HIGH);
        int index = queue.addPerson(TEST_PERSON);
        assertTrue(queue.updatePersonFname(index, "updated"));
    }

    @Test
    void updatePersonLnameTest() {
        Person TEST_PERSON = new Person("High", "high", LocalDate.now(), "123", Priority.HIGH);
        int index = queue.addPerson(TEST_PERSON);
        assertTrue(queue.updatePersonLname(index, "updated"));
    }

    @Test
    void updatePersonDateOfArrivalTest() {
        Person TEST_PERSON = new Person("High", "high", LocalDate.now(), "123", Priority.HIGH);
        int index = queue.addPerson(TEST_PERSON);
        assertTrue(queue.updatePersonDateOfArrival(index, LocalDate.now()));
    }

    @Test
    void updatePersonPassportNum() {
        Person TEST_PERSON = new Person("High", "high", LocalDate.now(), "123", Priority.HIGH);
        int index = queue.addPerson(TEST_PERSON);
        assertTrue(queue.updatePersonPassportNumber(index, "updatePassport"));
    }

    @Test
    void getCurrentPersonTest() {
        Person TEST_PERSON = new Person("High", "high", LocalDate.now(), "123", Priority.HIGH);
        int index = queue.addPerson(TEST_PERSON);
        assertEquals(TEST_PERSON, queue.next());
    }


    /**
     * Ensures that remove Amount from the end of the queue works
     */
    @Test
    void removeFromEndTest() {
        Person TEST_PERSON = new Person("High", "high", LocalDate.now(), "123", Priority.HIGH);
        Person TEST_PERSON1 = new Person("High", "high", LocalDate.now(), "123", Priority.HIGH);
        Person TEST_PERSON2 = new Person("High", "high", LocalDate.now(), "123", Priority.HIGH);
        queue.removeFromEnd(3);
        assertNull(queue.next());
    }

    /**
     * Ensures that removing amount from the end of the queue which is greater then size still returns null and does not throw error
     */
    @Test
    void removeFromEndGreaterThenSizeTest() {
        Person TEST_PERSON = new Person("High", "high", LocalDate.now(), "123", Priority.HIGH);
        Person TEST_PERSON1 = new Person("High", "high", LocalDate.now(), "123", Priority.HIGH);
        queue.removeFromEnd(2);
        assertNull(queue.next());
    }


}