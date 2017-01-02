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

        encodersForward(5, 0.25);
        Thread.sleep(200);

        gyroTurnRight(65, 0.25);
        Thread.sleep(200);

        encodersForward(50, 0.25);
        Thread.sleep(200);

        gyroTurnRight(35, 0.25);
        Thread.sleep(200);

        encodersForward(30, 0.25);
        Thread.sleep(200);
    }
}