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

        encodersForward(4, 0.3);
        Thread.sleep(200);

        shoot();
        Thread.sleep(200);

        /*servoGate.setPosition(0.35);
        Thread.sleep(750);

        shoot();
        Thread.sleep(200);/*/

        encodersForward(18, 0.3);
        Thread.sleep(200);

        gyroTurnRight(65, 0.25);
        Thread.sleep(200);

        encodersForward(36, 0.3);
        Thread.sleep(200);

        gyroTurnLeft(65, 0.25);
        Thread.sleep(200);

        moveUntilWhiteLineStraight(0.18);
        Thread.sleep(200);

        gyroTurnRight(65, 0.25);
        Thread.sleep(200);

        lineFollowerTwoSensors(10);
        Thread.sleep(200);

        colorSensorFrontCache = colorSensorFrontReader.read(0x04, 1);
        telemetry.addData("Front", colorSensorFrontCache[0] & 0xFF);
        telemetry.update();

        if ((colorSensorFrontCache[0] & 0xFF) <= 6) { // blue

            encodersBackward(2, 0.25);
            Thread.sleep(200);
            servoBeacon.setPosition(1.0);
            Thread.sleep(500);
            encodersForward(2, 0.25);
            Thread.sleep(500);
        }
        else {

            encodersBackward(2, 0.25);
            Thread.sleep(200);
            servoBeacon.setPosition(0.0);
            Thread.sleep(500);
            encodersForward(2, 0.25);
            Thread.sleep(500);
        }

        encodersBackward(8, 0.3);
        Thread.sleep(200);

        servoBeacon.setPosition(0.5);
        Thread.sleep(300);

        gyroTurnLeft(65, 0.25);
        Thread.sleep(200);

        encodersForward(5, 0.3);
        Thread.sleep(200);

        moveUntilWhiteLineStraight(0.18);
        Thread.sleep(200);

        gyroTurnRight(65, 0.25);
        Thread.sleep(200);

        lineFollowerTwoSensors(10);
        Thread.sleep(200);

        colorSensorFrontCache = colorSensorFrontReader.read(0x04, 1);
        telemetry.addData("Front", colorSensorFrontCache[0] & 0xFF);
        telemetry.update();

        if ((colorSensorFrontCache[0] & 0xFF) <= 6) { // blue

            encodersBackward(2, 0.25);
            Thread.sleep(200);
            servoBeacon.setPosition(1.0);
            Thread.sleep(500);
            encodersForward(2, 0.25);
            Thread.sleep(500);
        }
        else {

            encodersBackward(2, 0.25);
            Thread.sleep(200);
            servoBeacon.setPosition(0.0);
            Thread.sleep(500);
            encodersForward(2, 0.25);
            Thread.sleep(500);
        }

        encodersBackward(5, 0.3);
        Thread.sleep(200);

        servoBeacon.setPosition(0.5);
        Thread.sleep(300);
    }
}
