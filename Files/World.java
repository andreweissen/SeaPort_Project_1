/**
 * World.java - Central class that stores/organizes all other <code>Thing</code>-based objects
 * Begun 01/15/18
 * @author Andrew Eissen
 */

//package project1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * This class represents the world as it exists per the contents of the user-selected
 * <code>.txt</code> file. It accepts <code>Scanner</code> data and sorts through it, assembling
 * proper instances of the applicable classes and moving them into the proper <code>ArrayList</code>
 * or field. It also hosts a global <code>allThings</code> <code>ArrayList</code> that contains
 * a complete listing of all <code>Thing</code>-based objects for ease of searching/sorting.
 * <br />
 * <br />
 * The class also contains a number of utility methods recommended by the rubric for ease in adding
 * appropriate objects to the chosen locations. Though
 * <a href="https://github.com/andreweissen">the author</a> believes that the use of a set of
 * <code>HashMap</code>s would have largely negated the need for such methods, they were included in
 * an attempt to remain true to the specifics of the rubric.
 * <br />
 * <br />
 * Class extends <code>Thing</code>
 * @see project1.Thing
 * @author Andrew Eissen
 */
final class World extends Thing {

    // Rubric-required fields
    private ArrayList<Thing> allThings;
    private ArrayList<SeaPort> ports;
    private PortTime time;

    /**
     * Parameterized constructor
     * @param scannerContents Content of the <code>.txt</code> file
     */
    protected World(Scanner scannerContents) {
        super(scannerContents);
        this.setAllThings(new ArrayList<>());
        this.setPorts(new ArrayList<>());
        this.process(scannerContents);
    }

    // Setters

    /**
     * Setter for <code>allThings</code>, the global <code>Thing</code> repository.
     * @param allThings <code>ArrayList</code>
     * @return void
     */
    private void setAllThings(ArrayList<Thing> allThings) {
        this.allThings = allThings;
    }

    /**
     * Setter for <code>ports</code>, the local <code>ArrayList</code> for <code>SeaPort</code>s.
     * @param ports <code>ArrayList</code>
     * @return void
     */
    private void setPorts(ArrayList<SeaPort> ports) {
        this.ports = ports;
    }

    /**
     * Setter for <code>time</code>, a yet unused variable that is not mentioned in the rubric.
     * @param time <code>PortTime</code>
     * @return void
     */
    private void setTime(PortTime time) {
        this.time = time;
    }

    // Getters

    /**
     * Getter for <code>allThings</code>, used often by <code>SeaPortProgram</code>.
     * @see project1.SeaPortProgram
     * @return <code>this.allThings</code>
     */
    protected ArrayList<Thing> getAllThings() {
        return this.allThings;
    }

    /**
     * Getter for <code>ports</code>
     * @return <code>this.ports</code>
     */
    protected ArrayList<SeaPort> getPorts() {
        return this.ports;
    }

    /**
     * Getter for <code>time</code>
     * @return <code>this.time</code>
     */
    protected PortTime getTime() {
        return this.time;
    }

    // Handlers

