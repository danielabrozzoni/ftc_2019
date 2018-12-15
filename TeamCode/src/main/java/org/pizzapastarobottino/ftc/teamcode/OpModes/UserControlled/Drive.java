package org.pizzapastarobottino.ftc.teamcode.OpModes.UserControlled;

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

@TeleOp(name = "Guida", group = "A")
//@Disabled
public class Drive extends OpMode {
    private Hardware robot = new Hardware();

    double[] powers = new double[7];
    private final int ANTERIORE_DX = 0;
    private final int ANTERIORE_SX = 1;
    private final int POSTERIORE_DX = 2;
    private final int POSTERIORE_SX = 3;
    private final int ASSE_VERTICALE = 4;
    private final int ASSE_ORIZZONTALE = 5;
    private final int AVANTI_PINZA = 6;
    private boolean A_HAS_BEEN_PRESSED = false; // pressing a it changes the modality
    private boolean A_HAS_BEEN_RELASED = true;
    private boolean BACK_HAS_BEEN_RELASED = true;
    private boolean START_HAS_BEEN_RELASED = true;

    private double MAX_VELOCITA_MARCIA[] = {0.2, 0.5, 1};
    private int MARCIA_ATTUALE = 1;

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    private void antipanico() {
        robot.forEachMechanism(new Consumer<Mechanism>() {
            @Override
            public void accept(Mechanism mechanism) {
                mechanism.stop();
            }
        });
    }

    private void asseVerticale(double delta){
        powers[ASSE_VERTICALE] = delta;
    }

    private void asseOrizzontale(double delta){
        powers[ASSE_ORIZZONTALE] = delta;
        // Prima fai uscire pinxa, poi azze orixxontale
    }

    private void avantiPinza(double delta){
        powers[AVANTI_PINZA] = delta;
        //telemetry.addLine("AVANTI_PINZA " + powers[AVANTI_PINZA]);
    }

    private void tellState(){
        if(A_HAS_BEEN_PRESSED)
            telemetry.addLine("Modalità: Avanti pinza");
        else
            telemetry.addLine("Modalità: Asse orizzontale");

        telemetry.addLine("");
        telemetry.addLine("Marcia attuale:" + (MARCIA_ATTUALE + 1));
        telemetry.addLine("");

        telemetry.addLine("Analogico sinistro -> X: " + gamepad2.left_stick_x + " Y:" + gamepad2.left_stick_y);
        telemetry.addLine("Analogico destro -> X: " + gamepad2.right_stick_x + " Y:" + gamepad2.right_stick_y);
        telemetry.addLine("Bottoni -> A: " + gamepad2.a + " B: " + gamepad2.b + " X: " + gamepad2.x + " Y: " + gamepad2.y);
        telemetry.addLine("Dpad -> Su: " + gamepad2.dpad_up + " giu: " + gamepad2.dpad_down + " sx: " + gamepad2.dpad_left + " dx: " + gamepad2.dpad_right);
        telemetry.addLine("Bumpers -> sx " + gamepad2.left_bumper + " dx: " + gamepad2.right_bumper);
        telemetry.addLine("Trigger -> sx: " + gamepad2.left_trigger +  " dx: " + gamepad2.right_trigger);

    }

    private void trasla(double delta, double x, double y){
        double[] powers2;
        double temp = -x;
        x = -y;
        y = temp;

        try {
            powers2 = MechanumWheels.getPowerFast(delta,
                    Math.atan2(
                            y * Configs.yDirectionFactor,
                            x * Configs.xDirectionFactor
                    ));
            //telemetry.addLine(String.valueOf(powers2[0]));
            //telemetry.addLine(String.valueOf(powers2[1]));
            powers[ANTERIORE_DX] = powers[POSTERIORE_DX] = powers2[0];
            powers[ANTERIORE_SX] = powers[POSTERIORE_SX] = powers2[1];
        } catch (UndeliverablePowerException e) {
            telemetry.addLine(e.getMessage());
            telemetry.update();
        }
    }

    private void sterza(double delta, double x){
        if(x < 0)
            powers[ANTERIORE_SX] = powers[POSTERIORE_DX] = delta;
        else
            powers[ANTERIORE_DX] = powers[POSTERIORE_SX] = delta;
    }

