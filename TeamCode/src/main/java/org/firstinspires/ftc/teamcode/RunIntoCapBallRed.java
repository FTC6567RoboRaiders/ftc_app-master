package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Alex Snyder on 10/26/2016.
 */

@Autonomous

public class RunIntoCapBallRed extends AutonomousHeaderOld {

    @Override
    public void runOpMode() throws InterruptedException {

        while (opModeIsActive()) {

            initialize();

            calibrateGyro();

            waitForStart();

            encodersForward(3, 0.25);
            Thread.sleep(200);

            gyroTurnLeft(60, 0.25);
            Thread.sleep(200);

            encodersForward(50, 0.25);
            Thread.sleep(200);

            encodersBackward(10, 0.25);
            Thread.sleep(200);
        }
    }
}