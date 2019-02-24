# SARAHAssignment
MarsSimulator

This program has been made using java 8 streams along with maven.
This program can be run via command line few instruciton to run program as below

Compile, Test, Run and Packaging

Option-1

- Compile: `mvn compile`

- Test: `mvn test`

- Run: `mvn exec:java`

- Packaging: `mvn package`, compiled jar in *target/* folder

Than you can run via below command
java -jar Toy-Explorer-1.0-SNAPSHOT.jar command-data.txt

Note : where command-data.txt is command input file which is placed under resource in our packaged application

Option-2

Import whole application in IntelliJ or any other tool
and click on Run than  Edit Configuration for Application as below
Main: com.toysimulator.Main
Program Aeguments: src\main\resources\command-data.txt

than simply run your application as Run as Java Application from Main class, it should display expected output in console.





Mars Explorer Simulator
===================

Description
-----------

- The application is a simulation of a mars explorer moving on a square tabletop,
  of dimensions 5 units x 5 units.
- There are no obstructions on the table surface when the explorer is placed on 
    the table at the first time.
- The explorer is free to roam around the surface of the table, but must be
  prevented from falling to destruction. Any movement pthat would result in the
  explorer falling from the table must be prevented, however further valid
  movement commands must still be allowed.
- The explorer only can move to units which is not occupied by any block object.
- The explorer only can move to the sorrounding units vertically or horizontally.
- The unit of table can be occupied by block object if there is no explorer or 
    other block object. Once it is occupied, the explorer can't move into it.

Create an application that can read in commands of the following (textual) form:

    PLACE X,Y
    BLOCK X,Y
    REPORT
    EXPLORER X,Y

- PLACE will put the toy explorer on the table in position X,Y and remove all 
    existing blocks on the table.
    - The origin (0,0) can be considered to be the bottom left most corner. 
    - The first valid command to the explorer is a PLACE command, after that, any
    sequence of commands may be issued, in any order, including another PLACE
    command. The application should discard all commands in the sequence until
    a valid PLACE command has been executed.

- BLOCK will put the obstruction object to the unit of given position X, Y. 
    - If the explorer has been placed to the unit, the BLOCK can be ignored. 
    - If the unit has been occupied by obstruction object before, the BLOCK 
        command can be ignored.

- REPORT will announce the X,Y of the explorer, and the positions of blocks.

- EXPLORE will find the shortest path to move from the original position 
    to the target position X,Y through units along the path. Command will 
    print out the positions of path which the explorer 
    - A explorer that is not on the table can choose to ignore the EXPLORE command.
    - A explorer can only move nearest position vertically or horizontallhy. e.g. 
        A explorer with position (0,0) can only move to (0,1), (1,0) positions instead of (1,1).
    - A explore can choose to ignore the position which is not on the table
    - A exploer can choose to ignore the target position which has be occupied 
        with obstruction

- Input can be from a file, or from standard input, as the developer chooses.
- Provide test data to exercise the application.
- The application must be a command line application.

Constraints
-----------

- The toy explorer must not fall off the table during movement. This also
  includes the initial placement of the toy explorer.
- Any move that would cause the explorer to fall must be ignored.
- The path must not contain any unit with blocks  

Example Input and Output
------------------------

### Example a

    PLACE 0,0
    REPORT

Expected output:

    E:(0,0) B: 

### Example b

    PLACE 0,0
    BLOCK 0,1
    BLOCK 0,2
    REPORT 

Expected output:

    E:(0,0) B: (0,1) (0,2)

### Example c

    PLACE 0,0
    BLOCK 0,1
    BLOCK 0,2
    PLACE 0,1
    REPORT

Expected output:

    E:(0,1) B: 

### Example d

    PLACE 0,0
    BLOCK 0,2
    EXPLORER 0,3

Expected output

    PATH: (0,0) (0,1) (1,1) (1,2) (1,3) (0,3)


### Example d

    PLACE 0,0
    BLOCK 0,2
    EXPLORER 0,3
    REPORT 

Expected output

    E:(0,3) B: (0,2)


Deliverables
------------

Please provide your source code, and any test code/data you using in
developing your solution.

Please engineer your solution to a standard you consider suitable for
production. It is not required to provide any graphical output showing the
movement of the toy explorer.

Please do not put your name in any of the submitted code since this makes 
it harder for us to review your submission anonymously.







