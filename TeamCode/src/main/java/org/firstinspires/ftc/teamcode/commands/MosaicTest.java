package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.pantherprogrammingrc.commands.Command;
import org.firstinspires.ftc.teamcode.subsystems.PioneerMotor;
import org.firstinspires.ftc.teamcode.subsystems.Vision;
import org.firstinspires.ftc.pantherprogrammingrc.util.Timer;

public class MosaicTest extends Command {
    int stage;
    char[] mosaicPattern;

    // Subsystems
    Vision vision;
    PioneerMotor motor;

    // Util
    Timer timer;

    public MosaicTest(Vision vision, PioneerMotor motor) {
        this.vision = vision;
        this.motor = motor;

        this.timer = new Timer();

        this.stage = 0;
        this.mosaicPattern = new char[]{};

        addRequiredSubsystems(vision, motor);
    }

    @Override
    public void init() {
        timer.start();
        mosaicPattern = vision.getMosaicOrder();

        if (!(mosaicPattern.length >0)) {
            stage = 3;
        }
    }

    @Override
    public void update() {
        if (!(mosaicPattern.length > 0)) return;

        if (timer.getTimeSeconds() < 0.5) {
            if (mosaicPattern[stage] == 'P') {
                motor.setPower(0.2);
            } else {
                motor.setPower(-0.2);
            }
        } else {
            motor.stop();

            if (timer.getTimeSeconds() > 1) {
                timer.reset();
                timer.start();

                stage++;
            }
        }
    }

    @Override
    public void onFinish(boolean commandInterrupted) {
        if (commandInterrupted) {
            motor.stop();
        }
    }

    @Override
    public boolean isFinished() {
        return (stage > 2);
    }
}
