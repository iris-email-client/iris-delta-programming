# IRIS EMAIL CLIENT

This is the DeltaJ version of IRIS email client. The following features 
are implemented in this version

* Send and Receive e-mail messages.
* Multiple folders (though we are not able to create new folders).
* Address book.
* Relational database.
* Lucene persistence.
* Tags.

## Import into Eclipse IDE

1. Install Java 7 SDK;
2. Download Eclipse Kepler and Luna;
3. Install Xtext 2.5.x;
3. Install DeltaJ 1.5 from https://www.tu-braunschweig.de/isf/research/deltas/
5. Clone this repository and import the project in Eclipse as an existing Maven project;
6. Install whatever Eclipse suggests;
7. Remove the maven nature of the project (if you have imported as a maven project, DeltaJ don't work very well with Maven);
8. Build the system with "Project -> Clean...";
11. Choose product(s) to generate;
12. In the terminal, shell or cmd, do a "mvn clean package";
13. Have fun! :-)

