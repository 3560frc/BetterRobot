/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  
  WPI_TalonSRX shooter1, shooter2, shooterRight, shooterLeft;
  Solenoid lift1, lift2;

  public Shooter() {
    shooter1 = new WPI_TalonSRX(Constants.portShooter1);
    shooter2 = new WPI_TalonSRX(Constants.portShooter2);
    shooterRight = new WPI_TalonSRX(Constants.portShooterRight);
    shooterLeft = new WPI_TalonSRX(Constants.portShooterLeft);
    lift1 = new Solenoid(Constants.portSolenoid1);
    lift2 = new Solenoid(Constants.portSolenoid2);
  }

  public void setIntake(double speed){
    shooter1.set(speed);
    shooter2.set(speed);
  }

  public void setLift(boolean state){
    lift1.set(state);
    lift2.set(state);
  }

  public void liftShooter(){
    lift1.set(true);
    lift2.set(true);
  }

  public void dropShooter(){
    lift1.set(false);
    lift2.set(false);
  }

  public void setFlywheel(double speed){
    shooterRight.set(speed);
    shooterLeft.set(-speed);
  }

  public void stop(){
    shooterRight.stopMotor();
    shooterLeft.stopMotor();
    shooter1.stopMotor();
    shooter2.stopMotor();
  }

  public void stopIntake(){
    shooter1.stopMotor();
    shooter2.stopMotor();
  }

  public void stopFlywheel(){
    shooterRight.stopMotor();
    shooterLeft.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
