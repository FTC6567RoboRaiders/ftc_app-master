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
 * Created by Katelin Zichittella on 11/20/2016.
 */

public abstract class AutonomousHeader1 extends LinearOpMode {

    DcMotor motorBackLeft, motorBackRight, motorFrontLeft, motorFrontRight,
            motorShooter, motorSweeper, motorLift;
    Servo servoBeacon;
    GyroSensor sensorGyro;

    byte[] rangeSensorLeftCache;
    byte[] rangeSensorRightCache;

    I2cDevice rangeSensorLeft;
    I2cDevice rangeSensorRight;
    I2cDeviceSynch rangeSensorLeftReader;
    I2cDeviceSynch rangeSensorRightReader;

    byte[] colorSensorLeftCache;
    byte[] colorSensorRightCache;
    byte[] colorSensorFrontCache;

    I2cDevice colorSensorLeft;
    I2cDevice colorSensorRight;
    I2cDevice colorSensorFront;
    I2cDeviceSynch colorSensorLeftReader;
    I2cDeviceSynch colorSensorRightReader;
    I2cDeviceSynch colorSensorFrontReader;

    public void initialize() {

        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorShooter = hardwareMap.dcMotor.get("motorShooter");
        motorSweeper = hardwareMap.dcMotor.get("motorSweeper");
        motorLift = hardwareMap.dcMotor.get("motorLift");
        rangeSensorLeft = hardwareMap.i2cDevice.get("rangeSensorLeft");
        rangeSensorRight = hardwareMap.i2cDevice.get("rangeSensorRight");
        colorSensorLeft = hardwareMap.i2cDevice.get("colorSensorLeft");
        colorSensorRight = hardwareMap.i2cDevice.get("colorSensorRight");
        colorSensorFront = hardwareMap.i2cDevice.get("colorSensorFront");
        sensorGyro = hardwareMap.gyroSensor.get("sensorGyro");
        servoBeacon = hardwareMap.servo.get("servoBeacon");

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

        setMotorPower(0.2, 0.2);

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

                setMotorPower(0.2, 0.2);
            }

            else if ((colorSensorLeftCache[0] & 0xFF) >= 8) { // white

                setMotorPower(0.05, 0.20);
            }

            else if ((colorSensorRightCache[0] & 0xFF) >= 8) { // white

                setMotorPower(0.20, 0.05);
            }

