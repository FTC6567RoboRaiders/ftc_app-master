package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by Katelin Zichittella on 11/14/16.
 */

@Autonomous
@Disabled

public class ParkRed extends AutonomousHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        calibrateGyro();

        waitForStart();

        Thread.sleep(10000);

        encodersForward(5, 0.5);
        Thread.sleep(200);

        gyroTurnLeft(22, 0.5);
        Thread.sleep(200);

        encodersForward(18, 0.5);
        Thread.sleep(200);

        gyroTurnLeft(28, 0.5);
        Thread.sleep(200);

        encodersForward(50, 0.5);
        Thread.sleep(200);

        gyroTurnLeft(35, 0.5);
        Thread.sleep(200);

        encodersForward(15, 0.5);
        Thread.sleep(200);
    }
}