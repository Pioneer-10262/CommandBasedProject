package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.panthercommandlib.commands.Command;
import org.firstinspires.ftc.teamcode.subsystems.PioneerMotor;
import org.firstinspires.ftc.panthercommandlib.util.Timer;

public class SpinMotor extends Command {
    private Timer timer;

    private PioneerMotor motor;

    public SpinMotor(PioneerMotor motor) {
        timer = new Timer();

        this.motor = motor;

        addRequiredSubsystems(motor);
    }

    @Override
    public void init() {
        timer.start();

        motor.setPower(0.5);
    }

    @Override
    public void update() {}

    @Override
    public void onFinish(boolean commandInterrupted) {
        motor.stop();
    }

    @Override
    public boolean isFinished() {
        return timer.getTimeSeconds() > 2;
    }
}
