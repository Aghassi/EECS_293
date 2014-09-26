1. You don't *need* to use this. If you already have a working build file then feel free to keep using it. But if you do choose to use it, then read on..

2. make sure to put build.xml and the 2 .jar files in the same directory. So in the end you should have a directory structure similar to this:

-/javadocs
-/src
./build.xml
./junit.jar
./org.hamcrest........jar

3. don't rename build.xml

4. Edit build.xml and change all of the project name references from 293TA_HW2 to your own project's name.

5. Inside of build.xml look at the two <target name='xxx'> blocks I have. The first ("run") is more or less what you'd do if your program had an entry point: run the main() function. The second is how to run junit tests through ant. Look at how these blocks of code work and change them and add new ones if needed.


In the end, this should give everyone that has been having some ant troubles a good start. There are plenty of resources online to help fill any gaps that you're still experiencing.