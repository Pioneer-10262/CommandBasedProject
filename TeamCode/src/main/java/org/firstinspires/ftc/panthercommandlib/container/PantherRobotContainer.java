package org.firstinspires.ftc.panthercommandlib.container;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.panthercommandlib.commands.CommandRunner;
import org.firstinspires.ftc.panthercommandlib.subsystems.SubsystemManager;

public abstract class PantherRobotContainer extends OpMode {

    /**
     * DO NOT RUN THIS -- NOTHING WILL WORK IF YOU OVERRIDE THIS
     */
    @Override
    public void init() {
        initializeSubsystems();
        initializeDefaultCommands();

        onInit();
    }

    /**
     * DO NOT RUN THIS -- NOTHING WILL WORK IF YOU OVERRIDE THIS
     */
    @Override
    public void loop() {
        onUpdate();

        CommandRunner.executeCommands();
        SubsystemManager.updateSubsystems();

    }

    public abstract void initializeSubsystems();
    public abstract void initializeDefaultCommands();

    public abstract void onUpdate();

    public abstract void onInit();
}
