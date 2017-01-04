package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Simon and Derin on 11/14/16.
 */

@Autonomous
public class ShootBallsBlue extends AutonomousHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        calibrateGyro();

        waitForStart();

        Thread.sleep(10000);

        encodersForward(6, 0.25);
        Thread.sleep(200);

        gyroTurnRight(20, 0.25);
        Thread.sleep(200);

        encodersForward(3, 0.25);
        Thread.sleep(200);

        shoot();
        Thread.sleep(200);

        servoGate.setPosition(0.3);
        Thread.sleep(100);

        shoot2();
        Thread.sleep(500);

        shoot3();
        Thread.sleep(200);

        encodersForward(5, 0.25);
        Thread.sleep(200);

        gyroTurnRight(28, 0.25);
        Thread.sleep(200);

        encodersForward(50, 0.25);
        Thread.sleep(200);

        gyroTurnRight(35, 0.25);
        Thread.sleep(200);

        encodersForward(15, 0.25);
        Thread.sleep(200);
    }
}