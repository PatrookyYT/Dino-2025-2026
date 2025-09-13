package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.DriveEncoderConstants;
import com.pedropathing.ftc.localization.constants.ThreeWheelConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(5.6); //5.62kg

    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName("FrontRight")
            .rightRearMotorName("BackRight")
            .leftFrontMotorName("FrontLeft")
            .leftRearMotorName("BackLeft")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD);

    /*public static ThreeWheelConstants localizerConstants = new ThreeWheelConstants()
            .forwardTicksToInches(.001989436789)
            .strafeTicksToInches(.001989436789)
            .turnTicksToInches(.001989436789)
            .leftPodY(0)
            .rightPodY(0)
            .strafePodX(0)
            .rightEncoder_HardwareMapName("FrontRight")
            .strafeEncoder_HardwareMapName("BackRight")
            .leftEncoder_HardwareMapName("BackLeft")
            .leftEncoderDirection(Encoder.FORWARD)
            .rightEncoderDirection(Encoder.FORWARD)
            .strafeEncoderDirection(Encoder.FORWARD);
*/
    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static DriveEncoderConstants localizerConstants = new DriveEncoderConstants()
            .rightFrontMotorName("FrontRight")
            .rightRearMotorName("BackRight")
            .leftRearMotorName("BackLeft")
            .leftFrontMotorName("FrontLeft")
            .leftFrontEncoderDirection(Encoder.REVERSE)
            .leftRearEncoderDirection(Encoder.FORWARD)
            .rightFrontEncoderDirection(Encoder.REVERSE)
            .rightRearEncoderDirection(Encoder.FORWARD)
            .robotWidth(16.5)
            .robotLength(13.5)
            .forwardTicksToInches(0.00781099737);


    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                //.threeWheelLocalizer(localizerConstants)
                .driveEncoderLocalizer(localizerConstants)
                .mecanumDrivetrain(driveConstants)
                .build();
    }
}
///// I dont understand how any of this works - Madelyn
//// why cant you just ask chatgpt to do this for us