package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Alex Snyder on 10/26/2016.
 */

@Autonomous

public class RunIntoCapBallBlue extends AutonomousHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        calibrateGyro();

        waitForStart();

        encodersForward(3, 0.25);
        Thread.sleep(200);
        telemetry.addData("Status", "Moved 3 inches");
        telemetry.update();

        gyroTurnRight(20, 0.2);
        Thread.sleep(200);
        telemetry.addData("Status", "Turning 45 degrees");
        telemetry.update();

        encodersForward(60, 1);
        Thread.sleep(200);
        telemetry.addData("Status", "Moved 60 inches");
        telemetry.update();

        encodersBackward(10, 0.25);
        Thread.sleep(200);
        telemetry.addData("Status", "Moved 10 inches backward");
        telemetry.update();

    }
}