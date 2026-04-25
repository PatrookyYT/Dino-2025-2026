package org.firstinspires.ftc.teamcode.Decode2026;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Functions;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@Autonomous(name = "Decode Auto Red Tag")
public class DecodeAutoRedTag extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor CarouselMotor;
    public ServoController ControlHub_ServoController;
    private ServoController ExpansionHub_ServoController;
    private Servo KickerServo;
    private DcMotor LauncherMotor;

    AprilTagProcessor aprilTag;
    VisionPortal visionPortal;

    double carousel_Distance = 240;
    double carousel_Speed = 0.05;
    double flywheel_Speed = .85;
    double intake_Speed = 0.92;
    double KServo_Jiggle = .775;
    double KServo_Jiggle_Time = .125;
    double KServo_Stop = 1;
    double KServo_UpPos = 0.55;    
    String startAction = "Unknown";
    double wheel_Speed = 1;


    @Override
    public void runOpMode() {
        boolean Running = true;
        boolean testMode = true; // Shows Telemetry data for Functions.drive and Functions.turn

        // Create the AprilTag processor while still in initialization mode
        aprilTag = new AprilTagProcessor.Builder()
            .setTagLibrary(AprilTagGameDatabase.getCurrentGameTagLibrary()) // Use the current season's tags
            .build();

        // Create the VisionPortal.Builder object to add the camera and the processor
        visionPortal = new VisionPortal.Builder()
            .setCamera(hardwareMap.get(WebcamName.class, "Webcam_1")) // Specify your camera name
            .addProcessor(aprilTag)
            .build();        

        // Optional: can start/stop streaming in init or during OpMode execution
        visionPortal.setProcessorEnabled(aprilTag, true);                

        // Report that op mode has been initialized
        telemetry.addData("Status", "Initialized With Camera");
        telemetry.update();

        Integer tryNumber = 0;


        waitForStart();
        runtime.reset();

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////// Driving starts here//////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////  

        //***VERY IMPORTANT**
        //Replace the device name (ex frontLeft) with the NAME OF THE
        //MOTORS DEFINED IN THE DRIVER HUB
        //ControlHub_ServoController = hardwareMap.get(ServoController.class, "Control Hub");

        //ExpansionHub_ServoController = hardwareMap.get(ServoController.class, "Expansion Hub 2");


        //Disable pwm
        //ControlHub_ServoController.pwmDisable();

        // 10in = 572

        // Initialize the carousel
        CarouselMotor = hardwareMap.get(DcMotor.class, "CarouselMotor");

        // Initialize the launcher
        LauncherMotor = hardwareMap.get(DcMotor.class, "LauncherMotor");

        LauncherMotor.setDirection(DcMotor.Direction.REVERSE);
        LauncherMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);    

        // Initialize the kicker
        KickerServo = hardwareMap.get(Servo.class, "KickerServo");

        Running = opModeIsActive();

        if(Running)
        {
            Functions.drive(this, hardwareMap, telemetry, 22, 22, -0.55, 22, 22, testMode);

            Functions.turn2(this, hardwareMap, telemetry, 500, "Left", 0.5, testMode);

            Functions.drive(this, hardwareMap, telemetry, 16, 16, -0.55, 16, 16, testMode);

            Functions.turn2(this, hardwareMap, telemetry, 1000, "Right", 0.5, testMode);


            try {
                long startTime = System.currentTimeMillis();

                long goalTime = 3000;

                while (!isStopRequested() && startAction.equals("Unknown") && (goalTime >= (System.currentTimeMillis() - startTime))) {
                    List<AprilTagDetection> detections = aprilTag.getDetections();
                    tryNumber++;

                    if (detections.size() > 0) {
                        switch (String.valueOf(detections.get(0).id)) {
                            case "21":
                                startAction = "Stay";
                                break;

                            case "22":
                                startAction = "Turn Right";
                                break;

                            case "23":
                                startAction = "Turn Left";
                                break;

                            default:
                                break;
                        }
                    }

                    telemetry.addData("startAction", startAction);
                    telemetry.addData("tryNumber", String.valueOf(tryNumber));
                    telemetry.addData("time elapsed", String.valueOf(startTime));

                    telemetry.update();
                }
                if(5 <= startTime){
                    telemetry.addData("startAction", startAction);
                    telemetry.addData("tryNumber", String.valueOf(tryNumber));

                    telemetry.update();
                }

            }
            catch (Exception ex) {
                telemetry.addData("Error", ex.getMessage());

                telemetry.update();
            }

            visionPortal.close();

            telemetry.addData("startAction", startAction);
            telemetry.update();     

            // Turn the carousel
            switch (startAction) {
                case "Turn Left":
                    Functions.moveCarousel(this, hardwareMap, telemetry, carousel_Distance, carousel_Speed, testMode);
                    break;

                case "Turn Right":
                    Functions.moveCarousel(this, hardwareMap, telemetry, carousel_Distance * 2, carousel_Speed, testMode);
                    break;

                default:
                    // Do Nothing
                    break;
            }

            // Turn to fire
            telemetry.addData("Current Action", "Turn to fire");
            telemetry.update();
            
            Functions.turn2(this, hardwareMap, telemetry, 600, "Left", 0.5, testMode);

            // Turn on flywheel
            telemetry.addData("Current Action", "Turn on flywheel");
            telemetry.update();
            LauncherMotor.setPower(flywheel_Speed+0.02);

            // Pause to let it spin up
            Functions.pause(3, this);

            // Fire the first artifact
            telemetry.addData("Current Action", "Fire");
            telemetry.update();

            KickerServo.setPosition(KServo_UpPos);
            Functions.pause(KServo_Jiggle_Time, this);
            KickerServo.setPosition(KServo_Jiggle);
            Functions.pause(KServo_Jiggle_Time, this);
            KickerServo.setPosition(KServo_UpPos);
            Functions.pause(KServo_Jiggle_Time, this);
            KickerServo.setPosition(KServo_Jiggle);
            Functions.pause(KServo_Jiggle_Time, this);
            KickerServo.setPosition(KServo_UpPos);
            Functions.pause(KServo_Jiggle_Time, this);
            KickerServo.setPosition(KServo_Jiggle);
            Functions.pause(KServo_Jiggle_Time, this);
            KickerServo.setPosition(KServo_UpPos);            
            Functions.pause(KServo_Jiggle_Time, this);            
            KickerServo.setPosition(KServo_Stop);    
            Functions.pause(1, this);            

            // Turn to the right
            telemetry.addData("Current Action", "Turn Right");
            telemetry.update();

            Functions.moveCarousel(this, hardwareMap, telemetry, carousel_Distance, carousel_Speed, testMode);

            // Pause to let it spin up
            Functions.pause(2, this);

            // Fire the 2nd artifact
            telemetry.addData("Current Action", "Fire");
            telemetry.update();

            KickerServo.setPosition(KServo_UpPos);
            Functions.pause(KServo_Jiggle_Time, this);
            KickerServo.setPosition(KServo_Jiggle);
            Functions.pause(KServo_Jiggle_Time, this);
            KickerServo.setPosition(KServo_UpPos);
            Functions.pause(KServo_Jiggle_Time, this);
            KickerServo.setPosition(KServo_Jiggle);
            Functions.pause(KServo_Jiggle_Time, this);
            KickerServo.setPosition(KServo_UpPos);
            Functions.pause(KServo_Jiggle_Time, this);
            KickerServo.setPosition(KServo_Jiggle);
            Functions.pause(KServo_Jiggle_Time, this);
            KickerServo.setPosition(KServo_UpPos);            
            Functions.pause(KServo_Jiggle_Time, this);            
            KickerServo.setPosition(KServo_Stop);    
            Functions.pause(1, this);                

            // Turn to the right
            telemetry.addData("Current Action", "Turn Right");
            telemetry.update();

            Functions.moveCarousel(this, hardwareMap, telemetry, carousel_Distance, carousel_Speed, testMode);

            // Pause to let it spin up
            Functions.pause(2, this);

            // Fire the 3rd artifact
            telemetry.addData("Current Action", "Fire");
            telemetry.update();

            KickerServo.setPosition(KServo_UpPos);
            Functions.pause(KServo_Jiggle_Time, this);
            KickerServo.setPosition(KServo_Jiggle);
            Functions.pause(KServo_Jiggle_Time, this);
            KickerServo.setPosition(KServo_UpPos);
            Functions.pause(KServo_Jiggle_Time, this);
            KickerServo.setPosition(KServo_Jiggle);
            Functions.pause(KServo_Jiggle_Time, this);
            KickerServo.setPosition(KServo_UpPos);
            Functions.pause(KServo_Jiggle_Time, this);
            KickerServo.setPosition(KServo_Jiggle);
            Functions.pause(KServo_Jiggle_Time, this);
            KickerServo.setPosition(KServo_UpPos);            
            Functions.pause(KServo_Jiggle_Time, this);            
            KickerServo.setPosition(KServo_Stop);    
            Functions.pause(1, this);

            // Turnoff the flywheel
            LauncherMotor.setPower(0);

            // Make sure the kicker is down
            Functions.pause(1.5, this);
            KickerServo.setPosition(KServo_Stop);    

            // Turn to leave
            telemetry.addData("Current Action", "Turn to Leave");
            telemetry.update();
            
            Functions.turn(this, hardwareMap, telemetry, "Slight-Right", 0.5, testMode);

            // Leave the area
            Functions.drive(this, hardwareMap, telemetry, 32, 32, -1, 32, 32, testMode);
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