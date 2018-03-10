/**
 * Person.java - Class for <code>Person</code> objects
 * Begun 01/15/18
 * @author Andrew Eissen
 */

//package project1;

import java.util.Scanner;

/**
 * A subclass of <code>Thing</code>, the <code>Person</code> class represents dock workers located
 * at specific <code>SeaPort</code>s. Each worker has a specific profession, notated in the class
 * itself as <code>skill</code>, and is placed in a <code>SeaPort</code>'s <code>persons</code>
 * <code>ArrayList</code>. Class contains setter/getter and the overridden <code>toString()</code>
 * method as per the rubric.
 * <br />
 * <br />
 * Class extends <code>Thing</code>
 * @see project1.Thing
 * @author Andrew Eissen
 */
final class Person extends Thing {

    // Rubric-required field
    private String skill;

    /**
     * Parameterized constructor
     * @param scannerContents Contents of the <code>.txt</code> file
     */
    protected Person(Scanner scannerContents) {
        super(scannerContents);

        if (scannerContents.hasNext()) {
            this.setSkill(scannerContents.next());
        } else {
            this.setSkill("Error");
        }
    }

    // Setter

    /**
     * Setter for <code>skill</code>
     * @param skill <code>String</code>
     * @return void
     */
    private void setSkill(String skill) {
        this.skill = skill;
    }

    // Getter

    /**
     * Getter for <code>skill</code>
     * @return <code>this.skill</code>
     */
    protected String getSkill() {
        return this.skill;
    }

    // Overridden method

    /**
     * @inheritdoc
     * @return <code>String</code>
     */
    @Override
    public String toString() {
        return "Person: " + super.toString() + " " + this.getSkill();
    }
}