package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by Katelin Zichittella on 9/20/2016.
 */

@Autonomous
@Disabled

public class AutonomousSensorTest1 extends AutonomousHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        calibrateGyro();

        waitForStart();

        lineFollowerTwoSensors(15);
        Thread.sleep(200);
    }
}
