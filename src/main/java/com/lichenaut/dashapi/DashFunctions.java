package com.lichenaut.dashapi;

import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import com.lichenaut.dashapi.util.DVelocityReference;

public class DashFunctions {

    private int getNeg(boolean isNeg) {if (isNeg) {return -1;} else {return 1;}}
    private double getVert(boolean adjustVertical, Vector direction) {if (adjustVertical) {return direction.getY();} else {return 1;}}

    /**
     *
     * @param e The entity to be dashed.
     * @param x The number of blocks the entity is to be dashed forwards/backwards. Positive values are forwards, negative values are backwards, and the maximum is 43.
     * @param y The number of blocks the entity is to be dashed up/down. Positive values are up, negative values are down, and the maximum is 60.
     * @param z The number of blocks the entity is to be dashed right/left. Positive values are right, negative values are left, and the maximum is 43.
     */
    public void dash(Entity e, int x, int y, int z) {
        boolean xNeg = false, yNeg = false, zNeg = false;
        if (x < 0) {x = Math.abs(x);xNeg = true;}
        if (y < 0) {y = Math.abs(y);yNeg = true;}
        if (z < 0) {z = Math.abs(z);zNeg = true;}
        if (x > 43) {x = 43;}
        if (y > 60) {y = 60;}
        if (z > 43) {z = 43;}
        e.setVelocity(new Vector(DVelocityReference.getXZVelocities(x)*getNeg(xNeg), DVelocityReference.getYVelocities().get(y)*getNeg(yNeg),
                DVelocityReference.getXZVelocities(z)*getNeg(zNeg)));
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x The number of blocks the entity is to be dashed forwards/backwards. Positive values are forwards, negative values are backwards, and the maximum is 43.
     * @param y The number of blocks the entity is to be dashed up/down. Positive values are up, negative values are down, and the maximum is 60.
     * @param z This can be a value from a vector. The entity will be dashed right/left according to this value.
     */
    public void dash(Entity e, int x, int y, Double z) {
        boolean xNeg = false, yNeg = false;
        if (x < 0) {x = Math.abs(x);xNeg = true;}
        if (y < 0) {y = Math.abs(y);yNeg = true;}
        if (x > 43) {x = 43;}
        if (y > 60) {y = 60;}
        e.setVelocity(new Vector(DVelocityReference.getXZVelocities(x)*getNeg(xNeg), DVelocityReference.getYVelocities().get(y)*getNeg(yNeg), z));
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x The number of blocks the entity is to be dashed forwards/backwards. Positive values are forwards, negative values are backwards, and the maximum is 43.
     * @param y This can be a value from a vector. The entity will be dashed up/down according to this value.
     * @param z This can be a value from a vector. The entity will be dashed right/left according to this value.
     */
    public void dash(Entity e, int x, Double y, Double z) {
        boolean xNeg = false;
        if (x < 0) {x = Math.abs(x);xNeg = true;}
        if (x > 43) {x = 43;}
        e.setVelocity(new Vector(DVelocityReference.getXZVelocities(x)*getNeg(xNeg), y, z));
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x This can be a value from a vector. The entity will be dashed forwards/backwards according to this value.
     * @param y This can be a value from a vector. The entity will be dashed up/down according to this value.
     * @param z This can be a value from a vector. The entity will be dashed right/left according to this value.
     */
    public void dash(Entity e, Double x, Double y, Double z) {e.setVelocity(new Vector(x, y, z));}

    /**
     *
     * @param e The entity to be dashed.
     * @param x This can be a value from a vector. The entity will be dashed forwards/backwards according to this value.
     * @param y The number of blocks the entity is to be dashed up/down. Positive values are up, negative values are down, and the maximum is 60.
     * @param z This can be a value from a vector. The entity will be dashed right/left according to this value.
     */
    public void dash(Entity e, Double x, int y, Double z) {
        boolean yNeg = false;
        if (y < 0) {y = Math.abs(y);yNeg = true;}
        if (y > 60) {y = 60;}
        e.setVelocity(new Vector(x, DVelocityReference.getYVelocities().get(y)*getNeg(yNeg), z));
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x This can be a value from a vector. The entity will be dashed forwards/backwards according to this value.
     * @param y The number of blocks the entity is to be dashed up/down. Positive values are up, negative values are down, and the maximum is 60.
     * @param z The number of blocks the entity is to be dashed right/left. Positive values are right, negative values are left, and the maximum is 43.
     */
    public void dash(Entity e, Double x, int y, int z) {
        boolean yNeg = false, zNeg = false;
        if (y < 0) {y = Math.abs(y);yNeg = true;}
        if (z < 0) {z = Math.abs(z);zNeg = true;}
        if (y > 60) {y = 60;}
        if (z > 43) {z = 43;}
        e.setVelocity(new Vector(x, DVelocityReference.getYVelocities().get(y)*getNeg(yNeg), DVelocityReference.getXZVelocities(z)*getNeg(zNeg)));
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x This can be a value from a vector. The entity will be dashed forwards/backwards according to this value.
     * @param y This can be a value from a vector. The entity will be dashed up/down according to this value.
     * @param z The number of blocks the entity is to be dashed right/left. Positive values are right, negative values are left, and the maximum is 43.
     */
    public void dash(Entity e, Double x, Double y, int z) {
        boolean zNeg = false;
        if (z < 0) {z = Math.abs(z);zNeg = true;}
        if (z > 43) {z = 43;}
        e.setVelocity(new Vector(x, y, DVelocityReference.getXZVelocities(z)*getNeg(zNeg)));
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x The number of blocks the entity is to be dashed forwards/backwards. Positive values are forwards, negative values are backwards, and the maximum is 43.
     * @param y This can be a value from a vector. The entity will be dashed up/down according to this value.
     * @param z The number of blocks the entity is to be dashed right/left. Positive values are right, negative values are left, and the maximum is 43.
     */
    public void dash(Entity e, int x, Double y, int z) {
        boolean xNeg = false, zNeg = false;
        if (x < 0) {x = Math.abs(x);xNeg = true;}
        if (z < 0) {z = Math.abs(z);zNeg = true;}
        if (x > 43) {x = 43;}
        if (z > 43) {z = 43;}
        e.setVelocity(new Vector(DVelocityReference.getXZVelocities(x)*getNeg(xNeg), y, DVelocityReference.getXZVelocities(z)*getNeg(zNeg)));
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x The number of blocks the entity is to be dashed forwards/backwards. Positive values are forwards, negative values are backwards, and the maximum is 43.
     * @param y The number of blocks the entity is to be dashed up/down. Positive values are up, negative values are down, and the maximum is 60.
     * @param z The number of blocks the entity is to be dashed right/left. Positive values are right, negative values are left, and the maximum is 43.
     * @param additive Whether the entity's current velocity is added to the dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the direction of the dash.
     * @param adjustVertical Whether the entity's looking up/down affects the y of the dash.
     */
    public void dash(Entity e, int x, int y, int z, boolean additive, boolean adjustHorizontal, boolean adjustVertical) {
        boolean xNeg = false, yNeg = false, zNeg = false;
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
            double dashAngle = 90 + Math.atan2(DVelocityReference.getXZVelocities(z)*getNeg(zNeg), DVelocityReference.getXZVelocities(x)*getNeg(xNeg))
                    * convertDegrees;
            double velocityLength = Math.sqrt(Math.pow(DVelocityReference.getXZVelocities(z)*getNeg(zNeg), 2) +
                    Math.pow(DVelocityReference.getXZVelocities(x)*getNeg(xNeg), 2));
            double newDashAngle = entityLook - dashAngle + 90;
            if (newDashAngle < 0) {newDashAngle += 360;}
            newZ = Math.cos(newDashAngle / convertDegrees) * velocityLength;
            newX = Math.sin(newDashAngle / convertDegrees) * velocityLength;
        } else {
            newZ = DVelocityReference.getXZVelocities(z)*getNeg(zNeg);
            newX = DVelocityReference.getXZVelocities(x)*getNeg(xNeg);
        }
        if (additive) {
            e.setVelocity(new Vector(newX+e.getVelocity().getX(), DVelocityReference.getYVelocities().get(y)*getNeg(yNeg)*getVert(adjustVertical,
                    e.getLocation().getDirection())+e.getVelocity().getY(), newZ+e.getVelocity().getZ()));
        } else {e.setVelocity(new Vector(newX, DVelocityReference.getYVelocities().get(y)*getNeg(yNeg)*getVert(adjustVertical, e.getLocation().getDirection()),
                newZ));}
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x The number of blocks the entity is to be dashed forwards/backwards. Positive values are forwards, negative values are backwards, and the maximum is 43.
     * @param y The number of blocks the entity is to be dashed up/down. Positive values are up, negative values are down, and the maximum is 60.
     * @param z This can be a value from a vector. The entity will be dashed right/left according to this value.
     * @param additive Whether the entity's current velocity is added to the dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the direction of the dash.
     * @param adjustVertical Whether the entity's looking up/down affects the y of the dash.
     */
    public void dash(Entity e, int x, int y, Double z, boolean additive, boolean adjustHorizontal, boolean adjustVertical) {
        boolean xNeg = false, yNeg = false;
        if (x < 0) {x = Math.abs(x);xNeg = true;}
        if (y < 0) {y = Math.abs(y);yNeg = true;}
        if (x > 43) {x = 43;}
        if (y > 60) {y = 60;}
        double newX;
        if (adjustHorizontal) {
            Vector entityDir = e.getLocation().getDirection();
            double convertDegrees = 180 / Math.PI;
            double entityLook = -Math.atan2(entityDir.getZ(), entityDir.getX()) * convertDegrees + 90;
            if (entityLook < 0) {entityLook += 360;}
            double dashAngle = 90 + Math.atan2(z, DVelocityReference.getXZVelocities(x)*getNeg(xNeg)) * convertDegrees;
            double velocityLength = Math.sqrt(Math.pow(z, 2) + Math.pow(DVelocityReference.getXZVelocities(x)*getNeg(xNeg), 2));
            double newDashAngle = entityLook - dashAngle + 90;
            if (newDashAngle < 0) {newDashAngle += 360;}
            z = Math.cos(newDashAngle / convertDegrees) * velocityLength;
            newX = Math.sin(newDashAngle / convertDegrees) * velocityLength;
        } else {newX = DVelocityReference.getXZVelocities(x)*getNeg(xNeg);}
        if (additive) {
            e.setVelocity(new Vector(newX+e.getVelocity().getX(), DVelocityReference.getYVelocities().get(y)*getNeg(yNeg)*getVert(adjustVertical,
                    e.getLocation().getDirection())+e.getVelocity().getY(), z+e.getVelocity().getZ()));
        } else {e.setVelocity(new Vector(newX, DVelocityReference.getYVelocities().get(y)*getNeg(yNeg)*getVert(adjustVertical, e.getLocation().getDirection()),
                z));}
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x The number of blocks the entity is to be dashed forwards/backwards. Positive values are forwards, negative values are backwards, and the maximum is 43.
     * @param y This can be a value from a vector. The entity will be dashed up/down according to this value.
     * @param z This can be a value from a vector. The entity will be dashed right/left according to this value.
     * @param additive Whether the entity's current velocity is added to the dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the direction of the dash.
     * @param adjustVertical Whether the entity's looking up/down affects the y of the dash.
     */
    public void dash(Entity e, int x, Double y, Double z, boolean additive, boolean adjustHorizontal, boolean adjustVertical) {
        boolean xNeg = false;
        if (x < 0) {x = Math.abs(x);xNeg = true;}
        if (x > 43) {x = 43;}
        double newX;
        if (adjustHorizontal) {
            Vector entityDir = e.getLocation().getDirection();
            double convertDegrees = 180 / Math.PI;
            double entityLook = -Math.atan2(entityDir.getZ(), entityDir.getX()) * convertDegrees + 90;
            if (entityLook < 0) {entityLook += 360;}
            double dashAngle = 90 + Math.atan2(z, DVelocityReference.getXZVelocities(x)*getNeg(xNeg))
                    * convertDegrees;
            double velocityLength = Math.sqrt(Math.pow(z, 2) +
                    Math.pow(DVelocityReference.getXZVelocities(x)*getNeg(xNeg), 2));
            double newDashAngle = entityLook - dashAngle + 90;
            if (newDashAngle < 0) {newDashAngle += 360;}
            z = Math.cos(newDashAngle / convertDegrees) * velocityLength;
            newX = Math.sin(newDashAngle / convertDegrees) * velocityLength;
        } else {newX = DVelocityReference.getXZVelocities(x)*getNeg(xNeg);}
        if (additive) {
            e.setVelocity(new Vector(newX+e.getVelocity().getX(), y*getVert(adjustVertical, e.getLocation().getDirection())+e.getVelocity().getY(),
                    z+e.getVelocity().getZ()));
        } else {e.setVelocity(new Vector(newX, y*getVert(adjustVertical, e.getLocation().getDirection()), z));}
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x This can be a value from a vector. The entity will be dashed forwards/backwards according to this value.
     * @param y This can be a value from a vector. The entity will be dashed up/down according to this value.
     * @param z This can be a value from a vector. The entity will be dashed right/left according to this value.
     * @param additive Whether the entity's current velocity is added to the dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the direction of the dash.
     * @param adjustVertical Whether the entity's looking up/down affects the y of the dash.
     */
    public void dash(Entity e, Double x, Double y, Double z, boolean additive, boolean adjustHorizontal, boolean adjustVertical) {
        if (adjustHorizontal) {
            Vector entityDir = e.getLocation().getDirection();
            double convertDegrees = 180 / Math.PI;
            double entityLook = -Math.atan2(entityDir.getZ(), entityDir.getX()) * convertDegrees + 90;
            if (entityLook < 0) {entityLook += 360;}
            double dashAngle = 90 + Math.atan2(z, x) * convertDegrees;
            double velocityLength = Math.sqrt(Math.pow(z, 2) + Math.pow(x, 2));
            double newDashAngle = entityLook - dashAngle + 90;
            if (newDashAngle < 0) {newDashAngle += 360;}
            z = Math.cos(newDashAngle / convertDegrees) * velocityLength;
            x = Math.sin(newDashAngle / convertDegrees) * velocityLength;
        }
        if (additive) {
            e.setVelocity(new Vector(x+e.getVelocity().getX(), y*getVert(adjustVertical, e.getLocation().getDirection())+e.getVelocity().getY(),
                    z+e.getVelocity().getZ()));
        } else {e.setVelocity(new Vector(x, y*getVert(adjustVertical, e.getLocation().getDirection()), z));}
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x This can be a value from a vector. The entity will be dashed forwards/backwards according to this value.
     * @param y The number of blocks the entity is to be dashed up/down. Positive values are up, negative values are down, and the maximum is 60.
     * @param z This can be a value from a vector. The entity will be dashed right/left according to this value.
     * @param additive Whether the entity's current velocity is added to the dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the direction of the dash.
     * @param adjustVertical Whether the entity's looking up/down affects the y of the dash.
     */
    public void dash(Entity e, Double x, int y, Double z, boolean additive, boolean adjustHorizontal, boolean adjustVertical) {
        boolean yNeg = false;
        if (y < 0) {y = Math.abs(y);yNeg = true;}
        if (y > 60) {y = 60;}
        if (adjustHorizontal) {
            Vector entityDir = e.getLocation().getDirection();
            double convertDegrees = 180 / Math.PI;
            double entityLook = -Math.atan2(entityDir.getZ(), entityDir.getX()) * convertDegrees + 90;
            if (entityLook < 0) {entityLook += 360;}
            double dashAngle = 90 + Math.atan2(z, x) * convertDegrees;
            double velocityLength = Math.sqrt(Math.pow(z, 2) + Math.pow(x, 2));
            double newDashAngle = entityLook - dashAngle + 90;
            if (newDashAngle < 0) {newDashAngle += 360;}
            z = Math.cos(newDashAngle / convertDegrees) * velocityLength;
            x = Math.sin(newDashAngle / convertDegrees) * velocityLength;
        }
        if (additive) {
            e.setVelocity(new Vector(x+e.getVelocity().getX(), DVelocityReference.getYVelocities().get(y)*getNeg(yNeg)*getVert(adjustVertical,
                    e.getLocation().getDirection())+e.getVelocity().getY(), z+e.getVelocity().getZ()));
        } else {e.setVelocity(new Vector(x, DVelocityReference.getYVelocities().get(y)*getNeg(yNeg)*getVert(adjustVertical, e.getLocation().getDirection()),
                z));}
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x This can be a value from a vector. The entity will be dashed forwards/backwards according to this value.
     * @param y The number of blocks the entity is to be dashed up/down. Positive values are up, negative values are down, and the maximum is 60.
     * @param z The number of blocks the entity is to be dashed right/left. Positive values are right, negative values are left, and the maximum is 43.
     * @param additive Whether the entity's current velocity is added to the dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the direction of the dash.
     * @param adjustVertical Whether the entity's looking up/down affects the y of the dash.
     */
    public void dash(Entity e, Double x, int y, int z, boolean additive, boolean adjustHorizontal, boolean adjustVertical) {
        boolean yNeg = false, zNeg = false;
        if (y < 0) {y = Math.abs(y);yNeg = true;}
        if (z < 0) {z = Math.abs(z);zNeg = true;}
        if (y > 60) {y = 60;}
        if (z > 43) {z = 43;}
        double newZ;
        if (adjustHorizontal) {
            Vector entityDir = e.getLocation().getDirection();
            double convertDegrees = 180 / Math.PI;
            double entityLook = -Math.atan2(entityDir.getZ(), entityDir.getX()) * convertDegrees + 90;
            if (entityLook < 0) {entityLook += 360;}
            double dashAngle = 90 + Math.atan2(DVelocityReference.getXZVelocities(z)*getNeg(zNeg), x) * convertDegrees;
            double velocityLength = Math.sqrt(Math.pow(DVelocityReference.getXZVelocities(z)*getNeg(zNeg), 2) + Math.pow(x, 2));
            double newDashAngle = entityLook - dashAngle + 90;
            if (newDashAngle < 0) {newDashAngle += 360;}
            newZ = Math.cos(newDashAngle / convertDegrees) * velocityLength;
            x = Math.sin(newDashAngle / convertDegrees) * velocityLength;
        } else {newZ = DVelocityReference.getXZVelocities(z)*getNeg(zNeg);}
        if (additive) {
            e.setVelocity(new Vector(x+e.getVelocity().getX(), DVelocityReference.getYVelocities().get(y)*getNeg(yNeg)*getVert(adjustVertical,
                    e.getLocation().getDirection())+e.getVelocity().getY(), newZ+e.getVelocity().getZ()));
        } else {e.setVelocity(new Vector(x, DVelocityReference.getYVelocities().get(y)*getNeg(yNeg)*getVert(adjustVertical, e.getLocation().getDirection()),
                newZ));}
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x This can be a value from a vector. The entity will be dashed forwards/backwards according to this value.
     * @param y This can be a value from a vector. The entity will be dashed up/down according to this value.
     * @param z The number of blocks the entity is to be dashed right/left. Positive values are right, negative values are left, and the maximum is 43.
     * @param additive Whether the entity's current velocity is added to the dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the direction of the dash.
     * @param adjustVertical Whether the entity's looking up/down affects the y of the dash.
     */
    public void dash(Entity e, Double x, Double y, int z, boolean additive, boolean adjustHorizontal, boolean adjustVertical) {
        boolean zNeg = false;
        if (z < 0) {z = Math.abs(z);zNeg = true;}
        if (z > 43) {z = 43;}
        double newZ;
        if (adjustHorizontal) {
            Vector entityDir = e.getLocation().getDirection();
            double convertDegrees = 180 / Math.PI;
            double entityLook = -Math.atan2(entityDir.getZ(), entityDir.getX()) * convertDegrees + 90;
            if (entityLook < 0) {entityLook += 360;}
            double dashAngle = 90 + Math.atan2(DVelocityReference.getXZVelocities(z)*getNeg(zNeg), x) * convertDegrees;
            double velocityLength = Math.sqrt(Math.pow(DVelocityReference.getXZVelocities(z)*getNeg(zNeg), 2) + Math.pow(x, 2));
            double newDashAngle = entityLook - dashAngle + 90;
            if (newDashAngle < 0) {newDashAngle += 360;}
            newZ = Math.cos(newDashAngle / convertDegrees) * velocityLength;
            x = Math.sin(newDashAngle / convertDegrees) * velocityLength;
        } else {newZ = DVelocityReference.getXZVelocities(z)*getNeg(zNeg);}
        if (additive) {
            e.setVelocity(new Vector(x+e.getVelocity().getX(), y*getVert(adjustVertical, e.getLocation().getDirection())+e.getVelocity().getY(),
                    newZ+e.getVelocity().getZ()));
        } else {e.setVelocity(new Vector(x, y*getVert(adjustVertical, e.getLocation().getDirection()), newZ));}
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x The number of blocks the entity is to be dashed forwards/backwards. Positive values are forwards, negative values are backwards, and the maximum is 43.
     * @param y This can be a value from a vector. The entity will be dashed up/down according to this value.
     * @param z The number of blocks the entity is to be dashed right/left. Positive values are right, negative values are left, and the maximum is 43.
     * @param additive Whether the entity's current velocity is added to the dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the direction of the dash.
     * @param adjustVertical Whether the entity's looking up/down affects the y of the dash.
     */
    public void dash(Entity e, int x, Double y, int z, boolean additive, boolean adjustHorizontal, boolean adjustVertical) {
        boolean xNeg = false, zNeg = false;
        if (x < 0) {x = Math.abs(x);xNeg = true;}
        if (z < 0) {z = Math.abs(z);zNeg = true;}
        if (x > 43) {x = 43;}
        if (z > 43) {z = 43;}
        double newX;
        double newZ;
        if (adjustHorizontal) {
            Vector entityDir = e.getLocation().getDirection();
            double convertDegrees = 180 / Math.PI;
            double entityLook = -Math.atan2(entityDir.getZ(), entityDir.getX()) * convertDegrees + 90;
            if (entityLook < 0) {entityLook += 360;}
            double dashAngle = 90 + Math.atan2(DVelocityReference.getXZVelocities(z)*getNeg(zNeg), DVelocityReference.getXZVelocities(x)*getNeg(xNeg))
                    * convertDegrees;
            double velocityLength = Math.sqrt(Math.pow(DVelocityReference.getXZVelocities(z)*getNeg(zNeg), 2) +
                    Math.pow(DVelocityReference.getXZVelocities(x)*getNeg(xNeg), 2));
            double newDashAngle = entityLook - dashAngle + 90;
            if (newDashAngle < 0) {newDashAngle += 360;}
            newZ = Math.cos(newDashAngle / convertDegrees) * velocityLength;
            newX = Math.sin(newDashAngle / convertDegrees) * velocityLength;
        } else {
            newZ = DVelocityReference.getXZVelocities(z)*getNeg(zNeg);
            newX = DVelocityReference.getXZVelocities(x)*getNeg(xNeg);
        }
        if (additive) {
            e.setVelocity(new Vector(newX+e.getVelocity().getX(), y*getVert(adjustVertical, e.getLocation().getDirection())+e.getVelocity().getY(),
                    newZ+e.getVelocity().getZ()));
        } else {e.setVelocity(new Vector(newX, y*getVert(adjustVertical, e.getLocation().getDirection()), newZ));}
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param v The vector the entity is dashed with.
     */
    public void dash(Entity e, Vector v) {e.setVelocity(v);}

    /**
     *
     * @param e The entity to be dashed.
     * @param v The vector the entity is dashed with.
     * @param x Multiplier for the vector's x value.
     * @param y Multiplier for the vector's y value.
     * @param z Multiplier for the vector's z value.
     */
    public void dash(Entity e, Vector v, Double x, Double y, Double z) {e.setVelocity(new Vector(v.getX()*x, v.getY()*y, v.getZ()*z));}

    /**
     *
     * @param e The entity to be dashed.
     * @param v The vector the entity is dashed with.
     * @param additive Whether the entity's current velocity is added to the dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the direction of the dash.
     * @param adjustVertical Whether the entity's looking up/down affects the y of the dash vector.
     */
    public void dash(Entity e, Vector v, boolean additive, boolean adjustHorizontal, boolean adjustVertical) {
        double newX = v.getX();
        double newZ = v.getZ();
        if (adjustHorizontal) {
            Vector entityDir = e.getLocation().getDirection();
            double convertDegrees = 180 / Math.PI;
            double entityLook = -Math.atan2(entityDir.getZ(), entityDir.getX()) * convertDegrees + 90;
            if (entityLook < 0) {entityLook += 360;}
            double dashAngle = 90 + Math.atan2(newZ, newZ) * convertDegrees;
            double velocityLength = Math.sqrt(Math.pow(newZ, 2) + Math.pow(newX, 2));
            double newDashAngle = entityLook - dashAngle + 90;
            if (newDashAngle < 0) {newDashAngle += 360;}
            newZ = Math.cos(newDashAngle / convertDegrees) * velocityLength;
            newX = Math.sin(newDashAngle / convertDegrees) * velocityLength;
        }
        if (additive) {
            e.setVelocity(new Vector(newX+e.getVelocity().getX(), v.getY()*getVert(adjustVertical, e.getLocation().getDirection())+e.getVelocity().getY(),
                    newZ+e.getVelocity().getZ()));
        } else {e.setVelocity(new Vector(newX, v.getY()*getVert(adjustVertical, e.getLocation().getDirection()), newZ));}
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param v The vector the entity is dashed with.
     * @param x Multiplier for the vector's x value.
     * @param y Multiplier for the vector's y value.
     * @param z Multiplier for the vector's z value.
     * @param additive Whether the entity's current velocity is added to the dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the direction of the dash.
     * @param adjustVertical Whether the entity's looking up/down affects the y of the dash vector.
     */
    public void dash(Entity e, Vector v, Double x, Double y, Double z, boolean additive, boolean adjustHorizontal, boolean adjustVertical) {
        double newX = v.getX();
        double newZ = v.getZ();
        if (adjustHorizontal) {
            Vector entityDir = e.getLocation().getDirection();
            double convertDegrees = 180 / Math.PI;
            double entityLook = -Math.atan2(entityDir.getZ(), entityDir.getX()) * convertDegrees + 90;
            if (entityLook < 0) {entityLook += 360;}
            double dashAngle = 90 + Math.atan2(newZ, newZ) * convertDegrees;
            double velocityLength = Math.sqrt(Math.pow(newZ, 2) + Math.pow(newX, 2));
            double newDashAngle = entityLook - dashAngle + 90;
            if (newDashAngle < 0) {newDashAngle += 360;}
            newZ = Math.cos(newDashAngle / convertDegrees) * velocityLength;
            newX = Math.sin(newDashAngle / convertDegrees) * velocityLength;
        }
        if (additive) {
            e.setVelocity(new Vector(newX*x+e.getVelocity().getX(), v.getY()*y*getVert(adjustVertical, e.getLocation().getDirection())+e.getVelocity().getY(),
                    newZ*z+e.getVelocity().getZ()));
        } else {e.setVelocity(new Vector(newX*x, v.getY()*y*getVert(adjustVertical, e.getLocation().getDirection()), newZ*z));}
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param v The vector the entity is dashed with.
     * @param additive Whether the entity's current velocity is added to the dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the direction of the dash.
     * @param adjustVertical Whether the entity's looking up/down affects the y of the dash vector.
     * @param x Multiplier for the vector's x value.
     * @param y Multiplier for the vector's y value.
     * @param z Multiplier for the vector's z value.
     */
    public void dash(Entity e, Vector v, boolean additive, boolean adjustHorizontal, boolean adjustVertical, Double x, Double y, Double z) {
        double newX = v.getX();
        double newZ = v.getZ();
        if (adjustHorizontal) {
            Vector entityDir = e.getLocation().getDirection();
            double convertDegrees = 180 / Math.PI;
            double entityLook = -Math.atan2(entityDir.getZ(), entityDir.getX()) * convertDegrees + 90;
            if (entityLook < 0) {entityLook += 360;}
            double dashAngle = 90 + Math.atan2(newZ, newZ) * convertDegrees;
            double velocityLength = Math.sqrt(Math.pow(newZ, 2) + Math.pow(newX, 2));
            double newDashAngle = entityLook - dashAngle + 90;
            if (newDashAngle < 0) {newDashAngle += 360;}
            newZ = Math.cos(newDashAngle / convertDegrees) * velocityLength;
            newX = Math.sin(newDashAngle / convertDegrees) * velocityLength;
        }
        if (additive) {
            e.setVelocity(new Vector(newX*x+e.getVelocity().getX(), v.getY()*y*getVert(adjustVertical, e.getLocation().getDirection())+e.getVelocity().getY(),
                    newZ*z+e.getVelocity().getZ()));
        } else {
            e.setVelocity(new Vector(newX*x, v.getY()*y*getVert(adjustVertical, e.getLocation().getDirection()), newZ*z));
        }
    }
}
