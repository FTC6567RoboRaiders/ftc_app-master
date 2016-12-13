package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Katelin Zichittella on 11/14/16.
 */

@Autonomous
public class ParkBlue extends AutonomousHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        calibrateGyro();

        waitForStart();

        Thread.sleep(5000);

        encodersForward(24, 0.25);
        Thread.sleep(200);

        gyroTurnRight(75, 0.25);
        Thread.sleep(200);

        encodersForward(50, 0.25);
        Thread.sleep(200);

        gyroTurnLeft(45, 0.25);
        Thread.sleep(200);

        encodersForward(16, 0.25);
        Thread.sleep(200);

        gyroTurnRight(45, 0.25);
        Thread.sleep(200);

        encodersForward(32, 0.25);
        Thread.sleep(200);
    }
}

