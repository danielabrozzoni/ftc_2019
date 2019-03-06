package org.pizzapastarobottino.ftc.teamcode.OpModes.UserControlled;

import android.util.Pair;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Consumer;
import org.pizzapastarobottino.ftc.teamcode.Configs;
import org.pizzapastarobottino.ftc.teamcode.Hardware.Hardware;
import org.pizzapastarobottino.ftc.teamcode.Hardware.Mechanism;
import org.pizzapastarobottino.ftc.teamcode.Hardware.UndeliverablePowerException;
import org.pizzapastarobottino.ftc.teamcode.Movement;


/**
 * Created by Daniela on 01/06/18.
 */

@TeleOp(name = "Barcelona", group = "A")

public class Barcelona extends OpMode {
    private Hardware robot = new Hardware();
    private Movement mMovement;
    private final double potere = 0.1;

    @Override
    public void init() {
        robot.init(hardwareMap);
        mMovement = new Movement(robot);
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
            mMovement.avanti(r);
        } else if(angolo >= 112.5 && angolo < 157.5) {      // su sinistra
            mMovement.diagonaleSuSinistra(r);
        } else if(angolo >= 157.5 || angolo < -157.5) {     // sinistra
            mMovement.sinistra(r);
        } else if(angolo >= -157.5 && angolo < -112.5) {    // giu sinistra
            mMovement.diagonaleGiuSinistra(r);
        } else if(angolo >= -112.5 && angolo < -67.5) {     // giu
            mMovement.indietro(r);
        } else if(angolo >= -67.5 && angolo < -22.5) {      // giu destra
            mMovement.diagonaleGiuDestra(r);
        } else if(angolo >= -22.5 && angolo < 22.5) {       // destra
            mMovement.destra(r);
        } else if(angolo >= 22.5 && angolo < 67.5) {        // su destra
            mMovement.diagonaleSuDestra(r);
        }
    }


    @Override
    public void loop() {

        tellState();


        //if (gamepad2.y) {
         //   antipanico();
         //   return;
        //}

        //cosi non puoi premere x e y in contemporanea
        if(gamepad2.y) {
            while (gamepad2.y)
                mMovement.alzaBraccio(potere);
        }
        else {
            while (gamepad2.x)
                mMovement.abbassaBraccio(potere);
        }
        trasla(gamepad2.left_stick_x, gamepad2.left_stick_y);

        if(Math.abs(gamepad2.right_stick_x) > 0.1) {
            mMovement.giraSuTeStesso(gamepad2.right_stick_x);
        }

        //trasla(gamepad2.dpad_up, gamepad2.dpad_down, gamepad2.dpad_left, gamepad2.dpad_right);

        mMovement.aggiorna();
    }


}