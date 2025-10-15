package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.panthercommandlib.commands.CommandRunner;
import org.firstinspires.ftc.panthercommandlib.commands.commandgroups.ParrallelCommandGroup;
import org.firstinspires.ftc.panthercommandlib.commands.commandgroups.SequentialCommandGroup;
import org.firstinspires.ftc.panthercommandlib.commands.standardcommands.InstantCommand;
import org.firstinspires.ftc.panthercommandlib.container.RobotContainer;
import org.firstinspires.ftc.panthercommandlib.triggers.Trigger;
import org.firstinspires.ftc.panthercommandlib.util.Timer;
import org.firstinspires.ftc.teamcode.commands.SpinMotor;
import org.firstinspires.ftc.teamcode.subsystems.PioneerMotor;
import org.firstinspires.ftc.teamcode.subsystems.Vision;


@TeleOp
public class MainTeleOpDecode extends RobotContainer {
    PioneerMotor motor;
    Vision vision;

    @Override
    public void onInit() {}

    @Override
    public void initializeSubsystems() {
        motor = new PioneerMotor(hardwareMap, "motor");
        vision = new Vision(hardwareMap, telemetry);
    }

    @Override
    public void initializeDefaultCommands() {
    }

    @Override
    public void addTriggers() {
        new Trigger(() -> gamepad1.a).onTrue(new SpinMotor(motor));
    }
}
