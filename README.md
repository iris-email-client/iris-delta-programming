# IRIS E-mail Client (Delta version)

This is the DeltaJ version of IRIS email client. The following features 
are implemented in this version

* Send and Receive e-mail messages.
* Multiple folders (though we are not able to create new folders).
* Address book.
* Relational database.
* Lucene persistence.
* Tags.
* Search, simple and advanced.

## Import into Eclipse IDE

1. Install Java 7 SDK;
2. Download Eclipse Kepler and Luna;
3. Install Xtext 2.5.x;
3. Install DeltaJ 1.5 from https://www.tu-braunschweig.de/isf/research/deltas/
5. Clone this repository and import the project in Eclipse as an existing Project into Workspace;
6. Install whatever Eclipse suggests;
7. DeltaJ don't work very well with Maven, if you have problems disable maven nature;
8. Build the system with "Project -> Clean...";
11. Choose product(s) to generate;
12. In the terminal,runs as (eclipse), shell or cmd, do a "mvn clean package" (we also have some maven profiles for running the program);
13. Have fun! :-)

