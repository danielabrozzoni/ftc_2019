package org.pizzapastarobottino.ftc.teamcode.OpModes.UserControlled;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Consumer;
import org.pizzapastarobottino.ftc.teamcode.Hardware.Hardware;
import org.pizzapastarobottino.ftc.teamcode.Hardware.Mechanism;
import org.pizzapastarobottino.ftc.teamcode.Movement.AutonomousMovement;
import org.pizzapastarobottino.ftc.teamcode.Movement.ControlledMovement;


/**
 * Created by Daniela on 01/06/18.
 */

@TeleOp(name = "Barcelona", group = "A")

public class Barcelona extends OpMode {
    protected Hardware robot = new Hardware();
    protected ControlledMovement mControlledMovement;
    protected AutonomousMovement mAutonomousMovement;
    private boolean eseguitoUp = false, downP = false;

    @Override
    public void init() {
        robot.init(hardwareMap);
        mControlledMovement = new ControlledMovement(robot);
        mAutonomousMovement = new AutonomousMovement(robot);
        eseguitoUp = false;
        downP = false;
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
       //telemetry.addLine("Sensore di colore1 -> R:" + robot.getColorSensor().red() + " B: " + robot.getColorSensor().blue() + " G: " + robot.getColorSensor().green() + " color int: " + robot.getColorSensor().alpha());
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
        tellState();

        if (gamepad2.right_trigger > 0) {
            mControlledMovement.braccioSu(gamepad2.right_trigger);
        }

        if (gamepad2.left_trigger > 0) {
            mControlledMovement.braccioGiu(gamepad2.left_trigger);
        }

        if (gamepad2.dpad_up && !eseguitoUp) {
            if (!MuoviGancio.isUpP()) {
                new MuoviGancio(this, true);
                eseguitoUp = true;
            }
        }

        if (gamepad2.dpad_down && eseguitoUp) {
            if (!MuoviGancio.isUpP()) {
                new MuoviGancio(this, false);
                eseguitoUp = false;
            }
        }

        if(gamepad2.x && gamepad2.y && gamepad1.right_trigger > 0.9) antipanico();

        trasla(gamepad2.left_stick_x, gamepad2.left_stick_y);

        if(Math.abs(gamepad2.right_stick_x) > 0.1) {
            mControlledMovement.giraSuTeStesso(gamepad2.right_stick_x);
        }

        mControlledMovement.aggiorna(telemetry);
    }

}

class MuoviGancio extends Thread{
    private Barcelona b;
    private boolean up;
    private static MuoviGancio instance;
    protected static boolean upP = false;

    public MuoviGancio(Barcelona b, boolean up) {
        this.b=b;
        this.up = up;
        instance = this;
        start();
    };

    public void run() {
        if (up) {
            synchronized (this) {
                upP = true;
            }
            b.telemetry.addLine("Eccoci");
            b.mAutonomousMovement.alzaGancio(1440);
            b.telemetry.addLine("Eccoci alla fine");
            synchronized (this) {
                upP = false;
            }
        }
        else {
            synchronized (this) {
                upP = true;
            }
            b.mAutonomousMovement.abbassaGancio(1440);
            synchronized (this) {
                upP = false;
            }
        }
    }

    public static boolean isUpP(){
        if(instance == null){
            return false;
        }
        synchronized (instance){
            return upP;
        }

    }


}