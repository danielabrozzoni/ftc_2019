package org.pizzapastarobottino.ftc.teamcode.OpModes.AutonomousPart;

/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.pizzapastarobottino.ftc.teamcode.Configs;
import org.pizzapastarobottino.ftc.teamcode.Hardware.Hardware;
import org.pizzapastarobottino.ftc.teamcode.Hardware.Motor;
import org.pizzapastarobottino.ftc.teamcode.Movement.AutonomousMovement;
import org.pizzapastarobottino.ftc.teamcode.Movement.ControlledMovement;

import java.util.ArrayList;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

//@Autonomous(name="Abs", group="A")
@Disabled
public abstract class Autonoma_abs extends LinearOpMode {

    // Declare OpMode members.
    protected ElapsedTime runtime = new ElapsedTime();
    protected Hardware robot = new Hardware();
    protected AutonomousMovement mAutonomousMovement;
    public final ArrayList<Integer> gearsIndex = new ArrayList<>();
    public final ArrayList<Motor> motors = new ArrayList<>();

    protected final int ANTERIORE_DX = 0;
    protected final int ANTERIORE_SX = 1;
    protected final int POSTERIORE_DX = 2;
    protected final int POSTERIORE_SX = 3;
    protected final int BRACCIO = 4;
    protected final int GANCIO = 5;

    protected final int AVANTI_TRENTA_CM = 772;
    protected final int AVANTI_SESSANTA_CM = 1542;
    protected final int AVANTI_NOVANTA_CM = 1314;

    protected final int TRASLA_DIECI_CM = 381;
    protected final int TRASLA_SETTANTA_CM = 2667;
    protected final int TRASLA_QUARANTADUE_CM = 1623;
    protected final int TRASLA_OTTANTACINQUE_CM = 3245;
    protected final int TRASLA_SESSANTA_CM = 2291;

    protected final int GIRA_90_GRADI = 1350;

    @Override
    public void runOpMode() {

    }

    public void gancio(){
        mAutonomousMovement.abbassaGancio(15);
        updateNoStop();
        mAutonomousMovement.alzaGancio(2);
        updateNoStop();
        mAutonomousMovement.indietro(AVANTI_TRENTA_CM/6);
        updateNoStop();
        mAutonomousMovement.sinistra(TRASLA_DIECI_CM/2);
        updateNoStop();
        mAutonomousMovement.avanti(AVANTI_TRENTA_CM/6);
        updateNoStop();
    }

    public void update(){
        sleep(500);
        mAutonomousMovement.aggiorna(telemetry);
    }

    public void updateNoStop(){
        mAutonomousMovement.aggiorna(telemetry);
    }

    public void initMotors(){
        robot.init(hardwareMap);

        motors.add(ANTERIORE_DX, robot.getMotor(Configs.motorRuotaAnterioreDX));
        motors.add(ANTERIORE_SX, robot.getMotor(Configs.motorRuotaAnterioreSX));
        motors.add(POSTERIORE_DX, robot.getMotor(Configs.motorRuotaPosterioreDX));
        motors.add(POSTERIORE_SX, robot.getMotor(Configs.motorRuotaPosterioreSX));

        gearsIndex.add(ANTERIORE_DX, 60);
        gearsIndex.add(ANTERIORE_SX, 60);
        gearsIndex.add(POSTERIORE_DX, 60);
        gearsIndex.add(POSTERIORE_SX, 60);

        mAutonomousMovement = new AutonomousMovement(robot);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

    }

}
