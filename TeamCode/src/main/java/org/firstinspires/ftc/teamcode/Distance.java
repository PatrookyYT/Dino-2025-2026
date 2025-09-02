package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.HashSet;

public class Distance {
    public static double GetDistanceLeft(LinearOpMode opMode, com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, org.firstinspires.ftc.robotcore.external.Telemetry telemetry)
    {
        DistanceSensor distanceSensorLeft;
        distanceSensorLeft = hardwareMap.get(DistanceSensor.class, "distanceSensorRight");

        return distanceSensorLeft.getDistance(DistanceUnit.INCH);
    }

    public static double GetDistanceRight(LinearOpMode opMode, com.qualcomm.robotcore.hardware.HardwareMap hardwareMap, org.firstinspires.ftc.robotcore.external.Telemetry telemetry)
    {
        DistanceSensor distanceSensorRight;
        distanceSensorRight = hardwareMap.get(DistanceSensor.class, "distanceSensorRight");

        return distanceSensorRight.getDistance(DistanceUnit.INCH);
    }
}