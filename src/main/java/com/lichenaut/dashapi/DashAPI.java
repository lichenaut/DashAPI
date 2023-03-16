package com.lichenaut.dashapi;

import org.bukkit.Bukkit;
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
            Bukkit.getPluginManager().registerEvents(this, this);
        }
    }

    public Logger getLog() {return log;}
    public int getNeg(boolean isNeg) {if (isNeg) {return -1;} else {return 1;}}
    public double getVertical(boolean adjustVertical, Vector direction) {if (adjustVertical) {return direction.getY();} else {return 1;}}

    /**
     *
     * @param e The entity to be dashed.
     * @param x The number of blocks the entity is to be dashed forwards/backwards, relative to the entity's looking direction. Positive values are forwards, negative values are backwards, and the maximum is 43.
     * @param y The number of blocks the entity is to be dashed up/down. Positive values are up, negative values are down, and the maximum is 60.
     * @param z The number of blocks the entity is to be dashed right/left, relative to the entity's looking direction. Positive values are right, negative values are left, and the maximum is 43.
     */
    public void dash(Entity e, int x, int y, int z) {
        boolean xNeg = false;boolean yNeg = false;boolean zNeg = false;
        if (x < 0) {x = Math.abs(x);xNeg = true;}
        if (y < 0) {y = Math.abs(y);yNeg = true;}
        if (z < 0) {z = Math.abs(z);zNeg = true;}
        if (x > 43) {x = 43;}
        if (y > 60) {y = 60;}
        if (z > 43) {z = 43;}
        Vector entityDir = e.getLocation().getDirection();
        double convertDegrees = 180 / Math.PI;
        double entityLook = -Math.atan2(entityDir.getZ(), entityDir.getX()) * convertDegrees + 90;
        if (entityLook < 0) {entityLook += 360;}
        double dashAngle = 90 + Math.atan2(DVelocityReference.getXZVelocities().get(z)*getNeg(zNeg), DVelocityReference.getXZVelocities().get(x)*getNeg(xNeg)) *
                convertDegrees;
        double velocityLength = Math.sqrt(Math.pow(DVelocityReference.getXZVelocities().get(z)*getNeg(zNeg), 2) +
                Math.pow(DVelocityReference.getXZVelocities().get(x)*getNeg(xNeg), 2));
        double newDashAngle = entityLook - dashAngle + 90;
        if (newDashAngle < 0) {newDashAngle += 360;}
        double newZ = Math.cos(newDashAngle / convertDegrees) * velocityLength;
        double newX = Math.sin(newDashAngle / convertDegrees) * velocityLength;
        e.setVelocity(new Vector(newX, DVelocityReference.getYVelocities().get(y)*getNeg(yNeg), newZ));
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x The number of blocks the entity is to be dashed forwards/backwards, relative to the entity's looking direction. Positive values are forwards, negative values are backwards, and the maximum is 43.
     * @param y The number of blocks the entity is to be dashed up/down. Positive values are up, negative values are down, and the maximum is 60.
     * @param z The number of blocks the entity is to be dashed right/left, relative to the entity's looking direction. Positive values are right, negative values are left, and the maximum is 43.
     * @param additive Whether the entity's current velocity is added to the dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the direction of the dash.
     * @param adjustVertical Whether the entity looking down decreases the height of the dash.
     */
    public void dash(Entity e, int x, int y, int z, boolean additive, boolean adjustHorizontal, boolean adjustVertical) {
        boolean xNeg = false;boolean yNeg = false;boolean zNeg = false;
        if (x < 0) {x = Math.abs(x);xNeg = true;}
        if (y < 0) {y = Math.abs(y);yNeg = true;}
        if (z < 0) {z = Math.abs(z);zNeg = true;}
        if (x > 43) {x = 43;}
        if (y > 60) {y = 60;}
        if (z > 43) {z = 43;}
        double newX;
        double newZ;
        if (adjustHorizontal) {
            Vector entityDir = e.getLocation().getDirection();
            double convertDegrees = 180 / Math.PI;
            double entityLook = -Math.atan2(entityDir.getZ(), entityDir.getX()) * convertDegrees + 90;
            if (entityLook < 0) {entityLook += 360;}
            double dashAngle = 90 + Math.atan2(DVelocityReference.getXZVelocities().get(z)*getNeg(zNeg), DVelocityReference.getXZVelocities().get(x)*getNeg(xNeg))
                    * convertDegrees;
            double velocityLength = Math.sqrt(Math.pow(DVelocityReference.getXZVelocities().get(z)*getNeg(zNeg), 2) +
                    Math.pow(DVelocityReference.getXZVelocities().get(x)*getNeg(xNeg), 2));
            double newDashAngle = entityLook - dashAngle + 90;
            if (newDashAngle < 0) {newDashAngle += 360;}
            newZ = Math.cos(newDashAngle / convertDegrees) * velocityLength;
            newX = Math.sin(newDashAngle / convertDegrees) * velocityLength;
        } else {
            newZ = DVelocityReference.getXZVelocities().get(z)*getNeg(zNeg);
            newX = DVelocityReference.getXZVelocities().get(x)*getNeg(xNeg);
        }
        if (additive) {e.setVelocity(new Vector(newX+e.getVelocity().getX(),
                DVelocityReference.getYVelocities().get(y)*getNeg(yNeg)*getVertical(adjustVertical, e.getLocation().getDirection())+e.getVelocity().getY(),
                newZ+e.getVelocity().getZ()));
        } else {e.setVelocity(new Vector(newX, DVelocityReference.getYVelocities().get(y)*getNeg(yNeg)*getVertical(adjustVertical,
                e.getLocation().getDirection()), newZ));}
    }
}
