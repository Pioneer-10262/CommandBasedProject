package org.firstinspires.ftc.panthercommandlib.container;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.panthercommandlib.commands.CommandRunner;
import org.firstinspires.ftc.panthercommandlib.subsystems.SubsystemManager;
import org.firstinspires.ftc.panthercommandlib.triggers.TriggerHandler;

public abstract class RobotContainer extends OpMode {

    /**
     * DO NOT RUN THIS -- NOTHING WILL WORK IF YOU OVERRIDE THIS
     */
    @Override
    public void init() {
        // First initialize the subsystems
        initializeSubsystems();

        // Then initialize the default commands
        initializeDefaultCommands();

        // Add the triggers
        addTriggers();

        // Finally run the init code
        onInit();
    }

    /**
     * DO NOT RUN THIS -- NOTHING WILL WORK IF YOU OVERRIDE THIS
     */
    @Override
    public void loop() {
        CommandRunner.executeCommands();

        SubsystemManager.updateSubsystems();

        TriggerHandler.updateTriggers();
    }

    /**
     * This code will run one time when the init button is pressed.
     */
    public abstract void onInit();

    /**
     * This is where you give your subsystems values.
     */
    public abstract void initializeSubsystems();

    /**
     * For any subysystems that you want to have default commands, make them here.
     *
     * <p> For example, if you want to give your Subsystem a default command, you would write: </p>
     * <code> Subsystem.setDefaultCommand(new SomeCommand()) </code>
     */
    public abstract void initializeDefaultCommands();

    /**
     * This is where you can add your triggers
     */
    public abstract void addTriggers();
}
