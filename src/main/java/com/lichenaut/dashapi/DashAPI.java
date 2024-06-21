package com.lichenaut.dashapi;

import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import com.lichenaut.dashapi.util.VelocityReference;

@SuppressWarnings("unused")
public class DashAPI {

    private int[][] processValues(int xyz, int... values) {
        int valuesLength = values.length;
        int[][] processedValues = new int[valuesLength][2];
        for (int i = 0; i < valuesLength; i++) {
            int[] valueCheck = (values[i] < 0) ? new int[] { Math.abs(values[i]), -1 } : new int[] { values[i], 1 };
            int maxValue;
            if (xyz > 99) {
                maxValue = 43;
                xyz -= 100;
            } else if (xyz < 10) {
                maxValue = 43;
            } else {
                maxValue = 60;
                xyz -= 10;
            }

            processedValues[i][0] = Math.min(valueCheck[0], maxValue);
            processedValues[i][1] = valueCheck[1];
        }

        return processedValues;
    }

    private <T extends Number> double[] prepMath(Entity e, T x, T y, T z) {
        double newX, newY, newZ;
        if (x instanceof Integer) {
            newX = VelocityReference.getXZVelocities((int) x);
        } else {
            newX = (double) x;
        }
        if (y instanceof Integer) {
            newY = VelocityReference.getYVelocities().get((int) y);
        } else {
            newY = (double) y;
        }
        if (z instanceof Integer) {
            newZ = VelocityReference.getXZVelocities((int) z);
        } else {
            newZ = (double) z;
        }

        return new double[] { newX, newY, newZ };
    }

    private <T extends Number> void dashMath(Entity e, T x, T y, T z) {
        double[] newValues = prepMath(e, x, y, z);
        e.setVelocity(new Vector(newValues[0], newValues[1], newValues[2]));
    }