            else {

                setMotorPower(0.2, 0.2);
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
        int PULSES = 1120;
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
        int PULSES = 1120;
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

    public void shoot (int distance, double power) {

        motorShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int DIAMETER = 2;
        int GEAR_RATIO = 120;
        int PULSES = 1680;
        double CIRCUMFERENCE = Math.PI * DIAMETER;
        double ROTATIONS = (distance / CIRCUMFERENCE) * GEAR_RATIO;
        double COUNTS = PULSES * ROTATIONS;

        COUNTS = COUNTS + Math.abs(motorShooter.getCurrentPosition());

        setMotorPower(power, power);

        while (motorShooter.getCurrentPosition() < COUNTS) {

            setMotorPower(power, power);
        }

        setMotorPower(0.0, 0.0);
    }

    public void setMotorPower (double left, double right) {

        motorBackLeft.setPower(left);
        motorBackRight.setPower(right);
        motorFrontLeft.setPower(left);
        motorFrontRight.setPower(right);
    }



    // UNUSED METHODS



    public void colorSensorTelemetry () {

        while (true) {

            colorSensorLeftReader.write8(3, 0);
            colorSensorRightReader.write8(3, 0);
            colorSensorFrontReader.write8(3, 1);

            colorSensorLeftCache = colorSensorLeftReader.read(0x08, 1);
            colorSensorRightCache = colorSensorRightReader.read(0x08, 1);
            colorSensorFrontCache = colorSensorFrontReader.read(0x04, 1);

            telemetry.addData("Left", colorSensorLeftCache[0] & 0xFF);
            telemetry.addData("Right", colorSensorRightCache[0] & 0xFF);
            telemetry.addData("Front", colorSensorFrontCache[0] & 0xFF);
            telemetry.update();
        }
    }

    public void rangeSensorTelemetryUltraSonic () {

        while (true) {

            rangeSensorLeftCache = rangeSensorLeftReader.read(0x04, 1);
            rangeSensorRightCache = rangeSensorRightReader.read(0x04, 1);

            telemetry.addData("Left", rangeSensorLeftCache[0] & 0xFF);
            telemetry.addData("Right", rangeSensorRightCache[0] & 0xFF);
            telemetry.update();
        }
    }

    public void rangeSensorTelemetryOptical () {

        while (true) {

            rangeSensorLeftCache = rangeSensorLeftReader.read(0x05, 1);
            rangeSensorRightCache = rangeSensorRightReader.read(0x05, 1);

            telemetry.addData("Left", rangeSensorLeftCache[0] & 0xFF);
            telemetry.addData("Right", rangeSensorRightCache[0] & 0xFF);
            telemetry.update();
        }
    }

    public void lineFollowerOneSensor () {

        setMotorPower(0.15, 0.05);

        rangeSensorLeftCache = rangeSensorLeftReader.read(0x04, 1);
        rangeSensorRightCache = rangeSensorRightReader.read(0x04, 1);

        while ((rangeSensorLeftCache[0] & 0xFF) > 10 || (rangeSensorRightCache[0] & 0xFF) > 10) {

            rangeSensorLeftCache = rangeSensorLeftReader.read(0x04, 1);
            rangeSensorRightCache = rangeSensorRightReader.read(0x04, 1);

            telemetry.addData("Left", rangeSensorLeftCache[0] & 0xFF);
            telemetry.addData("Right", rangeSensorRightCache[0] & 0xFF);
            telemetry.update();

            colorSensorLeftCache = colorSensorLeftReader.read(0x08, 1);
            colorSensorRightCache = colorSensorRightReader.read(0x08, 1);

            telemetry.addData("Left", colorSensorLeftCache[0] & 0xFF);
            telemetry.addData("Right", colorSensorRightCache[0] & 0xFF);
            telemetry.update();

            if ((colorSensorLeftCache[0] & 0xFF) > 18) { // white

                setMotorPower(0.05, 0.15);
            }

            else { // black

                setMotorPower(0.15, 0.05);
            }
        }

        setMotorPower(0.0, 0.0);
    }

    public void moveUntilCorrectDistance (int distance, double power) {

        rangeSensorLeftCache = rangeSensorLeftReader.read(0x04, 1);
        rangeSensorRightCache = rangeSensorRightReader.read(0x04, 1);

        if (power < 0) {

            setMotorPower(power, power);

            while ((rangeSensorLeftCache[0] & 0xFF) < distance || (rangeSensorRightCache[0] & 0xFF) < distance) {

                rangeSensorLeftCache = rangeSensorLeftReader.read(0x04, 1);
                rangeSensorRightCache = rangeSensorRightReader.read(0x04, 1);

                telemetry.addData("Left", rangeSensorLeftCache[0] & 0xFF);
                telemetry.addData("Right", rangeSensorRightCache[0] & 0xFF);
                telemetry.update();

                setMotorPower(power, power);
            }

            setMotorPower(0.0, 0.0);
        }

        else if (power >= 0) {

            setMotorPower(power, power);

            while ((rangeSensorLeftCache[0] & 0xFF) > distance || (rangeSensorRightCache[0] & 0xFF) > distance) {

                rangeSensorLeftCache = rangeSensorLeftReader.read(0x04, 1);
                rangeSensorRightCache = rangeSensorRightReader.read(0x04, 1);

                telemetry.addData("Left", rangeSensorLeftCache[0] & 0xFF);
                telemetry.addData("Right", rangeSensorRightCache[0] & 0xFF);
                telemetry.update();

                setMotorPower(power, power);
            }

            setMotorPower(0.0, 0.0);
        }
    }

    public void turnUntilAlignedLeft (double power) {

        rangeSensorLeftCache = rangeSensorLeftReader.read(0x04, 1);
        rangeSensorRightCache = rangeSensorRightReader.read(0x04, 1);

        while ((rangeSensorRightCache[0] & 0xFF) > (rangeSensorLeftCache[0] & 0xFF)) {

            rangeSensorLeftCache = rangeSensorLeftReader.read(0x04, 1);
            rangeSensorRightCache = rangeSensorRightReader.read(0x04, 1);

            telemetry.addData("Left", rangeSensorLeftCache[0] & 0xFF);
            telemetry.addData("Right", rangeSensorRightCache[0] & 0xFF);
            telemetry.update();

            setMotorPower(-power, power);
        }

        setMotorPower(0.0, 0.0);
    }

    public void turnUntilAlignedRight (double power) {

        rangeSensorLeftCache = rangeSensorLeftReader.read(0x04, 1);
        rangeSensorRightCache = rangeSensorRightReader.read(0x04, 1);

        while ((rangeSensorLeftCache[0] & 0xFF) > (rangeSensorRightCache[0] & 0xFF)) {

            rangeSensorLeftCache = rangeSensorLeftReader.read(0x04, 1);
            rangeSensorRightCache = rangeSensorRightReader.read(0x04, 1);

            telemetry.addData("Left", rangeSensorLeftCache[0] & 0xFF);
            telemetry.addData("Right", rangeSensorRightCache[0] & 0xFF);
            telemetry.update();

            setMotorPower(power, -power);
        }

        setMotorPower(0.0, 0.0);
    }

    public void adjustWithEncoders (int distance) throws InterruptedException {

        encodersForward(distance + 5, 0.5);
        Thread.sleep(1000);
        encodersBackward(distance, 0.1);
    }

    public void adjustWithRange (int distance) throws InterruptedException {

        encodersForward((distance / 2) + 5, 0.5);
        Thread.sleep(1000);
        moveUntilCorrectDistance(distance, -0.1);
    }

    public void turnUntilWhiteLineLeft () {

        setMotorPower(-0.1, 0.1);

        colorSensorRightCache = colorSensorRightReader.read(0x08, 1);

        while ((colorSensorRightCache[0] & 0xFF) < 5) { // black

            colorSensorRightCache = colorSensorRightReader.read(0x08, 1);

            telemetry.addData("Right", colorSensorRightCache[0] & 0xFF);
            telemetry.update();

            setMotorPower(-0.1, 0.1);
        }

        setMotorPower(0.0, 0.0);
    }

    public void turnUntilWhiteLineRight () {

        setMotorPower(0.1, -0.1);

        colorSensorLeftCache = colorSensorLeftReader.read(0x08, 1);

        while ((colorSensorLeftCache[0] & 0xFF) < 5) { // black

            colorSensorLeftCache = colorSensorLeftReader.read(0x08, 1);

            telemetry.addData("Left", colorSensorLeftCache[0] & 0xFF);
            telemetry.update();

            setMotorPower(0.1, -0.1);
        }

        setMotorPower(0.0, 0.0);
    }
}