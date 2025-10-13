package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.pantherprogrammingrc.commands.CommandRunner;
import org.firstinspires.ftc.pantherprogrammingrc.commands.standardcommands.InstantCommand;
import org.firstinspires.ftc.pantherprogrammingrc.container.PantherRobotContainer;
import org.firstinspires.ftc.teamcode.commands.MosaicTest;
import org.firstinspires.ftc.teamcode.commands.MotorPulse;
import org.firstinspires.ftc.teamcode.commands.SpinMotor;
import org.firstinspires.ftc.teamcode.subsystems.PioneerMotor;
import org.firstinspires.ftc.teamcode.subsystems.Vision;

@TeleOp
public class MainTeleOpDecode extends PantherRobotContainer {
    Vision vision;
    PioneerMotor motor;


    @Override
    public void onInit() {

    }

    @Override
    public void initializeSubsystems() {
        vision = new Vision(hardwareMap, telemetry);
        motor = new PioneerMotor(hardwareMap, "motor");
    }

    @Override
    public void initializeDefaultCommands() {
        motor.setDefaultCommand(new MotorPulse(motor));
    }

    @Override
    public void onUpdate() {
        if (gamepad1.a) {
            CommandRunner.runCommand(new MosaicTest(vision, motor));
        }

        if (gamepad1.b) {
            CommandRunner.runCommand(new InstantCommand(() -> gamepad1.rumble(1000)));
        }

        if (gamepad1.x) {
            CommandRunner.runCommand(new SpinMotor(motor));
        }
    }
}
