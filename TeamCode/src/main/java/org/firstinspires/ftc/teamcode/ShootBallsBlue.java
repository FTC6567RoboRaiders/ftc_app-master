package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by roboraiders on 10/25/16.
 */

@Autonomous

public class ShootBallsBlue extends ShootBallsHeader {
    @Override
    public void runOpMode() throws InterruptedException {

        initialize();
        calibrateGyro();
        waitForStart();

        encodersForward(60, 0.5);
        Thread.sleep(500);
        gyroTurnLeft(45,0.5);
        Thread.sleep(500);
        /*at some point in here, the ball would be launched,
        the code will be added once we have more info about the choo choo */
        encodersBackward(56,0.5);

    }

}