    private void giraSuTeStesso(double delta){
        powers[ANTERIORE_SX] = powers[POSTERIORE_SX] = delta;
        powers[ANTERIORE_DX] = powers[POSTERIORE_DX] = - delta;
    }

    private void resetPowers(){
        for(int i = ANTERIORE_DX; i <= POSTERIORE_SX; i++)
            powers[i] = 0;
        for(int i = ASSE_VERTICALE; i <= AVANTI_PINZA; i++)
            powers[i] = 0;
    }

    private void frena(){
        for(int i = ANTERIORE_DX; i <= POSTERIORE_SX; i++)
            powers[i] = -0.1;
    }

    private boolean robotFermo(){
        for(int i = ANTERIORE_DX; i <= POSTERIORE_SX; i++)
            if(powers[i] > 0)
                return false;
        return true;
    }

    @Override
    public void loop() {

        tellState();

        powers = new double[7];

        double delta = Math.sqrt(gamepad2.right_trigger);

        if (gamepad2.y) {
            antipanico();
            return;
        }

        if(gamepad2.a && A_HAS_BEEN_RELASED){
            A_HAS_BEEN_PRESSED = !A_HAS_BEEN_PRESSED;
            A_HAS_BEEN_RELASED = false;
        }

        if(!gamepad2.a)
            A_HAS_BEEN_RELASED = true;


        if (gamepad2.left_stick_x != 0 || gamepad2.left_stick_y != 0) {
            trasla(delta, gamepad2.left_stick_x, gamepad2.left_stick_y);
        } else if (gamepad2.b) {
            giraSuTeStesso(delta);
        } else if (gamepad2.x) {
            giraSuTeStesso(-delta);
        } else if (gamepad2.right_stick_x != 0) {
            sterza(delta, gamepad2.right_stick_x);
        }


        if (gamepad2.dpad_up) {
            asseVerticale(delta);
        } else if(gamepad2.dpad_down) {
            asseVerticale(-delta);
        } else if (gamepad2.dpad_left && !A_HAS_BEEN_PRESSED) {
            asseOrizzontale(delta);
        } else if (gamepad2.dpad_right && !A_HAS_BEEN_PRESSED) {
            asseOrizzontale(-delta);
        } else if (gamepad2.dpad_left && A_HAS_BEEN_PRESSED) {
            avantiPinza(delta);
        } else if (gamepad2.dpad_right && A_HAS_BEEN_PRESSED) {
            avantiPinza(-delta);
        }

        if(gamepad2.back && BACK_HAS_BEEN_RELASED) {
            if(MARCIA_ATTUALE == 0) MARCIA_ATTUALE = 2;
            else MARCIA_ATTUALE--;
            BACK_HAS_BEEN_RELASED = false;
        } else if(gamepad2.start && START_HAS_BEEN_RELASED) {
            MARCIA_ATTUALE = (MARCIA_ATTUALE + 1)%MAX_VELOCITA_MARCIA.length;
            START_HAS_BEEN_RELASED = false;
        }

        if(!gamepad2.back)
            BACK_HAS_BEEN_RELASED = true;
        if(!gamepad2.start)
            START_HAS_BEEN_RELASED = true;

        aggiorna();

    }

    private void aggiorna() {
        robot.getMotor(Configs.motorRuotaAnterioreDX).move(powers[ANTERIORE_DX] * Configs.ruotaAnterioreDXrotationFactor * MAX_VELOCITA_MARCIA[MARCIA_ATTUALE]);
        robot.getMotor(Configs.motorRuotaAnterioreSX).move(powers[ANTERIORE_SX] * Configs.ruotaAnterioreSXrotationFactor * MAX_VELOCITA_MARCIA[MARCIA_ATTUALE]);
        robot.getMotor(Configs.motorRuotaPosterioreDX).move(powers[POSTERIORE_DX] * Configs.ruotaPosterioreDXrotationFactor * MAX_VELOCITA_MARCIA[MARCIA_ATTUALE]);
        robot.getMotor(Configs.motorRuotaPosterioreSX).move(powers[POSTERIORE_SX] * Configs.ruotaPosterioreSXrotationFactor * MAX_VELOCITA_MARCIA[MARCIA_ATTUALE]);
    }
}