package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Katelin Zichittella on 3/1/2017.
 */

public abstract class MustardAutonomousHeader extends LinearOpMode {

    DcMotor motorLeft, motorRight;

    public void initialize() {

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    public void setMotorPower (double left, double right) {

        if (opModeIsActive()) {

            motorLeft.setPower(left);
            motorRight.setPower(right);
        }
    }
}
