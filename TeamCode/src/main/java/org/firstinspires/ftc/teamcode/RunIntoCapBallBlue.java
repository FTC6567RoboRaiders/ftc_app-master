package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Alex Snyder on 10/26/2016.
 */

@Autonomous

public class RunIntoCapBallBlue extends AutonomousHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        calibrateGyro();

        waitForStart();

        encodersForward(3, 0.1);
        Thread.sleep(200);

        gyroTurnRight(60, 0.1);
        Thread.sleep(200);

        encodersForward(50, 0.1);
        Thread.sleep(200);

        encodersBackward(10, 0.1);
        Thread.sleep(200);

        stop();
    }
}