package org.pizzapastarobottino.ftc.teamcode.Hardware;

public class UndeliverablePowerException extends Exception{

    public UndeliverablePowerException(double power) {
        super("Power troppo grande " + String.valueOf(power));
    }

}
