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

/* so i stole this code from modern robotics...but modified it to use the old ColorSensor classes and methods

   Hopefully this works!!! fingers crossed....


 */

//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name = "Color Sensors CS", group = "Steve")
// @Autonomous(...) is the other common choice
@Disabled
public class SMKColorSensor_CS extends OpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    ColorSensor colorSensorLeft;
    ColorSensor colorSensorRight;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init(){

        telemetry.addData("Status", "Initialized");
       // telemetry.update();

        //get the info about the color sensors from the configuration file

        colorSensorLeft = hardwareMap.colorSensor.get("colorSensorLeft");
        colorSensorRight = hardwareMap.colorSensor.get("colorSensorRight");

        colorSensorLeft.setI2cAddress(I2cAddr.create8bit(0x3c));   // left color sensor has i2c address of x3c
        colorSensorRight.setI2cAddress(I2cAddr.create8bit(0x3e));  // right color sensor has i2c address of x3e

        colorSensorLeft.enableLed(true);    // let there be LED light
        colorSensorRight.enableLed(true);    // let there be LED light

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


    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString());

        telemetry.addData("L - Clear", colorSensorLeft.alpha());
        telemetry.addData("L - Hue  ", colorSensorLeft.argb());
        telemetry.addData("L - Red  ", colorSensorLeft.red());
        telemetry.addData("L - Green", colorSensorLeft.green());
        telemetry.addData("L - Blue ", colorSensorLeft.blue());
       // telemetry.update();

        telemetry.addData("R - Clear", colorSensorRight.alpha());
        telemetry.addData("R - Hue  ", colorSensorRight.argb());
        telemetry.addData("R - Red  ", colorSensorRight.red());
        telemetry.addData("R - Green", colorSensorRight.green());
        telemetry.addData("R - Blue ", colorSensorRight.blue());
        telemetry.update();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}