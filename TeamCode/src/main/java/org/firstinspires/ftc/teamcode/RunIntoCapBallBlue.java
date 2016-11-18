package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Alex Snyder on 10/26/2016.
 */

@Autonomous

public class RunIntoCapBallBlue extends RunIntoCapBallHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        calibrateGyro();

        waitForStart();

        encodersForward(3, 0.5);

        Thread.sleep(250);

        gyroTurnRight(45, 0.5);

        Thread.sleep(250);

        encodersForward(60, 0.5);

        Thread.sleep(250);

        encodersForward(6, 1.0);

        Thread.sleep(250);

        gyroTurnLeft(720, 0.5);

    }
}