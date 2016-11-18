package org.firstinspires.ftc.teamcode;

/**
 * Created by saras on 10/25/2016.
 */

public class ShootBalls extends AdvancedSampleAutonomousHeader {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();



        calibrateGyro();

        waitForStart();

        move("f", 1, 1.1);

        gyroTurnLeft(45, 1.1);

        move("b", 1, 1.1);


    }
}
