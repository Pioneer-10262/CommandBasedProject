package org.firstinspires.ftc.panthercommandlib.commands.commandgroups;

import org.firstinspires.ftc.panthercommandlib.commands.Command;
import org.firstinspires.ftc.panthercommandlib.commands.CommandGroup;
import org.firstinspires.ftc.panthercommandlib.commands.CommandRunner;

public class SequentialCommandGroup extends CommandGroup {
    int index;
    boolean commandStarted;

    public SequentialCommandGroup(Command... commands) {
        super(commands);

        index = 0;
    }

    @Override
    public void update() {
        if (isFinished()) return;

        if (!commandStarted) {
            CommandRunner.forceRunCommand(commands.get(index));
            commandStarted = true;
        } else {
            if (!CommandRunner.getCommandsRunning().contains(commands.get(index))) {
                commandStarted = false;
                index++;
            }
        }
    }

    @Override
    public boolean isFinished() {
        return index >= commands.size();
    }
}
