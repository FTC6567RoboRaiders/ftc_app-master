package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * <!-- Purpose of this method, a one liner, nice and short -->
 * Abstract classed used as the "base" for FTC6567 RoboRaiders' competition autonomous opModes.
 * <br>
 *
 * <!-- Detailed description, the more information here the better -->
 * All FTC6567 RoboRaiders' competition autonomous opModes should "extend" this class.  Additional methods to be added to this class need to
 * be reviewed and approved to inclusion into this abstract class.
 * <br>
 * <br>
 * <!-- Change Activity table -->
 * <!-- Repeat the last set of lines starting with <tr> through (including the <td></td>)</tr> -->
 * <!-- Then update the information as follows: -->
 * <!-- -First column is programmer name - format first name initial then full last name -->
 * <!-- -Second column is the date of the change -->
 * <!-- -Third column is the description of the change itself, the more information the better -->
 *
 * <b>Change Activity</b>
 * <br>
 * <table border="1" bordercolor="red" cellpadding="1" cellspacing="1">
 * <tr>
 * <th width="15%">Programmer</th>
 * <th width="15%">Change Date</th>
 * <th>Change Description</th>
 * </tr>
 * <tr>
 * <td>K. Zichittella  </td>
 * <td>9/20/2016       </td>
 * <td>Initial version </td>
 * </tr>
 * <tr>
 * <td>J. Programmer   </td>
 * <td>11/23/2016      </td>
 * <td>Description of the change that was made, doesn't need to be too long but long enough
 * for the next poor slob who is reading this to understand</td>
 * </tr>
 * </table>
 *
 * Created by Katelin Zichittella on 11/20/2016.
 */

// REMEMBER TO FIX WHITE VALUES AFTER CALIBRATING

public abstract class AutonomousHeaderSMK extends LinearOpMode {

    /** Motor definitions, locations are defined by orientating oneself at the rear of the robot */
    DcMotor motorBackLeft, motorBackRight, motorFrontLeft, motorFrontRight,
            motorShooter, motorSweeper; /*motorLift*/


    /** Defines the servo used to capture the color beacon */
    Servo servoBeacon; /*servoLift*/

    /** Defines the gyro sensor used to orient the robot properly during autonomous */
    GyroSensor sensorGyro;

    /** Array of bytes that will contain the information read from the left range sensor, the term CACHE is used
     * to indicate that this is a copy of information returned from the sensor */
    byte[] rangeSensorLeftCache;

    /** Array of bytes that will contain the information read from the right range sensor, the term CACHE is used
     * to indicate that this is a copy of information returned from the sensor */
    byte[] rangeSensorRightCache;

    /** Defines the left range sensor on the front of the robot, used to determine the distance the robot is
     * from the color beacon during autonomous */
    I2cDevice rangeSensorLeft;

    /** Defines the right range sensor on the front of the robot, used to determine the distance the robot is
     * from the color beacon during autonomous */
    I2cDevice rangeSensorRight;

    /** Defines the interface to the left range sensor that allows for "easy" interaction with the sensor itself, for
     * more information refer to the JavaDoc on I2CDeviceSynch */
    I2cDeviceSynch rangeSensorLeftReader;

    /** Defines the interface to the right range sensor that allows for "easy" interaction with the sensor itself, for
     * more information refer to the JavaDoc on I2CDeviceSynch */
    I2cDeviceSynch rangeSensorRightReader;


    /** Array of bytes that will contain the information read from the left color sensor, the term CACHE is used
     * to indicate that this is a copy of information returned from the sensor */
    byte[] colorSensorLeftCache;

    /** Array of bytes that will contain the information read from the right color sensor, the term CACHE is used
     * to indicate that this is a copy of information returned from the sensor */
    byte[] colorSensorRightCache;

    /** Array of bytes that will contain the information read from the front color sensor, the term CACHE is used
     * to indicate that this is a copy of information returned from the sensor */
    byte[] colorSensorFrontCache;


    /** Defines the left color sensor on the front of the robot, used to follow the white line in front
     * of the color beacon that is to be "captured" during autonomous.  This sensor runs in active mode. */
    I2cDevice colorSensorLeft;

    /** Defines the right color sensor on the front of the robot, used to follow the white line in front
     * of the color beacon that is to be "captured" during autonomous.  This sensor runs in active mode. */
    I2cDevice colorSensorRight;

    /** Defines the front color sensor on the front of the robot, used to read the color of color beacon so that
     * the proper button on the color beacon is pushed to capture it during autonomous.  This sensor runs in
     * passive mode. */
    I2cDevice colorSensorFront;

    /** Defines the interface to the left color sensor that allows for "easy" interaction with the sensor itself, for
     * more information refer to the JavaDoc on I2CDeviceSynch */
    I2cDeviceSynch colorSensorLeftReader;

