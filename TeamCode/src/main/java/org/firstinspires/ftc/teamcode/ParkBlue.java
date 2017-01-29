package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Katelin Zichittella on 10/26/2016.
 */

@Autonomous

public class ParkBlue extends AutonomousHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        calibrateGyro();

        waitForStart();

        Thread.sleep(15000);

        encodersForward(4, 0.25);
        Thread.sleep(200);

        gyroTurnRight(22, 0.25);
        Thread.sleep(200);

        encodersForward(55, 0.25);
        Thread.sleep(200);

        encodersBackward(10, 0.25);
        Thread.sleep(200);
    }
}