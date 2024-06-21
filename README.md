# DashAPI

DashAPI adds "dash()": set the velocity of entities in terms of blocks instead of a vector.

[![Github All Releases](https://img.shields.io/github/downloads/lichenaut/DashAPI/total.svg)]()
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.lichenaut/DashAPI/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.lichenaut/DashAPI)

## Walkthrough

First, include DashAPI in your project. Here is a build tool list if you don't want to manually download it in: https://central.sonatype.com/artifact/io.github.lichenaut/DashAPI/1.0.8

Then, add this as a dependency in your "plugin.yml" file.

```depend: [DashAPI]```

To get an instance of this API for use, define a "DashFunctions" object with "DashAPI.getInstance()":

```
DashAPI api;

@Override
public void onEnable() {
    api = DashAPI.getInstance();
}
```

Example #1 (base function):

```
@EventHandler
public void onClick(PlayerInteractEntityEvent event) {
    api.dash(event.getRightClicked(), 10, 10, -10);
}
```

When a player interacts with an entity, the entity will be dashed 10 blocks forward, 10 blocks up, and 10 blocks left on Minecraft's x, y, and z axes, respectively.

Example #2 (adjustments):

```
@EventHandler
public void onCrouch(PlayerToggleSneakEvent event) {
    api.dash(event.getPlayer(), 10, 100, 0, true, true, true, true);
}
```

When a player crouches or uncrouches, they will be dashed forward relative to their looking direction due to "adjustHorizontal" being true, and the player's current velocity will be added to the dash due to "additive" being true. Additionally, as the maximum number of blocks an entity can be dashed vertically is 60 (43 horizontally), the plugin reduces the value 100 to 60 and dashes the player up 60 blocks, assuming no gravity (ex. player in flight). Finally, because "adjustVertical" is set to true, the player's looking up or down affects the y of the dash.

Example #3 (int and Double inputs):

```
@EventHandler
public void bounceClick(PlayerInteractEntityEvent event) {
    api.dash(event.getPlayer(), 0, event.getPlayer().getLocation().getDirection().getY() * -4, 0);
}
```

Int values for block distance and Double values for velocity can be used in any combination, and in this example it is (int, Double, int). In this example, when a player interacts with an entity, the y value of that player's looking direction is multiplied by -4, which means that if a player looks down to click an entity, they will be dashed with a high positive y velocity.

Example #4 (multipliers):

```
@EventHandler
public void pushAndPull(PlayerInteractEntityEvent event) {
    api.dash(event.getRightClicked(), event.getPlayer().getLocation().getDirection());
    api.dash(event.getPlayer(), event.getRightClicked().getVelocity(), 0.5, 0.5, 0.5);
}
```

The first dash is applied to the entity interacted by the player, based off of the direction the player is facing, creating a knockback effect. The second dash is applied to the player, based off of the velocity of the clicked entity, creating a pull effect. Additionally, the pull effect is halved with the x, y, and z multiplers, meaning that, if repeated, the interacted entity will eventually be too far away for the player to interact with.