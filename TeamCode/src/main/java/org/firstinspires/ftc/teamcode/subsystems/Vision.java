package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.panthercommandlib.subsystems.Subsystem;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
@SuppressWarnings("unused")
public class Vision extends Subsystem {
    final ArrayList<AprilTagDetection> tags = new ArrayList<>();
    final Telemetry telemetry;
    // ----------------------------
    final VisionPortal visionPortal;
    final AprilTagProcessor visionProcessor;
    private String mosaicOrder = "";

    // char, int, double, byte,

    public Vision(HardwareMap hardwareMap, Telemetry telemetry) {
        super(hardwareMap);

        this.visionProcessor = new AprilTagProcessor.Builder().build();
        this.visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "camera"))
                .addProcessor(visionProcessor)
                .enableLiveView(true).build();

        this.telemetry = telemetry;
    }

    @Override
    public void update() {
        tags.clear();
        tags.addAll(visionProcessor.getDetections());

        for (AprilTagDetection detection : tags) {
            String tagName = detection.metadata.name;


            if (tagName.contains("Obelisk")) {
                mosaicOrder = tagName.split("_")[1];
                break;
            }
        }

        telemetry.addData("Pattern: ", mosaicOrder);
    }

    public char[] getMosaicOrder() {
        // "PPG" -> ['P', 'P', 'G']
        return mosaicOrder.toCharArray();
    }

}
