package com.lichenaut.dashapi.util;

import java.util.HashMap;

public class DVelocityReference {

    public static Double getXZVelocities(int blocks) {
        return blocks * 0.090069767;
    }// 3.873 divided by 43

    public static HashMap<Integer, Double> getYVelocities() {
        HashMap<Integer, Double> yVelocities = new HashMap<>(61);
        yVelocities.put(0, 0.0);
        yVelocities.put(1, 0.369);
        yVelocities.put(2, 0.545);
        yVelocities.put(3, 0.685);
        yVelocities.put(4, 0.804);
        yVelocities.put(5, 0.91);
        yVelocities.put(6, 1.008);
        yVelocities.put(7, 1.099);
        yVelocities.put(8, 1.185);
        yVelocities.put(9, 1.266);
        yVelocities.put(10, 1.344);
        yVelocities.put(11, 1.419);
        yVelocities.put(12, 1.491);
        yVelocities.put(13, 1.56);
        yVelocities.put(14, 1.628);
        yVelocities.put(15, 1.693);
        yVelocities.put(16, 1.757);
        yVelocities.put(17, 1.82);
        yVelocities.put(18, 1.881);
        yVelocities.put(19, 1.941);
        yVelocities.put(20, 1.999);
        yVelocities.put(21, 2.057);
        yVelocities.put(22, 2.113);
        yVelocities.put(23, 2.169);
        yVelocities.put(24, 2.224);
        yVelocities.put(25, 2.278);
        yVelocities.put(26, 2.331);
        yVelocities.put(27, 2.383);
        yVelocities.put(28, 2.435);
        yVelocities.put(29, 2.486);
        yVelocities.put(30, 2.536);
        yVelocities.put(31, 2.587);
        yVelocities.put(32, 2.635);
        yVelocities.put(33, 2.684);
        yVelocities.put(34, 2.733);
        yVelocities.put(35, 2.78);
        yVelocities.put(36, 2.828);
        yVelocities.put(37, 2.875);
        yVelocities.put(38, 2.921);
        yVelocities.put(39, 2.967);
        yVelocities.put(40, 3.013);
        yVelocities.put(41, 3.058);
        yVelocities.put(42, 3.103);
        yVelocities.put(43, 3.147);
        yVelocities.put(44, 3.191);
        yVelocities.put(45, 3.235);
        yVelocities.put(46, 3.279);
        yVelocities.put(47, 3.322);
        yVelocities.put(48, 3.365);
        yVelocities.put(49, 3.408);
        yVelocities.put(50, 3.45);
        yVelocities.put(51, 3.492);
        yVelocities.put(52, 3.534);
        yVelocities.put(53, 3.576);
        yVelocities.put(54, 3.617);
        yVelocities.put(55, 3.658);
        yVelocities.put(56, 3.699);
        yVelocities.put(57, 3.74);
        yVelocities.put(58, 3.78);
        yVelocities.put(59, 3.82);
        yVelocities.put(60, 3.861);
        return yVelocities;
    }
}
