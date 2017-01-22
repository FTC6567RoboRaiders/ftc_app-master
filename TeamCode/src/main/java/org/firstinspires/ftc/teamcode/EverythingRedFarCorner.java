package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by Derin and Simon on 1/2/17.
 */

@Autonomous
@Disabled

// NEEDS TO BE TESTED

public class EverythingRedFarCorner extends AutonomousHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        calibrateGyro();

        waitForStart();

        Thread.sleep(10000);

        encodersForward(5, 0.25);
        Thread.sleep(200);

        gyroTurnLeft(25, 0.25);
        Thread.sleep(200);

        shoot();
        Thread.sleep(200);

        servoGate.setPosition(0.35);
        Thread.sleep(100);

        shoot2();
        Thread.sleep(500);

        gyroTurnLeft(50, 0.25);
        Thread.sleep(200);

        encodersForward(29,0.25);

        gyroTurnRight(45,0.25);

        moveUntilWhiteLineStraight(0.15, 10);
        Thread.sleep(200);

        encodersForward(1, 0.25);
        Thread.sleep(200);

        gyroTurnLeft(65, 0.25);
        Thread.sleep(200);

        lineFollowerTwoSensors(10);
        Thread.sleep(200);

        colorSensorFrontCache = colorSensorFrontReader.read(0x04, 1);
        telemetry.addData("Front", colorSensorFrontCache[0] & 0xFF);
        telemetry.update();

        if ((colorSensorFrontCache[0] & 0xFF) >= 6) { // red

            encodersBackward(2, 0.25);
            Thread.sleep(200);
            servoBeacon.setPosition(1.0);
            Thread.sleep(500);
            encodersForward(2, 0.2);
            Thread.sleep(500);
        }
        else {

            encodersBackward(2, 0.25);
            Thread.sleep(200);
            servoBeacon.setPosition(0.0);
            Thread.sleep(500);
            encodersForward(2, 0.2);
            Thread.sleep(500);
        }

        encodersBackward(10, 0.3);
        Thread.sleep(200);

        servoBeacon.setPosition(0.5);
        Thread.sleep(300);

        gyroTurnRight(68, 0.25);
        Thread.sleep(200);

        encodersForward(5, 0.3);
        Thread.sleep(200);

        moveUntilWhiteLineStraight(0.15, 10);
        Thread.sleep(200);

        encodersForward(1, 0.25);
        Thread.sleep(200);

        gyroTurnLeft(65, 0.25);
        Thread.sleep(200);

        lineFollowerTwoSensors(10);
        Thread.sleep(200);

        colorSensorFrontCache = colorSensorFrontReader.read(0x04, 1);
        telemetry.addData("Front", colorSensorFrontCache[0] & 0xFF);
        telemetry.update();

        if ((colorSensorFrontCache[0] & 0xFF) >= 6) { // red

            encodersBackward(2, 0.25);
            Thread.sleep(200);
            servoBeacon.setPosition(1.0);
            Thread.sleep(500);
            encodersForward(2, 0.2);
            Thread.sleep(500);
        }
        else {

            encodersBackward(2, 0.25);
            Thread.sleep(200);
            servoBeacon.setPosition(0.0);
            Thread.sleep(500);
            encodersForward(2, 0.2);
            Thread.sleep(500);
        }

        encodersBackward(5, 0.3);
        Thread.sleep(200);

        servoBeacon.setPosition(0.5);
        Thread.sleep(300);

        encodersBackward(20, 0.25);
        Thread.sleep(200);

        gyroTurnLeft(90, 0.25);
        Thread.sleep(200);

        encodersForward(40, 0.25);
        Thread.sleep(200);

        gyroTurnRight(135, 0.25);
        Thread.sleep(200);

        encodersBackward(3, 0.25);
        Thread.sleep(200);
    }
}