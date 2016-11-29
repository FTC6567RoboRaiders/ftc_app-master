package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Simon and Derin on 11/14/16.
 */

@Autonomous
public class ShootBallsRed extends AutonomousHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        calibrateGyro();

        waitForStart();

        shoot();
        Thread.sleep(1000);

        shoot();
        Thread.sleep(200);

        encodersForward(30, 0.25);
        Thread.sleep(200);

        gyroTurnLeft(45, 0.25);
        Thread.sleep(200);

        encodersForward(20, 0.25);
        Thread.sleep(200);

        gyroTurnLeft(45, 0.25);
        Thread.sleep(200);

        encodersForward(30, 0.25);
        Thread.sleep(200);

        stop();
    }
}

