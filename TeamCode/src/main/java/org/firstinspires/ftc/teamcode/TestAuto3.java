package org.firstinspires.ftc.teamcode;

//Importing

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;

//##################################
//#                                #
//# Made by Coder Ricky Adams (14) #
//#      for the 2023-2024         #
//#       Centerstage FTC,         #
//#     with teammates Michael     #
//#    Shenback and Max Corum      #
//# (and a little help from Ethan) #
//#  as engineers. Use this as a   #
//#  basis for any code you need   #
//#     for future events.         #
//#                                #
//#  -Team Dinomite8472 10/7/2023  #
//#                                #
//##################################


//Note to used Android studio to show errors in code, and the robot
//control console (ip address to connect directly to control hub,
//in my case http://192.168.43.1:8080/?page=connection.html&pop=true)
//to export the code into the mini i-pad (Driver hub)


//Replace ' name = "OpMode3" ' with the name you want
//to display on control hub, and ' class OpMode3 ' with
//the name of the file.
@TeleOp(name = "TestAuto003")
public class TestAuto3 extends LinearOpMode {

    private String action;
    private final int waitTime = 5;

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    public ServoController ControlHub_ServoController;
    //public ServoController ExpansionHub2_ServoController;


    @Override
    public void runOpMode() {
        boolean Running = true;
        // Report that op mode has been initialized
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //***VERY IMPORTANT**
        //Replace the device name (ex frontLeft) with the NAME OF THE
        //MOTORS DEFINED IN THE DRIVER HUB
        ControlHub_ServoController = hardwareMap.get(ServoController.class, "Control Hub");
        //ExpansionHub2_ServoController = hardwareMap.get(ServoController.class, "Expansion Hub 2");

        // Wait for the game to start (driver presses PLAY)
        boolean Start = false;
        boolean DriveTest = false;
        boolean distanceTest = true;
        boolean testMode = true;
        boolean turnTest = false;
        boolean viperSlideTest = false;

        while (Start = false) {
            if (gamepad1.dpad_down)
            {
                Start = true;
            }

            if (gamepad1.x)
            {
                DriveTest = true;
            }


            telemetry.addData("Running == ", Running);
            telemetry.addData("Drive Test == ", DriveTest);
            telemetry.addData("Turn Test == ", turnTest);

            telemetry.update();
        }
        waitForStart();
        runtime.reset();

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////// Driving starts here//////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////


        if(DriveTest == true) {
            // 16in = 1000

            //Functions.driveDirect(this, hardwareMap, telemetry, 80, 0.65, testMode);
            Functions.pause(2, this);
            //Functions.driveDirect(this, hardwareMap, telemetry, -80, 0.65, testMode);
            Functions.pause(2, this);

            Functions.drive(this, hardwareMap, telemetry, 80, 80, 0.65, 80, 80, testMode);
            Functions.pause(2, this);
            Functions.drive(this, hardwareMap, telemetry, -80, -80, 0.65, -80, -80, testMode);
            Functions.pause(2, this);

        }

        if (turnTest == true)
        {
            //IMU.getRobotYawPitchRollAngles;
            Functions.turn(this, hardwareMap, telemetry, "Left", 0.5, testMode);
            Functions.pause(2, this);
            Functions.turn(this, hardwareMap, telemetry, "Left", 0.5, testMode);
            Functions.pause(2, this);
            Functions.turn(this, hardwareMap, telemetry, "Right", 0.5, testMode);
            Functions.pause(2, this);
            Functions.turn(this, hardwareMap, telemetry, "Left", 0.5, testMode);
            Functions.pause(2, this);

        }


        if (distanceTest) {
            //Functions.driveUntilDistanceKiro(this, hardwareMap, telemetry, 0.6, 0.05, testMode);
            Functions.pause(2, this);

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