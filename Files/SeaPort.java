/**
 * SeaPort.java - Class for <code>SeaPort</code> objects
 * Begun 01/15/18
 * @author Andrew Eissen
 */

//package project1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents ports in the world, containing a series of <code>Thing</code> subclass
 * <code>ArrayList</code>s that hold docks, queued ships, all ships, and persons present at the
 * location. No special methods are contained apart from the setters/getters and an overridden
 * <code>toString()</code> method.
 * <br />
 * <br />
 * Class extends <code>Thing</code>
 * @see project1.Thing
 * @author Andrew Eissen
 */
final class SeaPort extends Thing {

    // Rubric-required <code>ArrayList</code>s
    private ArrayList<Dock> docks;
    private ArrayList<Ship> que;
    private ArrayList<Ship> ships;
    private ArrayList<Person> persons;

    /**
     * Parameterized constructor
     * @param scannerContents - Contents of <code>.txt</code> file
     */
    protected SeaPort(Scanner scannerContents) {
        super(scannerContents);
        this.setDocks(new ArrayList<>());
        this.setQue(new ArrayList<>());
        this.setShips(new ArrayList<>());
        this.setPersons(new ArrayList<>());
    }

    // Setters

    /**
     * Setter for <code>docks</code>
     * @param docks <code>ArrayList</code>
     * @return void
     */
    private void setDocks(ArrayList<Dock> docks) {
        this.docks = docks;
    }

    /**
     * Setter for <code>que</code>
     * @param que <code>ArrayList</code>
     * @return void
     */
    private void setQue(ArrayList<Ship> que) {
        this.que = que;
    }

    /**
     * Setter for <code>ships</code>
     * @param ships <code>ArrayList</code>
     * @return void
     */
    private void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    /**
     * Setter for <code>persons</code>
     * @param persons <code>ArrayList</code>
     * @return void
     */
    private void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    // Getters

    /**
     * Getter for <code>docks</code>
     * @return <code>this.dock</code>
     */
    protected ArrayList<Dock> getDocks() {
        return this.docks;
    }

    /**
     * Getter for <code>que</code>
     * @return <code>this.que</code>
     */
    protected ArrayList<Ship> getQue() {
        return this.que;
    }

    /**
     * Getter for <code>ships</code>
     * @return <code>this.ships</code>
     */
    protected ArrayList<Ship> getShips() {
        return this.ships;
    }

    /**
     * Getter for <code>persons</code>
     * @return <code>this.persons</code>
     */
    protected ArrayList<Person> getPersons() {
        return this.persons;
    }

    // Overridden methods

    /**
     * @inheritdoc
     * @return stringOutput <code>String</code>
     */
    @Override
    public String toString() {
        String stringOutput;

        // A near-identical implementation of the method as denoted in the rubric
        stringOutput = "\n\nSeaPort: " + super.toString();
        for (Dock dock: this.getDocks()) {
            stringOutput += "\n> " + dock.toString();
        }

        stringOutput += "\n\n --- List of all ships in que:";
        for (Ship shipQue: this.getQue()) {
            stringOutput += "\n> " + shipQue.toString();
        }

        // Since the above output displays ship-related details, this one is just a quick summary
        stringOutput += "\n\n --- List of all ships:";
        for (Ship shipAll: this.getShips()) {
            stringOutput += "\n> " + shipAll.getName() + " " + shipAll.getIndex() + " ("
                + shipAll.getClass().getSimpleName() + ")";
        }

        stringOutput += "\n\n --- List of all persons:";
        for (Person person: this.getPersons()) {
            stringOutput += "\n> " + person.toString();
        }

        return stringOutput;
    }
}