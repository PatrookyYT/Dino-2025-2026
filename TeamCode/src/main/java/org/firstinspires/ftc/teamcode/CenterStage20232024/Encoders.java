//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//
//@Autonomous(name = "EUcoders")
//public class Encoders extends LinearOpMode {
//
//  private DcMotorEx BackLeft;
//  private DcMotorEx FrontLeft;
//  private DcMotorEx FrontRight;
//  private DcMotorEx BackRight;
//
//  int Leftpos;
//  int Rightpos;
//
//  /**
//   * This function is executed when this OpMode is selected from the Driver Station.
//   */
//  @Override
//  public void runOpMode() {
//    BackLeft = hardwareMap.get(DcMotorEx.class, "BackLeft");
//    FrontLeft = hardwareMap.get(DcMotorEx.class, "FrontLeft");
//    FrontRight = hardwareMap.get(DcMotorEx.class, "FrontRight");
//    BackRight = hardwareMap.get(DcMotorEx.class, "BackRight");
//
//    // Put initialization blocks here.
//    BackLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//    FrontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//    FrontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//    BackRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//    FrontRight.setDirection(DcMotorEx.Direction.REVERSE);
//    BackRight.setDirection(DcMotorEx.Direction.REVERSE);
//    Rightpos = 0;
//    Leftpos = 0;
//    waitForStart();
//    drive(10, 10, 0.01, 10, 10);
//    // drive is in this order: BackLeft target, BackRight target, Speed, FrontLeft target, and FrontRight target
//  }
//
//  /**
//   * Allows for use of encoders
//   */
//  public void drive(int BackLeft_target, int BackRight_target, double Speed, int FrontLeft_target, int FrontRight_target) {
//    Leftpos += BackLeft_target;
//    Rightpos += BackRight_target;
//    BackLeft.setTargetPosition(Leftpos);
//    BackRight.setTargetPosition(Rightpos);
//    FrontLeft.setTargetPosition(Leftpos);
//    FrontRight.setTargetPosition(Rightpos);
//    FrontRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//    BackRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//    FrontLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//    BackLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//    BackLeft.setPower(Speed);
//    BackRight.setPower(Speed);
//    FrontLeft.setPower(Speed);
//    FrontRight.setPower(Speed);
//    while (opModeIsActive() && BackRight.isBusy() && FrontRight.isBusy() && BackLeft.isBusy() && FrontLeft.isBusy()) {
//      idle();
//    }
//  }
//}