    private <T extends Number> void dashMath(Entity e, T x, T y, T z, boolean additive, boolean adjustHorizontal,
            boolean adjustVertical) {
        double[] newValues = prepMath(e, x, y, z);
        double newX = newValues[0];
        double newY = newValues[1];
        double newZ = newValues[2];

        if (adjustHorizontal) {
            Vector entityDir = e.getLocation().getDirection();
            double convertDegrees = 180 / Math.PI;
            double entityLook = -Math.atan2(entityDir.getZ(), entityDir.getX()) * convertDegrees + 90;
            if (entityLook < 0) {
                entityLook += 360;
            }
            double dashAngle = 90 + Math.atan2(newZ,
                    newX)
                    * convertDegrees;
            double velocityLength = Math.sqrt(Math.pow(newZ, 2) +
                    Math.pow(newX, 2));
            double newDashAngle = entityLook - dashAngle + 90;
            if (newDashAngle < 0) {
                newDashAngle += 360;
            }
            newZ = Math.cos(newDashAngle / convertDegrees) * velocityLength;
            newX = Math.sin(newDashAngle / convertDegrees) * velocityLength;
        }

        double vert = adjustVertical ? e.getLocation().getDirection().getY() : 1;
        Vector dashVector;
        if (additive) {
            Vector entityVelocity = e.getVelocity();
            dashVector = new Vector(
                    newX + entityVelocity.getX(),
                    newY * vert + entityVelocity.getY(),
                    newZ + entityVelocity.getZ());
        } else {
            dashVector = new Vector(newX,
                    newY
                            * vert,
                    newZ);
        }
        e.setVelocity(dashVector);
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x The number of blocks the entity is to be dashed forwards/backwards.
     *          Positive values are forwards, negative values are backwards, and the
     *          maximum is 43.
     * @param y The number of blocks the entity is to be dashed up/down. Positive
     *          values are up, negative values are down, and the maximum is 60.
     * @param z The number of blocks the entity is to be dashed right/left. Positive
     *          values are right, negative values are left, and the maximum is 43.
     */
    public void dash(Entity e, int x, int y, int z) {
        int[][] processedValues = processValues(111, x, y, z);
        dashMath(e, processedValues[0][0] * processedValues[0][1], processedValues[1][0] * processedValues[1][1],
                processedValues[2][0] * processedValues[2][1]);
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x The number of blocks the entity is to be dashed forwards/backwards.
     *          Positive values are forwards, negative values are backwards, and the
     *          maximum is 43.
     * @param y The number of blocks the entity is to be dashed up/down. Positive
     *          values are up, negative values are down, and the maximum is 60.
     * @param z This can be a value from a vector. The entity will be dashed
     *          right/left according to this value.
     */
    public void dash(Entity e, int x, int y, Double z) {
        int[][] processedValues = processValues(110, x, y);
        dashMath(e, processedValues[0][0] * processedValues[0][1], processedValues[1][0] * processedValues[1][1], z);
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x The number of blocks the entity is to be dashed forwards/backwards.
     *          Positive values are forwards, negative values are backwards, and the
     *          maximum is 43.
     * @param y This can be a value from a vector. The entity will be dashed up/down
     *          according to this value.
     * @param z This can be a value from a vector. The entity will be dashed
     *          right/left according to this value.
     */
    public void dash(Entity e, int x, Double y, Double z) {
        int[][] processedValues = processValues(100, x);
        dashMath(e, processedValues[0][0] * processedValues[0][1], y, z);
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x This can be a value from a vector. The entity will be dashed
     *          forwards/backwards according to this value.
     * @param y This can be a value from a vector. The entity will be dashed up/down
     *          according to this value.
     * @param z This can be a value from a vector. The entity will be dashed
     *          right/left according to this value.
     */
    public void dash(Entity e, Double x, Double y, Double z) {
        e.setVelocity(new Vector(x, y, z));
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x This can be a value from a vector. The entity will be dashed
     *          forwards/backwards according to this value.
     * @param y The number of blocks the entity is to be dashed up/down. Positive
     *          values are up, negative values are down, and the maximum is 60.
     * @param z This can be a value from a vector. The entity will be dashed
     *          right/left according to this value.
     */
    public void dash(Entity e, Double x, int y, Double z) {
        int[][] processedValues = processValues(10, y);
        dashMath(e, x, processedValues[0][0] * processedValues[0][1], z);
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x This can be a value from a vector. The entity will be dashed
     *          forwards/backwards according to this value.
     * @param y The number of blocks the entity is to be dashed up/down. Positive
     *          values are up, negative values are down, and the maximum is 60.
     * @param z The number of blocks the entity is to be dashed right/left. Positive
     *          values are right, negative values are left, and the maximum is 43.
     */
    public void dash(Entity e, Double x, int y, int z) {
        int[][] processedValues = processValues(11, y, z);
        dashMath(e, x, processedValues[0][0] * processedValues[0][1], processedValues[1][0] * processedValues[1][1]);
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x This can be a value from a vector. The entity will be dashed
     *          forwards/backwards according to this value.
     * @param y This can be a value from a vector. The entity will be dashed up/down
     *          according to this value.
     * @param z The number of blocks the entity is to be dashed right/left. Positive
     *          values are right, negative values are left, and the maximum is 43.
     */
    public void dash(Entity e, Double x, Double y, int z) {
        int[][] processedValues = processValues(1, z);
        dashMath(e, x, y, processedValues[0][0] * processedValues[0][1]);
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param x The number of blocks the entity is to be dashed forwards/backwards.
     *          Positive values are forwards, negative values are backwards, and the
     *          maximum is 43.
     * @param y This can be a value from a vector. The entity will be dashed up/down
     *          according to this value.
     * @param z The number of blocks the entity is to be dashed right/left. Positive
     *          values are right, negative values are left, and the maximum is 43.
     */
    public void dash(Entity e, int x, Double y, int z) {
        int[][] processedValues = processValues(101, x, z);
        dashMath(e, processedValues[0][0] * processedValues[0][1], y, processedValues[1][0] * processedValues[1][1]);
    }

    /**
     *
     * @param e                The entity to be dashed.
     * @param x                The number of blocks the entity is to be dashed
     *                         forwards/backwards. Positive values are forwards,
     *                         negative values are backwards, and the maximum is 43.
     * @param y                The number of blocks the entity is to be dashed
     *                         up/down. Positive values are up, negative values are
     *                         down, and the maximum is 60.
     * @param z                The number of blocks the entity is to be dashed
     *                         right/left. Positive values are right, negative
     *                         values are left, and the maximum is 43.
     * @param additive         Whether the entity's current velocity is added to the
     *                         dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the
     *                         direction of the dash.
     * @param adjustVertical   Whether the entity's looking up/down affects the y of
     *                         the dash.
     */
    public void dash(Entity e, int x, int y, int z, boolean additive, boolean adjustHorizontal,
            boolean adjustVertical) {
        int[][] processedValues = processValues(111, x, y, z);
        dashMath(e, processedValues[0][0] * processedValues[0][1], processedValues[1][0] * processedValues[1][1],
                processedValues[2][0] * processedValues[2][1], additive, adjustHorizontal, adjustVertical);
    }

    /**
     *
     * @param e                The entity to be dashed.
     * @param x                The number of blocks the entity is to be dashed
     *                         forwards/backwards. Positive values are forwards,
     *                         negative values are backwards, and the maximum is 43.
     * @param y                The number of blocks the entity is to be dashed
     *                         up/down. Positive values are up, negative values are
     *                         down, and the maximum is 60.
     * @param z                This can be a value from a vector. The entity will be
     *                         dashed right/left according to this value.
     * @param additive         Whether the entity's current velocity is added to the
     *                         dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the
     *                         direction of the dash.
     * @param adjustVertical   Whether the entity's looking up/down affects the y of
     *                         the dash.
     */
    public void dash(Entity e, int x, int y, Double z, boolean additive, boolean adjustHorizontal,
            boolean adjustVertical) {
        int[][] processedValues = processValues(110, x, y);
        dashMath(e, processedValues[0][0] * processedValues[0][1], processedValues[1][0] * processedValues[1][1], z,
                additive, adjustHorizontal, adjustVertical);
    }

    /**
     *
     * @param e                The entity to be dashed.
     * @param x                The number of blocks the entity is to be dashed
     *                         forwards/backwards. Positive values are forwards,
     *                         negative values are backwards, and the maximum is 43.
     * @param y                This can be a value from a vector. The entity will be
     *                         dashed up/down according to this value.
     * @param z                This can be a value from a vector. The entity will be
     *                         dashed right/left according to this value.
     * @param additive         Whether the entity's current velocity is added to the
     *                         dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the
     *                         direction of the dash.
     * @param adjustVertical   Whether the entity's looking up/down affects the y of
     *                         the dash.
     */
    public void dash(Entity e, int x, Double y, Double z, boolean additive, boolean adjustHorizontal,
            boolean adjustVertical) {
        int[][] processedValues = processValues(100, x);
        dashMath(e, processedValues[0][0] * processedValues[0][1], y, z, additive, adjustHorizontal, adjustVertical);
    }

    /**
     *
     * @param e                The entity to be dashed.
     * @param x                This can be a value from a vector. The entity will be
     *                         dashed forwards/backwards according to this value.
     * @param y                This can be a value from a vector. The entity will be
     *                         dashed up/down according to this value.
     * @param z                This can be a value from a vector. The entity will be
     *                         dashed right/left according to this value.
     * @param additive         Whether the entity's current velocity is added to the
     *                         dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the
     *                         direction of the dash.
     * @param adjustVertical   Whether the entity's looking up/down affects the y of
     *                         the dash.
     */
    public void dash(Entity e, Double x, Double y, Double z, boolean additive, boolean adjustHorizontal,
            boolean adjustVertical) {
        dashMath(e, x, y, z, additive, adjustHorizontal, adjustVertical);
    }

    /**
     *
     * @param e                The entity to be dashed.
     * @param x                This can be a value from a vector. The entity will be
     *                         dashed forwards/backwards according to this value.
     * @param y                The number of blocks the entity is to be dashed
     *                         up/down. Positive values are up, negative values are
     *                         down, and the maximum is 60.
     * @param z                This can be a value from a vector. The entity will be
     *                         dashed right/left according to this value.
     * @param additive         Whether the entity's current velocity is added to the
     *                         dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the
     *                         direction of the dash.
     * @param adjustVertical   Whether the entity's looking up/down affects the y of
     *                         the dash.
     */
    public void dash(Entity e, Double x, int y, Double z, boolean additive, boolean adjustHorizontal,
            boolean adjustVertical) {
        int[][] processedValues = processValues(10, y);
        dashMath(e, x, processedValues[0][0] * processedValues[0][1], z, additive, adjustHorizontal, adjustVertical);
    }

    /**
     *
     * @param e                The entity to be dashed.
     * @param x                This can be a value from a vector. The entity will be
     *                         dashed forwards/backwards according to this value.
     * @param y                The number of blocks the entity is to be dashed
     *                         up/down. Positive values are up, negative values are
     *                         down, and the maximum is 60.
     * @param z                The number of blocks the entity is to be dashed
     *                         right/left. Positive values are right, negative
     *                         values are left, and the maximum is 43.
     * @param additive         Whether the entity's current velocity is added to the
     *                         dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the
     *                         direction of the dash.
     * @param adjustVertical   Whether the entity's looking up/down affects the y of
     *                         the dash.
     */
    public void dash(Entity e, Double x, int y, int z, boolean additive, boolean adjustHorizontal,
            boolean adjustVertical) {
        int[][] processedValues = processValues(11, y, z);
        dashMath(e, x, processedValues[0][0] * processedValues[0][1], processedValues[1][0] * processedValues[1][1],
                additive, adjustHorizontal, adjustVertical);
    }

    /**
     *
     * @param e                The entity to be dashed.
     * @param x                This can be a value from a vector. The entity will be
     *                         dashed forwards/backwards according to this value.
     * @param y                This can be a value from a vector. The entity will be
     *                         dashed up/down according to this value.
     * @param z                The number of blocks the entity is to be dashed
     *                         right/left. Positive values are right, negative
     *                         values are left, and the maximum is 43.
     * @param additive         Whether the entity's current velocity is added to the
     *                         dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the
     *                         direction of the dash.
     * @param adjustVertical   Whether the entity's looking up/down affects the y of
     *                         the dash.
     */
    public void dash(Entity e, Double x, Double y, int z, boolean additive, boolean adjustHorizontal,
            boolean adjustVertical) {
        int[][] processedValues = processValues(1, z);
        dashMath(e, x, y, processedValues[0][0] * processedValues[0][1], additive, adjustHorizontal, adjustVertical);
    }

    /**
     *
     * @param e                The entity to be dashed.
     * @param x                The number of blocks the entity is to be dashed
     *                         forwards/backwards. Positive values are forwards,
     *                         negative values are backwards, and the maximum is 43.
     * @param y                This can be a value from a vector. The entity will be
     *                         dashed up/down according to this value.
     * @param z                The number of blocks the entity is to be dashed
     *                         right/left. Positive values are right, negative
     *                         values are left, and the maximum is 43.
     * @param additive         Whether the entity's current velocity is added to the
     *                         dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the
     *                         direction of the dash.
     * @param adjustVertical   Whether the entity's looking up/down affects the y of
     *                         the dash.
     */
    public void dash(Entity e, int x, Double y, int z, boolean additive, boolean adjustHorizontal,
            boolean adjustVertical) {
        int[][] processedValues = processValues(101, x, z);
        dashMath(e, processedValues[0][0] * processedValues[0][1], y, processedValues[1][0] * processedValues[1][1],
                additive, adjustHorizontal, adjustVertical);
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param v The vector the entity is dashed with.
     */
    public void dash(Entity e, Vector v) {
        e.setVelocity(v);
    }

    /**
     *
     * @param e The entity to be dashed.
     * @param v The vector the entity is dashed with.
     * @param x Multiplier for the vector's x value.
     * @param y Multiplier for the vector's y value.
     * @param z Multiplier for the vector's z value.
     */
    public void dash(Entity e, Vector v, Double x, Double y, Double z) {
        e.setVelocity(new Vector(v.getX() * x, v.getY() * y, v.getZ() * z));
    }

    /**
     *
     * @param e                The entity to be dashed.
     * @param v                The vector the entity is dashed with.
     * @param additive         Whether the entity's current velocity is added to the
     *                         dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the
     *                         direction of the dash.
     * @param adjustVertical   Whether the entity's looking up/down affects the y of
     *                         the dash vector.
     */
    public void dash(Entity e, Vector v, boolean additive, boolean adjustHorizontal, boolean adjustVertical) {
        dashMath(e, v.getX(), v.getY(), v.getZ(), additive, adjustHorizontal, adjustVertical);
    }

    /**
     *
     * @param e                The entity to be dashed.
     * @param v                The vector the entity is dashed with.
     * @param x                Multiplier for the vector's x value.
     * @param y                Multiplier for the vector's y value.
     * @param z                Multiplier for the vector's z value.
     * @param additive         Whether the entity's current velocity is added to the
     *                         dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the
     *                         direction of the dash.
     * @param adjustVertical   Whether the entity's looking up/down affects the y of
     *                         the dash vector.
     */
    public void dash(Entity e, Vector v, Double x, Double y, Double z, boolean additive, boolean adjustHorizontal,
            boolean adjustVertical) {
        dashMath(e, v.getX() * x, v.getY() * y, v.getZ() * z, additive, adjustHorizontal, adjustVertical);
    }

    /**
     *
     * @param e                The entity to be dashed.
     * @param v                The vector the entity is dashed with.
     * @param additive         Whether the entity's current velocity is added to the
     *                         dash.
     * @param adjustHorizontal Whether the entity's looking angle affects the
     *                         direction of the dash.
     * @param adjustVertical   Whether the entity's looking up/down affects the y of
     *                         the dash vector.
     * @param x                Multiplier for the vector's x value.
     * @param y                Multiplier for the vector's y value.
     * @param z                Multiplier for the vector's z value.
     */
    public void dash(Entity e, Vector v, boolean additive, boolean adjustHorizontal, boolean adjustVertical, Double x,
            Double y, Double z) {
        dashMath(e, v.getX() * x, v.getY() * y, v.getZ() * z, additive, adjustHorizontal, adjustVertical);
    }
}
