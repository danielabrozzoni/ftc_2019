package org.pizzapastarobottino.ftc.teamcode.OpModes.UserControlled;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Consumer;
import org.pizzapastarobottino.ftc.teamcode.Configs;
import org.pizzapastarobottino.ftc.teamcode.Hardware.Hardware;
import org.pizzapastarobottino.ftc.teamcode.Hardware.Mechanism;
import org.pizzapastarobottino.ftc.teamcode.Hardware.Motor;

@TeleOp(name = "GuidaEnc", group = "A")
@Deprecated
public class EncodersSpeed extends OpMode {

    private Hardware robot = new Hardware();

    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.forEachMechanism(new Consumer<Mechanism>() {
            @Override
            public void accept(Mechanism motor) {
                motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                motor.move(0);
            }
        });
    }

    @Override
    public void loop() {
        robot.getMotor(Configs.motorRuotaPosterioreDX).goTo(1000);

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        robot.getMotor(Configs.motorRuotaPosterioreSX).goTo(1000);

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        robot.getMotor(Configs.motorRuotaAnterioreDX).goTo(1000);

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        robot.getMotor(Configs.motorRuotaAnterioreSX).goTo(1000);

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
