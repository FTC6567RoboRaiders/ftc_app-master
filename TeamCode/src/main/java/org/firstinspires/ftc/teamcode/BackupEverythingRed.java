package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by Alex and Simon on 1/16/17.
 */

@Autonomous
@Disabled

public class BackupEverythingRed extends AutonomousHeaderPretzel {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        /*shoot();
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

        encodersForward(22, 0.3);
        Thread.sleep(200);*/

        encoderTurnRight(18, 0.3);
        Thread.sleep(200);

        /*encodersForward(31, 0.3);
        Thread.sleep(200);*/

        encoderTurnRight(18, 0.3);
        Thread.sleep(200);

        /*encodersForward(5, 0.3);
        Thread.sleep(200);

        moveUntilWhiteLineStraight(0.12, 10);
        Thread.sleep(200);

        encodersForward(1, 0.25);
        Thread.sleep(200);*/

        encoderTurnLeft(18, 0.3);
        Thread.sleep(200);

        /*lineFollowerTwoSensors(10);
        Thread.sleep(200);

        colorSensorFrontCache = colorSensorFrontReader.read(0x04, 1);
        telemetry.addData("Front", colorSensorFrontCache[0] & 0xFF);
        telemetry.update();

        if ((colorSensorFrontCache[0] & 0xFF) <= 6) { // blue

            encodersBackward(2, 0.25);
            Thread.sleep(200);
            servoBeacon.setPosition(1.0);
            Thread.sleep(500);
            encodersForward(4, 0.2);
            Thread.sleep(500);
        }
        else {

            encodersBackward(2, 0.25);
            Thread.sleep(200);
            servoBeacon.setPosition(0.0);
            Thread.sleep(500);
            encodersForward(4, 0.2);
            Thread.sleep(500);
        }

        encodersBackward(0.5, 0.2);
        Thread.sleep(100);
        servoBeacon.setPosition(0.5);
        Thread.sleep(300);

        colorSensorFrontCache = colorSensorFrontReader.read(0x04, 1);
        telemetry.addData("Front", colorSensorFrontCache[0] & 0xFF);
        telemetry.update();

        if ((colorSensorFrontCache[0] & 0xFF) >= 6) { // red

            Thread.sleep(5000);
            encodersBackward(2, 0.25);
            Thread.sleep(200);
            servoBeacon.setPosition(0.0);
            Thread.sleep(500);
            encodersForward(4, 0.2);
            Thread.sleep(500);
        }
        else {

            Thread.sleep(100);
        }

        encodersBackward(14, 0.3);
        Thread.sleep(200);

        servoBeacon.setPosition(0.5);
        Thread.sleep(300);*/

        encoderTurnRight(18, 0.3);
        Thread.sleep(200);

        /*encodersForward(20, 0.35);
        Thread.sleep(200);*/

        encoderTurnLeft(6, 0.25);
        Thread.sleep(200);

        /*moveUntilWhiteLineStraight(0.12, 10);
        Thread.sleep(200);

        encodersForward(1, 0.25);
        Thread.sleep(200);*/

        encoderTurnLeft(18, 0.3);
        Thread.sleep(200);

        /*lineFollowerTwoSensors(10);
        Thread.sleep(200);

        colorSensorFrontCache = colorSensorFrontReader.read(0x04, 1);
        telemetry.addData("Front", colorSensorFrontCache[0] & 0xFF);
        telemetry.update();

        if ((colorSensorFrontCache[0] & 0xFF) <= 6) { // blue

            encodersBackward(2, 0.25);
            Thread.sleep(200);
            servoBeacon.setPosition(1.0);
            Thread.sleep(500);
            encodersForward(4, 0.2);
            Thread.sleep(500);
        }
        else {

            encodersBackward(2, 0.25);
            Thread.sleep(200);
            servoBeacon.setPosition(0.0);
            Thread.sleep(500);
            encodersForward(4, 0.2);
            Thread.sleep(500);
        }

        encodersBackward(0.5, 0.2);
        Thread.sleep(100);
        servoBeacon.setPosition(0.5);
        Thread.sleep(300);

        colorSensorFrontCache = colorSensorFrontReader.read(0x04, 1);
        telemetry.addData("Front", colorSensorFrontCache[0] & 0xFF);
        telemetry.update();

        if ((colorSensorFrontCache[0] & 0xFF) >= 6) { // red

            Thread.sleep(5000);
            encodersBackward(2, 0.25);
            Thread.sleep(200);
            servoBeacon.setPosition(0.0);
            Thread.sleep(500);
            encodersForward(4, 0.2);
            Thread.sleep(500);
        }
        else {

            Thread.sleep(100);
        }

        encodersBackward(5, 0.3);
        Thread.sleep(200);

        servoBeacon.setPosition(0.5);
        Thread.sleep(300);*/
    }
}