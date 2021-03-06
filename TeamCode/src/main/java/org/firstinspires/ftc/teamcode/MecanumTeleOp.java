package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Katelin Zichittella on 2/22/2017.
 */

@TeleOp
@Disabled

public class MecanumTeleOp extends OpMode {

    DcMotor motorBackLeft, motorBackRight, motorFrontLeft, motorFrontRight;

    @Override
    public void init() {

        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");

        motorBackRight.setDirection(DcMotor.Direction.REVERSE);
        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {

        float backLeft = gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x;
        float backRight = gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x;
        float frontLeft = gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x;
        float frontRight = gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x;

        backLeft = Range.clip(backLeft, -1, 1);
        backRight = Range.clip(backRight, -1, 1);
        frontLeft = Range.clip(frontLeft, -1, 1);
        frontRight = Range.clip(frontRight, -1, 1);

        backLeft = (float) scaleInput(backLeft);
        backRight = (float) scaleInput(backRight);
        frontLeft = (float) scaleInput(frontLeft);
        frontRight = (float) scaleInput(frontRight);

        setMotorPower(backLeft, backRight, frontLeft, frontRight);
    }

    @Override
    public void stop() {


    }

    public void setMotorPower(float backLeft, float backRight, float frontLeft, float frontRight) {

        motorBackLeft.setPower(backLeft);
        motorBackRight.setPower(backRight);
        motorFrontLeft.setPower(frontLeft);
        motorFrontRight.setPower(frontRight);
    }

    double scaleInput(double dVal) {

        double[] scaleArray = {0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00};

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
