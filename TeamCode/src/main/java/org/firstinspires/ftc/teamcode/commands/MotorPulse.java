package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.panthercommandlib.commands.Command;
import org.firstinspires.ftc.teamcode.subsystems.PioneerMotor;
import org.firstinspires.ftc.panthercommandlib.util.Timer;

public class MotorPulse extends Command {
    private final PioneerMotor motor;
    private final Timer timer;

    public MotorPulse(PioneerMotor motor) {
        this.motor = motor;
        this.timer = new Timer();

        addRequiredSubsystems(motor);
    }

    @Override
    public void init() {
        timer.reset();
        timer.start();
    }

    @Override
    public void update() {
        double time = timer.getTimeSeconds();

        if (time < 0.25) {
            motor.setPower(0.2);
        } else if (time < 0.5) {
            motor.stop();
        } else {
            // Restart the cycle
            timer.reset();
            timer.start();
        }
    }

    @Override
    public void onFinish(boolean commandInterrupted) {
        motor.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
