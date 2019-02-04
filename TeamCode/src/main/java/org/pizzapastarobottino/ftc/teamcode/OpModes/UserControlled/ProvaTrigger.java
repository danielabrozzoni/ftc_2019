package org.pizzapastarobottino.ftc.teamcode.OpModes.UserControlled;

import android.util.Pair;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Consumer;
import org.pizzapastarobottino.ftc.teamcode.Configs;
import org.pizzapastarobottino.ftc.teamcode.Hardware.Hardware;
import org.pizzapastarobottino.ftc.teamcode.Hardware.Mechanism;
import org.pizzapastarobottino.ftc.teamcode.Hardware.UndeliverablePowerException;


/**
 * Created by Daniela on 01/06/18.
 */

@TeleOp(name = "ProvaTrigger", group = "A")

public class ProvaTrigger extends OpMode {
    private Hardware robot = new Hardware();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    private void tellState(){
        telemetry.addLine("Analogico sinistro -> X: " + gamepad2.left_stick_x + " Y:" + gamepad2.left_stick_y);
        telemetry.addLine("Analogico destro -> X: " + gamepad2.right_stick_x + " Y:" + gamepad2.right_stick_y);
        telemetry.addLine("Bottoni -> A: " + gamepad2.a + " B: " + gamepad2.b + " X: " + gamepad2.x + " Y: " + gamepad2.y);
        telemetry.addLine("Dpad -> Su: " + gamepad2.dpad_up + " giu: " + gamepad2.dpad_down + " sx: " + gamepad2.dpad_left + " dx: " + gamepad2.dpad_right);
        telemetry.addLine("Bumpers -> sx " + gamepad2.left_bumper + " dx: " + gamepad2.right_bumper);
        telemetry.addLine("Trigger -> sx: " + gamepad2.left_trigger +  " dx: " + gamepad2.right_trigger);
        telemetry.addLine("Trigger -> sx: " + gamepad1.left_trigger +  " dx: " + gamepad1.right_trigger);
    }


    @Override
    public void loop() {

        tellState();

    }
}