package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;



/**----------------------------------------------------------------------------------------------**/
/** KetchUpBot                                                                                   **/
/**----------------------------------------------------------------------------------------------**/
/**                                                                                              **/
/** THIS IS NOT AN OPMODE                                                                        **/
/**                                                                                              **/
/** This class defines the specific hardware for the FTC6567 KetchUp Robot.                      **/
/**                                                                                              **/
/** KetchUpBot class assumes the following devices names have been configured on Ketchup:        **/
/**                                                                                              **/
/** Motors                                                                                       **/
/** - Left  Drive Motor: "motorLeft"                                                             **/
/** - Right Drive Motor: "motorRight"                                                            **/
/**                                                                                              **/
/**                                                                                              **/
/** Color Sensors                                                                                **/
/** - Left  Color Sensor: "colorSensorLeft"                                                      **/
/** - Right Color Sensor: "colorSensorRight"                                                     **/
/**                                                                                              **/
/**                                                                                              **/
/** Range Sensors                                                                                **/
/** - Left  Range Sensor: "rangeSensorLeft"                                                      **/
/** - Right Range Sensor: "rangeSensorRight"                                                     **/
/**                                                                                              **/
/**                                                                                              **/
/**                                                                                              **/
/**----------------------------------------------------------------------------------------------**/
/**  Change Activity                                                                             **/
/**                                                                                              **/
/**  Change Flag    Date       Programmer       Description                                      **/
/**    $00          10/10/16   S. Kocik         Initial version                                  **/
/**                                                                                              **/
/**                                                                                              **/
/**                                                                                              **/
/**                                                                                              **/
/**                                                                                              **/
/**                                                                                              **/
/**----------------------------------------------------------------------------------------------**/

public class KetchupBot
{
    /* Public OpMode members. */
    public DcMotor                      leftMotor         = null;
    public DcMotor                      rightMotor        = null;

    public ColorSensor                  colorSensorLeft   = null;
    public ColorSensor                  colorSensorRight  = null;

    public ModernRoboticsI2cRangeSensor rangeSensorLeft   = null;
    public ModernRoboticsI2cRangeSensor rangeSensorRight  = null;

/**
    public Servo    leftClaw    = null;
    public Servo    rightClaw   = null;

    public static final double MID_SERVO       =  0.5 ;
    public static final double ARM_UP_POWER    =  0.45 ;
    public static final double ARM_DOWN_POWER  = -0.45 ;
*/

    /* local OpMode members. */
    HardwareMap hwMap           = null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public KetchupBot(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftMotor   = hwMap.dcMotor.get("motorLeft");
        rightMotor  = hwMap.dcMotor.get("motorRight");
        leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        leftMotor.setPower(0);
        rightMotor.setPower(0);

        // For now set all motors to run without encoders.
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        // Define and Initialize Color Sensors
        colorSensorLeft  = hwMap.colorSensor.get("colorSensorLeft");
        colorSensorRight = hwMap.colorSensor.get("colorSensorRight");

        //* Define and Initialize Range Sensors
        rangeSensorLeft  = hwMap.get(ModernRoboticsI2cRangeSensor.class, "rangeSensorLeft");
        rangeSensorRight = hwMap.get(ModernRoboticsI2cRangeSensor.class, "rangeSensorRight");


    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     * @throws InterruptedException
     */
    public void waitForTick(long periodMs) throws InterruptedException {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}

