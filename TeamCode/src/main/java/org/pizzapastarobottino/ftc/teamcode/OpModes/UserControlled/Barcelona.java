package org.pizzapastarobottino.ftc.teamcode.OpModes.UserControlled;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Consumer;
import org.pizzapastarobottino.ftc.teamcode.Hardware.Hardware;
import org.pizzapastarobottino.ftc.teamcode.Hardware.Mechanism;
import org.pizzapastarobottino.ftc.teamcode.Movement.ControlledMovement;


/**
 * Created by Daniela on 01/06/18.
 */

@TeleOp(name = "Barcelona", group = "A")

public class Barcelona extends OpMode {
    private Hardware robot = new Hardware();
    private ControlledMovement mControlledMovement;

    @Override
    public void init() {
        robot.init(hardwareMap);
        mControlledMovement = new ControlledMovement(robot);
    }

    private void antipanico() {
        robot.forEachMechanism(new Consumer<Mechanism>() {
            @Override
            public void accept(Mechanism mechanism) {
                mechanism.stop();
            }
        });
    }

    private void tellState(){
        telemetry.addLine("Analogico sinistro -> X: " + gamepad2.left_stick_x + " Y:" + gamepad2.left_stick_y);
        telemetry.addLine("Analogico destro -> X: " + gamepad2.right_stick_x + " Y:" + gamepad2.right_stick_y);
        telemetry.addLine("Bottoni -> A: " + gamepad2.a + " B: " + gamepad2.b + " X: " + gamepad2.x + " Y: " + gamepad2.y);
        telemetry.addLine("Dpad -> Su: " + gamepad2.dpad_up + " giu: " + gamepad2.dpad_down + " sx: " + gamepad2.dpad_left + " dx: " + gamepad2.dpad_right);
        telemetry.addLine("Bumpers -> sx " + gamepad2.left_bumper + " dx: " + gamepad2.right_bumper);
        telemetry.addLine("Trigger -> sx: " + gamepad2.left_trigger +  " dx: " + gamepad2.right_trigger);
        telemetry.addLine("Sensore di colore1 -> R:" + robot.getColorSensor().red() + " B: " + robot.getColorSensor().blue() + " G: " + robot.getColorSensor().green() + " color int: " + robot.getColorSensor().alpha());
    }


    private void trasla(float x, float y) {

        float r = x * x + y * y;

        if(r < 0.2)
            return;

        y = -y;
        double angolo = Math.toDegrees(Math.atan2(y, x));
        //telemetry.addLine("Arcatan: " + angolo);


        if(angolo >= 67.5 && angolo < 112.5) {              //su
            mControlledMovement.avanti(r);
        } else if(angolo >= 112.5 && angolo < 157.5) {      // su sinistra
            mControlledMovement.diagonaleSuSinistra(r);
        } else if(angolo >= 157.5 || angolo < -157.5) {     // sinistra
            mControlledMovement.sinistra(r);
        } else if(angolo >= -157.5 && angolo < -112.5) {    // giu sinistra
            mControlledMovement.diagonaleGiuSinistra(r);
        } else if(angolo >= -112.5 && angolo < -67.5) {     // giu
            mControlledMovement.indietro(r);
        } else if(angolo >= -67.5 && angolo < -22.5) {      // giu destra
            mControlledMovement.diagonaleGiuDestra(r);
        } else if(angolo >= -22.5 && angolo < 22.5) {       // destra
            mControlledMovement.destra(r);
        } else if(angolo >= 22.5 && angolo < 67.5) {        // su destra
            mControlledMovement.diagonaleSuDestra(r);
        }
    }

    @Override
    public void loop() {
        float potenza = 1;
        tellState();

        if (gamepad2.right_trigger > 0) {
            mControlledMovement.braccioSu(gamepad2.right_trigger);
        }

        if (gamepad2.left_trigger > 0) {
            mControlledMovement.braccioGiu(gamepad2.left_trigger);
        }

        if (gamepad2.y) {
            mControlledMovement.alzaGancio(potenza);
        }

        if (gamepad2.x) {
            mControlledMovement.markerOut(potenza);
        }

        if (gamepad2.a) {
            mControlledMovement.abbassaGancio(potenza);
        }

        trasla(gamepad2.left_stick_x, gamepad2.left_stick_y);

        if(Math.abs(gamepad2.right_stick_x) > 0.1) {
            mControlledMovement.giraSuTeStesso(gamepad2.right_stick_x);
        }

        mControlledMovement.aggiorna();
    }

}