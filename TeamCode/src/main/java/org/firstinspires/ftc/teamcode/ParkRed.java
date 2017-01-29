package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Katelin Zichittella on 10/26/2016.
 */

@Autonomous

public class ParkRed extends AutonomousHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        calibrateGyro();

        waitForStart();

        Thread.sleep(15000);

        encodersForward(6, 0.25);
        Thread.sleep(200);

        gyroTurnLeft(23, 0.25);
        Thread.sleep(200);

        encodersForward(55, 0.25);
        Thread.sleep(200);
    }
}