/**
 * SeaPortProgram.java - Constructs the main interface
 * Begun 01/15/18
 * @author Andrew Eissen
 */

//package project1;

import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This is the central class of the program. It initializes the program and assembles the GUI and
 * includes click handlers related to the two major buttons, "Read" and "Search." It contains a
 * <code>private</code> instance of the <code>World</code> class which is built from the
 * <tt>.txt</tt> file selected by the user.
 * <br />
 * <br />
 * Class extends <code>JFrame</code>
 * @see javax.swing.JFrame
 * @author Andrew Eissen
 */
final class SeaPortProgram extends JFrame {

    // New world instance
    private World world;

    // Window-related fields
    private String title;
    private int width;
    private int height;

    // GUI related fields
    private JFrame mainFrame;
    private JTextArea mainTextArea;
    private JScrollPane mainScrollPane;
    private JPanel mainPanel, optionsPanel;
    private JButton readButton, searchButton;
    private JLabel searchTextLabel;
    private JTextField searchTextField;
    private String[] searchComboBoxValues;
    private JComboBox<String> searchComboBox;

    // User input-related fields
    private JFileChooser fileChooser;
    private Scanner scannerContents;

    /**
     * Default, no-parameters constructor
     */
    protected SeaPortProgram() {
        super("SeaPortProgram");
        this.setWindowTitle("SeaPortProgram");
        this.setWindowWidth(800);
        this.setWindowHeight(500);
    }

    /**
     * Fully-parameterized constructor, accepting size parameters and title
     * @param title <code>String</code>
     * @param width <code>int</code>
     * @param height <code>int</code>
     */
    protected SeaPortProgram(String title, int width, int height) {
        super(title);
        this.setWindowTitle(title);
        this.setWindowWidth(width);
        this.setWindowHeight(height);
    }

    /**
     * Setter for <code>title</code>
     * @param title <code>String</code>
     * @return void
     */
    private void setWindowTitle(String title) {
        this.title = title;
    }

    /**
     * Setter for <code>width</code>
     * @param width <code>int</code>
     * @return void
     */
    private void setWindowWidth(int width) {
        if (width < 800) {
            this.width = 800;
        } else {
            this.width = width;
        }
    }

    /**
     * Setter for <code>height</code>
     * @param height <code>int</code>
     * @return void
     */
    private void setWindowHeight(int height) {
        if (height < 500) {
            this.height = 500;
        } else {
            this.height = height;
        }
    }

    /**
     * Getter for <code>title</code>
     * @return this.title
     */
    protected String getWindowTitle() {
        return this.title;
    }

    /**
     * Getter for <code>width</code>
     * @return this.width
     */
    protected int getWindowWidth() {
        return this.width;
    }

    /**
     * Getter for <code>height</code>
     * @return this.height
     */
    protected int getWindowHeight() {
        return this.height;
    }

