package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
    private final TalonSRX leftLead = new TalonSRX(36);
    private final TalonSRX leftFollow1 = new TalonSRX(35);
    private final TalonSRX leftFollow2 = new TalonSRX(20);

    private final TalonSRX rightLead = new TalonSRX(16);
    private final TalonSRX rightFollow1 = new TalonSRX(25);
    private final TalonSRX rightFollow2 = new TalonSRX(27);

    public DriveSubsystem() {
        leftFollow1.follow(leftLead);
        leftFollow2.follow(leftLead);
        rightFollow1.follow(rightLead);
        rightFollow2.follow(rightLead);

        rightFollow1.setInverted(InvertType.FollowMaster);
        rightFollow2.setInverted(InvertType.FollowMaster);
        rightLead.setInverted(true);
    }

    public void setRight(double speed) {
        rightLead.set(ControlMode.PercentOutput, speed);
    }

    public void setLeft(double speed) {
        leftLead.set(ControlMode.PercentOutput, speed);
    }
}
