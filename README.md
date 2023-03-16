# DashAPI

DashAPI is a Spigot / (preferrably) Paper plugin that adds a function for setting the velocity of entities in terms of blocks rather than a vector.

Include the .jar file in your "plugins" folder and add this dependency to your pom.xml.

        <dependency>
            <groupId>io.github.lichenaut</groupId>
            <artifactId>DashAPI</artifactId>
            <version>1.0.4</version>
        </dependency>
        
For tools other than Maven: https://central.sonatype.com/artifact/io.github.lichenaut/DashAPI/1.0.4

To get an instance of this API for use, define a "DashFunctions" object with "DashAPI.getInstance()":

![image](https://user-images.githubusercontent.com/81048400/225512094-16e55346-dc67-4fb6-918b-4b165631a0ef.png)

Example #1 (base function):

![image](https://user-images.githubusercontent.com/81048400/225510373-f0dd76f5-1cfe-4f51-9e91-00a48710b790.png)

When a player interacts with an entity, the entity will be sent 10 blocks forward, 10 blocks up, and 10 blocks left, relative to the direction that entity is looking.

Example #2 (optioned function):

![image](https://user-images.githubusercontent.com/81048400/225510778-2930ace2-b573-43e3-8e06-d89c0a2a3095.png)

When a player crouches or uncrouches, they will be sent forward on Minecraft's x-axis (not relative to looking direction due to "adjustHorizontal" being false), but this time the player's current velocity will be added to the dash due to "additive" being true. Additionally, as the maximum number of blocks an entity can be launched vertically is 60, the plugin reduces the value 100 to 60 and launches the player up 60 blocks. Finally, because "adjustVertical" is set to true, the player's looking up or down affects the verticality of the dash.

Final points:

The maximum number of blocks an entity can be launched horizontally is 43.

The base function's defaults for "additive", "adjustHorizontal", and "adjustVertical", are "false", "true", and "false", respectively.

[![Github All Releases](https://img.shields.io/github/downloads/lichenaut/DashAPI/total.svg)]()
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.lichenaut/DashAPI/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.lichenaut/DashAPI)
