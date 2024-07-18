package frc.robot.subsystems;

import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretSubsystem extends SubsystemBase {
    private final TalonFX turretMotor = new TalonFX(15);
    private final TalonFXConfiguration turretMotorConfiguration;
    private final SoftwareLimitSwitchConfigs limitSwitchConfigs;
    private boolean limitsEnabled;

    public TurretSubsystem() {
        this.turretMotor.setPosition(0);
        this.turretMotorConfiguration = new TalonFXConfiguration();
        this.limitsEnabled = true;
        SmartDashboard.putBoolean("Turret encoder limits", this.limitsEnabled);
        this.limitSwitchConfigs = new SoftwareLimitSwitchConfigs()
                .withReverseSoftLimitEnable(this.limitsEnabled)
                .withReverseSoftLimitThreshold(turretRotationsToTurretMotorRotations(-0.45))
                .withForwardSoftLimitEnable(this.limitsEnabled)
                .withForwardSoftLimitThreshold(turretRotationsToTurretMotorRotations(0.45));

        turretMotor.getConfigurator().apply(this.turretMotorConfiguration);
        turretMotor.getConfigurator().apply(this.limitSwitchConfigs);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Rotations",
                turretMotorRotationsToTurretRotations(this.turretMotor.getPosition().getValueAsDouble()));
    }

    public static double turretRotationsToTurretMotorRotations(double turretRots) {
        return turretRots
                * 36.0 /* 36:1 gear ratio */ 
                / 20.0 /* 1:20 gear ratio (maybe???) */ 
                * 140.0 /* 140:1 gear ratio (approx)*/;
    }

    public static double turretMotorRotationsToTurretRotations(double motorRots) {
        return motorRots
                / 36.0 /* 36:1 gear ratio */
                * 20.0 /* 1:20 gear ratio (maybe???) */ 
                / 140.0 /* 140:1 gear ratio (approx) */;
    }

    public void setSpeed(double speed) {
        turretMotor.set(speed);
    }

    public void toggleEncoderLimits() {
        this.limitsEnabled = !this.limitsEnabled;

        if (this.limitsEnabled) {
            this.turretMotor.setPosition(0);
        }

        SmartDashboard.putBoolean("Turret encoder limits", this.limitsEnabled);
        this.limitSwitchConfigs.ForwardSoftLimitEnable = this.limitsEnabled;
        this.limitSwitchConfigs.ReverseSoftLimitEnable = this.limitsEnabled;
        this.turretMotor.getConfigurator().apply(this.limitSwitchConfigs);
    }

    public boolean getLimitsEnabled() {
        return this.limitsEnabled;
    }
}
