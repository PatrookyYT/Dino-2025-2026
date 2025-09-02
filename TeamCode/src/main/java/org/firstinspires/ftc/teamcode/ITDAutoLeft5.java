package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "ITD AutoLeft5 (High-bar)")
public class ITDAutoLeft5 extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    public ServoController ControlHub_ServoController;
    private CRServo ClawArmServo;
    private DcMotor FrontArmMotor;

    @Override
    public void runOpMode() {

        boolean Running = true;
        boolean testMode = true; // Shows Telemetry data for Functions.drive and Functions.turn

        // Report that op mode has been initialized
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //***VERY IMPORTANT**
        //Replace the device name (ex frontLeft) with the NAME OF THE
        //MOTORS DEFINED IN THE DRIVER HUB
        ControlHub_ServoController = hardwareMap.get(ServoController.class, "Control Hub");
        FrontArmMotor = hardwareMap.get(DcMotor.class, "FrontArmMotor");

        ClawArmServo = hardwareMap.get(CRServo.class, "ClawArmServo");

        FrontArmMotor.setDirection(DcMotor.Direction.REVERSE);

        FrontArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Disable pwm
        ControlHub_ServoController.pwmDisable();

        waitForStart();
        runtime.reset();

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////// Driving starts here//////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // 10in = 572

        Running = opModeIsActive();

        if(Running)
        {
            // Drive to pixel

            ClawArmServo.setPower(1);

            //Fix Strafe
            Functions.drive(this, hardwareMap, telemetry, -8, 8, 0.4, 8, -8, testMode);
            Functions.pause(0.25, this);

            Functions.drive(this, hardwareMap, telemetry, -20, -20, 0.4, -20, -20, testMode);
            Functions.pause(0.25, this);

            Functions.turn(this, hardwareMap, telemetry, "Right", 0.4, testMode);
            Functions.pause(0.25, this);
//10.78
            Functions.driveUntilDistance(this, hardwareMap, telemetry, 6.7,47, 0.05, testMode);
            Functions.pause(0.25, this);
            Functions.driveUntilDistance(this, hardwareMap, telemetry, 6.7,47, 0.05, testMode);
            Functions.pause(0.25, this);

            //Functions.frontArmMove(this, hardwareMap, telemetry, 12, 0.175, "Front", testMode);
            Functions.pause(0.25, this);

            ClawArmServo.setPower(0);
            Functions.drive(this, hardwareMap, telemetry, -3, -3, 0.2, -3, -3, testMode);
            Functions.pause(0.25, this);

            Functions.drive(this, hardwareMap, telemetry, -11, -11, 0.4, -11, -11, testMode);
            Functions.pause(0.25, this);

            Functions.drive(this, hardwareMap, telemetry, -65, 65, 0.4, 65, -65, testMode);
            Functions.pause(0.25, this);
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