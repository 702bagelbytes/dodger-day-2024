// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.TurretCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class RobotContainer {
  private final DriveSubsystem driveSubsystem;
  private final TurretSubsystem turretSubsystem;
  private final CommandXboxController controller;

  public RobotContainer() {
    this.controller = new CommandXboxController(0);
    this.driveSubsystem = new DriveSubsystem();
    this.driveSubsystem
        .setDefaultCommand(new DriveCommand(this.driveSubsystem, controller::getLeftY, controller::getRightX));
    this.turretSubsystem = new TurretSubsystem();
    configureBindings();

    SmartDashboard.putData("Toggle encoder limits", Commands.runOnce(this.turretSubsystem::toggleEncoderLimits, this.turretSubsystem));
  }

  private void configureBindings() {
    this.controller.povRight()
        .whileTrue(new TurretCommand(turretSubsystem, 0.55))
        .onFalse(new TurretCommand(turretSubsystem, 0));
    this.controller.povLeft()
        .whileTrue(new TurretCommand(turretSubsystem, -0.55))
        .onFalse(new TurretCommand(turretSubsystem, 0));

  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
