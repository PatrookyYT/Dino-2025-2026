package org.firstinspires.ftc.teamcode.Decode2026;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Functions;

@Autonomous(name = "Decode Auto (Basic)")
public class DecodeAutoBasic extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    public ServoController ControlHub_ServoController;
    private ServoController ExpansionHub_ServoController;

    @Override
    public void runOpMode() {

        boolean Running = true;
        boolean testMode = true; // Shows Telemetry data for Functions.drive and Functions.turn

        // Report that op mode has been initialized
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////// Driving starts here//////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //***VERY IMPORTANT**
        //Replace the device name (ex frontLeft) with the NAME OF THE
        //MOTORS DEFINED IN THE DRIVER HUB
        ControlHub_ServoController = hardwareMap.get(ServoController.class, "Control Hub");

        ExpansionHub_ServoController = hardwareMap.get(ServoController.class, "Expansion Hub 2");


        //Disable pwm
        ControlHub_ServoController.pwmDisable();

        // 10in = 572

        Running = opModeIsActive();

        if(Running)
        {
            // Drive to pixel
            Functions.drive(this, hardwareMap, telemetry, -32, -32, 0.45, -32, -32, testMode);
        }
    }

    //Very complicated code to make it so that when it is showing
    //seconds it has gone (directionTime) will be a one decimal number,
    //ex 0.2. Add 0's to both tens to increase the number of decimal
    //places shown.
    private String formatSeconds(double inputSeconds){
        double fixedValue = Math.floor(inputSeconds * 10) / 10;
        return String.valueOf(fixedValue);
    }
}