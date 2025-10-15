package org.firstinspires.ftc.panthercommandlib.commands;

public abstract class CommandGroup extends Command {
    protected final Command[] commands;

    public CommandGroup(Command... commands) {
        this.commands = commands;
    }

    @Override
    public void onFinish(boolean commandInterrupted) {}

    public abstract void update();
    public abstract boolean isFinished();
}