    /**
     * This method, as the name implies, assembles the <code>Swing</code>-based GUI and is invoked
     * only by the <code>main</code> method. As no visual design was provided, the author took
     * certain liberties in the course of designing the GUI, though he remained true to the rubric's
     * requirement to place a <code>JTextArea</code> within a <code>JScrollPane</code> in the
     * <code>BorderLayout.CENTER</code> position of a <code>JFrame</code>. Similarly, as per the
     * rubric, the use of <code>FlowLayout</code> was avoided in favor of <code>BorderLayout</code>
     * for the text area and <code>GridLayout</code> for the top bar options panel. Though the GUI
     * does resize nicely, making it too small does break the effect a bit and results in a
     * <span style="color:yellow;font-family:'Comic Sans MS';">jAnkY</span> looking interface.
     *
     * @return void
     */
    private void constructGUI() {

        // Layout manager definitions, eschewing FlowLayout as per rubric
        this.mainPanel = new JPanel(new BorderLayout());
        this.optionsPanel = new JPanel(new GridLayout(1, 5, 5, 5));

        // Central text area related definitions
        this.mainTextArea = new JTextArea();
        this.mainTextArea.setEditable(false);                       // Forbid editing JTextArea
        this.mainTextArea.setFont(new Font("Monospaced", 0, 12));   // Insert Font as per rubric
        this.mainTextArea.setLineWrap(true);                        // Precaution to encourage wrap

        // Add JTextArea to JScrollPane as per rubric
        this.mainScrollPane = new JScrollPane(this.mainTextArea);

        // Create buttons for options menu
        this.readButton = new JButton("Read");
        this.searchButton = new JButton("Search");

        // Search related definitions
        this.searchTextLabel = new JLabel("Search entries:", JLabel.RIGHT);
        this.searchTextField = new JTextField("", 10);

        /**
         * Combo box definitions, using <code>String</code> array idea from
         * <a href="www.codejava.net/java-se/swing/jcombobox-basic-tutorial-and-examples">this</a>.
         */
        this.searchComboBoxValues = new String[] {"By name", "By index", "By skill"};
        this.searchComboBox = new JComboBox<>(this.searchComboBoxValues);

        // Add UI options to top panel
        this.optionsPanel.add(this.readButton);         // Read button first
        this.optionsPanel.add(this.searchTextLabel);    // Search text label "Search entries:"
        this.optionsPanel.add(this.searchTextField);    // Search bar itself
        this.optionsPanel.add(this.searchComboBox);     // Sorting options combo box
        this.optionsPanel.add(this.searchButton);       // Search button itself

        // Per rubric, JTextArea within JScrollPane on JPanel in the BorderLayout.CENTER position
        this.mainPanel.add(this.mainScrollPane, BorderLayout.CENTER);

        // Add buttons options UI to the top of the main panel
        this.mainPanel.add(this.optionsPanel, BorderLayout.NORTH);

        // Add borders for a cleaner look
        this.optionsPanel.setBorder(BorderFactory.createTitledBorder("Options"));
        this.mainScrollPane.setBorder(BorderFactory.createTitledBorder("World"));
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Read button handler
        this.readButton.addActionListener((ActionEvent e) -> {
            this.readFileContents();
        });

        // Search button handler
        this.searchButton.addActionListener((ActionEvent e) -> {
            this.searchWorldContents();
        });

        // Placement/sizing details for main JFrame element
        this.mainFrame = new JFrame(this.getWindowTitle());
        this.mainFrame.setContentPane(this.mainPanel);
        this.mainFrame.setSize(this.getWindowWidth(), this.getWindowHeight());
        this.mainFrame.setVisible(true);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method serves as the click handler for presses of the "Read" button. As per the project
     * design rubric, the method employs <code>JFileChooser</code> to open a file selection dialog
     * popup for the user to select the proper file. A <code>.txt</code> files-only filter was
     * applied to fix a bug the author discovered that would allow the program to run files of any
     * extension. Since the input will be a text file, such a restriction was deemed sensible.
     * Related to this was the subsequent restriction forbidding improperly-formatted text files
     * from erroneously being run.
     *
     * @return void
     */
    private void readFileContents() {

        // Declarations
        int selection;
        FileReader fileReader;
        FileNameExtensionFilter filter;

        // Main folder, as per rubric
        this.fileChooser = new JFileChooser(".");

        /**
         * Addition of <code>.txt</code> file-only filter, as per the answer on the following page:
         * <br />
         * <a href=
         * "//stackoverflow.com/questions/15771949/how-do-i-make-jfilechooser-only-accept-txt"
         * >See here</a>
         */
        filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        this.fileChooser.setFileFilter(filter);

        selection = this.fileChooser.showOpenDialog(new JFrame());

        if (selection == JFileChooser.APPROVE_OPTION) {
            try {
                fileReader = new FileReader(this.fileChooser.getSelectedFile());
                this.scannerContents = new Scanner(fileReader);
            } catch (FileNotFoundException ex) {
                this.displayErrorPopup("Error: No such file found. Please try again.");
            }
        }

        // Create new <code>World</code> instance, passing <code>Scanner</code> contents
        this.world = new World(this.scannerContents);

        // Forbid users from using a text file that is not in the proper format
        if (this.world.getAllThings().isEmpty()) {
            // Clear any previous results from proper input and set World object to null
            this.mainTextArea.setText("");
            this.world = null;
            this.displayErrorPopup("Error: File data may be empty or corrupted. Please try again.");
        } else {
            this.mainTextArea.setText("" + this.world.toString()); // Clear text area first
        }

    }

    /**
     * Method serves as the handler for clicks of the "Search" button. The method retrieves the
     * user-inputted values for <code>this.searchTextField</code> and
     * <code>this.searchComboBox</code> and makes evaluation decisions based on these options. The
     * author believes this would be far easier to implement with a set of <code>HashMap</code>s,
     * but in the interest of preserving professor sanity, simpler, less effective methods have been
     * employed instead.
     * <br />
     * <br />
     * Initially, there was a fair bit of code repetition contained within the body of the method's
     * following <code>switch</code> statement, much of which was reduced and moved into a few
     * individual methods for optimized reuse. As the operations of both selection by name and also
     * by index are basically the same, they were consolidated into a single operation that
     * passes their index to <code>assembleResults</code>. The index value ultimately determines
     * which method to be used for comparison, either <code>Thing.class.getIndex</code> or else
     * <code>Thing.class.getName</code>.
     * <br />
     * <br />
     * Skill is special, so it gets its own handler within the <code>switch</code> statement, seeing
     * as skills are encapsulated within <code>Person</code>s and not included within
     * <code>World.class.allThings</code>.
     *
     * @return void
     */
    private void searchWorldContents() {

        // Prevent users seeking to hit the search button before building da world
        if (this.world == null || this.scannerContents == null) {
            this.displayErrorPopup("Error: No world initialized. Please try again.");
            return;
        }

        // Declaration
        String resultsString, searchText;
        int dropdownSelection;

        // Definitions
        resultsString = "";
        searchText = this.searchTextField.getText().trim();
        dropdownSelection = this.searchComboBox.getSelectedIndex();

        // Catch users hitting the Search button without entering any content, sneaky buggers
        if (searchText.equals("")) {
            this.displayErrorPopup("Error: No search terms included. Please try again.");
            return;
        }

        // See discussion above for more details
        switch(dropdownSelection) {
            case 0: // By name
            case 1: // By index
                resultsString = this.assembleResults(dropdownSelection, searchText);
                this.displayStatus(resultsString, searchText);
                break;
            case 2: // By skill
                for (SeaPort port : this.world.getPorts()) {
                    for (Person person : port.getPersons()) {
                        if (person.getSkill().equals(searchText)) {
                            resultsString += person.getName() + " (id #" + person.getIndex()
                                + ")\n";
                        }
                    }
                }
                this.displayStatus(resultsString, searchText);
                break;
            default:
                break;
        }
    }

    /**
     * This method was added to minimize code repetition and inefficiency within the
     * <code>switch</code> statement of the preceeding method, <code>searchWorldContents</code>.
     * <br />
     * <br />
     * Initially, the author employed a ternary operation within the body of the <code>for</code>
     * loop to select which method to use to compare with the value of <code>target</code>. However,
     * to reduce the need to select the same operation each time when the operation is already known
     * the first time, the author removed this and replaced it in favor of reflection, choosing the
     * desired method before the loop and invoking that method as desired within. This removed the
     * needless <code>if</code> operation and slightly improved efficiency.
     *
     * @see java.lang.reflect
     * @param index The value of <code>this.searchComboBox.getSelectedIndex()</code>
     * @param target The search term inputted by the user
     * @return resultsString The assembled <code>String</code> of search values
     */
    private String assembleResults(int index, String target) {

        // Declarations/definitions
        Method getParam;
        String parameter, methodName;
        String resultsString = "";

        /**
         * Compare 1 ternary operation per method invocation vs
         * <code>World.class.allThings.size()</code> operations per method invocation. Thank the
         * Java gods for reflection.
         */
        methodName = (index == 0) ? "getName" : "getIndex";

        try {
            // Either Thing.class.getName or Thing.class.getIndex
            getParam = Thing.class.getDeclaredMethod(methodName);

            for (Thing item : this.world.getAllThings()) {

                // Hacky? Yes. But it leaves Strings alone and converts Integers to String
                parameter = "" + getParam.invoke(item);

                if (parameter.equals(target)) {
                    resultsString += item.getName() + " " + item.getIndex() + " ("
                        + item.getClass().getSimpleName() + ")\n";
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
        return resultsString;
    }

    /**
     * Like the method below, this method displays a <code>JOptionPane</code> depending on the
     * content of <code>resultsString</code>. If the <code>String</code> is empty, that means there
     * is no matching input, and thus an error message variation is displayed. Otherwise, the
     * <code>String</code> is displayed as the results in a success-style popup.
     *
     * @param resultsString The <code>String</code> representing the search results
     * @param target The search term itself, also a <code>String</code>
     * @return void
     */
    private void displayStatus(String resultsString, String target) {
        if (resultsString.equals("")) {
            JOptionPane.showMessageDialog(
                null,
                "Warning: '" + target + "' not found.",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                null,
                resultsString,
                "Search results for '" + target + "'",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    /**
     * Method simply displays an error popup <code>JOptionPane</code> window to notify the user of
     * an error in input or somesuch.
     *
     * @param message The <code>String</code> to be used as the body of the popup
     * @return void
     */
    private void displayErrorPopup(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Main method; initiates new <code>SeaPortProgram</code> object and invokes
     * <code>constructGUI</code> method. Only <code>public</code> method in the program.
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        SeaPortProgram newCollection = new SeaPortProgram();
        newCollection.constructGUI();
    }
}