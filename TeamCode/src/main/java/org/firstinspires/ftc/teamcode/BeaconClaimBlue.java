package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by Katelin Zichittella on 10/31/2016.
 */

@Autonomous

public class BeaconClaimBlue extends AutonomousHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        calibrateGyro();

        waitForStart();

        shoot();
        Thread.sleep(200);

        servoGate.setPosition(0.3);
        Thread.sleep(100);

        shoot2();
        Thread.sleep(600);

        servoGate.setPosition(0.0);
        Thread.sleep(100);

        shoot3();
        Thread.sleep(200);

        servoBeacon.setPosition(0.5);
        Thread.sleep(200);

        encodersForward(20, 0.6);
        Thread.sleep(200);

        gyroTurnRight(68, 0.5);
        Thread.sleep(200);

        encodersForward(30, 0.6);
        Thread.sleep(200);

        gyroTurnLeft(45, 0.5);
        Thread.sleep(200);

        encodersForward(5, 0.6);
        Thread.sleep(200);

        moveUntilWhiteLineStraight(0.24);
        Thread.sleep(200);

        encodersForward(1, 0.5);
        Thread.sleep(200);

        gyroTurnRight(48, 0.5);
        Thread.sleep(200);

        lineFollowerTwoSensors(10);
        Thread.sleep(200);

        colorSensorFrontCache = colorSensorFrontReader.read(0x04, 1);
        telemetry.addData("Front", colorSensorFrontCache[0] & 0xFF);
        telemetry.update();

        if ((colorSensorFrontCache[0] & 0xFF) <= 6) { // blue

            encodersBackward(2, 0.5);
            Thread.sleep(200);
            servoBeacon.setPosition(1.0);
            Thread.sleep(500);
            encodersForward(4, 0.4);
            Thread.sleep(500);
        }
        else {

            encodersBackward(2, 0.5);
            Thread.sleep(200);
            servoBeacon.setPosition(0.0);
            Thread.sleep(500);
            encodersForward(4, 0.4);
            Thread.sleep(500);
        }

        encodersBackward(1, 0.4);
        Thread.sleep(100);
        servoBeacon.setPosition(0.5);
        Thread.sleep(300);

        colorSensorFrontCache = colorSensorFrontReader.read(0x04, 1);
        telemetry.addData("Front", colorSensorFrontCache[0] & 0xFF);
        telemetry.update();

        if ((colorSensorFrontCache[0] & 0xFF) >= 6) { // red

            Thread.sleep(5000);
            encodersBackward(2, 0.5);
            Thread.sleep(200);
            servoBeacon.setPosition(0.0);
            Thread.sleep(500);
            encodersForward(4, 0.4);
            Thread.sleep(500);
        }
        else {

            Thread.sleep(100);
        }

        encodersBackward(14, 0.5);
        Thread.sleep(200);

        servoBeacon.setPosition(0.5);
        Thread.sleep(300);

        gyroTurnLeft(65, 0.5);
        Thread.sleep(200);

        encodersForward(24, 0.6);
        Thread.sleep(200);

        gyroTurnRight(5, 0.5);
        Thread.sleep(200);

        moveUntilWhiteLineStraight(0.24);
        Thread.sleep(200);

        encodersForward(1, 0.5);
        Thread.sleep(200);

        gyroTurnRight(54, 0.5);
        Thread.sleep(200);

        lineFollowerTwoSensors(10);
        Thread.sleep(200);

        colorSensorFrontCache = colorSensorFrontReader.read(0x04, 1);
        telemetry.addData("Front", colorSensorFrontCache[0] & 0xFF);
        telemetry.update();

        if ((colorSensorFrontCache[0] & 0xFF) <= 6) { // blue

            encodersBackward(2, 0.5);
            Thread.sleep(200);
            servoBeacon.setPosition(1.0);
            Thread.sleep(500);
            encodersForward(4, 0.4);
            Thread.sleep(500);
        }
        else {

            encodersBackward(2, 0.5);
            Thread.sleep(200);
            servoBeacon.setPosition(0.0);
            Thread.sleep(500);
            encodersForward(4, 0.4);
            Thread.sleep(500);
        }

        encodersBackward(1, 0.4);
        Thread.sleep(100);
        servoBeacon.setPosition(0.5);
        Thread.sleep(300);

        colorSensorFrontCache = colorSensorFrontReader.read(0x04, 1);
        telemetry.addData("Front", colorSensorFrontCache[0] & 0xFF);
        telemetry.update();

        if ((colorSensorFrontCache[0] & 0xFF) >= 6) { // red

            Thread.sleep(5000);
            encodersBackward(2, 0.5);
            Thread.sleep(200);
            servoBeacon.setPosition(0.0);
            Thread.sleep(500);
            encodersForward(4, 0.4);
            Thread.sleep(500);
        }
        else {

            Thread.sleep(100);
        }

        encodersBackward(5, 0.6);
        Thread.sleep(200);

        servoBeacon.setPosition(0.5);
        Thread.sleep(300);
    }
}