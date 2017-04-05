package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by Katelin Zichittella on 11/14/16.
 */

@Autonomous
@Disabled

public class ParkBlue extends AutonomousHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        calibrateGyro();

        waitForStart();

        Thread.sleep(10000);

        encodersForward(6, 0.5);
        Thread.sleep(200);

        gyroTurnRight(20, 0.5);
        Thread.sleep(200);

        encodersForward(3, 0.5);
        Thread.sleep(200);

        encodersForward(5, 0.5);
        Thread.sleep(200);

        gyroTurnRight(28, 0.5);
        Thread.sleep(200);

        encodersForward(50, 0.5);
        Thread.sleep(200);

        gyroTurnRight(30, 0.5);
        Thread.sleep(200);

        encodersForward(15, 0.5);
        Thread.sleep(200);
    }
}