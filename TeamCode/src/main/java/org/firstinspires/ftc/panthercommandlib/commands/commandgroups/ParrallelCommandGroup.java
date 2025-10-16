package org.firstinspires.ftc.panthercommandlib.commands.commandgroups;

import org.firstinspires.ftc.panthercommandlib.commands.Command;
import org.firstinspires.ftc.panthercommandlib.commands.CommandGroup;
import org.firstinspires.ftc.panthercommandlib.commands.CommandRunner;

public class ParrallelCommandGroup extends CommandGroup {
    boolean commandsRun;

    public ParrallelCommandGroup(Command... commands) {
        super(commands);

        commandsRun = false;
    }

    @Override
    public final void update() {
        if (isFinished()) return;

        for (Command command : commands) {
            CommandRunner.forceRunCommand(command);
        }

        commandsRun = true;
    }

    @Override
    public final boolean isFinished() {
        return commandsRun;
    }
}
