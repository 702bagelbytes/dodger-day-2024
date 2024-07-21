package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class FlapCommand extends Command {
    private final ShooterSubsystem shooterSubsystem;
    private final double speed;
    private boolean needsToRevert = false;

    public FlapCommand(ShooterSubsystem shooterSubsystem, double speed) {
        this.shooterSubsystem = shooterSubsystem;
        this.speed = speed;
        addRequirements(shooterSubsystem);

        if (speed < 0) {
            // this.shooterSubsystem.setFlapInverted(this.shooterSubsystem);;
            // this.needsToRevert = true;
        }
    }

    @Override
    public void initialize() {
        this.shooterSubsystem.setFlap(speed);
    }

    @Override
    public void end(boolean interrupted) {
        // if (this.needsToRevert)
    }
}
