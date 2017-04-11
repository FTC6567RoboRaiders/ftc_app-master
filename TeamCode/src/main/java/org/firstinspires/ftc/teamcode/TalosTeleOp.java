package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Katelin Zichittella on 11/1/2016.
 *
 * S. Kocik - 170411 - Changed motorLift to motorLift1 and added motorLift2 since
 *                     hardware was changed to use two motors for the lift
 *
 */

@TeleOp

public class TalosTeleOp extends OpMode {

    DcMotor motorBackLeft, motorBackRight, motorFrontLeft, motorFrontRight,
            motorShooter, motorSweeper, motorLift1, motorLift2;         /*@SK1C*/
    Servo servoBeacon, servoGate, servoLiftLeft, servoLiftRight;
    GyroSensor sensorGyro;

    double motorFactor = 1.0;
    double sweeperMode;
    boolean gamepad2_a_currState = false;
    boolean gamepad2_a_prevState = false;
    boolean gamepad2_b_currState = false;
    boolean gamepad2_b_prevState = false;

    byte[] rangeSensorLeftCache;
    byte[] rangeSensorRightCache;

    I2cDevice rangeSensorLeft;
    I2cDevice rangeSensorRight;
    I2cDeviceSynch rangeSensorLeftReader;
    I2cDeviceSynch rangeSensorRightReader;

    byte[] colorSensorLeftCache;
    byte[] colorSensorRightCache;
    byte[] colorSensorFrontCache;

    I2cDevice colorSensorLeft;
    I2cDevice colorSensorRight;
    I2cDevice colorSensorFront;
    I2cDeviceSynch colorSensorLeftReader;
    I2cDeviceSynch colorSensorRightReader;
    I2cDeviceSynch colorSensorFrontReader;

