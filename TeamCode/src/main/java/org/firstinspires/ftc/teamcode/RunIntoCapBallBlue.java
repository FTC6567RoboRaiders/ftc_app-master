package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Alex Snyder on 10/26/2016.
 */

@Autonomous

public class RunIntoCapBallBlue extends AutonomousHeader1 {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        calibrateGyro();

        waitForStart();

        encodersForward(3, 0.5);
        Thread.sleep(200);

        gyroTurnRight(45, 0.5);
        Thread.sleep(200);

        encodersForward(60, 0.5);
        Thread.sleep(200);

        encodersBackward(10, 0.5);
        Thread.sleep(200);
    }
}