package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Simon and Derin on 11/14/16.
 */

@Autonomous
public class ParkRed extends AutonomousHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        while (opModeIsActive()) {

            initialize();

            calibrateGyro();

            waitForStart();

            encodersForward(30, 0.5);
            Thread.sleep(200);

            gyroTurnLeft(45, 0.5);
            Thread.sleep(200);

            encodersForward(70, 0.5);
            Thread.sleep(200);

            gyroTurnLeft(45, 0.5);
            Thread.sleep(200);

            encodersForward(30, 0.5);
            Thread.sleep(200);
        }
    }
}

