// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.FlapCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.TurretCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class RobotContainer {
  private final DriveSubsystem driveSubsystem;
  private final TurretSubsystem turretSubsystem;
  private final ShooterSubsystem shooterSubsystem;
  private final CommandXboxController controller;

  public RobotContainer() {
    this.controller = new CommandXboxController(0);
    this.driveSubsystem = new DriveSubsystem();
    // this.driveSubsystem
    // .setDefaultCommand(new DriveCommand(this.driveSubsystem,
    // controller::getLeftY, controller::getRightX));
    this.turretSubsystem = new TurretSubsystem();

    this.shooterSubsystem = new ShooterSubsystem();
    configureBindings();

    SmartDashboard.putData("Toggle encoder limits",
        Commands.runOnce(this.turretSubsystem::toggleEncoderLimits, this.turretSubsystem));
  }

  private void configureBindings() {
    this.controller.povRight()
        .whileTrue(new TurretCommand(turretSubsystem, 0.25))
        .onFalse(new TurretCommand(turretSubsystem, 0));
    this.controller.povLeft()
        .whileTrue(new TurretCommand(turretSubsystem, -0.25))
        .onFalse(new TurretCommand(turretSubsystem, 0));

    this.controller.povUp()
        .whileTrue(new FlapCommand(shooterSubsystem, 0.2))
        .onFalse(new FlapCommand(shooterSubsystem, 0));
    this.controller.povDown()
        .whileTrue(new FlapCommand(shooterSubsystem, -0.2))
        .onFalse(new FlapCommand(shooterSubsystem, 0));

    this.controller.a()
        .onTrue(new ShootCommand(shooterSubsystem));

    // this.controller.a()
    // .onTrue(Commands.runOnce(() ->
    // shooterSubsystem.shooterTrigger.set(TalonSRXControlMode.PercentOutput, 1),
    // shooterSubsystem))
    // .onFalse(Commands.runOnce(() ->
    // shooterSubsystem.shooterTrigger.set(TalonSRXControlMode.PercentOutput, 0),
    // shooterSubsystem));

  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
