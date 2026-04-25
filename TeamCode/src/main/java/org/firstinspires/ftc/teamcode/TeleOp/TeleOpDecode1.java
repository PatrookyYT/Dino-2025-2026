package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

import org.firstinspires.ftc.teamcode.Functions;

@TeleOp(name = "TeleOpDecode1E")
public class TeleOpDecode1 extends LinearOpMode {

    private DcMotor FrontRight;
    private DcMotor BackRight;
    private DcMotor FrontLeft;
    private DcMotor BackLeft;

    private DcMotor LauncherMotor;

    private DcMotor IntakeMotor;

    private DcMotor CarouselMotor;

    private Servo KickerServo;


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
        double cDistance_Full = 240;
        double cDistance_Half = 120;
        double carousel_Speed = 0.05;
        double kicker_Speed = 0.45;
        double flywheel_Speed = 0.92;
        double wheel_Speed = 1;

        double KServo_Stop = 1;
        double KServo_UpPos = 0.55;

        //double KServo_DownPos = 1;

        float verticalGp1_left;
        float horizontalGp1_left;
        float verticalGp1_right;
        float horizontalGp1_right;

        float verticalGp2_left;
        float horizontalGp2_left;
        float verticalGp2_right;
        float horizontalGp2_right;
        float pivot;

        String aprilTagValue = "?";


        boolean viperSlideIsUp = false;
        boolean clawArmServoIsOpen = false;

        boolean debugInfo = true;

        BackLeft = hardwareMap.get(DcMotor.class, "BackLeft");
        BackRight = hardwareMap.get(DcMotor.class, "BackRight");
        FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");

        LauncherMotor = hardwareMap.get(DcMotor.class, "LauncherMotor");

        IntakeMotor = hardwareMap.get(DcMotor.class, "IntakeMotor");

        CarouselMotor = hardwareMap.get(DcMotor.class, "CarouselMotor");

        KickerServo = hardwareMap.get(Servo.class, "KickerServo");

        ControlHub_ServoController = hardwareMap.get(ServoController.class, "Control Hub");

        ExpansionHub_ServoController = hardwareMap.get(ServoController.class, "Expansion Hub 1");
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

            BackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            BackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

            BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            LauncherMotor.setDirection(DcMotor.Direction.REVERSE);

            LauncherMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            IntakeMotor.setDirection(DcMotor.Direction.FORWARD);

            IntakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            CarouselMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            CarouselMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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

                if(verticalGp2_right < -0.001 || verticalGp2_right > 0.001 ) {LauncherMotor.setPower(verticalGp2_right * carousel_Speed);}

                IntakeMotor.setPower(verticalGp1_right);


                if (gamepad2.x){
                    LauncherMotor.setPower(0);
                }



                if (gamepad1.x) {
                    LauncherMotor.setPower(0);
                } else if (gamepad1.b) {
                    LauncherMotor.setPower(0.5);
                } else if (gamepad1.a) {
                    LauncherMotor.setPower(0.92);
                }

                if(gamepad1.y || gamepad2.y) {
                    KickerServo.setPosition(KServo_UpPos);
                    Functions.pause(1, this);
                    KickerServo.setPosition(KServo_Stop);
                }
                /*if (gamepad1.dpad_down) {
                    KickerServo.setPosition(KServo_Stop);
                }*/


                //#TODO: Change D-Pad directions, make sure to *upload first* and test drive before changing

                if (gamepad1.dpad_right) {
                    // Turns the robot left (Hopefully)
                    BackLeft.setPower(D_Speed);
                    BackRight.setPower(D_Speed);
                    FrontLeft.setPower(-D_Speed);
                    FrontRight.setPower(-D_Speed);
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
                    BackLeft.setPower(-D_Speed);
                    BackRight.setPower(-D_Speed);
                    FrontLeft.setPower(D_Speed);
                    FrontRight.setPower(D_Speed);
                } else {
                    // Stops all movement
                    BackLeft.setPower(0);
                    BackRight.setPower(0);
                    FrontLeft.setPower(0);
                    FrontRight.setPower(0);
                }

