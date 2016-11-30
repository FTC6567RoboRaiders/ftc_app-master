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

        encodersForward(24, 0.25);
        Thread.sleep(200);

        gyroTurnRight(90, 0.25);
        Thread.sleep(200);

        encodersForward(15, 0.25);
        Thread.sleep(200);

        gyroTurnLeft(90, 0.25);
        Thread.sleep(200);

        encodersBackward(11, 0.25);
        Thread.sleep(200);

        /*shoot();
        Thread.sleep(1000);

        shoot();
        Thread.sleep(200);*/

        encodersForward(30, 0.25);
        Thread.sleep(200);

        gyroTurnRight(45, 0.25);
        Thread.sleep(200);

        encodersForward(20, 0.25);
        Thread.sleep(200);

        gyroTurnRight(45, 0.25);
        Thread.sleep(200);

        encodersForward(30, 0.25);
        Thread.sleep(200);

        stop();
    }
}

