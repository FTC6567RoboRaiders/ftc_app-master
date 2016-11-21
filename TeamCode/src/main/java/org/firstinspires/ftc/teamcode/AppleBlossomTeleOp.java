package org.firstinspires.ftc.teamcode; // These lines import necessary software for this op mode.

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Robotics Club on 3/31/16.
 */

@TeleOp // This line registers the op mode so that it appears on the drop down list on the driver
        // controller phone as a teleop op mode to choose to run.
@Disabled // This line temporarily takes the op mode off of the list.

public class AppleBlossomTeleOp extends OpMode { // This line establishes the name of the op mode and extends
                                                 // the header file "OpMode" in order to create a teleop op mode.

    DcMotor motorLeft, motorRight, motorArm; // These lines establish the names of the motors and servos we will
    Servo servoScoop;                        // be using.

    @Override
    public void init() { // This section of the code is the initialization routine the robot undergoes.

        motorLeft = hardwareMap.dcMotor.get("motorLeft");   // These lines establish a link between the code and
        motorRight = hardwareMap.dcMotor.get("motorRight"); // the hardware. The names in the quotations are the
        motorArm = hardwareMap.dcMotor.get("motorArm");     // names of the motors and servos we set on the phone.
        servoScoop = hardwareMap.servo.get("servoScoop");

        motorRight.setDirection(DcMotor.Direction.REVERSE); // This line reverses the right motor so that the robot
                                                            // drives straight.

        servoScoop.setPosition(0.0); // This line initializes the servo to its default position.
    }

    @Override
    public void loop() { // This section of the code is the main teleop program.

        float left = -gamepad1.left_stick_y;   // These three lines establish the joystick input values as the
        float right = -gamepad1.right_stick_y; // float variables of "left", "right", and "arm".
        float arm = gamepad2.right_stick_y;

        right = Range.clip(right, -1, 1);     // These three lines clip the extreme ends of the joystick
        left = Range.clip(left, -1, 1);       // input values in the resulting floats to avoid exceeding
        arm = Range.clip(arm, -1, 1);         // values accepted by the program.

        right = (float) scaleInput(right);    // These three lines scale the joystick input values in the
        left = (float) scaleInput(left);      // resulting floats to the values in the array in the double
        arm = (float) scaleInput(arm);        // below, which are the only ones the program accepts.

        setMotorPower(left, right, arm);      // This line is an implementation of the public void below.
                                              // It sets the power of the motors to the joystick input values
                                              // in the floats.

        if (gamepad2.b) {                // These lines set the position of the servo to its "up" position
                                         // whenever "b" is pressed on the second controller.
            servoScoop.setPosition(0.0);
        }

        if (gamepad2.a) {                // These lines set the position of the servo to its "down" position
                                         // whenever "a" is pressed on the second controller.
            servoScoop.setPosition(1.0);
        }
    }

    public void setMotorPower(float left, float right, float arm) { // This is a public void, or a "program
                                                                    // within a program". In the parentheses
        motorLeft.setPower(left);                                   // are three parameters, which can be
        motorRight.setPower(right);                                 // changed with each implementation of the
        motorArm.setPower(arm);                                     // program. Whatever is inputted into the
    }                                                               // parameters above is then substituted into
                                                                    // its corresponding spot in the public void.

    double scaleInput(double dVal)  { // When implemented above, this double scales the joystick input values in the floats.

        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }
}