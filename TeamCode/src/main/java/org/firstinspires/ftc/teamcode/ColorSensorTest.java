package org.firstinspires.ftc.teamcode;

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
    public void init() {

        telemetry.addData("Status", "Initialized");

        colorSensorLeft = hardwareMap.i2cDevice.get("colorSensorLeft");
        colorSensorRight = hardwareMap.i2cDevice.get("colorSensorRight");
        colorSensorFront = hardwareMap.i2cDevice.get("colorSensorFront");
        rangeSensorLeft = hardwareMap.i2cDevice.get("rangeSensorLeft");
        rangeSensorRight = hardwareMap.i2cDevice.get("rangeSensorRight");

        colorSensorLeftReader = new I2cDeviceSynchImpl(colorSensorLeft, I2cAddr.create8bit(0x3c), false);
        colorSensorRightReader = new I2cDeviceSynchImpl(colorSensorRight, I2cAddr.create8bit(0x3e), false);
        colorSensorFrontReader = new I2cDeviceSynchImpl(colorSensorFront, I2cAddr.create8bit(0x42), false);
        rangeSensorLeftReader = new I2cDeviceSynchImpl(rangeSensorLeft, I2cAddr.create8bit(0x28), false);
        rangeSensorRightReader = new I2cDeviceSynchImpl(rangeSensorRight, I2cAddr.create8bit(0x30), false);

        colorSensorLeftReader.engage();
        colorSensorRightReader.engage();
        colorSensorFrontReader.engage();
        rangeSensorLeftReader.engage();
        rangeSensorRightReader.engage();
    }

    @Override
    public void start() {

        runtime.reset();

        colorSensorLeftReader.write8(3, 0);
        colorSensorRightReader.write8(3, 0);
        colorSensorFrontReader.write8(3, 1);
    }

    @Override
    public void loop() {

        colorSensorLeftCache = colorSensorLeftReader.read(0x08, 1);
        colorSensorRightCache = colorSensorRightReader.read(0x08, 1);
        colorSensorFrontCache = colorSensorFrontReader.read(0x04, 1);
        rangeSensorLeftCache = rangeSensorLeftReader.read(0x04, 1);
        rangeSensorRightCache = rangeSensorRightReader.read(0x04, 1);

        telemetry.addData("LeftColor", colorSensorLeftCache[0] & 0xFF);
        telemetry.addData("RightColor", colorSensorRightCache[0] & 0xFF);
        telemetry.addData("FrontColor", colorSensorFrontCache[0] & 0xFF);
        telemetry.addData("LeftRange", rangeSensorLeftCache[0] & 0xFF);
        telemetry.addData("RightRange", rangeSensorRightCache[0] & 0xFF);
        telemetry.update();
    }

    @Override
    public void stop() {

    }
}