    /**
     * This is the main method of the <code>World</code> class, invoked after the definitions of the
     * <code>ArrayList</code>s <code>allThings</code> and <code>ports</code> in the body of the main
     * constructor.
     * <br />
     * <br />
     * As per the rubric, it breaks down the contents of the user-inputted <code>.txt</code> file
     * into individual lines representing specific <code>Thing</code>s in the world, building
     * appropriate objects of the given type and moving those into the proper locations as per the
     * organizational structure denoted on the first page of the Project 1 rubric. Blank lines are
     * skipped and beginning/ending whitespace is removed prior to evaluation. Each new object is
     * added to the global <code>allThings</code> listing and passed to an invoked utility handler
     * that assigns the object to the proper location.
     *
     * @param scannerContents Contents of the user-inputted <code>.txt</code> file
     * @return void
     */
    private void process(Scanner scannerContents) {

        // Assorted method fields
        String lineString;
        Scanner lineContents;

        while (scannerContents.hasNextLine()) {
            lineString = scannerContents.nextLine().trim(); // Remove spaces

            // Avoid evaluating any blank lines if exist
            if (lineString.length() == 0) {
                continue;
            }

            // Scanner for each line's contents
            lineContents = new Scanner(lineString);

            if (lineContents.hasNext()) {

                /**
                 * Builds <code>Thing</code> objects & stuff, passing them to the appropriate adder
                 * method. For ease of sorting later on, all <tt>Thing</tt>s are stuffed into an
                 * <code>ArrayList</code>, namely <code>allThings</code>. The program iterates over
                 * the structure in search of the proper input. It would be easier to use ye olde
                 * <code>HashMap</code>s but this is simpler and more in line with the ethos of the
                 * rubric.
                 */
                switch(lineContents.next().trim()) {
                    case "port":
                        SeaPort newSeaPort = new SeaPort(lineContents);
                        this.getAllThings().add(newSeaPort);
                        this.getPorts().add(newSeaPort);
                        break;
                    case "dock":
                        Dock newDock = new Dock(lineContents);
                        this.getAllThings().add(newDock);
                        this.addThingToList(newDock, "getDocks");
                        break;
                    case "pship":
                        PassengerShip newPassengerShip = new PassengerShip(lineContents);
                        this.getAllThings().add(newPassengerShip);
                        this.addShipToParent(newPassengerShip);
                        break;
                    case "cship":
                        CargoShip newCargoShip = new CargoShip(lineContents);
                        this.getAllThings().add(newCargoShip);
                        this.addShipToParent(newCargoShip);
                        break;
                    case "person":
                        Person newPerson = new Person(lineContents);
                        this.getAllThings().add(newPerson);
                        this.addThingToList(newPerson, "getPersons");
                        break;
                    case "job":
                        Job newJob = new Job(lineContents);
                        this.getAllThings().add(newJob);
                        this.addJobToShip(newJob);
                        break;
                    default: // Added because required by Google styleguide
                        break;
                }
            }
        }
    }

    /**
     * Generic method based on the example provided in the project design rubric. This method
     * accepts an <code>ArrayList</code> of <code>Thing</code> type objects and sorts through them
     * in search of the object with an index matching the input index. The use of this method was
     * initially conceived of as a means of reducing code repetition as it existed in the program
     * prior to the method's use.
     *
     * @param <T> extends <code>Thing</code>
     * @param thingsList <code>ArrayList</code>
     * @param index <code>int</code>
     * @return <code>thing</code> <code>T</code>
     */
    private <T extends Thing> T getImmediateParentByIndex(ArrayList<T> thingsList, int index) {
        for (T thing : thingsList) {
            if (thing.getIndex() == index) {
                return thing;
            }
        }
        return null;
    }

    /**
     * Another generic method used to get assorted <code>Thing</code> subclass objects from their
     * appropriate <code>SeaPort</code> <code>ArrayList</code>s. Accepts an index and a
     * <code>String</code> representation of a method and relies upon the lower-level method
     * <code>getImmediateParentByIndex</code> within the body of the <code>for in</code> loop.
     *
     * @see java.lang.reflect
     * @param <T> extends <code>Thing</code>
     * @param index <code>int</code>
     * @param methodName <code>String</code>
     * @return newThing <code>T</code>
     */
    @SuppressWarnings("unchecked")
    private <T extends Thing> T getThingByIndex(int index, String methodName) {

        // Declarations
        Method getList;
        T newThing;
        ArrayList<T> thingsList;

        try {
            // Either SeaPort.class.getShips or SeaPort.class.getDocks
            getList = SeaPort.class.getDeclaredMethod(methodName);
            for (SeaPort port : this.getPorts()) {

                /**
                 * Though it throws an unchecked cast error, my scouring of StackOverflow produced
                 * no better ideas for how to convert <code>Object</code> to <code>ArrayList</code>,
                 * hence the rationale for adding a <code>SuppressWarnings</code> tag to this
                 * method and the related one following it.
                 * @see /questions/11611120/return-arraylist-object-dynamically-in-java-reflection
                 */
                thingsList = (ArrayList<T>) getList.invoke(port);
                newThing = this.getImmediateParentByIndex(thingsList, index);

                if (newThing != null) {
                    return newThing;
                }
            }
        } catch (
            NoSuchMethodException |
            SecurityException |
            IllegalAccessException |
            IllegalArgumentException |
            InvocationTargetException ex
        ) {
            System.out.println("Error: " + ex);
        }
        return null;
    }

