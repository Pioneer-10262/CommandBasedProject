package org.firstinspires.ftc.panthercommandlib.commands.standardcommands;

import org.firstinspires.ftc.panthercommandlib.commands.Command;

public class InstantCommand extends Command {
    Runnable code;

    /**
     * This will run the code given, and afterwards end IMMEDIATELY
     *
     * @param code The code that you want to run instantly
     */
    public InstantCommand(Runnable code) {
        this.code = code;
    }

    /**
     * This is a completely empty command, nothing will run and it will END IMMEDIATELY.
     */
    public InstantCommand() {
        this.code = () -> {};
    }

    @Override
    public void init() {
        code.run();
    }

    @Override
    public void update() {}

    @Override
    public void onFinish(boolean commandInterrupted) {}

    @Override
    public boolean isFinished() {
        return true;
    }
}
