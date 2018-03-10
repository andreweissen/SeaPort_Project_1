/**
 * Job.java - Class for <code>Job</code> objects
 * Begun 01/15/18
 * @author Andrew Eissen
 */

//package project1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class, extending <code>Thing</code>, is technically an optional class until Projects 3 & 4.
 * It represents the jobs available to be filled onboard <code>Ship</code>s, both passenger liners
 * and cargo ships. It contains the appropriate setters/getters and <code>toString()</code> method
 * as per all <code>Thing</code> objects.
 * <br />
 * <br />
 * Class extends <code>Thing</code>
 * @see project1.Thing
 * @author Andrew Eissen
 */
final class Job extends Thing {

    // Rubric-required fields
    private double duration;
    private ArrayList<String> requirements;

    /**
     * Parameterized constructor
     * @param scannerContents Contents of <code>.txt</code> file
     */
    protected Job(Scanner scannerContents) {
        super(scannerContents);
        if (scannerContents.hasNextDouble()) {
            this.setDuration(scannerContents.nextDouble());
        }

        this.setRequirements(new ArrayList<>());
        while (scannerContents.hasNext()) {
            this.getRequirements().add(scannerContents.next());
        }
    }

    // Setters

    /**
     * Setter for <code>duration</code>
     * @param duration <code>double</code>
     * @return void
     */
    private void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * Setter for <code>requirements</code>
     * @param requirements <code>ArrayList</code>
     * @return void
     */
    private void setRequirements(ArrayList<String> requirements) {
        this.requirements = requirements;
    }

    // Getters

    /**
     * Getter for <code>duration</code>
     * @return <code>this.duration</code>
     */
    protected double getDuration() {
        return this.duration;
    }

    /**
     * Getter for <code>requirements</code>
     * @return <code>this.requirements</code>
     */
    protected ArrayList<String> getRequirements() {
        return this.requirements;
    }

    // Overriden method

    /**
     * @inheritdoc
     * @return stringOutput <code>String</code>
     */
    @Override
    public String toString() {
        String stringOutput;

        stringOutput = "\t\t" + super.toString() + "\n\t\tDuration: " + this.getDuration()
            + "\n\t\tRequirements:";

        if (this.getRequirements().isEmpty()) {
            stringOutput += "\n\t\t\t - None";
        } else {
            for(String requiredSkill : this.getRequirements()){
                stringOutput += "\n\t\t\t - " + requiredSkill;
            }
        }

        return stringOutput;
    }
}