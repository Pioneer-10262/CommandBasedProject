package org.firstinspires.ftc.panthercommandlib.commands.standardcommands;


import org.firstinspires.ftc.panthercommandlib.commands.Command;

public class TimedCommand extends Command {
    Command commandWithDeadline;

    public TimedCommand(Command command, double commandDurationSeconds) {
        this.commandWithDeadline = command.withDeadline(new WaitCommand(commandDurationSeconds));
    }

    @Override
    public void update() {
        commandWithDeadline.update();
    }

    @Override
    public void onFinish(boolean commandInterrupted) {
        commandWithDeadline.onFinish(commandInterrupted);
    }

    @Override
    public boolean isFinished() {
        return commandWithDeadline.isFinished();
    }
}
