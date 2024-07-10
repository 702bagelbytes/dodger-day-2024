package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretSubsystem extends SubsystemBase {
    private final TalonFX turretMotor = new TalonFX(15);
    private final TalonFXConfiguration turretMotorConfiguration;

    public TurretSubsystem() {
        this.turretMotorConfiguration = new TalonFXConfiguration();
        turretMotor.getConfigurator().apply(turretMotorConfiguration);
    }

    public void setSpeed(double speed) {
        turretMotor.set(speed);
    }
}
