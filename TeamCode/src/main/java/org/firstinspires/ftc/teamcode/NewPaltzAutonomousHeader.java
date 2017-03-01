package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Katelin Zichittella on 3/1/2017.
 */

public abstract class NewPaltzAutonomousHeader extends LinearOpMode {

    DcMotor motorBackLeft, motorBackRight, motorFrontLeft, motorFrontRight;

    public void initialize() {

        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");

        motorBackRight.setDirection(DcMotor.Direction.REVERSE);
        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
    }

    public void setMotorPower (double left, double right) {

        if (opModeIsActive()) {

            motorBackLeft.setPower(left);
            motorBackRight.setPower(right);
            motorFrontLeft.setPower(left);
            motorFrontRight.setPower(right);
        }
    }
}