    /** Defines the interface to the right color sensor that allows for "easy" interaction with the sensor itself, for
     * more information refer to the JavaDoc on I2CDeviceSynch */
    I2cDeviceSynch colorSensorRightReader;

    /** Defines the interface to the front color sensor that allows for "easy" interaction with the sensor itself, for
     * more information refer to the JavaDoc on I2CDeviceSynch */
    I2cDeviceSynch colorSensorFrontReader;

    public void initialize() {

        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorShooter = hardwareMap.dcMotor.get("motorShooter");
        motorSweeper = hardwareMap.dcMotor.get("motorSweeper");
        // motorLift = hardwareMap.dcMotor.get("motorLift");
        rangeSensorLeft = hardwareMap.i2cDevice.get("rangeSensorLeft");
        rangeSensorRight = hardwareMap.i2cDevice.get("rangeSensorRight");
        colorSensorLeft = hardwareMap.i2cDevice.get("colorSensorLeft");
        colorSensorRight = hardwareMap.i2cDevice.get("colorSensorRight");
        colorSensorFront = hardwareMap.i2cDevice.get("colorSensorFront");
        sensorGyro = hardwareMap.gyroSensor.get("sensorGyro");
        servoBeacon = hardwareMap.servo.get("servoBeacon");
        // servoLift = hardwareMap.servo.get("servoLift");

        colorSensorLeftReader = new I2cDeviceSynchImpl(colorSensorLeft, I2cAddr.create8bit(0x3c), false);
        colorSensorRightReader = new I2cDeviceSynchImpl(colorSensorRight, I2cAddr.create8bit(0x3e), false);
        colorSensorFrontReader = new I2cDeviceSynchImpl(colorSensorFront, I2cAddr.create8bit(0x40), false);
        rangeSensorLeftReader = new I2cDeviceSynchImpl(rangeSensorLeft, I2cAddr.create8bit(0x28), false);
        rangeSensorRightReader = new I2cDeviceSynchImpl(rangeSensorRight, I2cAddr.create8bit(0x30), false);

        colorSensorLeftReader.write8(3, 0);
        colorSensorRightReader.write8(3, 0);
        colorSensorFrontReader.write8(3, 1);

        colorSensorLeftReader.engage();
        colorSensorRightReader.engage();
        colorSensorFrontReader.engage();
        rangeSensorLeftReader.engage();
        rangeSensorRightReader.engage();

        motorBackRight.setDirection(DcMotor.Direction.REVERSE);
        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
        servoBeacon.setPosition(0.5);
        // servoLift.setPosition(0.0);
    }

    public void calibrateGyro () throws InterruptedException {

        while (sensorGyro.isCalibrating()) {

            Thread.sleep(50);

            telemetry.addData("Calibrated", false);
            telemetry.update();
        }

        telemetry.addData("Calibrated", true);
        telemetry.update();
    }

    public void lineFollowerTwoSensors (int distance) { // may be the better bet

        setMotorPower(0.1, 0.1);

        rangeSensorLeftCache = rangeSensorLeftReader.read(0x04, 1);
        rangeSensorRightCache = rangeSensorRightReader.read(0x04, 1);

        while ((rangeSensorLeftCache[0] & 0xFF) > distance || (rangeSensorRightCache[0] & 0xFF) > distance) {

            rangeSensorLeftCache = rangeSensorLeftReader.read(0x04, 1);
            rangeSensorRightCache = rangeSensorRightReader.read(0x04, 1);

            /*telemetry.addData("LeftRange", rangeSensorLeftCache[0] & 0xFF);
            telemetry.addData("RightRange", rangeSensorRightCache[0] & 0xFF);
            telemetry.update();*/

            colorSensorLeftCache = colorSensorLeftReader.read(0x08, 1);
            colorSensorRightCache = colorSensorRightReader.read(0x08, 1);

            telemetry.addData("LeftColor", colorSensorLeftCache[0] & 0xFF);
            telemetry.addData("RightColor", colorSensorRightCache[0] & 0xFF);
            telemetry.update();

            if ((colorSensorLeftCache[0] & 0xFF) < 5 && (colorSensorRightCache[0] & 0xFF) < 5) { // black

                setMotorPower(0.1, 0.1);
            }

            else if ((colorSensorLeftCache[0] & 0xFF) >= 5) { // white

                setMotorPower(0.025, 0.1);
            }

            else if ((colorSensorRightCache[0] & 0xFF) >= 5) { // white

                setMotorPower(0.1, 0.025);
            }

            else {

                setMotorPower(0.1, 0.1);
            }
        }

        setMotorPower(0.0, 0.0);
    }

