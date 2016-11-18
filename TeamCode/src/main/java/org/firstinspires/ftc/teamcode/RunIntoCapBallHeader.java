package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * Created by Alex on 10/11/16.
 */
public abstract class RunIntoCapBallHeader extends LinearOpMode {

    DcMotor motorLeft, motorRight;
    GyroSensor sensorGyro;

    public void initialize () {

     motorLeft = hardwareMap.dcMotor.get ("motorLeft");
     motorRight = hardwareMap.dcMotor.get ("motorRight");

        motorRight.setDirection (DcMotor.Direction.REVERSE);
    }

    public void setMotorPower(double left, double right) {

            motorLeft.setPower(left);
            motorRight.setPower(right);


    }

    public void encodersForward(int distance, double power) {

        int DIAMETER = 4;
        int GEAR_RATIO = 1;
        int PULSES = 560;
        double CIRCUMFERENCE = Math.PI * DIAMETER;
        double ROTATIONS = (distance / CIRCUMFERENCE) * GEAR_RATIO;
        double COUNTS = PULSES * ROTATIONS;

        COUNTS = COUNTS + Math.abs(motorLeft.getCurrentPosition()); // can start from the end of the last instance
        // of this method being utilized

        setMotorPower(power, power);

        while (motorLeft.getCurrentPosition() < COUNTS) { // while the encoder count is less than the specified count

            setMotorPower(power, power);
        }

        setMotorPower(0.0, 0.0);
    }

    public void encodersBackward(int distance, double power) {

        int DIAMETER = 4;
        int GEAR_RATIO = 1;
        int PULSES = 560;
        double CIRCUMFERENCE = Math.PI * DIAMETER;
        double ROTATIONS = (distance / CIRCUMFERENCE) * GEAR_RATIO;
        double COUNTS = PULSES * ROTATIONS;

        COUNTS = Math.abs(motorLeft.getCurrentPosition()) - COUNTS; // can start from the end of the last instance
        // of this method being utilized
        setMotorPower(-power, -power);

        while (motorLeft.getCurrentPosition() > COUNTS) { // while the encoder count is greater than the specified count

            setMotorPower(-power, -power);
        }

        setMotorPower(0.0, 0.0);
    }

    public void gyroTurnRight (int degrees, double power) {

        sensorGyro.resetZAxisIntegrator(); // resets the degree of the gyro sensor to 0

        int heading = sensorGyro.getHeading();

        setMotorPower(power, -power);

        while (heading < degrees) { // while the degree of the gyro sensor is less than the specified degree

            setMotorPower(power, -power);

            if (heading >= 180) {

                heading = 360 - heading;
            }
        }

        setMotorPower(0, 0);
    }

    public void gyroTurnLeft (int degrees, double power) {

        sensorGyro.resetZAxisIntegrator(); // resets the degree of the gyro sensor to 0

        int heading = sensorGyro.getHeading();

        setMotorPower(-power, power);

        while (heading < degrees) { // while the degree of the gyro sensor is less than the specified degree

            setMotorPower(-power, power);

            if (heading >= 180) {

                heading = 360 - heading;
            }
        }

        setMotorPower(0, 0);
    }

    public void calibrateGyro () throws InterruptedException {

        while (sensorGyro.isCalibrating()) {

            Thread.sleep(50);

            telemetry.addData("Calibrated", false);
            telemetry.update();
        }

        telemetry.addData("Calibrated", true);
        telemetry.update();

    }


}