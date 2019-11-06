package DataStructure;

import DataTypes.Person;
import Exceptions.IDNotFoundException;

import java.time.LocalDate;

/**
 * Handles Priority and other functionality to the doubleLinkedList
 * This is a singleton however is not thread safe.
 */
public class ImmigrationQueue {

    private static int nextID = 0;
    private static ImmigrationQueue single_instance = null;
    private DoubleLinkedList list;

    public ImmigrationQueue() {
        this.list = new DoubleLinkedList();
    }

    public static int getNextID() {
        nextID++;
        return nextID;
    }

    /**
     * Insert a Person into the list at the correct position corresponding to their Priority Level
     *
     * @param person person The person to be inserted into the list
     * @return Returns the ID of the person in the queue this may not reflect their index in the queue.
     */
    public int addPerson(Person person) {
        //Loop through util lower prority is found then insert at the index

        DoubleLinkedList.Iterator itr = list.iterator();
        int index = 0;
        while (itr.hasNext()) {
            if (itr.next().getPriorityLevel().value() < person.getPriorityLevel().value()) {
                break;
            }
            index++;
        }
        list.add(index, person);
        return person.getID();
    }

    /**
     * Get a person's information with his/her given index
     *
     * @param ID The index of the Person given when first added to the queue.
     * @return the person with the given ID
     */
    public Person get(int ID) {
        for (Person person : (Iterable<Person>) list) {
            if (person.getID() == ID) return person;
        }
        return null;
    }

    /**
     * Remove Person for queue that has the given ID
     *
     * @param ID The ID of the person to be removed from list
     * @return Returns true if element was found and deleted False otherwise
     */
    public boolean removePerson(int ID) {
        DoubleLinkedList.Iterator itr = list.iterator();
        int index = 0;
        while (itr.hasNext()) {
            if (itr.next().getID() == ID) {
                list.remove(index);
                return true;
            }
            index++;
        }
        return false;
    }

    /**
     * @param amount Amount of Person's to be removed from end of list
     */
    public void removeFromEnd(int amount) {
        for (int i = 0; i < amount; i++) {
            list.removeLast();
        }
    }

    public boolean updatePersonFname(int ID, String fname) {
        try {
            list.get(getIndexThroughID(ID)).setFname(fname);
            return true;
        } catch (IDNotFoundException e) {
            return false;
        }
    }

    public boolean updatePersonLname(int ID, String lname) {
        try {
            list.get(getIndexThroughID(ID)).setLname(lname);
            return true;
        } catch (IDNotFoundException e) {
            return false;
        }
    }

    public boolean updatePersonDateOfArrival(int ID, LocalDate dateOfArrival) {
        try {
            list.get(getIndexThroughID(ID)).setDateOfArrival(dateOfArrival);
            return true;
        } catch (IDNotFoundException e) {
            return false;
        }
    }

    public boolean updatePersonPassportNumber(int ID, String passportNum) {
        try {
            list.get(getIndexThroughID(ID)).setPassportNum(passportNum);
            return true;
        } catch (IDNotFoundException e) {
            return false;
        }
    }

    /**
     * Gets the next Person and removes them from the list
     *
     * @return The person at head of the queue
     */
    public Person next() {
        Person next = list.getFirst();
        list.removeFirst();
        return next;
    }

    public static ImmigrationQueue getInstance() {
        if (single_instance == null) {
            single_instance = new ImmigrationQueue();
        }
        return single_instance;
    }

    /**
     * Gets the index of the person in the list with the given ID
     *
     * @param ID The ID of the person of which the index is needed
     * @return returns the Index of the person in the list
     * @throws IDNotFoundException Will be throw if the Person can not be found in the list
     */
    private int getIndexThroughID(int ID) throws IDNotFoundException {
        DoubleLinkedList.Iterator itr = list.iterator();
        int index = 0;
        while (itr.hasNext()) {
            if (itr.next().getID() == ID) {
                return index;
            }
            index++;
        }
        throw new IDNotFoundException();
    }

    /**
     * Gets the next person but does not remove from queue
     *
     * @return The next person in the queue
     */
    public Person getNext() {
        return list.getFirst();
    }


}
