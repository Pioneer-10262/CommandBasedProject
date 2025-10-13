package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.pantherprogrammingrc.subsystems.Subsystem;

public class PioneerMotor extends Subsystem {
    private final DcMotor motor;

    public PioneerMotor(HardwareMap hardwareMap, String name) {
        super(hardwareMap);

        motor = hardwareMap.get(DcMotor.class, name);
    }

    @Override
    public void update() {}

    public void setPower(double power) {
        motor.setPower(power);
    }

    public void stop() {
        motor.setPower(0);
    }

    public void getPower() {
        motor.getPower();
    }
}
