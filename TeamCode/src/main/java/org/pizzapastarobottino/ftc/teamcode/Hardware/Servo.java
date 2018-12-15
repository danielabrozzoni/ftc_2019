package org.pizzapastarobottino.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Servo extends Mechanism {
    private com.qualcomm.robotcore.hardware.Servo servo;
    private String id = null;
    private final double directionFactor;
    private final double partenzaFactor;
    public Servo(com.qualcomm.robotcore.hardware.Servo servo, String id, double directionFactor,
                 double partenzaFactor) {
        this.servo = servo;
        this.id = id;
        this.directionFactor = directionFactor;
        this.partenzaFactor = partenzaFactor;
    }

    public String getId() {
        return super.getId();
    }

    public void setId(String id) {
        super.setId(id);
    }

    public void setPosition(double position){
        servo.setPosition(position);
    }

    public double getPosition(){
        return servo.getPosition();
    }

    public void move(double delta){
        if(servo.getPosition() + delta > 1) delta = 1 - servo.getPosition();
        servo.setPosition(servo.getPosition() + delta);
    }

    @Override
    public int hashCode() {
        return super.getId().hashCode();
    }

    public void stop(){
        servo.setPosition(getPosition());
    }

    @Override
    public void setMode(DcMotor.RunMode mode) {
        // It does nothing
    }

    @Override
    public void init() {
        servo.setPosition(partenzaFactor + directionFactor*0);
    }


}
