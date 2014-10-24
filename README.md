umw_330
=======

group project for UMW CPSC 330

Hey guys, this is the readme file for our repository.  You can think of it as a "wiki" that describs our project and the structure of our repository.

/docs
will contain important documents related to our project

/trunk
will contain the java source code of our project


SOFTWARE REQUIREMENTS
======================================
Java JDK 8+ (1.8+)
-this is the latest version of the Java Development Kit.  It includes the javac "compiler" and the java "runner" along with other tools like javadoc, javaw, etc.

MongoDB 2.6+ https://fastdl.mongodb.org/win32/mongodb-win32-x86_64-2008plus-2.6.5.zip
-this is our database.  It's a NoSQL Database and is currently top trending tech (as opposed to relational databases like Oracle, MySQL, SQL Server)

Maven 3.2+ https://maven.apache.org/download.cgi
-this is an automated build and dependency tool.  You can think of this as a "helper" utility to the basic java compiler, as you can specify dependencies like the MongoDB Java Driver, the Jetty Application Server, the Freemarker Template Enginer, and they will be downloaded and included in your class path automatically and seemlessly
-for instance, without maven, we'd have to download a bunch of .jar files (java application libraries) and use the -jar arguement when compiling to add them to our classpath, etc.  It just makes life easier.  Other alternative build tools are ANT or GRADLE.




