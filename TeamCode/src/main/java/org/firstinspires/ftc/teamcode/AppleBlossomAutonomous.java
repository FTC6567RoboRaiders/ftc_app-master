package org.firstinspires.ftc.teamcode; // These lines import necessary software for this op mode.

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by Robotics Club on 4/27/16.
 */

@Autonomous // This line registers the op mode so that it appears on the drop down list on the driver
            // controller phone as an autonomous op mode to choose to run.
@Disabled // This line temporarily takes the op mode off of the list.

public class AppleBlossomAutonomous extends AppleBlossomOp { // This line establishes the name of the op mode and extends
    // the header file "AppleBlossomOp", which in turn extends the
    // header file "LinearOpMode", in order to access all of the
    // information and public voids in "AppleBlossomOp" and to
    // create an autonomous op mode.

    @Override
    public void runOpMode() throws InterruptedException { // This section of the code has both the
        // initialization routine the robot undergoes
        // and the main autonomous program that runs
        // in a linear fashion.

        initialize(); // This implementation of the public void initialize initializes everything.

        waitForStart(); // Everything before this line is the initialization routine the robot undergoes,
        // while everything after it is the main autonomous program.

        setMotorPower(1.0, 1.0, 0); // "The left and right motors are turned on at a speed of 1.0...
        Thread.sleep(500);          // ...the program pauses for 500 milliseconds (this is how long
        // the robot is moving forward)...
        setMotorPower(0, 0, 0);     // ...the robot stops...
        Thread.sleep(500);          // ...and pauses for 500 milliseconds...

        setMotorPower(1.0, -1.0, 0); // ...the left motor is turned on at a speed of 1.0, while the
        // right motor is turned on at a speed of -1.0...
        Thread.sleep(1000);          // ...the program pauses for 1000 milliseconds (this is how long
        // the robot is turning to the right)...
        setMotorPower(0, 0, 0);      // ...the robot stops...
        Thread.sleep(500);           // ...and pauses for 500 milliseconds...

        setMotorPower(0, 0, 1.0); // ...the arm motor is turned on at a speed of 1.0...
        Thread.sleep(100);        // ...the program pauses for 100 milliseconds (this is how long the
        // arm is moving up)...
        setMotorPower(0, 0, 0);   // ...the robot stops...
        Thread.sleep(500);        // .. and pauses for 500 milliseconds...

        servoScoop.setPosition(1); // ...servoScoop is set to its "down" position...
        Thread.sleep(100);         // ...and the program pauses for 100 milliseconds."

    }
}
