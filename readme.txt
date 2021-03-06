JavaForce SDK
=============

Version 17.5.0

What is it?
===========
JavaForce is a Java library extending the capabilities of Java.

The main library includes a VoIP stack and bindings for FFMPEG, OpenGL and Camera.

Includes many apps built around the library such as jfPhone, jfVideo Creator, jfAudio, jfMusic, etc.

JF is also the core library in the Java infused Linux Operating system : http://jfLinux.sourceforge.net

JF is also used to create jfCraft, a Minecraft clone : http://pquiring.github.io/jfcraft

Projects
========
jfPhone - Java VoIP soft phone

jfVideo Creator - video production

jfPaint - multi-tabbed image editor

jfPBX - Java VoIP PBX

jfBroadcast - VoIP Auto Dialer System

jfTerm - Telnet client application that includes scripting support

jfEdit - multi-tabbed text editor.

jfHex - multi-tabbed hex editor.

and many more...

Folders
=======
 /          - main folder (run ant here to build /src)
 /src       - the javaforce source files
 /jars      - 3rd party files
 /stubs     - native launcher stubs
 /native    - native library with JNI bindings for FFMPEG, OpenGL, Camera
 /classes   - javaforce compiled files
 /projects  - source for all sub-projects

Building
========
All projects are built with Apache Ant (http://ant.apache.org).
Make sure to run ant in the main folder to build the /src folder and then in any of the apps in /projects.
If building on Windows make sure to copy /native/*.dll to /windows/system32 since some of the
build tools require them.

Common Ant tasks:
-----------------
compile : compile projects java files
jar : build the projects main jar file
depjars : copy dependant jar files into project folder
install : install files into proper folders (Linux only)
 - for the main JavaForce library you MUST specify -Dbits=32|64
 - requires root access
 - example : "sudo ant install -Dbits=32"
deb : build Ubuntu deb file (after install)
rpm : build Fedora rpm file (after install)
 - linux packaging requires files.lst and linux stub (/stubs/linux)
msi : build Windows msi file with JRE bundled (64bit)
msinojre : build Windows msi file without JRE bundled (64bit)
 - msi creation requires
  - wixtoolset in path (http://wixtoolset.org/)
  - wix64.xml file
  - jre prep for native packaging (see below)
  - windows stub created (/stubs/windows)
dmg : build Mac dmg file using hdiutil (mac only)
genisodmg : build Mac dmg file using geniso (cygwin/linux/mac) (uncompressed)
 - dmg creation requires
  - /projects/jfdmg installed (sudo ant install)
  - jre prep for native packaging (see below)
  - Info.plist, ${app}.icns and macfiles.lst
  - see projects/jfedit for only example
  - jimgconvert can convert images to .icns file format (mac icons)
  - mac stub created (/stubs/mac)
javadoc : create javadoc api help files (open ./javadoc/index.html)

JavaForce Ant tasks:
--------------------
jre-base : prep JRE for creating native packages (msi, dmg)
jre-base-desktop : prep JRE with desktop support
jre-base-javac : prep JRE with java compiler support

Building stubs (native launchers)
---------------------------------
Building the stubs requires gcc in your path.
Windows:Please install cygwin and install mingw-gcc 64bit packages.
Linux:Debian/Ubuntu:run 'ant deb' to install required packages.
Linux:RedHat/Fedora:run 'ant rpm' to install required packages.

Building native library (ffmpeg, OpenGL, Camera)
--------------------------------------------
Building the native library will requires gcc in your path.
Windows:Please install cygwin and install mingw-gcc 64bit packages.
Linux:Requires gcc

License
=======
JavaForce itself is licensed under the LGPL license which can be read in license.txt.
The MSI installers show the Common Public License 1.0 which is acceptable as well.
The other jars in /jars each have their own licensing.
  filters.jar - Apache License 2.0 (http://www.jhlabs.com)
  bouncycastle.jar - MIT license? (http://www.bouncycastle.org)
  derby.jar - Apache License 2.0 (http://db.apache.com/derby)
  jcifs.jar - LGPL (http://jcifs.samba.org)
  jsp-api.jar & servlet-api.jar - Apache License 2.0 (http://tomcat.apache.com)
  jsch.jar & jzlib.jar - BSD license (http://www.jcraft.com)

Enjoy!

Peter Quiring
pquiring@gmail.com

Web : pquiring.github.io/javaforce

Git : github.com/pquiring/javaforce

Released : Mar 8, 2019
