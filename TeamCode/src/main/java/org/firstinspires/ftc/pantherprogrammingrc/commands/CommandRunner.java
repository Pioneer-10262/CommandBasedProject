package org.firstinspires.ftc.pantherprogrammingrc.commands;

import android.util.Pair;

import org.firstinspires.ftc.pantherprogrammingrc.subsystems.Subsystem;
import org.firstinspires.ftc.pantherprogrammingrc.subsystems.SubsystemManager;

import java.util.HashSet;

public class CommandRunner {
    // This will store a list of all the subsystems that are currently in use by all running commands
    private final static HashSet<Subsystem> subsystemsInUse = new HashSet<>();
    private final static HashSet<Command> commandsRunning = new HashSet<>();

    /**
     * This will start to run the command that put in, but if there is another command running,
     * this one will not run.
     *
     * @param command The command that you want to run. If the command that runs
     */
    public static void runCommand(Command command) {
        if (commandsRunning.contains(command)) return;

        HashSet<Subsystem> requiredSubsystems = command.getRequiredSubsystems();

        for (Subsystem sub : requiredSubsystems) {
            if (subsystemsInUse.contains(sub)) {
                return;
            }
        }

        for (Subsystem sub : requiredSubsystems) {
            sub.getDefaultCommand().onFinish(true);
        }

        subsystemsInUse.addAll(command.getRequiredSubsystems());
        commandsRunning.add(command);

        command.init();

        Command deadlineCommand = command.getDeadlineCommand();

        if (!(deadlineCommand == null)) {
            forceRunCommand(deadlineCommand);
        }
    }

    /**
     * This will start to run the command that put in, but unlike <code>runCommand()</code>,
     * this will run and interrupt any other commands that are using subsystems that this one requires
     *
     * @param command The command that you want to run. If the command that runs
     */
    public static void forceRunCommand(Command command) {
        if (commandsRunning.contains(command)) return;

        boolean interruptedAnotherCommand = false;

        HashSet<Subsystem> requiredSubsystems = command.getRequiredSubsystems();

        for (Command runningCommand : new HashSet<>(commandsRunning)) {
            for (Subsystem sub : requiredSubsystems) {
                if (runningCommand.usingSubsystem(sub)) {
                    endCommand(runningCommand, true);

                    interruptedAnotherCommand = true;
                    break;
                }
            }
        }

        if (!interruptedAnotherCommand) {
            for (Subsystem sub : requiredSubsystems) {
                sub.getDefaultCommand().onFinish(true);
            }
        }

        subsystemsInUse.addAll(requiredSubsystems);
        commandsRunning.add(command);
        command.init();
    }

    /**
     * This updates all commands
     */
    public static void executeCommands() {
        HashSet<Pair<Command, Boolean>> toEnd = new HashSet<>();

        for (Command command : commandsRunning) {
            if (command.isFinished()) {
                toEnd.add(new Pair<>(command, false));
            } else if (command.getDeadlineCommand() != null){
                if (command.getDeadlineCommand().isFinished()) {
                    toEnd.add(new Pair<>(command, true));
                }
            } else {
                command.update();
            }
        }

        for (Pair<Command, Boolean> command : toEnd) {
            endCommand(command.first, command.second);
        }

        for (Subsystem sub : SubsystemManager.getSubsystems()) {
            if (!subsystemsInUse.contains(sub)) {
                sub.getDefaultCommand().update();
            }
        }
    }

    /** Ends commands that we want and we can choose if they are interrupted or not.
     *
     * @param command The command that you want to end
     * @param interrupted Whether or not you interrupted that command with another one
     */
    private static void endCommand(Command command, boolean interrupted) {
        for (Subsystem s : command.getRequiredSubsystems()) {
            subsystemsInUse.remove(s);
        }

        commandsRunning.remove(command);

        command.onFinish(interrupted);

        // TODO: DECIDE IF THIS SHOULD BE FORCE RUN OR NOT
        forceRunCommand(command.getNextCommand());
    }

    /** Ends commands that we want and marks it as interrupted since we cut it out
     *
     * @param command The command that you want to end
     */
    public static void forceEndCommand(Command command) {
        for (Subsystem s : command.getRequiredSubsystems()) {
            subsystemsInUse.remove(s);
        }

        commandsRunning.remove(command);

        command.onFinish(true);

        // TODO: DECIDE IF THIS SHOULD BE FORCE RUN OR NOT
        forceRunCommand(command.getNextCommand());
    }

}
