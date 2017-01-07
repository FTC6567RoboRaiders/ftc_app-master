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

        Thread.sleep(10000);

        encodersForward(6, 0.25);
        Thread.sleep(200);

        gyroTurnRight(20, 0.25);
        Thread.sleep(200);

        encodersForward(8, 0.25);
        Thread.sleep(200);

        gyroTurnRight(28, 0.25);
        Thread.sleep(200);

        encodersForward(50, 0.25);
        Thread.sleep(200);

        gyroTurnRight(30, 0.25);
        Thread.sleep(200);

        encodersForward(15, 0.25);
        Thread.sleep(200);
    }
}