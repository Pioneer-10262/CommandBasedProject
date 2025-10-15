package org.firstinspires.ftc.panthercommandlib.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CommandGroup extends Command {
    protected final List<Command> commands;

    /**
     * Group of commands that can run in different ways
     *
     * @param commands The group of commands
     */
    public CommandGroup(Command... commands) {
        this.commands = Arrays.stream(commands).collect(Collectors.toList());
    }

    /**
     * @param command The command you want to add to the group-- It gets added after all the other commands
     * @return This.
     */
    public CommandGroup addCommand(Command command) {
        commands.add(command);

        return this;
    }

    /**
     * This is default, you should not have to change this!
     *
     * @param commandInterrupted This value is true if the command was interrupted by another one
     */
    @Override
    public void onFinish(boolean commandInterrupted) {}

    // ----------------------------------

    /**
     * This code should decide how <code>commands</code> get run.
     */
    public abstract void update();

    /**
     * @return The value that decides when all commands are run and this can finish
     */
    public abstract boolean isFinished();
}
