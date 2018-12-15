package org.pizzapastarobottino.ftc.teamcode.OpModes.UserControlled;

import org.pizzapastarobottino.ftc.teamcode.Hardware.UndeliverablePowerException;

import java.lang.Math;


import static java.lang.Math.max;

public class MechanumWheels {

    /**
     * Given two values, it returns two values that are less than 1.0 and are in proportion
     * with the initial ones
     *
     * @param a first value
     * @param b second value
     * @return two values that are in proportion with (a, b) and are less than 1.0
     */

    private static double[] normalize(double a, double b) {
        if (max(Math.abs(a), Math.abs(b)) <= 1.0) return new double[]{a, b};
        return new double[]{a / max(Math.abs(a), Math.abs(b)), b / max(Math.abs(a), Math.abs(b))};
    }

    @Deprecated
    public static double[] getPower(double angle, double power) throws UndeliverablePowerException {
        if (power > 1) {
            throw new UndeliverablePowerException(power);
        }
        /*double cos45 = Math.cos(Math.PI / 4);
        Complex given = new Complex(power * (1+cos45), 0);
        given = given.times(new Complex(Math.cos(angle), Math.sin(angle)));
        given = given.times(new Complex(cos45, cos45));
        return normalize(given.re(), -given.im());
        */

        double cos45 = Math.cos(Math.PI / 4);
        Complex given = new Complex(Math.cos(angle), Math.sin(angle));
        given = given.times(new Complex(cos45, cos45));
        return normalize(given.re() * power, given.im() * power);
    }

    public static double[] getPowerFast(double power, double angle) throws UndeliverablePowerException {
        if (power > 1) {
            throw new UndeliverablePowerException(power);
        }
        return new double[]{Math.cos(angle + Math.PI / 4) * power, Math.sin(angle + Math.PI / 4) * power};
    }
}