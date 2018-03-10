### Object Oriented and Concurrent Programming, SeaPort Project 1 ###

#### Overview ####

The four-part SeaPort Project Series served as the primary means of concept enforcement in CMSC 335, Object Oriented and Current Programming. At its most basic level, the program employs the fundamental principles of object oriented programming and multithreading concurrency to chart the movements of hypothetical ships in a set of sea ports, processing their jobs in real time with the application of included dock workers and moving the vessels about from queue to dock to harbor as their jobs are completed.

Over the course of five projects, including the four required and an alternate Project 4, the author expanded a hand-drawn GUI constructed with Swing that provides users with a comprehensive overview of the world and its contents, in addition to a number of secondary utility features. Users are permitted access to a search function for any and all objects in the world, as well as a sorting option that lists objects by a number of user-selected parameters such as weight, draft, etc. Furthermore, each of the jobs in question is interactive, with the user given the ability to suspend, cancel, or complete ongoing jobs as desired.

#### About Project 1 ####

Project 1 is specifically concerned with the basic creation of the underlying major classes that represent the various objects in the world, as well as a GUI meant to display the raw `toString()` output of each individual world object. Each world is constructed via a specifically formatted `.txt` text file, specified by the user via a `JFileChooser` modal window. Improper input is handled by the program gracefully, alerting the user to inconsistent file formatting or illegitimate text entries. Furthermore, a search function is included to permit users to find individual objects by either their name, ID, or skill (specifically for dock workers). Searches are displayed in a `JOptionPane` for ease of access.

Included are all files, documentation (notated as `Eissen_Project1.pdf`), and the project requirements rubrics (notated as `SeaPort_Project1_Rubric.pdf`).