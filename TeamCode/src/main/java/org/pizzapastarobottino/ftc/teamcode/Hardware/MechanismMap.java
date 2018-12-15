package org.pizzapastarobottino.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.HashMap;

public class MechanismMap<K> extends HashMap<K, Mechanism> {

    public Mechanism put(K key, Motor motor, DcMotorSimple.Direction baseDirection, int pulsePerRound){
        motor.setId(key.toString());
        motor.setBaseDirection(baseDirection);
        motor.setPulsePerRound(pulsePerRound);
        return super.put(key, motor);
    }

    public Mechanism put(K key, Servo servo){
        servo.setId(key.toString());
        return super.put(key, servo);
    }

}
