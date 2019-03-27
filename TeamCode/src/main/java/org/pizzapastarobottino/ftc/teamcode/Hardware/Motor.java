package org.pizzapastarobottino.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Motor extends Mechanism {

    private DcMotor motor;
    private DcMotorSimple.Direction baseDirection = DcMotorSimple.Direction.FORWARD;
    private int pulsePerRound = 0;
    private int currentStepEncoder = 0;

    public Motor(DcMotor motor) {
        this.motor = motor;
    }

    public void setId(String id) {
        super.setId(id);
    }

    public void setBaseDirection(DcMotorSimple.Direction baseDirection) {
        this.baseDirection = baseDirection;
        motor.setDirection(baseDirection);
    }

    public void reverse(double power) {
        move(power, baseDirection.inverted());
    }

    public void forward(double power) {
        move(power, baseDirection);
    }

    private void move(double power, DcMotorSimple.Direction d) {
        if (power < 0) {
            d = d.inverted();
            power = -power;
        }

        motor.setDirection(d);
        motor.setPower(power);
    }

    public void move(double power){
        DcMotorSimple.Direction d = baseDirection;
        if (power < 0) {
            d = d.inverted();
            power = -power;
        }

        motor.setDirection(d);
        motor.setPower(power);
    }

    public void setPulsePerRound(int pulsePerRound) {
        this.pulsePerRound = pulsePerRound;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void goTo(int delta){
        while(motor.isBusy());
        currentStepEncoder += delta;
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setTargetPosition(currentStepEncoder);
        motor.setPower(1);
    }

    private void setPower(double power){
        motor.setPower(power);
    }

    public void stop(){
        this.setPower(0);
    }

    public void setMode(DcMotor.RunMode mode){
        motor.setMode(mode);
    }

    @Override
    public void init() {
        this.setPower(0);
    }

    public void setTargetPosition(int position) {
        motor.setTargetPosition(position);
    }

    public boolean isBusy() {
        return motor.isBusy();
    }

    public void setPower(int power) {
        motor.setPower(power);
    }

    public int getCurrentPosition() {
        return motor.getCurrentPosition();
    }

    public int getTargetPosition() {
        return motor.getTargetPosition();
    }
}