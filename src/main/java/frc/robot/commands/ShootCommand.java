package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootCommand extends SequentialCommandGroup {
    private final ShooterSubsystem shooterSubsystem;

    public ShootCommand(ShooterSubsystem shooterSubsystem) {
        this.shooterSubsystem = shooterSubsystem;

        addRequirements(shooterSubsystem);

        // The inputted values are not final. (m/s)
        var rampUp = Commands.runOnce(() -> {
            this.shooterSubsystem.setRampSpeed(14.0);
        }, this.shooterSubsystem);

        var delay = Commands.waitSeconds(1.5);

        var shoot = Commands.runOnce(() -> {
            this.shooterSubsystem.setTriggerSpeed(9.5);
        }, this.shooterSubsystem);

        var delay2 = Commands.waitSeconds(1.2);

        var cleanup = Commands.runOnce(() -> {
            this.shooterSubsystem.setRampSpeedFiltered(0);
            this.shooterSubsystem.setTriggerSpeed(0);
        }, this.shooterSubsystem);

        this.addCommands(rampUp, delay, shoot, delay2, cleanup);
    }
}
