package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
//@Disabled

public class CalibrateColorSensorLeft extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    byte[] colorSensorLeftcache;

    I2cDevice colorSensorLeft;
    I2cDeviceSynch colorSensorLeftreader;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        colorSensorLeft = hardwareMap.i2cDevice.get("colorSensorLeft");
        colorSensorLeftreader = new I2cDeviceSynchImpl(colorSensorLeft, I2cAddr.create8bit(0x3c), false);
        colorSensorLeftreader.engage();

        colorSensorLeftreader.write8(3, 0);    //Set the mode of the color sensor to Active


        telemetry.addData("Heelo", "Point the color sensor into open space. Then press and hold A");
        telemetry.update();
        while (!gamepad1.a) {

        }

        colorSensorLeftreader.write8(3, 66); //Black Level Calibration
        telemetry.addData("Hello", "Please Wait for Black Level Calibration");
        telemetry.update();
        colorSensorLeftcache = colorSensorLeftreader.read(0x08, 1);
        while (colorSensorLeftcache[0] == 66) {
            colorSensorLeftcache = colorSensorLeftreader.read(0x08, 1);
        }

        while (gamepad1.a) {
            telemetry.addData("How's life going?", "Release the A button");
            telemetry.update();
        }


        telemetry.addData("I bet you're really stressed out right now because your phone is talking to you and you could be doing something useful with your life like roasting someone. Sorry, I won't do it again.", "Point the color sensor 5cm or 2in away at a white target. Then press A");
        telemetry.update();
        while (!gamepad1.a) {

        }
        colorSensorLeftreader.write8(3, 67); //White Level Calibration
        telemetry.addData(":)", "Wait for White Balance Calibration");
        telemetry.update();
        colorSensorLeftcache = colorSensorLeftreader.read(0x08, 1);
        while (colorSensorLeftcache[0] == 67) {
            colorSensorLeftcache = colorSensorLeftreader.read(0x08, 1);
        }

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            if (gamepad1.x) {
                colorSensorLeftreader.write8(3, 0);    //Set the mode of the color sensor to Active
                while (gamepad1.x) {
                }
            } else if (gamepad1.y) {
                colorSensorLeftreader.write8(3, 1);    //Set the mode of the color sensor to Passive
                while (gamepad1.y) {
                }
            }

            colorSensorLeftcache = colorSensorLeftreader.read(0x08, 1);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("1 C#", colorSensorLeftcache[0] & 0xFF);
            telemetry.addData("2", "Press X for Active");
            telemetry.addData("3", "Press Y for Passive");
            telemetry.update();

            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
    }
}

