package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

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

        shoot(4, 1.0);
        Thread.sleep(500);

        shoot(4, 1.0);
        Thread.sleep(500);

        encodersForward(25, 0.5);
        Thread.sleep(200);

        gyroTurnRight(35, 0.5);
        Thread.sleep(200);

        moveUntilWhiteLineRight(0.35);
        Thread.sleep(200);

        gyroTurnRight(35, 0.5);
        Thread.sleep(200);

        lineFollowerTwoSensors(10);
        Thread.sleep(200);

        colorSensorFrontCache = colorSensorFrontReader.read(0x04, 1);
        telemetry.addData("Front", colorSensorFrontCache[0] & 0xFF);
        telemetry.update();

        if ((colorSensorFrontCache[0] & 0xFF) <= 6) { // blue

            encodersBackward(2, 0.5);
            Thread.sleep(200);
            servoBeacon.setPosition(0.0);
            Thread.sleep(500);
            encodersForward(5, 0.5);
            Thread.sleep(500);
        }

        else {

            encodersBackward(2, 0.5);
            Thread.sleep(200);
            servoBeacon.setPosition(1.0);
            Thread.sleep(500);
            encodersForward(5, 0.5);
            Thread.sleep(500);
        }

        encodersBackward(5, 0.5);
        Thread.sleep(200);

        servoBeacon.setPosition(0.5);
        Thread.sleep(300);

        gyroTurnLeft(80, 0.5);
        Thread.sleep(200);

        encodersForward(4, 0.5);
        Thread.sleep(200);

        moveUntilWhiteLineStraight(0.30);
        Thread.sleep(200);

        encodersForward(1, 0.5);
        Thread.sleep(200);

        gyroTurnRight(80, 0.5);
        Thread.sleep(200);

        lineFollowerTwoSensors(10);
        Thread.sleep(200);

        colorSensorFrontCache = colorSensorFrontReader.read(0x04, 1);
        telemetry.addData("Front", colorSensorFrontCache[0] & 0xFF);
        telemetry.update();

        if ((colorSensorFrontCache[0] & 0xFF) <= 6) { // blue

            encodersBackward(2, 0.5);
            Thread.sleep(200);
            servoBeacon.setPosition(0.0);
            Thread.sleep(500);
            encodersForward(5, 0.5);
            Thread.sleep(500);
        }

        else {

            encodersBackward(2, 0.5);
            Thread.sleep(200);
            servoBeacon.setPosition(1.0);
            Thread.sleep(500);
            encodersForward(5, 0.5);
            Thread.sleep(500);
        }

        encodersBackward(5, 0.5);
        Thread.sleep(200);

        servoBeacon.setPosition(0.5);
        Thread.sleep(300);
    }
}
