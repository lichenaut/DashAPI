# DashAPI

DashAPI is a Spigot / (preferrably) Paper plugin that adds a function for setting the velocity of entities in terms of blocks rather than a vector.

To add to your project, add the .jar file to your "plugins" folder and add this dependency to your pom.xml.

        <dependency>
            <groupId>io.github.lichenaut</groupId>
            <artifactId>DashAPI</artifactId>
            <version>1.0.6</version>
        </dependency>
        
For tools other than Maven: https://central.sonatype.com/artifact/io.github.lichenaut/DashAPI/1.0.6

Then, add this as a dependency in your "plugin.yml" file.

![image](https://user-images.githubusercontent.com/81048400/225530011-7ba0fa9f-205e-4315-90b2-43cb2f673463.png)

To get an instance of this API for use, define a "DashFunctions" object with "DashAPI.getInstance()":

![image](https://user-images.githubusercontent.com/81048400/225512094-16e55346-dc67-4fb6-918b-4b165631a0ef.png)

Example #1 (base function):

![image](https://user-images.githubusercontent.com/81048400/225510373-f0dd76f5-1cfe-4f51-9e91-00a48710b790.png)

When a player interacts with an entity, the entity will be dashed 10 blocks forward, 10 blocks up, and 10 blocks left on Minecraft's x, y, and z axes, respectively.

Example #2 (adjustments):

![image](https://user-images.githubusercontent.com/81048400/226056873-b857b8de-0ae9-4e9a-8d50-75b268d8291e.png)

When a player crouches or uncrouches, they will be dashed forward relative to their looking direction due to "adjustHorizontal" being true, and the player's current velocity will be added to the dash due to "additive" being true. Additionally, as the maximum number of blocks an entity can be dashed vertically is 60 (43 horizontally), the plugin reduces the value 100 to 60 and dashes the player up 60 blocks. Finally, because "adjustVertical" is set to true, the player's looking up or down affects the y of the dash.

Example #3 (int and Double inputs):

![image](https://user-images.githubusercontent.com/81048400/226048635-8c145d94-146f-440c-a9c2-e4274a15b9c5.png)

Int values for block distance and Double values for velocity can be used in any combination, and in this example it is (int, Double, int). In this example, when a player interacts with an entity, the y value of that player's looking direction is multiplied by 4 and made negative, which means that if a player looks down to click an entity, they will be dashed with a high positive y velocity.

Example #4 (multipliers):

![image](https://user-images.githubusercontent.com/81048400/226046720-45d2d5f0-9868-4a6e-822c-bc011ea43a94.png)

The first dash is applied to the entity interacted by the player, based off of the direction the player is facing, creating a knockback effect. The second dash is applied to the player, based off of the velocity of the clicked entity, creating a pull effect. Additionally, the pull effect is halved with the x, y, and z multiplers, meaning that, if repeated, the interacted entity will eventually be too far away for the player to interact with.

[![Github All Releases](https://img.shields.io/github/downloads/lichenaut/DashAPI/total.svg)]()
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.lichenaut/DashAPI/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.lichenaut/DashAPI)