    public void moveUntilWhiteLineLeft(double power) {

        setMotorPower(power, power);

        colorSensorLeftCache = colorSensorLeftReader.read(0x08, 1);

        while ((colorSensorLeftCache[0] & 0xFF) < 5) { // black

            colorSensorLeftCache = colorSensorLeftReader.read(0x08, 1);

            telemetry.addData("Left", colorSensorLeftCache[0] & 0xFF);
            telemetry.update();

            setMotorPower(power, power);
        }

        setMotorPower(0.0, 0.0);
    }

    public void moveUntilWhiteLineRight(double power) {

        setMotorPower(power, power);

        colorSensorRightCache = colorSensorRightReader.read(0x08, 1);

        while ((colorSensorRightCache[0] & 0xFF) < 5) { // black

            colorSensorRightCache = colorSensorRightReader.read(0x08, 1);

            telemetry.addData("Right", colorSensorRightCache[0] & 0xFF);
            telemetry.update();

            setMotorPower(power, power);
        }

        setMotorPower(0.0, 0.0);
    }

    public void moveUntilWhiteLineStraight(double power) {

        setMotorPower(power, power);

        colorSensorLeftCache = colorSensorLeftReader.read(0x08, 1);
        colorSensorRightCache = colorSensorRightReader.read(0x08, 1);

        while ((colorSensorLeftCache[0] & 0xFF) < 5 && (colorSensorRightCache[0] & 0xFF) < 5) { // black

            colorSensorLeftCache = colorSensorLeftReader.read(0x08, 1);
            colorSensorRightCache = colorSensorRightReader.read(0x08, 1);

            telemetry.addData("Left", colorSensorLeftCache[0] & 0xFF);
            telemetry.addData("Right", colorSensorRightCache[0] & 0xFF);
            telemetry.update();

            setMotorPower(power, power);
        }

        setMotorPower(0.0, 0.0);
    }

    public void encodersForward (int distance, double power) {

        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int DIAMETER = 4;
        int GEAR_RATIO = 1;
        int PULSES = 560;
        double CIRCUMFERENCE = Math.PI * DIAMETER;
        double ROTATIONS = (distance / CIRCUMFERENCE) * GEAR_RATIO;
        double COUNTS = PULSES * ROTATIONS;

        COUNTS = COUNTS + Math.abs(motorBackLeft.getCurrentPosition());

        setMotorPower(power, power);

        while (motorBackLeft.getCurrentPosition() < COUNTS) {

            setMotorPower(power, power);
        }

        setMotorPower(0.0, 0.0);
    }

    public void encodersBackward (int distance, double power) {

        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int DIAMETER = 4;
        int GEAR_RATIO = 1;
        int PULSES = 560;
        double CIRCUMFERENCE = Math.PI * DIAMETER;
        double ROTATIONS = (distance / CIRCUMFERENCE) * GEAR_RATIO;
        double COUNTS = PULSES * ROTATIONS;

        COUNTS = Math.abs(motorBackLeft.getCurrentPosition()) - COUNTS;

        setMotorPower(-power, -power);

        while (motorBackLeft.getCurrentPosition() > COUNTS) {

            setMotorPower(-power, -power);
        }

        setMotorPower(0.0, 0.0);
    }

    public void gyroTurnRight (int degrees, double power) {

        sensorGyro.resetZAxisIntegrator();

        int heading = sensorGyro.getHeading();

        setMotorPower(power, -power);

        while (heading < degrees) {

            heading = sensorGyro.getHeading();

            setMotorPower(power, -power);

            if (heading >= 180) {

                heading = 360 - heading;
            }
        }

        setMotorPower(0, 0);
    }

    public void gyroTurnLeft (int degrees, double power) {

        sensorGyro.resetZAxisIntegrator();

        int heading = sensorGyro.getHeading();

        setMotorPower(-power, power);

        while (heading < degrees) {

            heading = sensorGyro.getHeading();

            setMotorPower(-power, power);

            if (heading >= 180) {

                heading = 360 - heading;
            }
        }

        setMotorPower(0, 0);
    }

    public void shoot () {

        motorShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double GEAR_RATIO = 1.456;
        int PULSES = 1680;
        double COUNTS = PULSES * GEAR_RATIO;

        COUNTS = COUNTS + Math.abs(motorShooter.getCurrentPosition());

        motorShooter.setPower(1.0);

        while (motorShooter.getCurrentPosition() < COUNTS) {

            motorShooter.setPower(1.0);
        }

        motorShooter.setPower(0.0);
    }

    public void setMotorPower (double left, double right) {

        motorBackLeft.setPower(left);
        motorBackRight.setPower(right);
        motorFrontLeft.setPower(left);
        motorFrontRight.setPower(right);
    }
}