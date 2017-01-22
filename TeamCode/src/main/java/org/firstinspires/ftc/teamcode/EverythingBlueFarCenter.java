package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by Derin and Simon on 1/2/2017.
 */

@Autonomous
@Disabled

// NEEDS TO BE TESTED

public class EverythingBlueFarCenter extends AutonomousHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        calibrateGyro();

        waitForStart();

        encodersForward(5, 0.25);
        Thread.sleep(200);

        gyroTurnRight(15, 0.25);
        Thread.sleep(200);

        shoot();
        Thread.sleep(200);

        servoGate.setPosition(0.35);
        Thread.sleep(100);

        shoot2();
        Thread.sleep(500);

        shoot3();
        Thread.sleep(200);

        servoBeacon.setPosition(0.5);
        Thread.sleep(200);

        gyroTurnRight(50, 0.25);
        Thread.sleep(500);

        encodersForward(25, 0.25);
        Thread.sleep(500);

        gyroTurnLeft(65, 0.25);
        Thread.sleep(500);

        encodersForward(20, 0.3);
        Thread.sleep(200);

        gyroTurnRight(65, 0.25);
        Thread.sleep(200);

        encodersForward(35, 0.3);
        Thread.sleep(200);

        gyroTurnLeft(62, 0.25);
        Thread.sleep(200);

        moveUntilWhiteLineStraight(0.15, 10);
        Thread.sleep(200);

        encodersForward(1, 0.25);
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

        gyroTurnLeft(62, 0.25);
        Thread.sleep(200);

        encodersForward(5, 0.3);
        Thread.sleep(200);

        moveUntilWhiteLineStraight(0.15, 10);
        Thread.sleep(200);

        encodersForward(1, 0.25);
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

        encodersBackward(28, 0.4);
        Thread.sleep(200);

        gyroTurnLeft(48, 0.25);
        Thread.sleep(200);

        encodersBackward(50, 0.4);
        Thread.sleep(500);

    }
}
