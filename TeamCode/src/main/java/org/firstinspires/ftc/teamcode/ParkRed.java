package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Katelin Zichittella on 11/14/16.
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

        encodersForward(55, 0.25);
        Thread.sleep(200);

        gyroTurnLeft(35, 0.25);
        Thread.sleep(200);

        encodersForward(15, 0.25);
        Thread.sleep(200);
    }
}

