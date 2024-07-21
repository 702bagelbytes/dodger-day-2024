package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootCommand extends SequentialCommandGroup {
    private final SlewRateLimiter rateLimiter = new SlewRateLimiter(0.1);

    public ShootCommand(ShooterSubsystem shooterSubsystem) {
        // The inputted values are not final. (m/s)
        var rampUp = Commands.runOnce(() -> {
            shooterSubsystem.setRampSpeed(1, rateLimiter);
        }, shooterSubsystem);

        var delay = Commands.waitSeconds(1.5);

        var shoot = Commands.runOnce(() -> {
            shooterSubsystem.setTriggerSpeed(1);
        }, shooterSubsystem);

        var delay2 = Commands.waitSeconds(1.2);

        var cleanup = Commands.runOnce(() -> {
            shooterSubsystem.setRampSpeed(0);
            shooterSubsystem.setTriggerSpeed(0);
        }, shooterSubsystem);

        this.addCommands(
            Commands.print("Ramping up shooter"),
            rampUp,
            Commands.print("Shooter was told to spin"),
            delay,
            Commands.print("Sending the ball to the shooter"),
            shoot,
            Commands.print("The trigger was told to spin"),
            delay2,
            Commands.print("We waited for the shot, now it's time to clean up"),
            cleanup
        );
    }
}
