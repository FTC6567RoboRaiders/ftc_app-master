package org.firstinspires.ftc.teamcode;

/*
Modern Robotics Color Sensors Example with color number
Created 9/29/2016 by Colton Mehlhoff of Modern Robotics using FTC SDK 2.2
Reuse permitted with credit where credit is due

ModernRoboticsI2cColorSensor class is not being used because it can not access color number.
ColorSensor class is not being used because it can not access color number.

To change color sensor I2C Addresses, go to http://modernroboticsedu.com/mod/lesson/view.php?id=96
Support is available by emailing support@modernroboticsinc.com.
*/

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp

public class ColorSensorTest extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    byte[] colorSensorLeftCache;
    byte[] colorSensorRightCache;

    I2cDevice colorSensorLeft;
    I2cDevice colorSensorRight;
    I2cDeviceSynch colorSensorLeftReader;
    I2cDeviceSynch colorSensorRightReader;

    /*
     * Code to run ONCE when the driver hits INIT
     */

    @Override
    public void init() {

        telemetry.addData("Status", "Initialized");

        colorSensorLeft = hardwareMap.i2cDevice.get("colorSensorLeft");
        colorSensorRight = hardwareMap.i2cDevice.get("colorSensorRight");

        colorSensorLeftReader = new I2cDeviceSynchImpl(colorSensorLeft, I2cAddr.create8bit(0x3c), false);
        colorSensorRightReader = new I2cDeviceSynchImpl(colorSensorRight, I2cAddr.create8bit(0x3e), false);

        colorSensorLeftReader.engage();
        colorSensorRightReader.engage();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */

    @Override
    public void init_loop() {

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */

    @Override
    public void start() {

        runtime.reset();

        colorSensorLeftReader.write8(3, 0); // turns the LEDs on
        colorSensorRightReader.write8(3, 0);
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */

    @Override
    public void loop() {

        colorSensorLeftCache = colorSensorLeftReader.read(0x04, 1); // gets color number values
        colorSensorRightCache = colorSensorRightReader.read(0x04, 1);

        telemetry.addData("Left Color Number", colorSensorLeftCache[0] & 0xFF); // displays color number values
        telemetry.addData("Right Color Number", colorSensorRightCache[0] & 0xFF);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */

    @Override
    public void stop() {

    }

}