                // TODO Check april tag
                /*if (horizontalGp1_right != 0 && aprilTagValue.equals("?")) {
                    aprilTagValue = "!";
                }*/

                //###Manuel###

                if(gamepad2.dpad_right) {
                    CarouselMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    CarouselMotor.setPower(0.12);
                }
                if(gamepad2.dpad_left) {
                    CarouselMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    CarouselMotor.setPower(-0.12);
                } if (!gamepad2.dpad_right && !gamepad2.dpad_left) {
                    CarouselMotor.setPower(0);
                }



                //###Auto###

                if(gamepad1.right_bumper) {
                    CarouselMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                    CarouselMotor.setTargetPosition((int)(-cDistance_Full));

                    CarouselMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    CarouselMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

                    CarouselMotor.setPower(carousel_Speed);

                    while (opModeIsActive() && CarouselMotor.isBusy())
                    {
                        telemetry.addData("Motor:", CarouselMotor.getDeviceName());
                        telemetry.addData("Speedy:", CarouselMotor.getPower() + String.valueOf(carousel_Speed));
                        telemetry.addData("Distance:", CarouselMotor.getCurrentPosition());
                        telemetry.addData("Target:", CarouselMotor.getTargetPosition());
                        telemetry.addData("%:", (CarouselMotor.getCurrentPosition()/(CarouselMotor.getTargetPosition()+0.0000001)));
                        telemetry.addData("IsBusy:", CarouselMotor.isBusy());
                        telemetry.update();
                    }

                    CarouselMotor.setPower(0);
                    CarouselMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                }
                if(gamepad1.left_bumper) {
                    CarouselMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                    CarouselMotor.setTargetPosition((int)(cDistance_Full));

                    CarouselMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    CarouselMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

                    CarouselMotor.setPower(carousel_Speed);

                    while (opModeIsActive() && CarouselMotor.isBusy())
                    {
                        telemetry.addData("Motor:", CarouselMotor.getDeviceName());
                        telemetry.addData("Speedy:", CarouselMotor.getPower() + String.valueOf(carousel_Speed));
                        telemetry.addData("Distance:", CarouselMotor.getCurrentPosition());
                        telemetry.addData("Target:", CarouselMotor.getTargetPosition());
                        telemetry.addData("%:", (CarouselMotor.getCurrentPosition()/(CarouselMotor.getTargetPosition()+0.0000001)));
                        telemetry.addData("IsBusy:", CarouselMotor.isBusy());
                        telemetry.update();
                    }

                    CarouselMotor.setPower(0);
                    CarouselMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                }
                if(gamepad1.back){
                    flywheel_Speed += 0.01;
                }
                if(gamepad1.start){
                    flywheel_Speed -= 0.01;
                }


                //telemetry.addData("Color", Functions.GetColor(this, hardwareMap, telemetry, testMode));

                telemetry.addLine("Wheels:");
                telemetry.addData("BackLeft", BackLeft.getPower());
                telemetry.addData("BackRight", BackRight.getPower());
                telemetry.addData("FrontLeft", FrontLeft.getPower());
                telemetry.addData("FrontRight", FrontRight.getPower());


                telemetry.addLine("\nServos:");
                telemetry.addData("KickerServo position", KickerServo.getPosition());

                telemetry.addLine("\nLauncher:");
                telemetry.addData("LauncherMotor", LauncherMotor.getPower());

                telemetry.addLine("\nCarousel:");
                telemetry.addData("CarouselMotor Speed", CarouselMotor.getPower());


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
                telemetry.addData("DropperServo", KickerServo.getPortNumber());
                telemetry.addData("LauncherMotor", LauncherMotor.getPortNumber());
                telemetry.addData("IntakeMotor", IntakeMotor.getPortNumber());
                telemetry.addData("CarouselMotor", CarouselMotor.getPortNumber());

                telemetry.addData("April Tag", aprilTagValue);
            }
        }
    }
}