package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Katelin Zichittella on 10/22/2016.
 */

@Autonomous

public class AdvancedSampleAutonomous extends AdvancedSampleAutonomousHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        encodersForward(12, 1.0);

        Thread.sleep(500);

        encodersBackward(12, 1.0);

        Thread.sleep(500);

        gyroTurnRight(90, 1.0);

        Thread.sleep(500);

        gyroTurnLeft(90, 1.0);

        Thread.sleep(500);
    }
}
