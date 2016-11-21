package org.firstinspires.ftc.teamcode; // These lines import necessary software for this program.

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Robotics Club on 4/18/16.
 */

public abstract class AppleBlossomOp extends LinearOpMode { // This line establishes this program as a public abstract class
    // that extends the header file "LinearOpMode". This makes it
    // a header file itself that the real autonomous op modes will
    // extend.

    DcMotor motorLeft, motorRight, motorArm; // These lines establish the names of the motors and servos we
    Servo servoScoop;                        // will be using.

    public void initialize() {                              // This is a public void, or a "program within
        // a program". It will go at the start of each
        // autonomous op mode and will serve as the
        // initialization routine the robot undergoes.

        motorLeft = hardwareMap.dcMotor.get("motorLeft");   // These lines establish a link between the code and the
        motorRight = hardwareMap.dcMotor.get("motorRight"); // hardware. The names in quotations are the names of the
        motorArm = hardwareMap.dcMotor.get("motorArm");     // motors and servos we set on the phone.
        servoScoop = hardwareMap.servo.get("servoScoop");

        motorRight.setDirection(DcMotor.Direction.REVERSE); // This line reverses the right motor so that the robot
        // drives straight.

        servoScoop.setPosition(0.0); // This line initializes the servo to its default position.
    }
    public void setMotorPower(double left, double right, double arm) { // This is another public void.
        // In the parentheses are three
        motorLeft.setPower(left);                                      // parameters, which can be changed
        motorRight.setPower(right);                                    // with each implementation of the
        motorArm.setPower(arm);                                        // void. Whatever is inputted into
    }                                                                  // the parameters in the autonomous
    // op modes is then substituted into
    // its corresponding spot in the
    // public void.
}

