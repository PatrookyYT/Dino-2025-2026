package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

@TeleOp(name = "TeleOpFTCBasic (Use For Driving)")
public class TeleOpFTCBasic extends LinearOpMode {

    private DcMotor FrontRight;
    private DcMotor BackRight;
    private DcMotor FrontLeft;
    private DcMotor BackLeft;


    private Servo IntakeServo;


    private ServoController ControlHub_ServoController;

    /**
     * This function is executed when this OpMode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        boolean testMode;
        double D_Speed = 0.5;
        double halfSpeed = 0.5;
        double arm_SpeedLeft = 0.5;
        double arm_SpeedRight = 0.3;
        double wheel_Speed = 1;

        double Servo_Stop = 0.5;
        double Servo_Intake = 1;
        double Servo_Outtake = 0;

        float verticalGp1_left;
        float horizontalGp1_left;
        float verticalGp1_right;
        float horizontalGp1_right;

        float verticalGp2_left;
        float horizontalGp2_left;
        float verticalGp2_right;
        float horizontalGp2_right;
        float pivot;

        boolean StarMotorActive = false;
        boolean clawArmServoIsOpen = false;

        boolean debugInfo = true;

        BackLeft = hardwareMap.get(DcMotor.class, "BackLeft");
        BackRight = hardwareMap.get(DcMotor.class, "BackRight");
        FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");

        IntakeServo = hardwareMap.get(Servo.class, "IntakeServo");


        ControlHub_ServoController = hardwareMap.get(ServoController.class, "Control Hub");

        //Disable pwm
        ControlHub_ServoController.pwmDisable();


        waitForStart();
        if (opModeIsActive()) {

            BackLeft.setDirection(DcMotor.Direction.FORWARD);
            BackRight.setDirection(DcMotor.Direction.FORWARD);
            FrontLeft.setDirection(DcMotor.Direction.REVERSE);
            FrontRight.setDirection(DcMotor.Direction.REVERSE);

            //StarMotors.setDirection(DcMotor.Direction.FORWARD);


            BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            //StarMotors.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (opModeIsActive()) {
                telemetry.update();
                //
                // Used for stopping motor and servo movement
                //
                testMode = debugInfo;
                //
                // Used for arm movement
                //
                verticalGp1_left = gamepad1.left_stick_y;
                //
                // Used for wheel movement
                //
                horizontalGp1_left = -gamepad1.left_stick_x;
                //
                // Used for wheel movement
                //
                verticalGp1_right = gamepad1.right_stick_y;
                //
                // Used for wheel movement
                //
                horizontalGp1_right = -gamepad1.right_stick_x;
                //
                // Used for wheel movement
                //
                verticalGp2_left = gamepad2.left_stick_y;
                //
                // Used for wheel movement
                //
                horizontalGp2_left = -gamepad2.left_stick_x;
                //
                // Used for wheel movement
                //
                verticalGp2_right = gamepad2.right_stick_y;
                //
                // Used for wheel movement
                //
                horizontalGp2_right = -gamepad2.right_stick_x;
                //
                // Used for wheel movement
                //
                pivot = gamepad1.left_stick_x;
                //
                // Gamepad 1 commands start
                //
                // Used for Mecanum wheel movement
                //
                BackLeft.setPower((horizontalGp1_left - verticalGp1_left) * wheel_Speed);
                BackRight.setPower((horizontalGp1_left + verticalGp1_left) * wheel_Speed);
                FrontLeft.setPower((horizontalGp1_left + verticalGp1_left) * wheel_Speed);
                FrontRight.setPower((horizontalGp1_left - verticalGp1_left) * wheel_Speed);



                if (gamepad1.dpad_left) {
                    // Turns the robot left (Hopefully)
                    BackLeft.setPower(-D_Speed);
                    BackRight.setPower(-D_Speed);
                    FrontLeft.setPower(D_Speed);
                    FrontRight.setPower(D_Speed);
                } else if (gamepad1.dpad_up) {
                    // Makes the robot go forward (Hopefully)
                    BackLeft.setPower(D_Speed);
                    BackRight.setPower(-D_Speed);
                    FrontLeft.setPower(-D_Speed);
                    FrontRight.setPower(D_Speed);
                } else if (gamepad1.dpad_down) {
                    // Makes the rbot go backwards (Hopefully)
                    BackLeft.setPower(-D_Speed);
                    BackRight.setPower(D_Speed);
                    FrontLeft.setPower(D_Speed);
                    FrontRight.setPower(-D_Speed);
                } else if (gamepad1.dpad_right) {
                    // Turns the robot right (Hopefully)
                    BackLeft.setPower(D_Speed);
                    BackRight.setPower(D_Speed);
                    FrontLeft.setPower(-D_Speed);
                    FrontRight.setPower(-D_Speed);
                } else {
                    // Stops all movement
                    BackLeft.setPower(0);
                    BackRight.setPower(0);
                    FrontLeft.setPower(0);
                    FrontRight.setPower(0);
                }

                if (gamepad1.y) {
                } else if (gamepad1.b) {
                    IntakeServo.setPosition(Servo_Stop);
                } else if (gamepad1.x) {
                } else if (gamepad1.a) {
                }

                if (gamepad1.left_bumper) {
                    IntakeServo.setPosition(Servo_Outtake);
                }
                if (gamepad1.right_bumper) {
                    IntakeServo.setPosition(Servo_Intake);
                }

                if (verticalGp2_right < 0) {
                } else if (false) {
                } else if (verticalGp2_right > 0) {
                    // Gamepad 2 commands end+++
                }
                if (gamepad2.dpad_left) {
                } else if (gamepad2.dpad_right) {
                }


                if (gamepad2.y) {
                } else if (gamepad2.b) {
                } else if (gamepad2.x) {
                } else if (gamepad2.a) {
                } else {
                }
                if (gamepad2.left_bumper) {
                }
                if (gamepad2.right_bumper) {
                }


                if (debugInfo) {
                    telemetry.addLine("Wheels:");
                    telemetry.addData("BackLeft", BackLeft.getPower());
                    telemetry.addData("BackRight", BackRight.getPower());
                    telemetry.addData("FrontLeft", FrontLeft.getPower());
                    telemetry.addData("FrontRight", FrontRight.getPower());

                    telemetry.addLine("\nGamePad1:");
                    telemetry.addData("VerticalGp1_Left", verticalGp1_left);
                    telemetry.addData("HorizontalGp1_left", horizontalGp1_left);
                    telemetry.addData("VerticalGp1_right", verticalGp1_right);
                    telemetry.addData("HorizontalGp1_right", horizontalGp1_right);
                    telemetry.addData("Dpad_up", gamepad1.dpad_up);
                    telemetry.addData("Dpad_down", gamepad1.dpad_down);
                    telemetry.addData("Dpad_left", gamepad1.dpad_left);
                    telemetry.addData("Dpad_right", gamepad1.dpad_right);
                    telemetry.addData("A", gamepad1.a);
                    telemetry.addData("X", gamepad1.x);
                    telemetry.addData("Y", gamepad1.y);
                    telemetry.addData("B", gamepad1.b);
                    telemetry.addData("Left_bumper", gamepad1.left_bumper);
                    telemetry.addData("Right_bumper", gamepad1.right_bumper);

                    telemetry.addLine("\nGamePad2:");
                    telemetry.addData("VerticalGp2_Left", verticalGp2_left);
                    telemetry.addData("HorizontalGp2_left", horizontalGp2_left);
                    telemetry.addData("VerticalGp2_right", verticalGp2_right);
                    telemetry.addData("HorizontalGp2_right", horizontalGp2_right);
                    telemetry.addData("Dpad_up", gamepad2.dpad_up);
                    telemetry.addData("Dpad_down", gamepad2.dpad_down);
                    telemetry.addData("Dpad_left", gamepad2.dpad_left);
                    telemetry.addData("Dpad_right", gamepad2.dpad_right);
                    telemetry.addData("A", gamepad2.a);
                    telemetry.addData("X", gamepad2.x);
                    telemetry.addData("Y", gamepad2.y);
                    telemetry.addData("B", gamepad2.b);
                    telemetry.addData("Left_bumper", gamepad2.left_bumper);
                    telemetry.addData("Right_bumper", gamepad2.right_bumper);

                    telemetry.addLine("\n Ports:");
                    telemetry.addData("BackLeft", BackLeft.getPortNumber());
                    telemetry.addData("BackRight", BackRight.getPortNumber());
                    telemetry.addData("FrontLeft", FrontLeft.getPortNumber());
                    telemetry.addData("FrontRight", FrontRight.getPortNumber());
                }
            }
        }
    }
}