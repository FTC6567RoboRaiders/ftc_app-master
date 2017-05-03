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

        /*servoBeacon.setPosition(0.5);
        Thread.sleep(200);*/

        encodersForward(20, 0.6); //* is 22 in red
        Thread.sleep(200);

        gyroTurnRight(68, 0.5);  //* is 70 in Red
        Thread.sleep(200);

        encodersForward(28, 0.6);  //* is 22 in red
        Thread.sleep(200);

        gyroTurnLeft(42, 0.5);  //* is 48 in red  was 45
        Thread.sleep(200);

        encodersForward(5, 0.6);
        Thread.sleep(200);

        moveUntilWhiteLineStraight(0.24);
        Thread.sleep(200);

        encodersForward(1, 0.5);
        Thread.sleep(200);

        gyroTurnRight(45, 0.5);    //* was 48
        Thread.sleep(200);

        lineFollowerTwoSensors(6);  //* was 9, changed to 11, then change to 6
        Thread.sleep(200);

        /*colorSensorFrontCache = colorSensorFrontReader.read(0x04, 1);
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
        }*/

        encodersBackward(2, 0.4);
        Thread.sleep(100);

        /*servoBeacon.setPosition(0.5);
        Thread.sleep(300);*/

        colorSensorFrontCache = colorSensorFrontReader.read(0x04, 1);
        telemetry.addData("Front", colorSensorFrontCache[0] & 0xFF);
        telemetry.update();

        if ((colorSensorFrontCache[0] & 0xFF) >= 6) { // red

            Thread.sleep(5000);

            encodersBackward(2, 0.5);
            Thread.sleep(200);

            /*servoBeacon.setPosition(0.0);
            Thread.sleep(500);*/

            encodersForward(4, 0.4);
            Thread.sleep(500);

            encodersBackward(15, 0.6);
            Thread.sleep(200);

            gyroTurnRight(65, 0.5);
            Thread.sleep(200);

            encodersForward(26, 0.4);
            Thread.sleep(200);

            gyroTurnLeft(42, 0.5);
            Thread.sleep(200);

            encodersForward(8, 0.4);
            Thread.sleep(20000);
        }
        else {

            Thread.sleep(100);
        }

        encodersBackward(14, 0.5);
        Thread.sleep(200);

        /*servoBeacon.setPosition(0.5);
        Thread.sleep(300);*/

        gyroTurnLeft(68, 0.5);
        Thread.sleep(200);

        encodersForward(26, 0.75);
        Thread.sleep(200);

        gyroTurnRight(5, 0.5);
        Thread.sleep(200);

        moveUntilWhiteLineStraight(0.28);
        Thread.sleep(200);

        encodersForward(1, 0.5);
        Thread.sleep(200);

        gyroTurnRight(54, 0.5);   //* is 46 in red
        Thread.sleep(200);

        lineFollowerTwoSensors(6);  //* was 9 now 6
        Thread.sleep(200);

        /*colorSensorFrontCache = colorSensorFrontReader.read(0x04, 1);
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
        }*/

        encodersBackward(2, 0.4);
        Thread.sleep(100);

        /*servoBeacon.setPosition(0.5);
        Thread.sleep(300);*/

        colorSensorFrontCache = colorSensorFrontReader.read(0x04, 1);
        telemetry.addData("Front", colorSensorFrontCache[0] & 0xFF);
        telemetry.update();

        if ((colorSensorFrontCache[0] & 0xFF) >= 6) { // red

            Thread.sleep(5000);

            /*encodersBackward(2, 0.5);
            Thread.sleep(200);

            servoBeacon.setPosition(0.0);
            Thread.sleep(500);*/

            encodersForward(4, 0.4);
            Thread.sleep(500);
        }
        else {

            Thread.sleep(100);
        }

        encodersBackward(5, 0.6);
        Thread.sleep(200);

        /*servoBeacon.setPosition(0.5);
        Thread.sleep(300);*/
    }
}