package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Alex and Nick on 10/26/2016.
 */

@Autonomous

public class RunIntoCapBallRed extends AutonomousHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        calibrateGyro();

        waitForStart();

        Thread.sleep(10000);

        encodersForward(3, 0.25);
        Thread.sleep(200);

        gyroTurnLeft(20, 0.2);
        Thread.sleep(200);

        encodersForward(60, 0.5);
        Thread.sleep(200);

        encodersBackward(10, 0.25);
        Thread.sleep(200);
    }
}