package org.firstinspires.ftc.teamcode;

/*

Configuration:
Core Device Interface with the default name "Device Interface Module 1"

*/


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "CDI Digital Out", group = "MRI")  // @Autonomous(...) is the other common choice
//@Disabled
public class CDI_DigitalOut extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    DeviceInterfaceModule CDI;

    byte cnt0 = 0;
    byte cnt1 = 1;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        CDI = hardwareMap.deviceInterfaceModule.get("Device Interface Module 1");
        CDI.setDigitalIOControlByte((byte) 0x0f); //Set D0 - D3 to output instead of input
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString());


        CDI.setDigitalChannelState(1,Boolean.TRUE); //Turns pins on to match the binary representation of count. ex. If count is 5, ports 0 and 2 will be on.
        runtime.reset();                      // Wait 0.5 seconds
        while (runtime.seconds() < 0.5) {     //
        }                                     //

        CDI.setDigitalChannelState(0,Boolean.TRUE); //Turns pins on to match the binary representation of count. ex. If count is 5, ports 0 and 2 will be on.


        telemetry.addData("Count", cnt0);//display count to Driver Station
        telemetry.addData("Count", cnt1);//display count to Driver Station

/*        count++; //increase count
        if(count > 2){ //reset count at 16
            count = 0;
        }*/


    }
    public void stop() {
        telemetry.addData("Status", "Stopped");

        CDI.setDigitalChannelState(0,Boolean.FALSE);
        CDI.setDigitalChannelState(1,Boolean.FALSE);
    }

}