package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Alex on 11/27/16.
 */

@TeleOp

public class CalibrateColorSensors extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    byte[] colorSensorLeftcache;
    byte[] colorSensorRightcache;
    byte[] colorSensorFrontcache;

    I2cDevice colorSensorLeft;
    I2cDeviceSynch colorSensorLeftreader;

    I2cDevice colorSensorRight;
    I2cDeviceSynch colorSensorRightreader;

    I2cDevice colorSensorFront;
    I2cDeviceSynch colorSensorFrontreader;

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        /*Calibrating Left and Right Sensors*/

        colorSensorLeft = hardwareMap.i2cDevice.get("colorSensorLeft");
        colorSensorLeftreader = new I2cDeviceSynchImpl(colorSensorLeft, I2cAddr.create8bit(0x3c), false);
        colorSensorLeftreader.engage();

        colorSensorLeftreader.write8(3, 0);    //Set the mode of the color sensor to Active


        colorSensorRight = hardwareMap.i2cDevice.get("colorSensorRight");
        colorSensorRightreader = new I2cDeviceSynchImpl(colorSensorRight, I2cAddr.create8bit(0x3e), false);
        colorSensorRightreader.engage();

        colorSensorRightreader.write8(3, 0);    //Set the mode of the color sensor to Active

        waitForStart();
        runtime.reset();

        telemetry.addData("Hello", "Point the color sensor into open space. Then press and hold A");
        telemetry.update();
        while (!gamepad1.a) {

        }

        colorSensorLeftreader.write8(3, 66); //Black Level Calibration
        telemetry.addData("Hello", "Please Wait for Black Level Calibration for Left Sensor.");
        telemetry.update();
        colorSensorLeftcache = colorSensorLeftreader.read(0x08, 1);
        while (colorSensorLeftcache[0] == 66) {
            colorSensorLeftcache = colorSensorLeftreader.read(0x08, 1);
        }

        colorSensorRightreader.write8(3, 66); //Black Level Calibration
        telemetry.addData("Hello", "Please Wait for Black Level Calibration for the Right Sensor.");
        telemetry.update();
        colorSensorRightcache = colorSensorRightreader.read(0x08, 1);
        while (colorSensorRightcache[0] == 66) {
            colorSensorRightcache = colorSensorRightreader.read(0x08, 1);
        }


        while (gamepad1.a) {
            telemetry.addData("Hey!", "Release the A button");
            telemetry.update();
        }


        telemetry.addData("I bet you're really stressed out right now because your phone is talking to you and you could be doing something useful with your life like roasting someone. Sorry, I won't do it again.", "Point the color sensor 5cm or 2in away at a white target. Then press A");
        telemetry.update();
        while (!gamepad1.a) {

        }
        colorSensorLeftreader.write8(3, 67); //White Level Calibration
        telemetry.addData(":)", "Wait for White Balance Calibration for the Left sensor.");
        telemetry.update();
        colorSensorLeftcache = colorSensorLeftreader.read(0x08, 1);
        while (colorSensorLeftcache[0] == 67) {
            colorSensorLeftcache = colorSensorLeftreader.read(0x08, 1);
        }

        colorSensorRightreader.write8(3, 67); //White Level Calibration
        telemetry.addData(":)", "Wait for White Balance Calibration for the right sensor.");
        telemetry.update();
        colorSensorRightcache = colorSensorRightreader.read(0x08, 1);
        while (colorSensorRightcache[0] == 67) {
            colorSensorRightcache = colorSensorRightreader.read(0x08, 1);
        }

        waitForStart();
        runtime.reset();

        /* Calibrate Front Sensor*/

        colorSensorFront = hardwareMap.i2cDevice.get("colorSensorFront");
        colorSensorFrontreader = new I2cDeviceSynchImpl(colorSensorFront, I2cAddr.create8bit(0x42), false);
        colorSensorFrontreader.engage();

        colorSensorFrontreader.write8(3, 1);    //Set the mode of the color sensor to Passive

        telemetry.addData("Hello", "Point the front color sensor into open space. Then press and hold B");
        telemetry.update();
        while (!gamepad1.b) {

        }


        colorSensorFrontreader.write8(3, 66); //Black Level Calibration
        telemetry.addData("Hello", "Please Wait for Black Level Calibration for the front sensor.");
        telemetry.update();
        colorSensorFrontcache = colorSensorFrontreader.read(0x04, 1);
        while (colorSensorFrontcache[0] == 66) {
            colorSensorFrontcache = colorSensorFrontreader.read(0x04, 1);
        }

        while (gamepad1.b) {
            telemetry.addData("Hey!", "Release the B button");
            telemetry.update();
        }


        telemetry.addData("Hi!", "Point the color sensor 5cm or 2in away at a white target. Then press B to calibrate the Front color sensor's white balance.");
        telemetry.update();
        while (!gamepad1.b) {

        }

        colorSensorFrontreader.write8(3, 67); //White Level Calibration
        telemetry.addData(":)", "Wait for White Balance Calibration for the Front sensor.");
        telemetry.update();
        colorSensorFrontcache = colorSensorFrontreader.read(0x04, 1);
        while (colorSensorFrontcache[0] == 67) {
            colorSensorFrontcache = colorSensorFrontreader.read(0x04, 1);
        }

        telemetry.addData(":)", "You're done!");
    }
}