package org.pizzapastarobottino.ftc.teamcode.Movement;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.pizzapastarobottino.ftc.teamcode.Configs;
import org.pizzapastarobottino.ftc.teamcode.Hardware.Hardware;
import org.pizzapastarobottino.ftc.teamcode.Hardware.Motor;

import java.util.ArrayList;

public class AutonomousMovement {

    private Hardware robot;

    public final ArrayList<Motor> motors = new ArrayList<>();
    public final ArrayList<Boolean> motoriAttivi = new ArrayList<>();


    private final int ANTERIORE_DX = 0;
    private final int ANTERIORE_SX = 1;
    private final int POSTERIORE_DX = 2;
    private final int POSTERIORE_SX = 3;
    private final int GANCIO = 4;

    public AutonomousMovement(Hardware robot) {
        this.robot = robot;
        motors.add(ANTERIORE_DX, robot.getMotor(Configs.motorRuotaAnterioreDX));
        motors.add(ANTERIORE_SX, robot.getMotor(Configs.motorRuotaAnterioreSX));
        motors.add(POSTERIORE_DX, robot.getMotor(Configs.motorRuotaPosterioreDX));
        motors.add(POSTERIORE_SX, robot.getMotor(Configs.motorRuotaPosterioreSX));
        motors.add(GANCIO, robot.getMotor(Configs.motorGancio));
        motoriAttivi.add(ANTERIORE_DX, true);
        motoriAttivi.add(ANTERIORE_SX, true);
        motoriAttivi.add(POSTERIORE_DX, true);
        motoriAttivi.add(POSTERIORE_SX, true);

        for(Motor m: motors) {

            m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            m.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void indietro(int posizione) {
        motors.get(ANTERIORE_DX).setBaseDirection(Configs.indietro);
        motors.get(POSTERIORE_DX).setBaseDirection(Configs.indietro);
        motors.get(POSTERIORE_SX).setBaseDirection(Configs.avanti);
        motors.get(ANTERIORE_SX).setBaseDirection(Configs.avanti);

        motoriAttivi.set(ANTERIORE_SX, true);
        motoriAttivi.set(ANTERIORE_DX, true);
        motoriAttivi.set(POSTERIORE_DX, true);
        motoriAttivi.set(POSTERIORE_SX, true);

        motors.get(ANTERIORE_DX).setTargetPosition(posizione);
        motors.get(ANTERIORE_SX).setTargetPosition(posizione);
        motors.get(POSTERIORE_DX).setTargetPosition(posizione);
        motors.get(POSTERIORE_SX).setTargetPosition(posizione);

        motors.get(POSTERIORE_DX).setPower(1);
        motors.get(POSTERIORE_SX).setPower(1);
        motors.get(ANTERIORE_DX).setPower(1);
        motors.get(ANTERIORE_SX).setPower(1);

    }

    public void avanti(int posizione) {
        motors.get(ANTERIORE_SX).setBaseDirection(Configs.indietro);
        motors.get(POSTERIORE_SX).setBaseDirection(Configs.indietro);
        motors.get(POSTERIORE_DX).setBaseDirection(Configs.avanti);
        motors.get(ANTERIORE_DX).setBaseDirection(Configs.avanti);

        motoriAttivi.set(ANTERIORE_SX, true);
        motoriAttivi.set(ANTERIORE_DX, true);
        motoriAttivi.set(POSTERIORE_DX, true);
        motoriAttivi.set(POSTERIORE_SX, true);


        motors.get(ANTERIORE_DX).setTargetPosition(posizione);

        motors.get(POSTERIORE_DX).setPower(1);
        motors.get(POSTERIORE_SX).setPower(1);
        motors.get(ANTERIORE_DX).setPower(1);
        motors.get(ANTERIORE_SX).setPower(1);

        while(motors.get(ANTERIORE_DX).getCurrentPosition() < posizione) { }

            motors.get(POSTERIORE_DX).setPower(0);
            motors.get(POSTERIORE_SX).setPower(0);
            motors.get(ANTERIORE_DX).setPower(0);
            motors.get(ANTERIORE_SX).setPower(0);


    }

    public void destra(int posizione) {
        motors.get(ANTERIORE_SX).setBaseDirection(Configs.indietro);
        motors.get(POSTERIORE_SX).setBaseDirection(Configs.avanti);
        motors.get(POSTERIORE_DX).setBaseDirection(Configs.avanti);
        motors.get(ANTERIORE_DX).setBaseDirection(Configs.indietro);

        motoriAttivi.set(ANTERIORE_SX, true);
        motoriAttivi.set(ANTERIORE_DX, true);
        motoriAttivi.set(POSTERIORE_DX, true);
        motoriAttivi.set(POSTERIORE_SX, true);

        motors.get(ANTERIORE_DX).setTargetPosition(posizione);

        motors.get(POSTERIORE_DX).setPower(1);
        motors.get(POSTERIORE_SX).setPower(1);
        motors.get(ANTERIORE_DX).setPower(1);
        motors.get(ANTERIORE_SX).setPower(1);

        while(motors.get(ANTERIORE_DX).getCurrentPosition() < posizione) { }

        motors.get(POSTERIORE_DX).setPower(0);
        motors.get(POSTERIORE_SX).setPower(0);
        motors.get(ANTERIORE_DX).setPower(0);
        motors.get(ANTERIORE_SX).setPower(0);

    }

    public void giraSuTeStesso(int posizione) {
        motors.get(ANTERIORE_SX).setBaseDirection(Configs.indietro);
        motors.get(POSTERIORE_SX).setBaseDirection(Configs.indietro);
        motors.get(POSTERIORE_DX).setBaseDirection(Configs.indietro);
        motors.get(ANTERIORE_DX).setBaseDirection(Configs.indietro);

        motoriAttivi.set(ANTERIORE_SX, true);
        motoriAttivi.set(ANTERIORE_DX, true);
        motoriAttivi.set(POSTERIORE_DX, true);
        motoriAttivi.set(POSTERIORE_SX, true);

        motors.get(ANTERIORE_DX).setTargetPosition(posizione);

        motors.get(POSTERIORE_DX).setPower(1);
        motors.get(POSTERIORE_SX).setPower(1);
        motors.get(ANTERIORE_DX).setPower(1);
        motors.get(ANTERIORE_SX).setPower(1);

        while(motors.get(ANTERIORE_DX).getCurrentPosition() < posizione) { }

        motors.get(POSTERIORE_DX).setPower(0);
        motors.get(POSTERIORE_SX).setPower(0);
        motors.get(ANTERIORE_DX).setPower(0);
        motors.get(ANTERIORE_SX).setPower(0);

    }

    public void sinistra(int posizione) {
        motors.get(ANTERIORE_SX).setBaseDirection(Configs.avanti);
        motors.get(POSTERIORE_SX).setBaseDirection(Configs.indietro);
        motors.get(POSTERIORE_DX).setBaseDirection(Configs.indietro);
        motors.get(ANTERIORE_DX).setBaseDirection(Configs.avanti);

        motoriAttivi.set(ANTERIORE_SX, true);
        motoriAttivi.set(ANTERIORE_DX, true);
        motoriAttivi.set(POSTERIORE_DX, true);
        motoriAttivi.set(POSTERIORE_SX, true);


        motors.get(ANTERIORE_DX).setTargetPosition(posizione);

        motors.get(POSTERIORE_DX).setPower(1);
        motors.get(POSTERIORE_SX).setPower(1);
        motors.get(ANTERIORE_DX).setPower(1);
        motors.get(ANTERIORE_SX).setPower(1);

        while(motors.get(ANTERIORE_DX).getCurrentPosition() < posizione) { }

        motors.get(POSTERIORE_DX).setPower(0);
        motors.get(POSTERIORE_SX).setPower(0);
        motors.get(ANTERIORE_DX).setPower(0);
        motors.get(ANTERIORE_SX).setPower(0);

    }

    public void diagonaleSuDestra(int posizione) {
        motors.get(ANTERIORE_SX).setBaseDirection(Configs.indietro);
        motors.get(POSTERIORE_DX).setBaseDirection(Configs.avanti);

        motoriAttivi.set(ANTERIORE_SX, true);
        motoriAttivi.set(ANTERIORE_DX, false);
        motoriAttivi.set(POSTERIORE_DX, true);
        motoriAttivi.set(POSTERIORE_SX, false);


        motors.get(ANTERIORE_SX).setTargetPosition(posizione);

        motors.get(ANTERIORE_SX).setPower(1);
        motors.get(POSTERIORE_DX).setPower(1);

        while(motors.get(ANTERIORE_SX).getCurrentPosition() < posizione) { }

        motors.get(POSTERIORE_DX).setPower(0);
        motors.get(ANTERIORE_SX).setPower(0);

    }

    public void diagonaleSuSinistra(int posizione) {
        motors.get(ANTERIORE_DX).setBaseDirection(Configs.avanti);
        motors.get(POSTERIORE_SX).setBaseDirection(Configs.indietro);

        motoriAttivi.set(ANTERIORE_SX, false);
        motoriAttivi.set(ANTERIORE_DX, true);
        motoriAttivi.set(POSTERIORE_DX, false);
        motoriAttivi.set(POSTERIORE_SX, true);


        motors.get(ANTERIORE_DX).setTargetPosition(posizione);

        motors.get(ANTERIORE_DX).setPower(1);
        motors.get(POSTERIORE_SX).setPower(1);

        while(motors.get(ANTERIORE_DX).getCurrentPosition() < posizione) { }

        motors.get(POSTERIORE_SX).setPower(0);
        motors.get(ANTERIORE_DX).setPower(0);

    }

    public void diagonaleGiuSinistra(int posizione) {
        motors.get(ANTERIORE_SX).setBaseDirection(Configs.avanti);
        motors.get(POSTERIORE_DX).setBaseDirection(Configs.indietro);

        motoriAttivi.set(ANTERIORE_SX, true);
        motoriAttivi.set(ANTERIORE_DX, false);
        motoriAttivi.set(POSTERIORE_DX, true);
        motoriAttivi.set(POSTERIORE_SX, false);


        motors.get(ANTERIORE_SX).setTargetPosition(posizione);

        motors.get(ANTERIORE_SX).setPower(1);
        motors.get(POSTERIORE_DX).setPower(1);

        while(motors.get(ANTERIORE_SX).getCurrentPosition() < posizione) { }

        motors.get(POSTERIORE_DX).setPower(0);
        motors.get(ANTERIORE_SX).setPower(0);

    }

    public void diagonaleGiuDestra(int posizione) {
        motors.get(ANTERIORE_DX).setBaseDirection(Configs.indietro);
        motors.get(POSTERIORE_SX).setBaseDirection(Configs.avanti);

        motoriAttivi.set(ANTERIORE_SX, false);
        motoriAttivi.set(ANTERIORE_DX, true);
        motoriAttivi.set(POSTERIORE_DX, false);
        motoriAttivi.set(POSTERIORE_SX, true);

        motors.get(ANTERIORE_DX).setTargetPosition(posizione);

        motors.get(ANTERIORE_DX).setPower(1);
        motors.get(POSTERIORE_SX).setPower(1);

        while(motors.get(ANTERIORE_DX).getCurrentPosition() < posizione) { }

        motors.get(POSTERIORE_SX).setPower(0);
        motors.get(ANTERIORE_DX).setPower(0);


    }

    public void abbassaBraccio(int posizione){
        motors.get(GANCIO).setBaseDirection(Configs.avanti);

        motoriAttivi.set(GANCIO, true);

    }

    public void aggiorna(Telemetry t) {

        while(motoriAttivi.get(ANTERIORE_DX) && motors.get(ANTERIORE_DX).getCurrentPosition() <  motors.get(ANTERIORE_DX).getTargetPosition() ||
                motoriAttivi.get(ANTERIORE_SX) && motors.get(ANTERIORE_SX).getCurrentPosition() <  motors.get(ANTERIORE_SX).getTargetPosition() ||
                motoriAttivi.get(POSTERIORE_DX) && motors.get(POSTERIORE_DX).getCurrentPosition() <  motors.get(POSTERIORE_DX).getTargetPosition() ||
                motoriAttivi.get(POSTERIORE_SX) && motors.get(POSTERIORE_SX).getCurrentPosition() <  motors.get(POSTERIORE_SX).getTargetPosition()) {
        t.addLine(" " +
                (motors.get(ANTERIORE_DX).getCurrentPosition() <  motors.get(ANTERIORE_DX).getTargetPosition()) + " " +
                (motors.get(ANTERIORE_SX).getCurrentPosition() <  motors.get(ANTERIORE_SX).getTargetPosition()) + " " +
                (motors.get(POSTERIORE_DX).getCurrentPosition() <  motors.get(POSTERIORE_DX).getTargetPosition()) + " " +
                (motors.get(POSTERIORE_SX).getCurrentPosition() <  motors.get(POSTERIORE_SX).getTargetPosition()));
        t.update();
        }

        for(Motor m: motors) {

            m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            m.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            m.setPower(0);
        }
    }
}
