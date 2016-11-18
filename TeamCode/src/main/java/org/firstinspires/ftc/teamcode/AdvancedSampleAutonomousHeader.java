package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * Created by saras on 10/24/2016.
 **/
abstract class AdvancedSampleAutonomousHeader extends LinearOpMode{
    private DcMotor motorLeft, motorRight,  motor_arm;
    private GyroSensor sensorGyro;

    public void initialize() {

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motor_arm = hardwareMap.dcMotor.get("");
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorRight.setDirection(DcMotor.Direction.REVERSE);

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

    public void gyroTurnRight(int degrees, double power) {

        sensorGyro.resetZAxisIntegrator(); // resets the degree of the gyro sensor to 0

        int heading = sensorGyro.getHeading();

        setMotorPower(power, -power);

        while (heading < degrees) { // while the degree of the gyro sensor is less than the specified degree
            heading = sensorGyro.getHeading();
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
            heading = sensorGyro.getHeading();
            setMotorPower(-power, power);

            if (heading >= 180) {

                heading = 360 - heading;
            }
        }

        setMotorPower(0, 0);
    }

    public void setMotorPower(double left, double right) {

        motorLeft.setPower(left);
        motorRight.setPower(right);
    }
    public void shoot(int s){
        if ( s > 1){
            motor_arm.setPower(1.);






        }

        if ( s <= 1){







        }

    }

    public void calibrateGyro() throws InterruptedException {

        while (sensorGyro.isCalibrating()){
            Thread.sleep(50);

            telemetry.addData("calibrated", false);
            telemetry.update();
        }

        telemetry.addData("calibrated", true);
        telemetry.update();

    }


    public void move(String s, int distance, double power){   //move combines moveing foward and moveing backwards


        if (s == "f"){
            encodersForward(distance, power );

        }
        else if (s == "b"){
            encodersBackward(distance, power);

            if (s == true)
        }
    }

    /*
    batman was a better super hero compared to kite man because he is a better crime fighter even though he has the awsome power of flying on a kite but batman can also glide
    but it is not as cool as kite man

    */


}





