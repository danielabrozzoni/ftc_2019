package org.pizzapastarobottino.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Mechanism {

    private String id = null;

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract void move(double delta);

    public abstract void stop();

    public abstract void setMode(DcMotor.RunMode mode);

    public abstract void init();
}
