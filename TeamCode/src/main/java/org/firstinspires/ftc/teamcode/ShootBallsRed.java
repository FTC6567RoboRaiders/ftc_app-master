package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Simon and Derin on 11/14/16.
 */

@Autonomous
public class ShootBallsRed extends AutonomousHeader {

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

