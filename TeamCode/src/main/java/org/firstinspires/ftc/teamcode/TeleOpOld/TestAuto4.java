package org.firstinspires.ftc.teamcode.TeleOpOld;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

@TeleOp(name = "TestAuto4old")
public class TestAuto4 extends LinearOpMode {

    private DcMotor FrontRight;
    private DcMotor BackRight;
    private DcMotor FrontLeft;
    private DcMotor BackLeft;

    private Servo servo;


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

        double Servo_Stop;
        double Servo_Intake;
        double Servo_Outtake;

        float verticalGp1_left;
        float horizontalGp1_left;
        float verticalGp1_right;
        float horizontalGp1_right;

        float verticalGp2_left;
        float horizontalGp2_left;
        float verticalGp2_right;
        float horizontalGp2_right;
        float pivot;

        boolean viperSlideIsUp = false;
        boolean clawArmServoIsOpen = false;

        boolean debugInfo = true;

        FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");



        ControlHub_ServoController = hardwareMap.get(ServoController.class, "Control Hub");

        //Disable pwm
        ControlHub_ServoController.pwmDisable();


        waitForStart();
        if (opModeIsActive()) {


            FrontRight.setDirection(DcMotor.Direction.FORWARD);


            FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

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

                if (gamepad1.x) {
                    FrontRight.setPower(0);
                } else if (gamepad1.y) {
                    FrontRight.setPower(0.25);
                } else if (gamepad1.b) {
                    FrontRight.setPower(0.5);
                } else if (gamepad1.a) {
                    FrontRight.setPower(0.75);
                }

            }
        }
    }
}