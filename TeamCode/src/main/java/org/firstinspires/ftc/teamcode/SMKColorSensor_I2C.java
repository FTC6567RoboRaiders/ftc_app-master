package org.firstinspires.ftc.teamcode;

/*
Modern Robotics Color Sensors Example with color number
Created 9/29/2016 by Colton Mehlhoff of Modern Robotics using FTC SDK 2.2
Reuse permitted with credit where credit is due

Configuration:
I2CDevice "ca" (MRI Color Sensor with I2C address 0x3a (0x1d 7-bit)
I2CDevice "cc" (MRI Color Sensor with default I2C address 0x3c (0x1e 7-bit)

ModernRoboticsI2cColorSensor class is not being used because it can not access color number.
ColorSensor class is not being used because it can not access color number.

To change color sensor I2C Addresses, go to http://modernroboticsedu.com/mod/lesson/view.php?id=96
Support is available by emailing support@modernroboticsinc.com.
*/

/* so i stole this code from modern robotics...it will print out the color number that each sensor is
   is reading...in addition it will output the address of each color sensor....

   Hopefully this works!!! fingers crossed....

   Now I have changed this - testing for github update  github testing



 */

//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name = "Color Sensors I2C", group = "Steve")
// @Autonomous(...) is the other common choice
@Disabled
public class SMKColorSensor_I2C extends OpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    byte[] colorLeftCache;
    byte[] colorRightCache;

    I2cDevice colorLeft;
    I2cDevice colorRight;
    I2cDeviceSynch colorLeftReader;
    I2cDeviceSynch colorRightReader;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        //the below lines set up the configuration file
        colorLeft = hardwareMap.i2cDevice.get("colorSensorLeft");
        colorRight = hardwareMap.i2cDevice.get("colorSensorRight");

        colorLeftReader = new I2cDeviceSynchImpl(colorLeft, I2cAddr.create8bit(0x3c), false);
        colorRightReader = new I2cDeviceSynchImpl(colorRight, I2cAddr.create8bit(0x3e), false);

        colorLeftReader.engage();
        colorRightReader.engage();

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

        colorLeftReader.write8(0x03, 0x00);     //Set the mode of the color sensor to passive
        colorRightReader.write8(0x03, 0x00);    //Set the mode of the color sensor to passive


        //Active - For measuring reflected light. Cancels out ambient light
        //Passive - For measuring ambient light, eg. the FTC Color Beacon
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString());

        colorLeftCache = colorLeftReader.read(0x04, 1);
        colorRightCache = colorRightReader.read(0x04, 1);

        //display values
        telemetry.addData("1 #Color Left", colorLeftCache[0] & 0xFF);
        telemetry.addData("2 #Color Right", colorRightCache[0] & 0xFF);

        telemetry.addData("3 A", colorLeftReader.getI2cAddress().get8Bit());
        telemetry.addData("4 A", colorRightReader.getI2cAddress().get8Bit());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}