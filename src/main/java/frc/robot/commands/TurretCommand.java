package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TurretSubsystem;

public class TurretCommand extends Command {
    private final TurretSubsystem turretSubsystem;
    private final double speed;

    public TurretCommand(TurretSubsystem turretSubsystem, double speed) {
        this.turretSubsystem = turretSubsystem;
        this.speed = speed;
        addRequirements(turretSubsystem);
    }

    @Override
    public void initialize() {
        this.turretSubsystem.setSpeed(speed);
    }

    @Override
    public void execute() {
        System.out.printf("hello, turning at %.2f\n", speed);
    }
}
