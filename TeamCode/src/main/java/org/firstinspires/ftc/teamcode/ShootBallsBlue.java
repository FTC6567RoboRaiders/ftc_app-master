package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Simon and Derin on 11/14/16.
 */

@Autonomous
public class ShootBallsBlue extends AutonomousHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        calibrateGyro();

        waitForStart();

        Thread.sleep(10000);

        encodersForward(24, 0.25);
        Thread.sleep(200);

        gyroTurnRight(75, 0.25);
        Thread.sleep(200);

        encodersForward(30, 0.25);
        Thread.sleep(200);

        gyroTurnLeft(75, 0.25);
        Thread.sleep(200);

        encodersBackward(18, 0.25);
        Thread.sleep(200);

        /*shoot();
        Thread.sleep(1000);

        shoot();*/
        Thread.sleep(200);

        encodersForward(20, 0.25);
        Thread.sleep(200);

        gyroTurnRight(45, 0.25);
        Thread.sleep(200);

        encodersForward(16, 0.25);
        Thread.sleep(200);

        gyroTurnRight(45, 0.25);
        Thread.sleep(200);

        encodersForward(32, 0.25);
        Thread.sleep(200);
    }
}