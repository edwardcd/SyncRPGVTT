The SyncRPG VTT, Powered by MapTool
Simply put, SyncRPG makes it easy to play your favorite tabletop roleplaying games (RPGs) with your friends over the internet and helps you keep records of the experience.

SyncRPG is a set of online tools, rule sets, and art assets aimed at facilitating the creation, sharing, and playing of traditional tabletop roleplaying games over the Internet. It is used to help run games efficiently, allows Game Masters (GMs) to share their encounters and non-player characters (NPCs) with each other, and enables players to keep historical records of their characters online—forever!

SyncRPG consists of a MapTool-based virtual tabletop (VTT) application combined with an online repository of art, maps, and NPC (both monster and class-leveled) statistics that are completely free for everyone to use, easily and instantly. Think of it as coming to our house to play a game: we have maps and a box of miniatures that everyone may use to start a game, and rules sheets for different monsters and characters—indexed and ready to go!

Resources
---------
- **Website:** https://www.syncrpg.com/
- **Introduction:** https://www.syncrpg.com/welcome
- **Forums:**  https://www.syncrpg.com/forums/
- **Google+ Community:**  https://plus.google.com/u/4/communities/114515734623192645690

MapTool
=======
Welcome to the MapTool 1.4 repository. The old version (1.3) still resides on [SourceForge](http://sourceforge.net/p/rptools/svn/HEAD/tree/) but should only be updated with major bug fixes.

So what is MapTool?
-------------------
Requirements
------------

- MapTool 1.4 requires [Java 1.7+](https://java.com/en/download/)
- Building MapTool requires the Java Development Kit (JDK): [How To Install JDK](doc/How_To_Install_JDK.md)

Resources
---------

 - **Website:** http://rptools.net/ 
 - **Forums:**  http://forums.rptools.net 
 - **Wiki:**    http://lmwcs.com/rptools/wiki/Main_Page 

Building Maptool
----------------

First, [install the JDK](doc/How_To_Install_JDK.md).

[Gradle](http://gradle.org/) is used to build MapTool 1.4. You do not need Gradle installed to perform the build as the repository has a small wrapper that will download and install it in a subdirectory for you. This means that the first time you do a build you will need to be connected to the internet and it will take a while
as it downloads everything it needs.

```Shell
./gradlew build  
```

For Windows, remember to flip the slash:

```Shell
.\gradlew build  
```

Building the Distributable Zip
------------------------------

```Shell
./gradlew clean release
```

For Windows, remember to flip the slash:

```Shell
.\gradlew clean release 
```

This will create a `.zip` file for use on all systems as well as a zipped `.app` for Mac OS X in the `maptool/build/` directory. The build number will be based on the latest tag and latest commit.

Developers
----------

Please follow our [Code Style and Guidelines](doc/Code_Style_and_Guidelines.md) when submitting patches and pull requests.


Optional
--------

- [How To Setup User Interface (UI) Tools for Maptool](doc/How_To_Setup_UI_Tools.md)
- [How To Setup Eclipse for MapTool](doc/How_To_Setup_Eclipse.md)
