package com.lichenaut.dashapi;

import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import util.DDirectoryMaker;
import util.DUpdateChecker;
import util.DVelocityReference;

import java.util.logging.Logger;

public final class DashAPI extends JavaPlugin implements Listener {

    private Logger log;

    @Override
    public void onEnable() {
        final DashAPI plugin = this;
        log = getLogger();
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Configuration config = getConfig();

        //int pluginId = ;
        //noinspection ALL
        //Metrics metrics = new Metrics(plugin, pluginId);

        if (config.getBoolean("disable-plugin")) {
            log.info("Plugin disabled in config.yml.");
        } else {
            //new DUpdateChecker(this, plugin).getVersion(version -> {if (!this.getDescription().getVersion().equals(version)) {getLog().info("Update available.");}});

            String dataFolderPath = getDataFolder().getPath();
            new DDirectoryMaker(plugin).makeDir(dataFolderPath);
        }
    }

    public Logger getLog() {return log;}
    public int getNeg(boolean isNeg) {if (isNeg) {return -1;} else {return 1;}}
    public void dash(Entity e,
                     int x, //Maximum 43
                     int y, //Maximum 60
                     int z //Maximum 43
    ) {
        if (x > 43) {x = 43;}
        if (y > 60) {y = 60;}
        if (z > 43) {z = 43;}
        boolean xNeg = false;boolean yNeg = false;boolean zNeg = false;
        if (x < 0) {x = Math.abs(x);xNeg = true;}
        if (y < 0) {y = Math.abs(y);yNeg = true;}
        if (z < 0) {z = Math.abs(z);zNeg = true;}
        Vector entityDir = e.getLocation().getDirection();
        double convertDegrees = 180 / Math.PI;
        double entityLook = -Math.atan2(entityDir.getZ(), entityDir.getX()) * convertDegrees + 90;
        if (entityLook < 0) {entityLook += 360;}
        double dashAngle = 90 + Math.atan2(DVelocityReference.getXZVelocities().get(z)*getNeg(zNeg), DVelocityReference.getXZVelocities().get(x)*getNeg(xNeg)) * convertDegrees;
        double velocityLength = Math.sqrt(Math.pow(DVelocityReference.getXZVelocities().get(z)*getNeg(zNeg), 2) + DVelocityReference.getXZVelocities().get(x)*getNeg(xNeg));
        double newDashAngle = entityLook - dashAngle + 90;
        if (newDashAngle < 0) {newDashAngle += 360;}
        double newZ = Math.cos(newDashAngle / convertDegrees) * velocityLength;
        double newX = Math.sin(newDashAngle / convertDegrees) * velocityLength;
        e.setVelocity(new Vector(newX, DVelocityReference.getYVelocities().get(y)*getNeg(yNeg), newZ));
    }
    public void dash(Entity e,
                     int x, //Maximum 43
                     int y, //Maximum 60
                     int z, //Maximum 43
                     boolean additive //Add dash to current velocity?
                     ) {
        if (x > 43) {x = 43;}
        if (y > 60) {y = 60;}
        if (z > 43) {z = 43;}
        boolean xNeg = false;boolean yNeg = false;boolean zNeg = false;
        if (x < 0) {x = Math.abs(x);xNeg = true;}
        if (y < 0) {y = Math.abs(y);yNeg = true;}
        if (z < 0) {z = Math.abs(z);zNeg = true;}
        Vector entityDir = e.getLocation().getDirection();
        double convertDegrees = 180 / Math.PI;
        double entityLook = -Math.atan2(entityDir.getZ(), entityDir.getX()) * convertDegrees + 90;
        if (entityLook < 0) {entityLook += 360;}
        double dashAngle = 90 + Math.atan2(DVelocityReference.getXZVelocities().get(z)*getNeg(zNeg), DVelocityReference.getXZVelocities().get(x)*getNeg(xNeg)) * convertDegrees;
        double velocityLength = Math.sqrt(Math.pow(DVelocityReference.getXZVelocities().get(z)*getNeg(zNeg), 2) + DVelocityReference.getXZVelocities().get(x)*getNeg(xNeg));
        double newDashAngle = entityLook - dashAngle + 90;
        if (newDashAngle < 0) {newDashAngle += 360;}
        double newZ = Math.cos(newDashAngle / convertDegrees) * velocityLength;
        double newX = Math.sin(newDashAngle / convertDegrees) * velocityLength;
        if (additive) {e.setVelocity(new Vector(newX+e.getVelocity().getX(), DVelocityReference.getYVelocities().get(y)*getNeg(yNeg)+e.getVelocity().getY(),
                newZ+e.getVelocity().getZ()));
        } else {e.setVelocity(new Vector(newX, DVelocityReference.getYVelocities().get(y)*getNeg(yNeg), newZ));}
    }
}
