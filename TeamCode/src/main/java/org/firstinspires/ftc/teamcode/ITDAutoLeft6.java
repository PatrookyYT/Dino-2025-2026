package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "ITD AutoLeft6")
public class ITDAutoLeft6 extends LinearOpMode {
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

        waitForStart();
        runtime.reset();

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////// Driving starts here//////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

        // 10in = 572

        Running = opModeIsActive();

        if(Running)
        {
            // Drive to pixel

            ClawArmServo.setPower(1);

            Functions.drive(this, hardwareMap, telemetry, -10, 10, 0.55, 10, -10, testMode);


            Functions.turn(this, hardwareMap, telemetry, "Right", 0.75, testMode);
            Functions.pause(0.25, this);

//10.78
            ClawArmServo.setPower(1);

//10.78
            Functions.drive(this, hardwareMap, telemetry, 13.5, 13.5, 0.45, 13.5, 13.5, testMode);

            //Functions.driveUntilDistance(opMode, hardwareMap, telemetry, 11.1, 47, Speed, testMode);

            Functions.frontArmMove(this, hardwareMap, telemetry, 16, 0.65, 1.25, testMode);
            Functions.drive(this, hardwareMap, telemetry, -4.5, -4.5, 0.135, -4.5, -4.5, testMode);
            Functions.frontArmMove(this, hardwareMap, telemetry, 16, 0.65, 0.25, testMode);
            ClawArmServo.setPower(-1);
            Functions.pause(0.25, this);
            Functions.frontArmStop(this, hardwareMap, telemetry, testMode);

            Functions.frontArmMove(this, hardwareMap, telemetry, -12, 0.3, 1.2, testMode);
            Functions.frontArmStop(this, hardwareMap, telemetry, testMode);
            Functions.reset(this, hardwareMap, telemetry, testMode);


            Functions.frontArmStop(this, hardwareMap, telemetry, testMode);
            ClawArmServo.setPower(-1);
            Functions.turn(this, hardwareMap, telemetry, "Left", 0.75, testMode);
            Functions.frontArmStop(this, hardwareMap, telemetry, testMode);

            Functions.drive(this, hardwareMap, telemetry, 24.5, -24.5, 0.6, -24.5, 24.5, testMode);
            Functions.pause(0.25, this);

            Functions.drive(this, hardwareMap, telemetry, -10, 10, 0.6, 10, -10, testMode);
            Functions.frontArmStop(this, hardwareMap, telemetry, testMode);

            Functions.drive(this, hardwareMap, telemetry, 29.1, 29.1, 0.7, 29.1, 29.1, testMode);
            Functions.turn(this, hardwareMap, telemetry, "Left", 0.75, testMode);
            Functions.pause(0.25, this);

            Functions.drive(this, hardwareMap, telemetry, -40, -40, 0.6, -40, -40, testMode);
            Functions.pause(0.25, this);

            Functions.drive(this, hardwareMap, telemetry, -10, 10, 0.57, 10, -10, testMode);
            Functions.pause(0.25, this);
            Functions.drive(this, hardwareMap, telemetry, 47.5, 47.5, 0.75, 47.5, 47.5, testMode);
            Functions.pause(0.25, this);
            Functions.drive(this, hardwareMap, telemetry, -47.5, -47.5, 0.75, -47.5, -47.5, testMode);
            Functions.pause(0.25, this);

            Functions.drive(this, hardwareMap, telemetry, -16, 16, 0.57, 16, -16, testMode);
            Functions.pause(0.25, this);
            Functions.drive(this, hardwareMap, telemetry, 48.5, 48.5, 0.75, 48.5, 48.5, testMode);
            Functions.pause(0.25, this);
            Functions.drive(this, hardwareMap, telemetry, -48.5, -48.5, 0.75, -48.5, -48.5, testMode);
            Functions.pause(0.25, this);

            Functions.drive(this, hardwareMap, telemetry, -10, 10, 0.57, 10, -10, testMode);
            Functions.pause(0.25, this);
            Functions.drive(this, hardwareMap, telemetry, 48.5, 48.5, 0.75, 48.5, 48.5, testMode);
            Functions.pause(0.25, this);
            Functions.drive(this, hardwareMap, telemetry, -48.5, -48.5, 0.75, -48.5, -48.5, testMode);
            Functions.pause(0.25, this);

            /*
            //Functions.driveUntilDistance(this, hardwareMap, telemetry, 10.3, 25, 0.35, testMode);
            //Functions.driveUntilDistance(this, hardwareMap, telemetry, 10.4, 25, 0.35, testMode);

            Functions.frontArmStop(this, hardwareMap, telemetry, testMode);
            Functions.frontArmMove(this, hardwareMap, telemetry, 19, 0.2, "Front", testMode);
            Functions.pause(0.5, this);

            Functions.frontArmStop(this, hardwareMap, telemetry, testMode);
            ClawArmServo.setPower(1);
            Functions.pause(1.5, this);
            Functions.frontArmStop(this, hardwareMap, telemetry, testMode);
            Functions.pause(0.1, this);

            Functions.frontArmMove(this, hardwareMap, telemetry, -25, 0.08, "Front", testMode);
            Functions.drive(this, hardwareMap, telemetry, -30, -30, 0.45, -30, -30, testMode);
            Functions.pause(0.25, this);

            Functions.turn(this, hardwareMap, telemetry, "Left", 0.4, testMode);
            Functions.frontArmStop(this, hardwareMap, telemetry, testMode);

            Functions.pause(0.25, this);

            Functions.hangSpec1(this, hardwareMap, telemetry, 0.2, testMode);
            */
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