    /**
     * Generic addition method that replaces a fair bit of copy/pasta methods that were basically
     * identical. Accepts a new <code>Thing</code> subclass object and a <code>String</code>
     * representation of a declared method as parameters, allowing for the selection of the proper
     * <code>SeaPort</code> <code>ArrayList</code> getter method depending on input.
     *
     * @see java.lang.reflect
     * @param <T> extends <code>Thing</code>
     * @param newThing <code>T</code>
     * @param methodName <code>String</code>
     */
    @SuppressWarnings("unchecked")
    private <T extends Thing> void addThingToList(T newThing, String methodName) {

        // Declarations
        SeaPort newPort;
        ArrayList<T> thingsList;
        Method getList;

        // Definition
        newPort = this.getImmediateParentByIndex(this.getPorts(), newThing.getParent());

        try {
            // Either SeaPort.class.getPersons() or SeaPort.class.getDocks();
            getList = SeaPort.class.getDeclaredMethod(methodName);

            // See casting comment in above method
            thingsList = (ArrayList<T>) getList.invoke(newPort); // newPort.getList()

            if (newPort != null) {
                thingsList.add(newThing);
            }
        } catch (
            NoSuchMethodException |
            SecurityException |
            IllegalAccessException |
            IllegalArgumentException |
            InvocationTargetException ex
        ) {
            System.out.println("Error: " + ex);
        }
    }

    /**
     * This method was a tricky one, and is similar in scope to the method below. In some cases, the
     * value of <code>getParent</code> for the new <code>Job</code> instance will not match that of
     * an extant ship in any <code>SeaPort</code>'s <code>getShips()</code> <code>ArrayList</code>,
     * so the method must check each port's docks for moored ships, find the ship, and add the
     * new job to that ship.
     *
     * @param newJob <code>Job</code>
     * @return void
     */
    private void addJobToShip(Job newJob) {
        Dock newDock;
        Ship newShip = this.getThingByIndex(newJob.getParent(), "getShips");

        if (newShip != null) {
            newShip.getJobs().add(newJob);
        } else {
            newDock = this.getThingByIndex(newJob.getParent(), "getDocks");
            newDock.getShip().getJobs().add(newJob);
        }
    }

    /**
     * This method was tricky to get right. As not all <code>Ship</code> objects are moored at a
     * <code>Dock</code>, the value of <code>getParent()</code> may not correspond to any extant
     * <code>Dock</code> objects. If the ship's <code>parent</code> is not a dock (i.e. is
     * <code>null</code>), then we add it to the all ships <code>ArrayList</code> <code>ships</code>
     * and add it to the queue (<code>getQue()</code>). If the ship is moored, we add it to the all
     * ships listing and set the value of the specific <code>Dock</code>'s <code>getShip()</code> as
     * the ship.
     *
     * @param newship <code>Ship</code>
     * return void
     */
    private void addShipToParent(Ship newShip) {
        SeaPort myPort;
        Dock myDock = this.getThingByIndex(newShip.getParent(), "getDocks");

        if (myDock == null) {
            myPort = this.getImmediateParentByIndex(this.getPorts(), newShip.getParent());
            myPort.getShips().add(newShip);
            myPort.getQue().add(newShip);
        } else {
            myPort = this.getImmediateParentByIndex(this.getPorts(), myDock.getParent());
            myDock.setShip(newShip);
            myPort.getShips().add(newShip);
        }
    }

    // Overridden methods

    /**
     * @inheritdoc
     * @return <code>stringOutput</code>
     */
    @Override
    public String toString() {
        String stringOutput = ">>>>> The world:\n";

        for (SeaPort seaPort : this.getPorts()) {
            stringOutput += seaPort.toString() + "\n";
        }
        return stringOutput;
    }
}