    /**
     * start() - overridden method used to instantiate (define and initialize)
     *           all of the different variables and objects that are needed to
     *           run Talos.
     */
    @Override
    public void start() {

        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorShooter = hardwareMap.dcMotor.get("motorShooter");
        motorSweeper = hardwareMap.dcMotor.get("motorSweeper");
        motorLift1 = hardwareMap.dcMotor.get("motorLift1");    /*@SK1C*/
        motorLift2 = hardwareMap.dcMotor.get("motorLift2");    /*@SK1A*/
        rangeSensorLeft = hardwareMap.i2cDevice.get("rangeSensorLeft");
        rangeSensorRight = hardwareMap.i2cDevice.get("rangeSensorRight");
        colorSensorLeft = hardwareMap.i2cDevice.get("colorSensorLeft");
        colorSensorRight = hardwareMap.i2cDevice.get("colorSensorRight");
        colorSensorFront = hardwareMap.i2cDevice.get("colorSensorFront");
        sensorGyro = hardwareMap.gyroSensor.get("sensorGyro");
        servoBeacon = hardwareMap.servo.get("servoBeacon");
        servoGate = hardwareMap.servo.get("servoGate");
        servoLiftLeft = hardwareMap.servo.get("servoLiftLeft");
        servoLiftRight = hardwareMap.servo.get("servoLiftRight");

        colorSensorLeftReader = new I2cDeviceSynchImpl(colorSensorLeft, I2cAddr.create8bit(0x3c), false);
        colorSensorRightReader = new I2cDeviceSynchImpl(colorSensorRight, I2cAddr.create8bit(0x3e), false);
        colorSensorFrontReader = new I2cDeviceSynchImpl(colorSensorFront, I2cAddr.create8bit(0x42), false);
        rangeSensorLeftReader = new I2cDeviceSynchImpl(rangeSensorLeft, I2cAddr.create8bit(0x28), false);
        rangeSensorRightReader = new I2cDeviceSynchImpl(rangeSensorRight, I2cAddr.create8bit(0x30), false);

        colorSensorLeftReader.write8(3, 0);
        colorSensorRightReader.write8(3, 0);
        colorSensorFrontReader.write8(3, 1);

        colorSensorLeftReader.engage();
        colorSensorRightReader.engage();
        colorSensorFrontReader.engage();
        rangeSensorLeftReader.engage();
        rangeSensorRightReader.engage();

        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        motorSweeper.setDirection(DcMotor.Direction.REVERSE);
        motorLift1.setDirection(DcMotor.Direction.REVERSE);     /*@SK1C*/
        motorLift2.setDirection(DcMotor.Direction.REVERSE);     /*@SK1A*/
        servoBeacon.setPosition(0.0);                           /* Beacon pusher to the right */
        servoGate.setPosition(0.0);                             /* Gate is up */
        servoLiftLeft.setPosition(0.7);                         /* Close the left arm */
        servoLiftRight.setPosition(0.7);                        /* Close the right arm */
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {

        // Get the initial values for the left and right side of the drive train
        // and the shoot and lift motors.
        float left = gamepad1.left_stick_y;
        float right = gamepad1.right_stick_y;
        float shoot = gamepad2.right_stick_y;
        float lift = gamepad2.left_stick_y;

        // Sometimes the numbers get all out of whack, don't ask it just happens
        // so make the numbers fit between -1 and 1 which are the mininum and
        // maximum that a motor can be given.
        left = Range.clip(left, -1, 1);
        right = Range.clip(right, -1, 1);
        shoot = Range.clip(shoot, -1, 1);
        lift = Range.clip(lift, -1, 1);

        // Now take that clipped number and scale it appropriately.  This is done
        // since using the joysticks it is sometime very difficult to push the
        // the joystick 100% in any given direction.  So scaling the input takes
        // this into consideration and scales the input properly.  This will give
        // more control to the driver and believe me s/he needs it.
        left = (float) scaleInput(left);
        right = (float) scaleInput(right);
        shoot = (float) scaleInput(shoot);
        lift = (float) scaleInput(lift);


        // Set the power for the motors, and attachments, here the drive train
        // power is scaled (left, right) appropriately.  See how motorFactor is
        // set below.
        setMotorPower(left * motorFactor, right * motorFactor);
        setAttachmentPower(sweeperMode, shoot, lift);

        // Get and set the current state of the A and B buttons on gamepad2
        gamepad2_a_currState = gamepad2.a;
        gamepad2_b_currState = gamepad2.b;

        // HALF THRUSTER SCOTTY
        // When the X button on gamepad1 is pushed, all motor calculations are at
        // half speed.
        if (gamepad1.x) {

            motorFactor = 0.5;
        }

        // FULL THRUSTERS SCOTTY
        // When the Y button on gamepad1 is pushed, all motor calculations are at
        // full speed (aka ludicrous speed)
        if (gamepad1.y) {

            motorFactor = 1.0;
        }

        // HALF REVERSE THRUSTERS SCOTTY
        // When the A button on gamepad1 is pushed, all motor calculations are at
        // half speed and in reverse.  This will be used when picking up the cap
        // ball.  Since the lift is on the back side of the robot, reversing the
        // motor calculations will allow the driver to drive the robot in reverse
        // as if s/he is driving normally.
        if (gamepad1.a) {

            motorFactor = -0.5;
        }

        // FULL REVERSE THRUSTERS SCOTTY
        // When the B button on gamepad1 is pushed, all motor calculations are at
        // full speed and in reverse.  This will be used when picking up the cap
        // ball.  Since the lift is on the back side of the robot, reversing the
        // motor calculations will allow the driver to drive the robot in reverse
        // as if s/he is driving normally.
        if (gamepad1.b) {

            motorFactor = -1.0;
        }

        // BEACON PUSHER LOGIC - PUSH IT, PUSH IT REAL GOOD...
        // When the left bumper on gamepad1 is pushed, the beacon pusher will
        // rotate to the left.
        if (gamepad1.left_bumper) {

            servoBeacon.setPosition(1);
        }

        // When the right bumper on gamepad1 is pushed, the beacon pusher will
        // rotate to the right.
        if (gamepad1.right_bumper) {

            servoBeacon.setPosition(0);
        }

        // CLOSE THE LIFT ARM LOGIC
        // If the current state of the A button on gamepad2 is true (aka pushed) -AND-
        // The current state of A button on gamepad2 is different from the previous state -
        // meaning that before the A button on gamepad2 was false (aka not pushed), then
        // close the arms.
        if (gamepad2_a_currState &&
            gamepad2_a_currState != gamepad2_a_prevState)  {

            servoLiftLeft.setPosition(0.70);                 // Left arm closed
            servoLiftRight.setPosition(0.70);                // Right arm closed
                                                             // Everybody clap their hands....

              gamepad2_a_prevState = gamepad2_a_currState;   // Remember the current state
        }
        // If the current state of the A button on gamepad2 is false (aka not pushed) -AND-
        // The current state of A button on gamepad2 is different from the previous state -
        // meaning that before the A button on gamepad2 was true (aka pushed) then do nothing
        else if (gamepad2_a_currState == false &&
                 gamepad2_a_currState != gamepad2_a_prevState)  {

            gamepad2_a_prevState = gamepad2_a_currState;     // Remember the current state
        }

        // OPEN THE LIFT ARM LOGIC
        // If the current state of the B button on gamepad2 is true (aka pushed) -AND-
        // The current state of B button on gamepad2 is different from the previous state -
        // meaning that before the B button on gamepad2 was false (aka not pushed), then
        // open the arms.
        if (gamepad2_b_currState &&
            gamepad2_b_currState != gamepad2_b_prevState)  {

            servoLiftLeft.setPosition(0.35);                 // Left arm open
            servoLiftRight.setPosition(0.35);                // Right arm open
                                                             // Everybody clap their hands...

            gamepad2_b_prevState = gamepad2_b_currState;     // Remember the current state
        }
        // If the current state of the B button on gamepad2 is false (aka not pushed) -AND-
        // The current state of B button on gamepad2 is different from the previous state -
        // meaning that before the B button on gamepad2 was true (aka pushed) then do nothing
        else if (gamepad2_b_currState == false &&
                 gamepad2_b_currState != gamepad2_b_prevState)  {

            gamepad2_b_prevState = gamepad2_b_currState;     // Remember the current state
        }

        // SWEEPER MOTOR LOGIC
        // When the right bumper on gamepad2 is pushed set the speed of the sweeper motor to
        // collect the balls.  The power setting is at the maximum...no Star Trek jokes please
        if (gamepad2.right_bumper) {

            sweeperMode = 1.0;                               // Full power to collect
        }
        // Else when the left bumper on gamepad2 is pushed, set the speed of the sweeper motor
        // in reverse, essentially pushing the balls out or unjamming the ball collector if it
        // got jammed.  Note: This will only work with raspberry jam...
        else if (gamepad2.left_bumper) {

            sweeperMode = -1.0;                              // Full power to push balls out
        }
        // Finally when neither the right bumper or left bumper on gamepad2 is pushed set the
        // sweeper motor to be off.
        else {

            sweeperMode = 0.0;                               // Turn off sweeper motor
        }

        // GATE SERVO LOGIC
        // When the dpad down button/toggle/whatever on gamepad2 is pushed, open the ball gate
        // so that a ball will drop down into the chuck-it.
        if (gamepad2.dpad_down) {

            servoGate.setPosition(0.3);
        }
        // When the dpad down button/toggle/whatever on gamepad2 is released, the gate is moved
        // to the up position blocking the other balls from falling into the chuck-it.
        else {

            servoGate.setPosition(0.0);
        }
    }

    @Override
    public void stop() {


    }

    /**
     * setMotorPower - sets the drive train motor powers
     *
     * @param left - left side power
     * @param right - right side power
     */
    public void setMotorPower (double left, double right) {

        motorBackLeft.setPower(left);
        motorBackRight.setPower(right);
        motorFrontLeft.setPower(left);
        motorFrontRight.setPower(right);
    }

    /**
     * setAttachmentPower - sets the different attachments' motor power
     *
     * @param sweeperMode - ball collection motor power
     * @param shoot - chuck-it motor power
     * @param lift - lift motor power (note there are two motors for the lift)
     */
    public void setAttachmentPower (double sweeperMode, double shoot, double lift) {

        motorSweeper.setPower(sweeperMode);
        motorShooter.setPower(shoot);
        motorLift1.setPower(lift);     /*@SK1C*/
        motorLift2.setPower(lift);     /*@SK1A*/

    }

    /**
     * scaleInput - scales the joystick input appropriately  This is done
     * since using the joysticks it is sometime very difficult to push the
     * the joystick 100% in any given direction.  So scaling the input takes
     * this into consideration and scales the input properly and gives the
     * driver more control
     *
     * @param dVal - the joystick value to scale
     * @return dScale - the scaled joystick value
     */
    double scaleInput(double dVal)  { // When implemented above, this double scales the joystick input values
        // in the floats.

        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        // So the way this works is through integer magic.  The calculation
        // of dval*16.0 is float, that is decimal points will be around, but then
        // casting it as an integer (int), the numbers right of the decimal point
        // are stripped off and presto chango you have an index into the scaleArray
        int index = (int) (dVal * 16.0);

        // index should be positive, since the value (dVal) could be negative upon
        // entry, thus based on the calculation above the index could be negative
        // which would be bad...and we all know what that means....
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.  What is interesting here is that
        // the scaleArray actually has 17 entries, why you may ask, well I answer, java
        // uses 0 based indexing, this means that the first entry in the scaleArray is
        // scaleArray[0], the second scaleArray[1] and so on.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.  So using the index that was calculated above return the
        // power setting from the scaleArray.  So an index of 8 is 0.30 (scaleArray[8])...see
        // above for why).
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