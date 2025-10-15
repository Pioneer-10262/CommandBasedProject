package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.panthercommandlib.util.Timer;


@TeleOp
public class MainTeleOpDecode extends OpMode {
    DcMotor pioneerMotor;
    Timer pioneerTimer;

    boolean timerHasStarted = false;

    @Override
    public void init() {
        pioneerMotor = hardwareMap.get(DcMotor.class, "motor");
        pioneerTimer = new Timer();
    }

    @Override
    public void loop() {
        if(!timerHasStarted){
            pioneerTimer.start();

            timerHasStarted = true;
        }

        // code runs

        if (pioneerTimer.getTimeSeconds() < 3) {
            pioneerMotor.setPower(0.9);
        }

        if (pioneerTimer.getTimeSeconds() > 6){
            pioneerMotor.setPower(0);
        }

        if (pioneerTimer.getTimeSeconds() > 9){
            pioneerTimer.reset();
            pioneerTimer.start();
        }
    }
}
