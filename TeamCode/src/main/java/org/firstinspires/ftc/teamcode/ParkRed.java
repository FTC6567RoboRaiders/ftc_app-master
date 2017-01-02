package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Simon and Derin on 11/14/16.
 */

@Autonomous
public class ParkRed extends AutonomousHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        calibrateGyro();

        waitForStart();

        Thread.sleep(10000);

        encodersForward(5, 0.25);
        Thread.sleep(200);

        gyroTurnLeft(73, 0.25);
        Thread.sleep(200);

        encodersForward(50, 0.25);
        Thread.sleep(200);

        gyroTurnLeft(35, 0.25);
        Thread.sleep(200);

        encodersForward(30, 0.25);
        Thread.sleep(200);
    }
}

