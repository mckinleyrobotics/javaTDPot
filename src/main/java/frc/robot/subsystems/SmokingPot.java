/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.MeasureWithPot;

/**
 * Add your docs here.
 */
public class SmokingPot extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  WPI_TalonSRX frontLeft = new WPI_TalonSRX(21);
  WPI_TalonSRX frontRight = new WPI_TalonSRX(23);
  WPI_TalonSRX backLeft = new WPI_TalonSRX(22);
  WPI_TalonSRX backRight = new WPI_TalonSRX(24);

  AnalogInput pot = new AnalogInput(1);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new MeasureWithPot());
  }

  public void potInit() {
    frontRight.configSelectedFeedbackSensor(FeedbackDevice.Analog);
  }

  public void potPeriodic() {
    int potPosition = frontRight.getSensorCollection().getAnalogIn();
    SmartDashboard.putNumber("Pot Value", potPosition);

    frontRight.setInverted(true);
    backRight.setInverted(true);

    double power = potPosition * 0.0009765625;

    if (Math.abs(power) < 0.1) {
      power = 0;
    }

    frontLeft.set(power);
    frontRight.set(power);
    backLeft.set(power);
    backRight.set(power);
  }
}
