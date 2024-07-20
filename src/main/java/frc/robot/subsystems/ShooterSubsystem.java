package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
    private final TalonSRX shooterRampLead = new TalonSRX(0);
    private final TalonSRX shooterRampFollow = new TalonSRX(0);
    private final TalonSRX shooterTrigger = new TalonSRX(0);
    private final SlewRateLimiter rateLimiter = new SlewRateLimiter(0.5);
    private final SlewRateLimiter shooterLimiter = new SlewRateLimiter(1);

    public ShooterSubsystem() {
        this.shooterRampFollow.follow(shooterRampLead);
    }

    public void setRampSpeedFiltered(double speed) {
        this.shooterRampLead.set(TalonSRXControlMode.Velocity, rateLimiter.calculate(speed));
    }

    public void setRampSpeed(double speed) {
        this.shooterRampLead.set(TalonSRXControlMode.Velocity, speed);
    }

    public void setTriggerSpeedFiltered(double speed) {
        this.shooterTrigger.set(TalonSRXControlMode.Velocity, shooterLimiter.calculate(speed));
    }

    public void setTriggerSpeed(double speed) {
        this.shooterTrigger.set(TalonSRXControlMode.Velocity, speed);
    }
}
