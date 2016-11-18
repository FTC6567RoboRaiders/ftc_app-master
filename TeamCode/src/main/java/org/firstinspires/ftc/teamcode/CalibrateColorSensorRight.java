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
public class CalibrateColorSensorRight extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    byte[] colorSensorRightcache;

    I2cDevice colorSensorRight;
    I2cDeviceSynch colorSensorRightreader;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        colorSensorRight = hardwareMap.i2cDevice.get("colorSensorRight");
        colorSensorRightreader = new I2cDeviceSynchImpl(colorSensorRight, I2cAddr.create8bit(0x3e), false);
        colorSensorRightreader.engage();

        colorSensorRightreader.write8(3, 0);    //Set the mode of the color sensor to Active


        telemetry.addData("Hi!", "Point the right color sensor into open space. Then press and hold B.");
        telemetry.update();
        while (!gamepad1.b) {

        }

        colorSensorRightreader.write8(3, 66); //Black Level Calibration
        telemetry.addData("Hello", "Please Wait for Black Level Calibration.");
        telemetry.update();
        colorSensorRightcache = colorSensorRightreader.read(0x08, 1);
        while (colorSensorRightcache[0] == 66) {
            colorSensorRightcache = colorSensorRightreader.read(0x08, 1);
        }

        while (gamepad1.b) {
            telemetry.addData("Heelo", "Release the B button.");
            telemetry.update();
        }


        telemetry.addData("The Roobt says Hi!", "Point the color sensor 5cm or 2in away at a white target. Then press B.");
        telemetry.update();
        while (!gamepad1.b) {

        }
        colorSensorRightreader.write8(3, 67); //White Level Calibration
        telemetry.addData("Who's on the roast list today?", "Please Wait while White Balance Calibration");
        telemetry.update();
        colorSensorRightcache = colorSensorRightreader.read(0x08, 1);
        while (colorSensorRightcache[0] == 67) {
            colorSensorRightcache = colorSensorRightreader.read(0x08, 1);
        }

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            if (gamepad1.x) {
                colorSensorRightreader.write8(3, 0);    //Set the mode of the color sensor to Active
                while (gamepad1.x) {
                }
            } else if (gamepad1.y) {
                colorSensorRightreader.write8(3, 1);    //Set the mode of the color sensor to Passive
                while (gamepad1.y) {
                }
            }

            colorSensorRightcache = colorSensorRightreader.read(0x08, 1);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("1 C#", colorSensorRightcache[0] & 0xFF);
            telemetry.addData("2", "Press X for Active");
            telemetry.addData("3", "Press Y for Passive");
            telemetry.update();

            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
    }
}