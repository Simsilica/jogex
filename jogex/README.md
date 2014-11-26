jogex parser
=============

This is the main library for reading .ogex and .oddl files from Java.

The parsers are split into two parts to better facilitate adapting to changes in either format.  
To read an OGEX file, the OgexParser first converts it into ODDL data structures with the OddlParser.
These ODDL data structures are then converted into Ogex-specific objects that reduce, simplify, and 
validate the raw ODDL structures.  

The OgexParser returns an OgexScene object that contains the OgexNodes of the scene.


To build from source:

1. checkout this directory
2. make sure you have a JDK installed and that JAVA_HOME is set correctly
3. run: 
`gradlew build`

### Testing Files from the Command Line

Both the OgexParser and OddlParser can be run from the command line to test reading
a file.  All that is required is that the jogex.jar and its dependencies be placed on
the current classpath.  The gradle build can produce a shell script (on Windows only right now, sorry)
for easily setting this up.

`gradlew setclasspath.cmd`
`setclasspath.cmd`

Then running the ODDL parser:

`java com.simsilica.oddl.OddlParser myFile.oddl`

If the file is read successfully then a toString() representation of the results are printed.

Running the OGEX parser is similar:
`java com.simsilica.oddl.OgexParser myFile.ogex`

Note: in either case, the extension is included for clarity but the .ogex could be read by the Oddl parser the same way.

