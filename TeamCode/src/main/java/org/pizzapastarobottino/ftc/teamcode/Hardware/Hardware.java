package org.pizzapastarobottino.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.Consumer;
import org.pizzapastarobottino.ftc.teamcode.Configs;

/**
 * This is NOT an opmode.
 * <p>
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 * <p>
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 * <p>
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class Hardware {

    private MechanismMap<String> mechanisms = new MechanismMap<>();

    private ColorSensor mColorSensor;
    private HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public Motor getMotor(String motor){
        return (Motor) mechanisms.get(motor);
    }

    public Servo getServo(String servo){
        return (Servo) mechanisms.get(servo);
    }

    public void forEachMechanism (Consumer<Mechanism> operation){
        for(Mechanism i : mechanisms.values()) {
            operation.accept(i);
        }
    }

    public Hardware() {
    }

    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map

        hwMap = ahwMap;
        mColorSensor = hwMap.colorSensor.get(Configs.colorSensor);
        // Define and Initialize Motors
        mechanisms.put(Configs.motorRuotaPosterioreDX, new Motor(hwMap.dcMotor.get(Configs.motorRuotaPosterioreDX)), Configs.avanti, 0);
        mechanisms.put(Configs.motorRuotaPosterioreSX, new Motor(hwMap.dcMotor.get(Configs.motorRuotaPosterioreSX)), Configs.indietro, 0);
        mechanisms.put(Configs.motorRuotaAnterioreDX, new Motor(hwMap.dcMotor.get(Configs.motorRuotaAnterioreDX)), Configs.avanti, 0);
        mechanisms.put(Configs.motorRuotaAnterioreSX, new Motor(hwMap.dcMotor.get(Configs.motorRuotaAnterioreSX)), Configs.indietro, 0);

        for(Mechanism i : mechanisms.values()){
            i.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            i.init();
        }

    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     **/
    public void waitForTick(long periodMs) {

        long remaining = periodMs - (long) period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }

    public ColorSensor getColorSensor() {
        return mColorSensor;
    }

}
