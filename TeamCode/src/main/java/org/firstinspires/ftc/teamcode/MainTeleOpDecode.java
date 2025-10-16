package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.panthercommandlib.commands.standardcommands.InstantCommand;
import org.firstinspires.ftc.panthercommandlib.RobotContainer;
import org.firstinspires.ftc.panthercommandlib.triggers.Trigger;
import org.firstinspires.ftc.teamcode.commands.MotorPulse;
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
        motor.setDefaultCommand(new MotorPulse(motor));
    }

    @Override
    public void addTriggers() {
        // Trigger to turn on the motor while the a button is pressed
        new Trigger(() -> gamepad1.a)
                .onTrue(new InstantCommand(() -> motor.setPower(0.5), motor))
                .onFalse(new InstantCommand(() -> motor.setPower(0), motor));

        // Trigger to run SpinMotor when the b button is pressed
        new Trigger(() -> gamepad1.b).onTrue(new SpinMotor(motor));
    }
}
