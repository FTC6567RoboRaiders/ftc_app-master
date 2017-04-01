package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Katelin Zichittella on 11/1/2016.
 */

@TeleOp

public class ShooterAdjust extends OpMode {

    DcMotor motorBackLeft, motorBackRight, motorFrontLeft, motorFrontRight,
            motorShooter, motorSweeper; /*motorLift*/
    Servo servoBeacon; /*servoLift*/
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

    @Override
    public void start() {

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
        servoBeacon.setPosition(0.0);
        // servoLift.setPosition(0.0);
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {

        float shoot = gamepad1.right_stick_y;

        shoot = Range.clip(shoot, -1, 1);

        shoot = (float) scaleInput(shoot);

        setShooterPower(shoot);
    }

    @Override
    public void stop() {


    }

    public void setShooterPower (double shoot) {

        motorShooter.setPower(shoot);
    }

    double scaleInput(double dVal)  { // When implemented above, this double scales the joystick input values
        // in the floats.

        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }
}