/**
 * Ship.java - Progenitor of <code>Ship</code> objects
 * Begun 01/15/18
 * @author Andrew Eissen
 */

//package project1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents general ship objects to be stored in queues or docks in port objects. The
 * class serves as the progenitor parent of two subclasses, namely <code>PassengerShip</code> and
 * <code>CargoShip</code>, and contains fields and <code>ArrayList</code>s related to the specific
 * physical properties of the vessel in question. However, like many of the world objects, the class
 * contains mainly setters and getters, the required <code>Scanner</code> based constructor, and the
 * overridden <code>toString()</code> method.
 * <br />
 * <br />
 * Interestingly, the Project 1 design rubric makes no mention of what to do with the
 * <code>PortTime</code> values <code>arrivalTime</code> and <code>dockTime</code>, so they are
 * simply included but left untouched.
 * <br />
 * <br />
 * Class extends <code>Thing</code>
 * @see project1.Thing
 * @see project1.PassengerShip
 * @see project1.CargoShip
 * @author Andrew Eissen
 */
class Ship extends Thing {

    // Rubric-required fields
    private PortTime arrivalTime, dockTime;
    private double draft, length, weight, width;
    private ArrayList<Job> jobs;

    /**
     * Parameterized constructor
     * @param scannerContents Contents of <code>.txt</code> file
     */
    protected Ship(Scanner scannerContents) {
        super(scannerContents);

        if (scannerContents.hasNextDouble()) {
            this.setWeight(scannerContents.nextDouble());
        }

        if (scannerContents.hasNextDouble()) {
            this.setLength(scannerContents.nextDouble());
        }

        if (scannerContents.hasNextDouble()) {
            this.setWidth(scannerContents.nextDouble());
        }

        if (scannerContents.hasNextDouble()) {
            this.setDraft(scannerContents.nextDouble());
        }

        this.setJobs(new ArrayList<>());
    }

    // Setters

    /**
     * Setter for <code>arrivalTime</code>
     * @param arrivalTime <code>PortTime</code>
     * @return void
     */
    private void setArrivalTime(PortTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Setter for <code>dockTime</code>
     * @param dockTime <code>PortTime</code>
     * @return void
     */
    private void setDockTime(PortTime dockTime) {
        this.dockTime = dockTime;
    }

    /**
     * Setter for <code>weight</code>
     * @param weight <code>double</code>
     * @return void
     */
    private void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Setter for <code>length</code>
     * @param length <code>double</code>
     * @return void
     */
    private void setLength(double length) {
        this.length = length;
    }

    /**
     * Setter for <code>width</code>
     * @param width <code>double</code>
     * @return void
     */
    private void setWidth(double width) {
        this.width = width;
    }

    /**
     * Setter for <code>draft</code>
     * @param draft <code>double</code>
     * @return void
     */
    private void setDraft(double draft) {
        this.draft = draft;
    }

    /**
     * Setter for <code>jobs</code>
     * @param jobs <code>ArrayList</code>
     * @return void
     */
    private void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }

    // Getters

    /**
     * Getter for <code>arrivalTime</code>
     * @return <code>this.arrivalTime</code>
     */
    protected PortTime getArrivalTime() {
        return this.arrivalTime;
    }

    /**
     * Getter for <code>dockTime</code>
     * @return <code>this.dockTime</code>
     */
    protected PortTime getDockTime() {
        return this.dockTime;
    }

    /**
     * Getter for <code>weight</code>
     * @return <code>this.weight</code>
     */
    protected double getWeight() {
        return this.weight;
    }

    /**
     * Getter for <code>length</code>
     * @return <code>this.length</code>
     */
    protected double getLength() {
        return this.length;
    }

    /**
     * Getter for <code>width</code>
     * @return <code>this.width</code>
     */
    protected double getWidth() {
        return this.width;
    }

    /**
     * Getter for <code>draft</code>
     * @return <code>this.draft</code>
     */
    protected double getDraft() {
        return this.draft;
    }

    /**
     * Getter for <code>jobs</code>
     * @return <code>this.jobs</code>
     */
    protected ArrayList<Job> getJobs() {
        return this.jobs;
    }

    // Overriden methods

    /**
     * @inheritdoc
     * @return stringOutput <code>String</code>
     */
    @Override
    public String toString() {
        String stringOutput;

        stringOutput = super.toString() + "\n\tWeight: " + this.getWeight() + "\n\tLength: "
            + this.getLength() + "\n\tWidth: " + this.getWidth() + "\n\tDraft: " + this.getDraft()
            + "\n\tJobs:";

        if (this.getJobs().isEmpty()){
            stringOutput += " None";
        } else {
            for (Job newJob : this.getJobs()) {
                stringOutput += "\n" + newJob.toString();
            }
        }

        return stringOutput;
    }
}