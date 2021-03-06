package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Alex and Nick on 10/26/2016.
 */

@Autonomous

public class RunIntoCapBallBlue extends AutonomousHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        calibrateGyro();

        waitForStart();

        Thread.sleep(15000);

        encodersForward(4, 0.5);
        Thread.sleep(200);

        gyroTurnRight(22, 0.5); // Was 18 degrees
        Thread.sleep(200);

        encodersForward(2, 0.5);
        Thread.sleep(200);

        shoot();
        Thread.sleep(200);

        servoGate.setPosition(0.3);
        Thread.sleep(100);

        shoot2();
        Thread.sleep(600);

        servoGate.setPosition(0.0);
        Thread.sleep(100);

        shoot3();
        Thread.sleep(200);

        encodersForward(53, 0.5);
        Thread.sleep(200);
    }
}