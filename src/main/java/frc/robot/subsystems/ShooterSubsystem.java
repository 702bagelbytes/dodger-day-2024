package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
    public final TalonSRX shooterRampLead = new TalonSRX(50);
    public final TalonSRX shooterRampFollow = new TalonSRX(60);
    public final TalonSRX shooterTrigger = new TalonSRX(5);

    private final SlewRateLimiter shooterLimiter = new SlewRateLimiter(0.2);

    public ShooterSubsystem() {
        shooterRampLead.setSelectedSensorPosition(0);
        shooterRampFollow.setSelectedSensorPosition(0);
        shooterTrigger.setSelectedSensorPosition(0);

        shooterRampLead.configAllSettings(new TalonSRXConfiguration());
        shooterRampFollow.configAllSettings(new TalonSRXConfiguration());
        shooterTrigger.configAllSettings(new TalonSRXConfiguration());

        shooterRampLead.setInverted(true);
        shooterRampFollow.follow(shooterRampLead);

        shooterTrigger.setInverted(true);
    }

    public void setRampSpeed(double speed, SlewRateLimiter rateLimiter) {
        this.shooterRampLead.set(TalonSRXControlMode.PercentOutput, rateLimiter.calculate(speed));
    }

    public void setRampSpeed(double speed) {
        this.shooterRampLead.set(TalonSRXControlMode.PercentOutput, speed);
    }

    public void setTriggerSpeedFiltered(double speed) {
        this.shooterTrigger.set(TalonSRXControlMode.PercentOutput, shooterLimiter.calculate(speed));
    }

    public void setTriggerSpeed(double speed) {
        this.shooterTrigger.set(TalonSRXControlMode.PercentOutput, speed);
    }
}
