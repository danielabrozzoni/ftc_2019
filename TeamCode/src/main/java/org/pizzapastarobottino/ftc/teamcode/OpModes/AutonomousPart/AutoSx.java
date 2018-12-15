package org.pizzapastarobottino.ftc.teamcode.OpModes.AutonomousPart;/* Copyright (c) 2017 FIRST. All rights reserved.
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
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.pizzapastarobottino.ftc.teamcode.Configs;
import org.pizzapastarobottino.ftc.teamcode.Hardware.Hardware;
import org.pizzapastarobottino.ftc.teamcode.Hardware.UndeliverablePowerException;
import org.pizzapastarobottino.ftc.teamcode.OpModes.UserControlled.Drive;
import org.pizzapastarobottino.ftc.teamcode.OpModes.UserControlled.MechanumWheels;


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

@Autonomous(name="AutoSx", group="A")
//@Disabled
public class AutoSx extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private Hardware robot = new Hardware();


    private void trasla(double delta, double x, double y){
        double[] powers;
        double temp = -x;
        x = -y;
        y = temp;

        try {
            powers = MechanumWheels.getPowerFast(delta,
                    Math.atan2(
                            y * Configs.yDirectionFactor,
                            x * Configs.xDirectionFactor
                    ));
            robot.getMotor(Configs.motorRuotaAnterioreDX).move(powers[0] * Configs.ruotaAnterioreDXrotationFactor);
            robot.getMotor(Configs.motorRuotaAnterioreSX).move(powers[1] * Configs.ruotaAnterioreSXrotationFactor);
            robot.getMotor(Configs.motorRuotaPosterioreDX).move(powers[0] * Configs.ruotaPosterioreDXrotationFactor);
            robot.getMotor(Configs.motorRuotaPosterioreSX).move(powers[1] * Configs.ruotaPosterioreSXrotationFactor);
        } catch (UndeliverablePowerException e) {
            telemetry.addLine(e.getMessage());
            telemetry.update();
        }
    }

    private void sterza(double delta, double x){
        if(x < 0) {
            robot.getMotor(Configs.motorRuotaAnterioreSX).move(delta * Configs.ruotaAnterioreSXrotationFactor);
            robot.getMotor(Configs.motorRuotaPosterioreDX).move(delta * Configs.ruotaPosterioreDXrotationFactor);
        }
        else {
            robot.getMotor(Configs.motorRuotaAnterioreDX).move(delta * Configs.ruotaAnterioreSXrotationFactor);
            robot.getMotor(Configs.motorRuotaPosterioreSX).move(delta * Configs.ruotaPosterioreDXrotationFactor);
        }

    }

    private void giraSuTeStesso(double delta){
        robot.getMotor(Configs.motorRuotaAnterioreDX).move(-delta * Configs.ruotaAnterioreDXrotationFactor);
        robot.getMotor(Configs.motorRuotaAnterioreSX).move(delta * Configs.ruotaAnterioreSXrotationFactor);
        robot.getMotor(Configs.motorRuotaPosterioreDX).move(-delta * Configs.ruotaPosterioreDXrotationFactor);
        robot.getMotor(Configs.motorRuotaPosterioreSX).move(delta * Configs.ruotaPosterioreSXrotationFactor);
    }

    private void resetPowers(){
        robot.getMotor(Configs.motorRuotaAnterioreDX).move(0);
        robot.getMotor(Configs.motorRuotaAnterioreSX).move(0);
        robot.getMotor(Configs.motorRuotaPosterioreDX).move(0);
        robot.getMotor(Configs.motorRuotaPosterioreSX).move(0);
    }


    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        while(runtime.milliseconds() < 900);
        resetPowers();

        while(runtime.milliseconds() < 900);
    }
}
