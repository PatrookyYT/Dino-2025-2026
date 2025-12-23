package org.firstinspires.ftc.teamcode.TeleOpOld;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

import org.firstinspires.ftc.teamcode.Functions;

@TeleOp(name = "TeleOpFTCold")
public class TeleOpFTCBasic extends LinearOpMode {

    private DcMotor FrontRight;
    private DcMotor BackRight;
    private DcMotor FrontLeft;
    private DcMotor BackLeft;

    private DcMotor WeeeMotor;

    private DcMotor IntakeMotor;

    private  DcMotor TheHornet;


    private Servo DropperServo;


    private ServoController ControlHub_ServoController;
    private ServoController ExpansionHub_ServoController;

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
        double intake_Speed = 0.92; //0.9367

        double Servo_Stop = 0.55;
        double Servo_Right = 0.75;
        double Servo_Left = 0.35;

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

        WeeeMotor = hardwareMap.get(DcMotor.class, "WeeeMotor");

        IntakeMotor = hardwareMap.get(DcMotor.class, "IntakeMotor");

        TheHornet = hardwareMap.get(DcMotor.class, "TheHornet");

        DropperServo = hardwareMap.get(Servo.class, "DropperServo");


        ControlHub_ServoController = hardwareMap.get(ServoController.class, "Control Hub");

        ExpansionHub_ServoController = hardwareMap.get(ServoController.class, "Expansion Hub 2");
        //Disable pwm
        ControlHub_ServoController.pwmDisable();


        waitForStart();
        if (opModeIsActive()) {

            BackLeft.setDirection(DcMotor.Direction.REVERSE);
            BackRight.setDirection(DcMotor.Direction.REVERSE);
            FrontLeft.setDirection(DcMotor.Direction.FORWARD);
            FrontRight.setDirection(DcMotor.Direction.FORWARD);

            /*
            BackLeft.setDirection(DcMotor.Direction.FORWARD);
            BackRight.setDirection(DcMotor.Direction.FORWARD);
            FrontLeft.setDirection(DcMotor.Direction.REVERSE);
            FrontRight.setDirection(DcMotor.Direction.REVERSE);
             */

            WeeeMotor.setDirection(DcMotor.Direction.REVERSE);

            TheHornet.setDirection(DcMotor.Direction.REVERSE);

            BackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            BackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

            BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            WeeeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


            DropperServo.setPosition(Servo_Stop);

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

                IntakeMotor.setPower(verticalGp1_right);

                TheHornet.setPower(horizontalGp1_right);

                if(verticalGp2_right < -0.2f || verticalGp2_right > 0.2f) {WeeeMotor.setPower(verticalGp2_right);}


                if (gamepad1.dpad_right) {
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
                } else if (gamepad1.dpad_left) {
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
                    WeeeMotor.setPower(intake_Speed);
                } else if (gamepad1.b) {
                    Functions.dropArtifacts(this, hardwareMap, telemetry, ControlHub_ServoController, ExpansionHub_ServoController, wheel_Speed, Servo_Stop, Servo_Left, Servo_Right, testMode);
                } else if (gamepad1.x) {
                    WeeeMotor.setPower(0);
                } else if (gamepad1.a) {
                    DropperServo.setPosition(Servo_Stop);
                }

                if (gamepad1.left_bumper) {
                    DropperServo.setPosition(Servo_Left);
                }
                if (gamepad1.right_bumper) {
                    DropperServo.setPosition(Servo_Right);
                }

                if (verticalGp2_right < 0) {
                } else if (false) {
                } else if (verticalGp2_right > 0) {
                    // Gamepad 2 commands end+++
                }
                if (gamepad2.dpad_left) {
                    DropperServo.setPosition(Servo_Left);
                }
                if (gamepad2.dpad_right) {
                    DropperServo.setPosition(Servo_Right);
                }


                if (gamepad2.y) {
                    WeeeMotor.setPower(intake_Speed);
                } else if (gamepad2.b) {
                    //Functions.dropArtifacts(this, hardwareMap, telemetry, ControlHub_ServoController, ExpansionHub_ServoController, wheel_Speed, Servo_Stop, Servo_Left, Servo_Right, testMode);
                } else if (gamepad2.x) {
                    WeeeMotor.setPower(0);
                } else if (gamepad2.a) {
                    DropperServo.setPosition(Servo_Stop);
                } else {
                }
                if (gamepad2.left_bumper) {
                }
                if (gamepad2.right_bumper) {
                }


                if (debugInfo) {
                    telemetry.addData("Color", Functions.GetColor(this, hardwareMap, telemetry, testMode));

                    telemetry.addLine("Wheels:");
                    telemetry.addData("BackLeft", BackLeft.getPower());
                    telemetry.addData("BackRight", BackRight.getPower());
                    telemetry.addData("FrontLeft", FrontLeft.getPower());
                    telemetry.addData("FrontRight", FrontRight.getPower());


                    telemetry.addLine("\nServos:");
                    telemetry.addData("DropperServo", DropperServo.getPosition());

                    telemetry.addLine("\nLauncher:");
                    telemetry.addData("WeeeMotor", WeeeMotor.getPower());


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
                    telemetry.addData("DropperServo", DropperServo.getPortNumber());
                    telemetry.addData("WeeeMotor", WeeeMotor.getPortNumber());
                }
            }
        }
    }
}