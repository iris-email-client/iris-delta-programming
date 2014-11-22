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

This version was successfully compiled using Eclipse 
Kepler and Java 7. You must clone the repository and 
import the projet into Eclipse using the maven plugin. 
You need to install Java 7 support to Eclipse Kepler 
(see: http://www.eclipse.org/downloads/java7/), and thus 
change the Java compiler of each project to 
Java 7. Finally (at least I hope so) it is also necessary 
to upgrade the maven plugin (add the update site 
http://download.eclipse.org/technology/m2e/releases and 
take a look at http://stackoverflow.com/questions/10564684/how-to-fix-error-updating-maven-project-unsupported-iclasspathentry-kind-4).